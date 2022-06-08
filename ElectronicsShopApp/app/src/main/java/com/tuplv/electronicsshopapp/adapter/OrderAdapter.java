package com.tuplv.electronicsshopapp.adapter;

import static com.tuplv.electronicsshopapp.Format.formatPrice;
import static com.tuplv.electronicsshopapp.activity.MainActivity.FRAGMENT_ORDER_STATUS;
import static com.tuplv.electronicsshopapp.activity.MainActivity.FRAGMENT_PURCHASE_HISTORY;
import static com.tuplv.electronicsshopapp.activity.MainActivity.currentFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.FluentIterable;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.activity.AddCommentActivity;
import com.tuplv.electronicsshopapp.activity.DetailsProductActivity;
import com.tuplv.electronicsshopapp.activity.OrderDetailActivity;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.model.Order;
import com.tuplv.electronicsshopapp.model.Product;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private final Context context;
    private final int layout;
    private final List<Order> listOrder;

    private GetAPI getAPI;

    public OrderAdapter(Context context, int layout, List<Order> listOrder) {
        this.context = context;
        this.layout = layout;
        this.listOrder = listOrder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "RecyclerView", "UseCompatLoadingForColorStateLists"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        getAPI = new GetAPI(context);
        Order order = listOrder.get(position);
        List<Product> listProduct = FluentIterable.from(order.getListProduct()).limit(1).toList();

        ProductAdapter listProductAdapter = new ProductAdapter(context, R.layout.item_sub_order, listProduct);
        holder.rvProductOder.setAdapter(listProductAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false);
        holder.rvProductOder.setLayoutManager(gridLayoutManager);

        double quantity = 0;
        for (int i = 0; i < order.getListProduct().size(); i++) {
            quantity += order.getListProduct().get(i).getQuantity();
        }

        if (order.getListProduct().size() <= 1) {
            holder.tvShowMoreProduct.setVisibility(View.GONE);
            holder.vLine1.setVisibility(View.GONE);
        }

        holder.tvQuantityProduct.setText(formatPrice(quantity) + " sản phẩm");
        holder.tvPriceTotal.setText(formatPrice(order.getPriceTotal()));

        if (currentFragment == FRAGMENT_PURCHASE_HISTORY) {
            holder.tvOrderStatus.setText("Đã giao hàng");
            holder.btnReceivedSuccess.setText("Đánh giá");
            holder.btnReceivedSuccess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToActivityAddComment(order);
                }
            });
        }

        if (currentFragment == FRAGMENT_ORDER_STATUS) {
            holder.btnReceivedSuccess.setEnabled(false);
            holder.btnReceivedSuccess.setBackgroundTintList(context.getResources().getColorStateList(R.color.gray_500));
            if (order.getStatus() == 0) {
                holder.tvOrderStatus.setText("Đơn hàng chờ xác nhận");
            } else if (order.getStatus() == 1) {
                holder.tvOrderStatus.setText("Đơn hàng đã xác nhận");
            } else if (order.getStatus() == 2) {
                holder.tvOrderStatus.setText("Đang giao hàng");
            } else if (order.getStatus() == 3) {
                holder.tvOrderStatus.setText("Giao hàng thành công");
                holder.btnReceivedSuccess.setEnabled(true);
                holder.btnReceivedSuccess.setBackgroundTintList(context.getResources().getColorStateList(R.color.toolbar));
            }
            holder.btnReceivedSuccess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getAPI.updateOder(order.getId(), 4, new GetAPI.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {

                        }

                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onResponse(String result) {
                            if (result.equals("success")) {
                                Toast.makeText(context, "Nhận hàng thành công", Toast.LENGTH_SHORT).show();
                                listOrder.remove(position);
                                notifyDataSetChanged();
                            }
                        }
                    });
                }
            });
        }

        holder.itemView.setOnClickListener(view -> goToActivityDetailOrder(order));
    }

    private void goToActivityAddComment(Order order) {
        Intent intent = new Intent(context, AddCommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", order);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void goToActivityDetailOrder(Order order) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", order);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (listOrder != null)
            return listOrder.size();
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View vLine1;
        RecyclerView rvProductOder;
        TextView tvShowMoreProduct, tvQuantityProduct, tvPriceTotal, tvOrderStatus;
        Button btnReceivedSuccess;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvProductOder = itemView.findViewById(R.id.rvProductOder);
            tvShowMoreProduct = itemView.findViewById(R.id.tvShowMoreProduct);
            tvQuantityProduct = itemView.findViewById(R.id.tvQuantityProduct);
            tvPriceTotal = itemView.findViewById(R.id.tvPriceTotal);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
            btnReceivedSuccess = itemView.findViewById(R.id.btnReceivedSuccess);
            vLine1 = itemView.findViewById(R.id.vLine1);
        }
    }
}
