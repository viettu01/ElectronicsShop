package com.tuplv.electronicsshopapp.adapter;

import static com.tuplv.electronicsshopapp.Format.formatPrice;
import static com.tuplv.electronicsshopapp.Format.sortText;
import static com.tuplv.electronicsshopapp.api.GetAPI.DOMAIN_IMAGE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.intef.OnCartClickListener;
import com.tuplv.electronicsshopapp.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    int layout;
    List<Product> list;
    OnCartClickListener listener;

    public CartAdapter(Context context, int layout, List<Product> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    public CartAdapter(Context context, int layout, List<Product> list, OnCartClickListener listener) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = list.get(position);

        Picasso.get().load(DOMAIN_IMAGE + "/product/" + product.getAvatar()).into(holder.imgProduct);
        holder.tvNameProduct.setText(sortText(product.getName(), 40));
        holder.edtQuantity.setText(String.valueOf(product.getQuantity()));
        holder.tvPriceProduct.setText(formatPrice((float) product.getPrice()) + " ₫");
        if (product.getQuantity() <= 1) {
            holder.btnSub.setEnabled(false);
        }
        holder.tvDeleteProductInCart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                listener.onClickDeleteProductInCart(product.getId());
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long oldQuantity = Integer.parseInt(holder.edtQuantity.getText().toString());
                long newQuantity = oldQuantity - 1;
                holder.btnSub.setEnabled(newQuantity > 1);
                product.setQuantity(newQuantity);
                holder.edtQuantity.setText(String.valueOf(newQuantity));
                listener.onChangeQuantityProductInCart(product);
            }
        });
        holder.btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long oldQuantity = Integer.parseInt(holder.edtQuantity.getText().toString());
                long newQuantity = oldQuantity + 1;
                holder.btnSub.setEnabled(newQuantity > 1);
                product.setQuantity(newQuantity);
                holder.edtQuantity.setText(String.valueOf(newQuantity));
                listener.onChangeQuantityProductInCart(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvNameProduct, tvPriceProduct, tvDeleteProductInCart;
        Button btnSub, btnSum;
        EditText edtQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct);
            tvDeleteProductInCart = itemView.findViewById(R.id.tvDeleteProductInCart);
            btnSub = itemView.findViewById(R.id.btnSub);
            btnSum = itemView.findViewById(R.id.btnSum);
            edtQuantity = itemView.findViewById(R.id.edtQuantity);
        }
    }
}
