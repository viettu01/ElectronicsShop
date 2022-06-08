package com.tuplv.electronicsshopapp.adapter;

import static com.tuplv.electronicsshopapp.api.GetAPI.DOMAIN_IMAGE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tuplv.electronicsshopapp.Format;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.activity.DetailsProductActivity;
import com.tuplv.electronicsshopapp.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    int layout;
    List<Product> list;

    public ProductAdapter(Context context, int layout, List<Product> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = list.get(position);

        if (layout == R.layout.item_product) {
            Picasso.get().load(DOMAIN_IMAGE + "/product/" + product.getAvatar()).into(holder.imgProduct);
            holder.tvNameProduct.setText(Format.sortText(product.getName(), 30));
            holder.tvPriceProduct.setText(Format.formatPrice(product.getPrice()) + " ₫");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToActivityDetails(product);
                }
            });
        } else if (layout == R.layout.item_sub_order) {
            Picasso.get().load(DOMAIN_IMAGE + "/product/" + product.getAvatar()).into(holder.imgProduct);
            holder.tvNameProduct.setText(Format.sortText(product.getName(), 30));
            holder.tvQuantityOneProduct.setText("x " + product.getQuantity());
            holder.tvPriceProduct.setText(Format.formatPrice(product.getPrice()) + " ₫");
        } else if (layout == R.layout.item_product_of_category) {
            Picasso.get().load(DOMAIN_IMAGE + "/product/" + product.getAvatar()).into(holder.imgProduct);
            holder.tvNameProduct.setText(product.getName());
            holder.tvPriceProduct.setText(Format.formatPrice(product.getPrice()) + " ₫");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToActivityDetails(product);
                }
            });
        } else if (layout == R.layout.item_search_product) {
            holder.tvNameProduct.setText(Format.sortText(product.getName(), 45));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToActivityDetails(product);
                }
            });
        } else if (layout == R.layout.item_add_comment) {
            Picasso.get().load(DOMAIN_IMAGE + "/product/" + product.getAvatar()).into(holder.imgProduct);
            holder.tvNameProduct.setText(Format.sortText(product.getName(), 40));
            holder.tvPriceProduct.setText(Format.formatPrice(product.getPrice()) + " ₫");
        }
    }

    private void goToActivityDetails(Product product) {
        Intent intent = new Intent(context, DetailsProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvNameProduct, tvPriceProduct, tvQuantityOneProduct;
        EditText edtContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct);

            if (layout == R.layout.item_sub_order) {
                tvQuantityOneProduct = itemView.findViewById(R.id.tvQuantityOneProduct);
            }
            if (layout == R.layout.item_add_comment){
                edtContent = itemView.findViewById(R.id.edtContent);
            }
        }
    }
}
