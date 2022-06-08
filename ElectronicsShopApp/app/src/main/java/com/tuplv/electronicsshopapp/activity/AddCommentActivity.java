package com.tuplv.electronicsshopapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.adapter.ProductAdapter;
import com.tuplv.electronicsshopapp.model.Order;
import com.tuplv.electronicsshopapp.model.Product;

import java.util.List;

public class AddCommentActivity extends AppCompatActivity {

    ImageView imgBack;
    Button btnConfirm;
    RecyclerView rvAddComment;
    List<Product> products;
    ProductAdapter productAdapter;
    Order order;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        mapping();
        bundle = getIntent().getExtras();
        order = (Order) bundle.get("order");
        loadDataProductInOrder();
    }

    private void mapping() {
        imgBack = findViewById(R.id.imgBack);
        btnConfirm = findViewById(R.id.btnConfirm);
        rvAddComment = findViewById(R.id.rvAddComment);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadDataProductInOrder() {
        products = order.getListProduct();
        productAdapter = new ProductAdapter(getApplicationContext(), R.layout.item_add_comment, products);
        rvAddComment.setAdapter(productAdapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvAddComment.setLayoutManager(staggeredGridLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        rvAddComment.addItemDecoration(itemDecoration);
    }
}