package com.tuplv.electronicsshopapp.activity;

import static com.tuplv.electronicsshopapp.api.GetAPI.DOMAIN_IMAGE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.fragment.ChangePasswordFragment;
import com.tuplv.electronicsshopapp.fragment.HomeFragment;
import com.tuplv.electronicsshopapp.fragment.OrderStatusFragment;
import com.tuplv.electronicsshopapp.fragment.ProfileFragment;
import com.tuplv.electronicsshopapp.fragment.PurchaseHistoryFragment;
import com.tuplv.electronicsshopapp.model.User;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_ORDER_STATUS = 3;
    public static final int FRAGMENT_PURCHASE_HISTORY = 4;
    public static final int FRAGMENT_PROFILE = 5;
    public static final int FRAGMENT_CHANGE_PASSWORD = 6;

    public static int currentFragment = FRAGMENT_HOME;

    DrawerLayout drawerLayout;
    Toolbar tbMain;
    NavigationView nav;
    ImageView imgAvatar;
    TextView tvFullName, tvEmail;

    SharedPreferences sharedPreferences;

    GetAPI getAPI = new GetAPI(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

        setSupportActionBar(tbMain);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, tbMain, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        nav.getMenu().findItem(R.id.nav_home).setChecked(true);

        sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE);
        loadDataHeaderNav();
    }

    private void mapping() {
        drawerLayout = findViewById(R.id.drawerLayout);
        tbMain = findViewById(R.id.tbMain);
        nav = findViewById(R.id.nav);
    }

    private void loadDataHeaderNav() {
        View headerView = nav.getHeaderView(0);
        imgAvatar = headerView.findViewById(R.id.imgAvatar);
        tvFullName = headerView.findViewById(R.id.tvFullName);
        tvEmail = headerView.findViewById(R.id.tvEmail);
        getAPI.loadDataUser(Integer.parseInt(sharedPreferences.getString("id", "0")), new GetAPI.VolleyGetSingleDataListener<User>() {
            @Override
            public void onError(String message) {
                Log.d("AAA", message);
            }

            @Override
            public void onResponse(User user) {
                Picasso.get().load(DOMAIN_IMAGE + "/user/" + user.getAvatar()).into(imgAvatar);
                tvFullName.setText(user.getFullName());
                tvEmail.setText(user.getEmail());
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                setTitle(R.string.app_name);
                if (currentFragment != FRAGMENT_HOME) {
                    replaceFragment(new HomeFragment());
                    currentFragment = FRAGMENT_HOME;
                }
                break;
            case R.id.nav_order_status:
                if (currentFragment != FRAGMENT_ORDER_STATUS) {
                    replaceFragment(new OrderStatusFragment());
                    currentFragment = FRAGMENT_ORDER_STATUS;
                }
                break;
            case R.id.nav_history:
                if (currentFragment != FRAGMENT_PURCHASE_HISTORY) {
                    replaceFragment(new PurchaseHistoryFragment());
                    currentFragment = FRAGMENT_PURCHASE_HISTORY;
                }
                break;
            case R.id.nav_profile:
                if (currentFragment != FRAGMENT_PROFILE) {
                    nav.getMenu().findItem(R.id.nav_profile).setCheckable(true);
                    replaceFragment(new ProfileFragment());
                    currentFragment = FRAGMENT_PROFILE;
                }
                break;
            case R.id.nav_change_password:
                if (currentFragment != FRAGMENT_CHANGE_PASSWORD) {
                    nav.getMenu().findItem(R.id.nav_change_password).setCheckable(true);
                    replaceFragment(new ChangePasswordFragment());
                    currentFragment = FRAGMENT_CHANGE_PASSWORD;
                }
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuCart) {
            startActivity(new Intent(this, CartActivity.class));
        } else if (item.getItemId() == R.id.mnuSearch) {
            startActivity(new Intent(this, SearchViewProductActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame, fragment);
        transaction.commit();
    }
}