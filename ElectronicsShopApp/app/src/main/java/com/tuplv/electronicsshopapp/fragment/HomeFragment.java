package com.tuplv.electronicsshopapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.adapter.CategoryAdapter;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.intef.OnCategoryClickListener;
import com.tuplv.electronicsshopapp.model.Category;

import java.util.List;

public class HomeFragment extends Fragment implements OnCategoryClickListener {

    RecyclerView rvCategory;

    List<Category> listCategory;
    CategoryAdapter categoryAdapter;

    GetAPI getAPI;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mapping(view);
        getAPI = new GetAPI(getActivity());
        loadDataCategory();
        replaceFragment(new AllProductFragment());
        return view;
    }

    private void mapping(View view) {
        rvCategory = view.findViewById(R.id.rvCategory);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentHomeFrame, fragment);
        transaction.commit();
    }

    private void loadDataCategory() {
        getAPI.getAllCategory(new GetAPI.VolleyGetListDataListener<Category>() {
            @Override
            public void onError(String message) {
                Log.d("AAA", message);
            }

            @Override
            public void onResponse(List<Category> list) {
                listCategory = list;
                categoryAdapter = new CategoryAdapter(getActivity(), R.layout.item_category, listCategory, HomeFragment.this);
                rvCategory.setAdapter(categoryAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
                rvCategory.setLayoutManager(gridLayoutManager);
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
}
