package com.tuplv.electronicsshopapp.activity;

import static com.tuplv.electronicsshopapp.Format.formatPrice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.adapter.ProductAdapter;
import com.tuplv.electronicsshopapp.model.Order;
import com.tuplv.electronicsshopapp.model.Product;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    Toolbar tbInfoOrder;
    TextView tvOrderStatus, tvTimeSuccessOrder, tvUserNameAndPhone, tvAddress;
    TextView tvPriceTotal, tvOrderId, tvOrderTime, tvPaymentTime, tvTimeSuccessOrder2;
    RecyclerView rvListProductOrder;
    Button btnRate;

    Order order;
    Bundle bundle;

    List<Product> listProduct;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        mapping();
        setSupportActionBar(tbInfoOrder);
        bundle = getIntent().getExtras();
        order = (Order) bundle.get("order");
        loadDataOrder();
    }

    @SuppressLint("SetTextI18n")
    private void loadDataOrder() {
        if (order.getStatus() == 0) {
            tvOrderStatus.setText("Đơn hàng chờ xác nhận");
        } else if (order.getStatus() == 1) {
            tvOrderStatus.setText("Đơn hàng đã xác nhận");
        } else if (order.getStatus() == 2) {
            tvOrderStatus.setText("Đang giao hàng");
        } else if (order.getStatus() == 3) {
            tvOrderStatus.setText("Giao hàng thành công");
        } else if (order.getStatus() == 4) {
            tvOrderStatus.setText("Đã nhận hàng");
        }
        tvTimeSuccessOrder.setText(order.getUpdatedAt());
        tvUserNameAndPhone.setText(order.getFullName() + " I " + order.getPhone());
        tvAddress.setText(order.getAddress());

        listProduct = order.getListProduct();
        productAdapter = new ProductAdapter(this, R.layout.item_sub_order, listProduct);
        rvListProductOrder.setAdapter(productAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        rvListProductOrder.setLayoutManager(gridLayoutManager);

        tvPriceTotal.setText(formatPrice(order.getPriceTotal()));
        tvOrderId.setText(String.valueOf(order.getId()));
        tvOrderTime.setText(order.getCreatedAt());
        tvPaymentTime.setText(order.getUpdatedAt());
        tvTimeSuccessOrder2.setText(order.getUpdatedAt());
    }

    private void mapping() {
        tbInfoOrder = findViewById(R.id.tbInfoOrder);
        tvOrderStatus = findViewById(R.id.tvOrderStatus);
        tvTimeSuccessOrder = findViewById(R.id.tvTimeSuccessOrder);
        tvUserNameAndPhone = findViewById(R.id.tvUserNameAndPhone);
        tvAddress = findViewById(R.id.tvAddress);
        tvPriceTotal = findViewById(R.id.tvPriceTotal);
        tvOrderId = findViewById(R.id.tvOrderId);
        tvOrderTime = findViewById(R.id.tvOrderTime);
        tvPaymentTime = findViewById(R.id.tvPaymentTime);
        tvTimeSuccessOrder2 = findViewById(R.id.tvTimeSuccessOrder2);
        rvListProductOrder = findViewById(R.id.rvListProductOrder);
        btnRate = findViewById(R.id.btnRate);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}