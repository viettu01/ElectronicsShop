package com.tuplv.electronicsshopapp.activity;

import static com.tuplv.electronicsshopapp.Format.formatPrice;
import static com.tuplv.electronicsshopapp.api.GetAPI.DOMAIN;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tuplv.electronicsshopapp.Format;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.adapter.CartAdapter;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.intef.OnCartClickListener;
import com.tuplv.electronicsshopapp.model.Cart;
import com.tuplv.electronicsshopapp.model.Product;
import com.tuplv.electronicsshopapp.model.User;
import com.tuplv.electronicsshopapp.singleton.MySingleton;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements OnCartClickListener, View.OnClickListener {

    Toolbar tbCart;
    RecyclerView rvCart;
    TextView tvCartNull, tvTotalMoney, tvUserNameAndPhone, tvAddress;
    Button btnPayment;
    Spinner spnPaymentMethods;
    ImageView imgEdit;

    CartAdapter adapter;
    ArrayAdapter adapterPaymentMethod;
    List<Product> listProduct;
    List<String> listPaymentMethod;

    SharedPreferences sharedPreferences;
    User userInfo;
    long userId, cartId;

    GetAPI getAPI = new GetAPI(this);
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mapping();
        setSupportActionBar(tbCart);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        userId = Integer.parseInt(sharedPreferences.getString("id", "0"));
        cartId = Integer.parseInt(sharedPreferences.getString("idCart", "0"));
        loadInfoUser();
        loadDataCart();
        loadDataPaymentMethod();
    }

    private void mapping() {
        tbCart = findViewById(R.id.tbCart);
        rvCart = findViewById(R.id.rvCart);
        tvCartNull = findViewById(R.id.tvCartNull);
        tvTotalMoney = findViewById(R.id.tvTotalMoney);
        tvUserNameAndPhone = findViewById(R.id.tvUserNameAndPhone);
        tvAddress = findViewById(R.id.tvAddress);
        btnPayment = findViewById(R.id.btnPayment);
        spnPaymentMethods = findViewById(R.id.spnPaymentMethods);
        imgEdit = findViewById(R.id.imgEdit);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vui lòng chờ ...");

        btnPayment.setOnClickListener(this);
    }

    private void loadInfoUser() {
        userInfo = new User();
        getAPI.loadDataUser(userId, new GetAPI.VolleyGetSingleDataListener<User>() {
            @Override
            public void onError(String message) {
                Log.d("AAA", message);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(User user) {
                userInfo = user;
                tvUserNameAndPhone.setText(userInfo.getFullName() + " I " + userInfo.getPhone());
                tvAddress.setText(userInfo.getAddress());
            }
        });
    }

    private void loadDataCart() {
        progressDialog.show();
        listProduct = new ArrayList<>();
        getAPI.loadDataCart(cartId, new GetAPI.VolleyGetListDataListener<Product>() {
            @Override
            public void onError(String message) {
                Log.d("AAA", message);
            }

            @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
            @Override
            public void onResponse(List<Product> products) {
                if (products.size() == 0) {
                    tvCartNull.setVisibility(View.VISIBLE);
                } else {
                    listProduct = products;
                    adapter = new CartAdapter(CartActivity.this, R.layout.item_cart, listProduct, CartActivity.this);
                    tvCartNull.setVisibility(View.GONE);
                    rvCart.setAdapter(adapter);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                    rvCart.setLayoutManager(staggeredGridLayoutManager);
                    tvTotalMoney.setText(formatPrice(loadPriceTotal()));
                }
                progressDialog.dismiss();
            }
        });
    }

    private void loadDataPaymentMethod() {
        listPaymentMethod = new ArrayList<>();
        listPaymentMethod.add("Thanh toán khi nhận hàng");
        listPaymentMethod.add("Ví MOMO");
        adapterPaymentMethod = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listPaymentMethod);
        adapterPaymentMethod.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnPaymentMethods.setAdapter(adapterPaymentMethod);
    }

    @SuppressLint("SetTextI18n")
    private void payment() {
        if (listProduct.size() == 0) {
            Toast.makeText(this, "Bạn chưa có sản phẩm nào trong giỏ hàng", Toast.LENGTH_SHORT).show();
        } else {
            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_info_user_order);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes(layoutParams);

            EditText edtFullName, edtAddress, edtPhone, edtNote;
            Button btnConfirm, btnCancel;

            edtFullName = dialog.findViewById(R.id.edtFullName);
            edtAddress = dialog.findViewById(R.id.edtAddress);
            edtPhone = dialog.findViewById(R.id.edtPhone);
            edtNote = dialog.findViewById(R.id.edtNote);
            btnConfirm = dialog.findViewById(R.id.btnConfirm);
            btnCancel = dialog.findViewById(R.id.btnCancel);

            edtFullName.setText(userInfo.getFullName());
            edtAddress.setText(userInfo.getAddress());
            edtPhone.setText(userInfo.getPhone());

            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String fullName = edtFullName.getText().toString();
                    String address = edtAddress.getText().toString();
                    String phone = edtPhone.getText().toString();
                    String note = edtNote.getText().toString();
                    String email = userInfo.getEmail();
                    if (fullName.equals("") || address.equals("") || phone.equals("")) {
                        Toast.makeText(CartActivity.this, "Bạn cần nhập đủ thông tin giao hàng", Toast.LENGTH_SHORT).show();
                        edtFullName.setHintTextColor(getResources().getColor(R.color.red_a700));
                        edtAddress.setHintTextColor(getResources().getColor(R.color.red_a700));
                        edtPhone.setHintTextColor(getResources().getColor(R.color.red_a700));
                    } else if (!isNumeric(phone)) {
                        Toast.makeText(CartActivity.this, "Giá trị bạn nhập không phải là số", Toast.LENGTH_SHORT).show();
                        edtPhone.setTextColor(getResources().getColor(R.color.red_a700));
                    } else {
                        edtPhone.setTextColor(getResources().getColor(R.color.black));
                        getAPI.sendEmail(email, fullName, new GetAPI.VolleyResponseListener() {
                            @Override
                            public void onError(String message) {

                            }

                            @Override
                            public void onResponse(String result) {

                            }
                        });
                        addOrder(userId, fullName, address, phone, note, loadPriceTotal());
                    }
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
    }

    private void addOrder(long userId, String fullName, String address, String phone, String note, double priceTotal) {
        progressDialog.show();
        getAPI.addOder(userId, fullName, address, phone, note, priceTotal, new GetAPI.VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(String result) {
                if (result.equals("true")) {
                    progressDialog.dismiss();
                    startActivity(new Intent(CartActivity.this, OrderSuccessActivity.class));
                    finish();
                } else {
                    Toast.makeText(CartActivity.this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgEdit:
                break;
            case R.id.btnPayment:
                payment();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onClickDeleteProductInCart(long productId) {
        progressDialog.show();
        getAPI.deleteProductInCart(cartId, productId, new GetAPI.VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String result) {
                if (result.equals("true")) {
                    Toast.makeText(CartActivity.this, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    if (listProduct.size() == 0) {
                        tvCartNull.setVisibility(View.VISIBLE);
                    }
                    tvTotalMoney.setText(formatPrice(loadPriceTotal()));
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(CartActivity.this, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onChangeQuantityProductInCart(Product product) {
        getAPI.updateQuantityProductInCart(cartId, product.getId(), product.getQuantity(), new GetAPI.VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String result) {
                if (result.equals("true")) {
                    tvTotalMoney.setText(Format.formatPrice(loadPriceTotal()));
                }
            }
        });
    }

    private double loadPriceTotal() {
        double total = 0;
        for (Product product : listProduct) {
            total += (product.getQuantity() * product.getPrice());
        }
        return total;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}