package com.tuplv.electronicsshopapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.FluentIterable;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.adapter.CategoryAdapter;
import com.tuplv.electronicsshopapp.adapter.ProductAdapter;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.intef.OnCategoryClickListener;
import com.tuplv.electronicsshopapp.model.Category;
import com.tuplv.electronicsshopapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AllProductFragment extends Fragment implements OnCategoryClickListener {

    ViewFlipper viewFlipper;
    ImageView imgvClickPreSlide, imgvClickNextSlide;
    TextView tvShowAllSmartphone, tvShowAllLaptop, tvShowAllTablet;
    RecyclerView rvAllCategoryAndProduct, rvSmartPhoneInHome, rvLaptopInHome, rvTabletInHome;

    Animation in, out;

    ProductAdapter adapter, adapterLaptop, adapterTablet;
    List<Product> listPhone, listLaptop, listTablet;

    CategoryAdapter categoryAdapter;
    List<Category> categories = new ArrayList<>();

    GetAPI getAPI = new GetAPI(getActivity());
    ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_product, container, false);
        requireActivity().setTitle(R.string.app_name);
        mapping(view);
        slideShow();
        loadData();

        return view;
    }

    private void mapping(View view) {
        viewFlipper = view.findViewById(R.id.viewFlipper);
        imgvClickPreSlide = view.findViewById(R.id.imgvClickPreSlide);
        imgvClickNextSlide = view.findViewById(R.id.imgvClickNextSlide);
        rvAllCategoryAndProduct = view.findViewById(R.id.rvAllCategoryAndProduct);
//        tvShowAllSmartphone = view.findViewById(R.id.tvShowAllSmartphone);
//        tvShowAllLaptop = view.findViewById(R.id.tvShowAllLaptop);
//        tvShowAllTablet = view.findViewById(R.id.tvShowAllTablet);
//        rvSmartPhoneInHome = view.findViewById(R.id.rvSmartPhoneInHome);
//        rvLaptopInHome = view.findViewById(R.id.rvLaptopInHome);
//        rvTabletInHome = view.findViewById(R.id.rvTabletInHome);

//        tvShowAllSmartphone.setOnClickListener(this);
//        tvShowAllLaptop.setOnClickListener(this);
//        tvShowAllTablet.setOnClickListener(this);
    }

    private void slideShow() {
        in = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        out = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        imgvClickNextSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewFlipper.isAutoStart()) {
                    viewFlipper.stopFlipping();
                    viewFlipper.showNext();
                    viewFlipper.startFlipping();
                    viewFlipper.setAutoStart(true);
                }
            }
        });

        imgvClickPreSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewFlipper.isAutoStart()) {
                    viewFlipper.stopFlipping();
                    viewFlipper.showPrevious();
                    viewFlipper.startFlipping();
                    viewFlipper.setAutoStart(true);
                }
                viewFlipper.showPrevious();
            }
        });
    }

    public void loadData() {
        getAPI.getAllCategoryAndProduct(new GetAPI.VolleyGetListDataListener<Category>() {
            @Override
            public void onError(String message) {
                Log.d("AAA", message);
            }

            @Override
            public void onResponse(List<Category> list) {
                categories = list;
                categoryAdapter = new CategoryAdapter(getActivity(), R.layout.item_sub_product_in_all_product, categories, AllProductFragment.this);
                rvAllCategoryAndProduct.setAdapter(categoryAdapter);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
                rvAllCategoryAndProduct.setLayoutManager(gridLayoutManager);
            }
        });
    }

    @Override
    public void onChangeFragment(long category_id, String name) {
        if (category_id == 0) {
            requireActivity().setTitle(R.string.app_name);
            replaceFragment(new AllProductFragment());
        } else {
            requireActivity().setTitle(name);
            Bundle bundle = new Bundle();
            bundle.putLong("categoryId", category_id);
            ProductOfCategoryFragment fragment = new ProductOfCategoryFragment();
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentHomeFrame, fragment);
        transaction.commit();
    }
}
