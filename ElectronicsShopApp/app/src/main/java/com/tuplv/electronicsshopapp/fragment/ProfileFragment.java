package com.tuplv.electronicsshopapp.fragment;

import static android.content.Context.MODE_PRIVATE;

import static com.tuplv.electronicsshopapp.Format.dateFormat;
import static com.tuplv.electronicsshopapp.api.GetAPI.DOMAIN_IMAGE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.tuplv.electronicsshopapp.Format;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.activity.EditProfileActivity;
import com.tuplv.electronicsshopapp.activity.LoginActivity;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.model.User;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    TextView tvFirstName, tvLastName, tvFullName, tvUserName, tvEmail, tvGender, tvBirthday, tvPhone, tvAddress, tvJob, tvFacebook;
    ImageView imgEdit, imgAvatar;
    Button btnLogout;

    SharedPreferences sharedPreferences;

    GetAPI getAPI = new GetAPI(getActivity());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        requireActivity().setTitle("Hồ sơ");
        mapping(view);

        sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        loadDataUser();
        btnLogout.setOnClickListener(this);
        imgEdit.setOnClickListener(this);

        return view;
    }

    private void mapping(View view) {
        tvFullName = view.findViewById(R.id.tvFullName);
        tvFirstName = view.findViewById(R.id.tvFirstName);
        tvLastName = view.findViewById(R.id.tvLastName);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvGender = view.findViewById(R.id.tvGender);
        tvBirthday = view.findViewById(R.id.tvBirthday);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvJob = view.findViewById(R.id.tvJob);
        tvFacebook = view.findViewById(R.id.tvFacebook);
        imgEdit = view.findViewById(R.id.imgEdit);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        btnLogout = view.findViewById(R.id.btnLogout);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataUser();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("id");
                editor.putBoolean("isCheck", false);
                editor.apply();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                requireActivity().finish();
                break;
            case R.id.imgEdit:
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
                break;
        }
    }

    private void loadDataUser() {
        getAPI.loadDataUser(Integer.parseInt(sharedPreferences.getString("id", "0")), new GetAPI.VolleyGetSingleDataListener<User>() {
            @Override
            public void onError(String message) {
                Log.d("AAA", message);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(User user) {
                Picasso.get().load(DOMAIN_IMAGE + "/user/" + user.getAvatar()).into(imgAvatar);
                tvFullName.setText(user.getFullName());
                tvUserName.setText(user.getUserName());
                tvEmail.setText(user.getEmail());
                tvGender.setText(user.getGender());
                tvBirthday.setText(dateFormat(user.getBirthday()));
                tvPhone.setText(user.getPhone());
                tvAddress.setText(user.getAddress());
                tvJob.setText(user.getJobs());
                tvFacebook.setText(user.getFacebook());
            }
        });
    }
}
