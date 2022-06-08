package com.tuplv.electronicsshopapp.activity;

import static com.tuplv.electronicsshopapp.Format.dateFormat;
import static com.tuplv.electronicsshopapp.api.GetAPI.DOMAIN;
import static com.tuplv.electronicsshopapp.api.GetAPI.DOMAIN_IMAGE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.RealPathUtil;
import com.tuplv.electronicsshopapp.api.ApiService;
import com.tuplv.electronicsshopapp.api.GetAPI;
import com.tuplv.electronicsshopapp.model.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_OPEN_CAMERA = 100;
    private static final int REQUEST_CODE_OPEN_GALLERY = 200;
    ActivityResultLauncher<Intent> activityResultLauncherForCamera;
    ActivityResultLauncher<Intent> activityResultLauncherForGallery;

    Toolbar tbEditProfile;
    CardView cvChangeAvatar;
    ImageView imgAvatar;
    EditText edtFullName, edtBirthday, edtPhone, edtAddress, edtJob, edtFacebook;
    Spinner spnGender;
    Button btnSave;

    ProgressDialog progressDialog;
    Uri uri;

    List<String> listGender;
    ArrayAdapter adapterGender;
    Bitmap bitmap;

    GetAPI getAPI = new GetAPI(this);
    SharedPreferences sharedPreferences, fileLogin;
    Calendar calendar;
    int userId;
    String userName, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mapping();
        setSupportActionBar(tbEditProfile);
        loadDataGender();
        sharedPreferences = getSharedPreferences("editProfile", MODE_PRIVATE);
        fileLogin = getSharedPreferences("login", MODE_PRIVATE);
        userId = Integer.parseInt(fileLogin.getString("id", "0"));
        loadDataUser();
        calendar = Calendar.getInstance();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                updateUser();
                break;
            case R.id.cvChangeAvatar:
                selectAvatar();
                break;
            case R.id.edtBirthday:
                selectBirthday();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == REQUEST_CODE_OPEN_CAMERA) {
                openCamera();
            }
            if (requestCode == REQUEST_CODE_OPEN_GALLERY) {
                openGallery();
            }
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

    private void mapping() {
        tbEditProfile = findViewById(R.id.tbEditProfile);
        cvChangeAvatar = findViewById(R.id.cvChangeAvatar);
        imgAvatar = findViewById(R.id.imgAvatar);
        edtFullName = findViewById(R.id.edtFullName);
        edtBirthday = findViewById(R.id.edtBirthday);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtJob = findViewById(R.id.edtJob);
        edtFacebook = findViewById(R.id.edtFacebook);
        spnGender = findViewById(R.id.spnGender);
        btnSave = findViewById(R.id.btnSave);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vui lòng chờ ...");

        btnSave.setOnClickListener(this);
        edtBirthday.setOnClickListener(this);
        cvChangeAvatar.setOnClickListener(this);

        activityResultLauncherForCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            //File object of camera image
                            File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");

                            //Uri of camera image
                            uri = FileProvider.getUriForFile(EditProfileActivity.this, getApplicationContext().getPackageName() + ".provider", file);
//                            Intent data = result.getData();
//                            if (data != null) {
//                                uri = data.getData();
//                            }
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                imgAvatar.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        activityResultLauncherForGallery = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data == null) {
                                return;
                            }
                            uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                imgAvatar.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void selectAvatar() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout);

        LinearLayout lnlTakeNewPhoto = bottomSheetDialog.findViewById(R.id.lnlTakeNewPhoto);
        LinearLayout lnlSelectFromLibrary = bottomSheetDialog.findViewById(R.id.lnlSelectFromLibrary);

        lnlTakeNewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(EditProfileActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EditProfileActivity.this,
                            new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_OPEN_CAMERA);
                } else {
                    openCamera();
                }
                bottomSheetDialog.dismiss();
            }
        });

        lnlSelectFromLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(EditProfileActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EditProfileActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_OPEN_GALLERY);
                } else {
                    openGallery();
                }
