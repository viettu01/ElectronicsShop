package com.tuplv.electronicsshopapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.adapter.ProductAdapter;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.model.Category;
import com.tuplv.electronicsshopapp.model.Product;

import java.util.List;

public class ProductOfCategoryFragment extends Fragment {

    RecyclerView rvProduct;
    TextView tvProductNull;

    ProductAdapter adapter;
    List<Product> products;

    GetAPI getAPI = new GetAPI(getActivity());

    long categoryId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_of_category, container, false);
        mapping(view);
        if (getArguments() != null) {
            categoryId = getArguments().getLong("categoryId");
        }
        loadData();
        return view;
    }

    private void mapping(View view) {
        rvProduct = view.findViewById(R.id.rvProduct);
        tvProductNull = view.findViewById(R.id.tvProductNull);
    }

    public void loadData() {
        getAPI.loadDataProductByIdCategory(categoryId, new GetAPI.VolleyGetSingleDataListener<Category>() {
            @Override
            public void onError(String message) {
                Log.d("AAA", message);
            }

            @Override
            public void onResponse(Category category) {
                if (category.getProducts().size() == 0) {
                    tvProductNull.setVisibility(View.VISIBLE);
                } else {
                    tvProductNull.setVisibility(View.GONE);
                    products = category.getProducts();
                    adapter = new ProductAdapter(getActivity(), R.layout.item_product_of_category, products);
                    rvProduct.setAdapter(adapter);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                    rvProduct.setLayoutManager(gridLayoutManager);
                }
            }
        });
    }
}
