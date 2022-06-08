package com.tuplv.electronicsshopapp.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tuplv.electronicsshopapp.R;

public class OrderSuccessActivity extends AppCompatActivity {

    Toolbar tbOrderSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);
        mapping();
        setSupportActionBar(tbOrderSuccess);
    }

    private void mapping() {
        tbOrderSuccess = findViewById(R.id.tbOrderSuccess);
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