package com.tuplv.electronicsshopapp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.api.GetAPI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtFullName, edtUserName, edtEmail, edtPhone, edtPassword, edtRePassword;
    Button btnRegister;
    TextView tvReturnLoginActivity;

    GetAPI getAPI = new GetAPI(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mapping();
        btnRegister.setOnClickListener(this);
        tvReturnLoginActivity.setOnClickListener(this);
    }

    private void mapping() {
        edtFullName = findViewById(R.id.edtFullName);
        edtUserName = findViewById(R.id.edtUserName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassword = findViewById(R.id.edtRePassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvReturnLoginActivity = findViewById(R.id.tvReturnLoginActivity);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                register();
                break;
            case R.id.tvReturnLoginActivity:
                finish();
                break;
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void register() {
        String fullName = edtFullName.getText().toString();
        String username = edtUserName.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();
        String password = edtPassword.getText().toString();
        String rePassword = edtRePassword.getText().toString();

        if (fullName.equals("") || username.equals("") || email.equals("") || phone.equals("") || password.equals("") || rePassword.equals("")) {
            edtFullName.setHintTextColor(getResources().getColor(R.color.red_a700));
            edtUserName.setHintTextColor(getResources().getColor(R.color.red_a700));
            edtEmail.setHintTextColor(getResources().getColor(R.color.red_a700));
            edtPhone.setHintTextColor(getResources().getColor(R.color.red_a700));
            edtPassword.setHintTextColor(getResources().getColor(R.color.red_a700));
            edtRePassword.setHintTextColor(getResources().getColor(R.color.red_a700));

            Toast.makeText(this, "Bạn cần nhập đủ các trường", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(rePassword)) {
            Toast.makeText(this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
        } else {
            getAPI.editUser("", username, email, password, fullName, "",
                    new SimpleDateFormat("yyyy-MM-dd").format(new Date()), phone,
                    "", "", "", "",
                    new GetAPI.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Log.d("AAA", message);
                        }

                        @Override
                        public void onResponse(String response) {
                            switch (response) {
                                case "1":
                                    Toast.makeText(CreateAccountActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                                    break;
                                case "2":
                                    Toast.makeText(CreateAccountActivity.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                                    break;
                                case "3":
                                    Toast.makeText(CreateAccountActivity.this, "Số điện thoại đã tồn tại", Toast.LENGTH_SHORT).show();
                                    break;
                                case "-1":
                                    Toast.makeText(CreateAccountActivity.this, "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(CreateAccountActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                    break;
                            }
                        }
                    });
        }
    }

//    public void registerUser(String firstName, String lastName, String username, String email, String phone, String rePassword) {
//        getAPI.addUser(firstName, lastName, username, email, phone, rePassword, new GetAPI.VolleyResponseListener() {
//            @Override
//            public void onError(String message) {
//                Log.d("AAA", message);
//            }
//
//            @Override
//            public void onResponse(String result) {
//                if (result.equals("success")) {
//                    Toast.makeText(CreateAccountActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toast.makeText(CreateAccountActivity.this, "Không thể tạo tài khoản", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

//    public void addUser() {
//        String url = "http://192.168.1.41:8080/ElectronicsShopBackend/api/user";
//        JSONObject jsonBody = new JSONObject();
//        try {
//            jsonBody.put("userName", edtUserName.getText().toString());
//            jsonBody.put("password", edtPassword.getText().toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        final String mRequestBody = jsonBody.toString();

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(CreateAccountActivity.this, "" + response, Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(CreateAccountActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                        Log.d("AAA", error.toString());
//                    }
//                }){
//            @Override
//            public String getBodyContentType() {
//                return "application/json; charset=utf-8";
//            }
//
//            @Override
//            public byte[] getBody() {
//                try {
//                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
//                } catch (UnsupportedEncodingException uee) {
//                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
//                    return null;
//                }
//            }

//            @Override
//            protected Response<JsonObjectRequest> parseNetworkResponse(NetworkResponse response) {
//                String responseString = "";
//                if (response != null) {
//                    responseString = String.valueOf(response.statusCode);
//                }
//                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//            }
//        };
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(CreateAccountActivity.this, "" + response, Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(CreateAccountActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                        Log.d("AAA", error.toString());
//                    }
//                }) {
//            @Override
//            public String getBodyContentType() {
//                return "application/json; charset=utf-8";
//            }
//
//            @Override
//            public byte[] getBody() throws AuthFailureError {
//                try {
//                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
//                } catch (UnsupportedEncodingException uee) {
//                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
//                    return null;
//                }
//            }

//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                String responseString = "";
//                if (response != null) {
//                    responseString = String.valueOf(response.statusCode);
//                }
//                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//            }
//        };

//        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
//    }
}