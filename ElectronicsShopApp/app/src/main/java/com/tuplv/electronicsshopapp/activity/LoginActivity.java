package com.tuplv.electronicsshopapp.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUserName, edtPassword;
    CheckBox cbRemember;
    TextView tvPrivacyPolicy, tvCreateAccount, tvForgotPassword;
    Button btnLogin;

    SharedPreferences sharedPreferences;

    GetAPI getAPI = new GetAPI(this);

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();

        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        edtUserName.setText(sharedPreferences.getString("username", ""));
        edtPassword.setText(sharedPreferences.getString("password", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("isCheck", false));

//        Toast.makeText(this, "is check: " + sharedPreferences.getBoolean("isCheck", false), Toast.LENGTH_SHORT).show();

        if (sharedPreferences.getBoolean("isCheck", false)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void mapping() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        cbRemember = findViewById(R.id.cbRemember);
        tvPrivacyPolicy = findViewById(R.id.tvPrivacyPolicy);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnLogin = findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vui lòng chờ ...");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Login();
                break;
            case R.id.tvPrivacyPolicy:
                break;
            case R.id.tvCreateAccount:
                startActivity(new Intent(this, CreateAccountActivity.class));
                break;
            case R.id.tvForgotPassword:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
        }
    }

    private void Login() {
        String username = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();
        boolean isCheck = cbRemember.isChecked();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (username.equals("") || password.equals("")) {
            edtUserName.setHintTextColor(getResources().getColor(R.color.red_a700));
            edtPassword.setHintTextColor(getResources().getColor(R.color.red_a700));
            Toast.makeText(this, "Bạn phải nhâp Tài khoản và Mật khẩu", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();
            getAPI.login(username, password, new GetAPI.VolleyGetSingleDataListener<User>() {
                @Override
                public void onError(String message) {
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(User response) {
                    if (response.getId() == -1) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    } else if (response.getId() == 0) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Tài khoản của bạn bị khóa", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        editor.putString("id", String.valueOf(response.getId()));
                        editor.putString("idCart", String.valueOf(response.getCartId()));
                        if (isCheck) {
                            editor.putString("username", username);
                            editor.putString("password", password);
                            editor.putBoolean("isCheck", true);
                        } else {
                            editor.remove("username");
                            editor.remove("password");
                            editor.remove("isCheck");
                        }
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}