//                Toast.makeText(EditProfileActivity.this, "Select from library", Toast.LENGTH_LONG).show();
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.show();
    }

    private void openCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
        uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activityResultLauncherForCamera.launch(intent);
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activityResultLauncherForGallery.launch(intent);
    }

    @SuppressLint("SimpleDateFormat")
    private void selectBirthday() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Calendar chooseDate = Calendar.getInstance();
                chooseDate.set(year, month, dayOfMonth);
                String strDate = simpleDateFormat.format(chooseDate.getTime());
                edtBirthday.setText(strDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void loadDataGender() {
        listGender = new ArrayList<>();
        listGender.add("Nam");
        listGender.add("Nữ");
        adapterGender = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listGender);
        adapterGender.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnGender.setAdapter(adapterGender);
    }

    private void loadDataUser() {
        getAPI.loadDataUser(userId, new GetAPI.VolleyGetSingleDataListener<User>() {
            @Override
            public void onError(String message) {
                Log.d("AAA", message);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(User user) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", user.getUserName());
                editor.putString("password", user.getPassword());
                editor.putString("email", user.getEmail());
                editor.apply();

                Picasso.get().load(DOMAIN_IMAGE + "/user/" + user.getAvatar()).into(imgAvatar);
                edtFullName.setText(user.getFullName());
                if (user.getGender().equals("Nữ")) {
                    spnGender.setSelection(1);
                } else {
                    spnGender.setSelection(0);
                }
                edtBirthday.setText(dateFormat(user.getBirthday()));
                edtPhone.setText(user.getPhone());
                edtAddress.setText(user.getAddress());
                edtJob.setText(user.getJobs());
                edtFacebook.setText(user.getFacebook());
                userName = user.getUserName();
                email = user.getEmail();
                password = user.getPassword();
            }
        });
    }

    private void updateUser() {
        progressDialog.show();

        String fullName = edtFullName.getText().toString().trim();
        String gender = spnGender.getSelectedItem().toString().trim();
        String birthday = edtBirthday.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String job = edtJob.getText().toString().trim();
        String facebook = edtFacebook.getText().toString().trim();

        RequestBody requestBodyId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(userId));
        RequestBody requestBodyFullName = RequestBody.create(MediaType.parse("multipart/form-data"), fullName);
        RequestBody requestBodyGender = RequestBody.create(MediaType.parse("multipart/form-data"), gender);
        RequestBody requestBodyBirthday = RequestBody.create(MediaType.parse("multipart/form-data"), birthday);
        RequestBody requestBodyPhone = RequestBody.create(MediaType.parse("multipart/form-data"), phone);
        RequestBody requestBodyAddress = RequestBody.create(MediaType.parse("multipart/form-data"), address);
        RequestBody requestBodyJob = RequestBody.create(MediaType.parse("multipart/form-data"), job);
        RequestBody requestFacebook = RequestBody.create(MediaType.parse("multipart/form-data"), facebook);
        RequestBody requestUsesName = RequestBody.create(MediaType.parse("multipart/form-data"), sharedPreferences.getString("username", ""));
        RequestBody requestEmail = RequestBody.create(MediaType.parse("multipart/form-data"), sharedPreferences.getString("email", ""));
        RequestBody requestPassword = RequestBody.create(MediaType.parse("multipart/form-data"), sharedPreferences.getString("password", ""));

        String strRealPath = RealPathUtil.getRealPath(this, uri);
        Log.e("AAA", strRealPath);
        File file = new File(strRealPath);
        RequestBody requestBodyAvatar = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBodyAvatar = MultipartBody.Part.createFormData("avatar", file.getName(), requestBodyAvatar);

        ApiService.apiService.updateAccount(requestBodyId, requestUsesName, requestEmail, requestPassword, requestBodyFullName,
                requestBodyGender, requestBodyBirthday, requestBodyPhone, requestBodyAddress,
                multipartBodyAvatar, requestBodyJob, requestFacebook).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Sửa thông tin thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Call api fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}