package com.tuplv.electronicsshopapp.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.adapter.OrderAdapter;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusFragment extends Fragment {
    RecyclerView rvOrderStatus;
    TextView tvOrderNull;

    GetAPI getAPI = new GetAPI(getActivity());
    SharedPreferences sharedPreferences;
    long userId;
    List<Order> listOrder;
    OrderAdapter orderAdapter;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_status, container, false);
        requireActivity().setTitle("Tình trạng đơn hàng");
        mapping(view);
        sharedPreferences = requireActivity().getSharedPreferences("login", MODE_PRIVATE);
        userId = Integer.parseInt(sharedPreferences.getString("id", "0"));
        loadDataOrder();
        return view;
    }

    private void mapping(View view) {
        rvOrderStatus = view.findViewById(R.id.rvOrderStatus);
        tvOrderNull = view.findViewById(R.id.tvOrderNull);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Vui lòng chờ ...");
    }

    int count = 0;

    private void loadDataOrder() {
        progressDialog.show();
        listOrder = new ArrayList<>();
        getAPI.getOrderByUserId(userId, new GetAPI.VolleyGetListDataListener<Order>() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(List<Order> list) {
                if (list.size() == 0) {
                    tvOrderNull.setVisibility(View.VISIBLE);
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getStatus() == 4) {
                            list.remove(i);
                            i--;
                        } else {
                            count++;
                        }
                    }

                    if (count == 0) {
                        tvOrderNull.setVisibility(View.VISIBLE);
                    } else {
                        tvOrderNull.setVisibility(View.GONE);
                        listOrder = list;
                        orderAdapter = new OrderAdapter(getActivity(), R.layout.item_order, listOrder);
                        rvOrderStatus.setAdapter(orderAdapter);
                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                        rvOrderStatus.setLayoutManager(staggeredGridLayoutManager);
                    }
                    progressDialog.dismiss();
                }
            }
        });
    }
}
