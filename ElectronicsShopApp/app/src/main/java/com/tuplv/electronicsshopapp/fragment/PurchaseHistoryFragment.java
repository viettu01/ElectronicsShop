package com.tuplv.electronicsshopapp.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.adapter.OrderAdapter;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.model.Order;

import java.util.List;

public class PurchaseHistoryFragment extends Fragment {
    RecyclerView rvHistory;
    TextView tvHistoryNull;

    GetAPI getAPI = new GetAPI(getActivity());
    SharedPreferences sharedPreferences;
    long userId;
    List<Order> listOrderHistory;
    OrderAdapter historyOrderAdapter;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchase_history, container, false);
        requireActivity().setTitle("Lịch sử mua hàng");
        mapping(view);
        sharedPreferences = requireActivity().getSharedPreferences("login", MODE_PRIVATE);
        userId = Integer.parseInt(sharedPreferences.getString("id", "0"));
        loadDataOrder(userId);
        return view;
    }

    private void mapping(View view) {
        rvHistory = view.findViewById(R.id.rvHistory);
        tvHistoryNull = view.findViewById(R.id.tvHistoryNull);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Vui lòng chờ ...");
    }

    int count = 0;

    private void loadDataOrder(long userId) {
        progressDialog.show();
        getAPI.getOrderByUserId(userId, new GetAPI.VolleyGetListDataListener<Order>() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(List<Order> list) {
                if (list.size() == 0) {
                    tvHistoryNull.setVisibility(View.VISIBLE);
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getStatus() != 4) {
                            list.remove(i);
                            i--;
                        } else {
                            count++;
                        }
                    }
                    if (count == 0) {
                        tvHistoryNull.setVisibility(View.VISIBLE);
                    } else {
                        tvHistoryNull.setVisibility(View.GONE);
                        listOrderHistory = list;
                        historyOrderAdapter = new OrderAdapter(getActivity(), R.layout.item_order, listOrderHistory);
                        rvHistory.setAdapter(historyOrderAdapter);
                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                        rvHistory.setLayoutManager(staggeredGridLayoutManager);
                    }
                    progressDialog.dismiss();
                }
            }
        });
    }
}
