package com.tuplv.electronicsshopapp.activity;

import static com.tuplv.electronicsshopapp.api.GetAPI.DOMAIN_IMAGE;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.squareup.picasso.Picasso;
import com.tuplv.electronicsshopapp.Format;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.adapter.CommentAdapter;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.model.Comment;
import com.tuplv.electronicsshopapp.model.Product;

import java.util.List;


public class DetailsProductActivity extends AppCompatActivity implements View.OnClickListener {

    WebView wvDescProduct;
    Toolbar tbDetails;
    ImageView imgProduct;
    TextView tvNameProduct, tvPriceProduct, tvCountComment;
    Button btnBuyNow, btnAddToCart;
    RecyclerView rvComment;
    CommentAdapter commentAdapter;

    GetAPI getAPI = new GetAPI(this);
    List<Comment> listComment;
    Product product;
    Bundle bundle;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        mapping();
        setSupportActionBar(tbDetails);

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        bundle = getIntent().getExtras();
        product = (Product) bundle.get("product");
        getData();
        btnAddToCart.setOnClickListener(this);
        btnBuyNow.setOnClickListener(this);
        loadDataComment(product.getId());
    }

    private void mapping() {
        tbDetails = findViewById(R.id.tbDetails);
        imgProduct = findViewById(R.id.imgProduct);
        tvNameProduct = findViewById(R.id.tvNameProduct);
        tvPriceProduct = findViewById(R.id.tvPriceProduct);
        btnBuyNow = findViewById(R.id.btnBuyNow);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        wvDescProduct = findViewById(R.id.wvDescProduct);
        rvComment = findViewById(R.id.rvComment);
        tvCountComment = findViewById(R.id.tvCountComment);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vui lòng chờ ...");
    }

    @SuppressLint("SetTextI18n")
    private void getData() {
        Picasso.get().load(DOMAIN_IMAGE + "/product/" + product.getAvatar()).into(imgProduct);
        tvNameProduct.setText(product.getName());
        tvPriceProduct.setText(Format.formatPrice(product.getPrice()) + " ₫");
        wvDescProduct.loadDataWithBaseURL(null, product.getDescription(), "text/html", "utf-8", null);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBuyNow:
                addProductToCart(product.getId());
                startActivity(new Intent(DetailsProductActivity.this, CartActivity.class));
                break;
            case R.id.btnAddToCart:
                addProductToCart(product.getId());
                break;
        }
    }

    public void addProductToCart(long productId) {
        progressDialog.show();
        getAPI.addProductToCart(Long.parseLong(sharedPreferences.getString("idCart", "0")), productId, new GetAPI.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("AAA", message);
            }

            @Override
            public void onResponse(String result) {
                if (result.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(DetailsProductActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailsProductActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loadDataComment(long productId) {
        getAPI.loadDataCommentByIdProduct(productId, new GetAPI.VolleyGetListDataListener<Comment>() {
            @Override
            public void onError(String message) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(List<Comment> list) {
                tvCountComment.setText("(" + list.size() + " đánh giá)");
                listComment = list;
                commentAdapter = new CommentAdapter(getApplicationContext(), R.layout.item_comment, listComment);
                rvComment.setAdapter(commentAdapter);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                rvComment.setLayoutManager(staggeredGridLayoutManager);
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                rvComment.addItemDecoration(itemDecoration);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.mnuCart) {
            startActivity(new Intent(this, CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}