package com.tuplv.electronicsshopapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.FluentIterable;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.intef.OnCategoryClickListener;
import com.tuplv.electronicsshopapp.model.Category;
import com.tuplv.electronicsshopapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    int layout;
    List<Category> listCategory;
    OnCategoryClickListener onCategoryClickListener;
    public static long category_id = 0;

    int row_index = -1;

    public CategoryAdapter(Context context, int layout, List<Category> listCategory, OnCategoryClickListener onCategoryClickListener) {
        this.context = context;
        this.layout = layout;
        this.listCategory = listCategory;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    public CategoryAdapter(Context context, int layout, List<Category> listCategory) {
        this.context = context;
        this.layout = layout;
        this.listCategory = listCategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor", "NotifyDataSetChanged", "RecyclerView"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = listCategory.get(position);

        if (layout != R.layout.item_sub_product_in_all_product) {
            holder.tvItemCategory.setText(category.getName());
            holder.tvItemCategory.setOnClickListener(v -> {
                row_index = position;
                category_id = category.getId();
//                row_index = (int) category_id;
//                if (position == 0) {
//                    category_id = 0;
//                }
                onCategoryClickListener.onChangeFragment(category_id, category.getName());
                notifyDataSetChanged();
            });
            Drawable drawable = context.getDrawable(R.drawable.custom_background_item_category_base);
            if (row_index == -1) {
                row_index = 0;
                drawable.setColorFilter(Color.parseColor("#62A2F8"), PorterDuff.Mode.SRC_ATOP);
                holder.tvItemCategory.setTextColor(Color.parseColor("#FFFFFF"));
            }
            if (row_index == position) {
                drawable.setColorFilter(Color.parseColor("#62A2F8"), PorterDuff.Mode.SRC_ATOP);
                holder.tvItemCategory.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                drawable.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
                holder.tvItemCategory.setTextColor(Color.parseColor("#000000"));
            }
            holder.lnlItemCategory.setBackground(drawable);
        } else {
            holder.tvNameCategory.setText(category.getName());

            List<Product> products = FluentIterable.from(category.getProducts()).limit(5).toList();

            ProductAdapter productAdapter = new ProductAdapter(context, R.layout.item_product, products);
            holder.rvProductOfCategory.setAdapter(productAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false);
            holder.rvProductOfCategory.setLayoutManager(gridLayoutManager);

            holder.tvShowAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;

                    onCategoryClickListener.onChangeFragment(category.getId(), category.getName());
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (listCategory != null) {
            return listCategory.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lnlItemCategory;
        TextView tvItemCategory;

        TextView tvNameCategory, tvShowAll;
        RecyclerView rvProductOfCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemCategory = itemView.findViewById(R.id.tvItemCategory);
            lnlItemCategory = itemView.findViewById(R.id.lnlItemCategory);

            if (layout == R.layout.item_sub_product_in_all_product) {
                tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
                tvShowAll = itemView.findViewById(R.id.tvShowAll);
                rvProductOfCategory = itemView.findViewById(R.id.rvProductOfCategory);
            }
        }
    }
}
