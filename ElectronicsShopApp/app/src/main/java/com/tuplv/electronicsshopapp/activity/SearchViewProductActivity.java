package com.tuplv.electronicsshopapp.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.adapter.ProductAdapter;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchViewProductActivity extends AppCompatActivity {

    ImageView imgBack;
    EditText edtSearch;
    RecyclerView rvProduct;

    List<Product> products;
    ProductAdapter productAdapter;
    GetAPI getAPI = new GetAPI(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_product);
        mapping();
    }

    private void mapping() {
        imgBack = findViewById(R.id.imgBack);
        edtSearch = findViewById(R.id.edtSearch);
        rvProduct = findViewById(R.id.rvProduct);

        imgBack.setOnClickListener(view -> finish());

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().equals("")) {
                    products = new ArrayList<>();
                    productAdapter = new ProductAdapter(getApplicationContext(), R.layout.item_product_of_category, products);
                    rvProduct.setAdapter(productAdapter);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
                    rvProduct.setLayoutManager(gridLayoutManager);
                } else {
                    getAPI.findProductByName(editable.toString().trim(), new GetAPI.VolleyGetListDataListener<Product>() {
                        @Override
                        public void onError(String message) {

                        }

                        @Override
                        public void onResponse(List<Product> list) {
                            products = list;
                            productAdapter = new ProductAdapter(getApplicationContext(), R.layout.item_search_product, products);
                            rvProduct.setAdapter(productAdapter);

                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
                            rvProduct.setLayoutManager(gridLayoutManager);
                            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                            rvProduct.addItemDecoration(itemDecoration);
                        }
                    });
                }
            }
        });
    }
}