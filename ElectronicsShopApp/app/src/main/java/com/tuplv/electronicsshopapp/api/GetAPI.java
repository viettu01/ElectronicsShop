package com.tuplv.electronicsshopapp.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import com.tuplv.electronicsshopapp.model.Cart;
import com.tuplv.electronicsshopapp.model.Category;
import com.tuplv.electronicsshopapp.model.Comment;
import com.tuplv.electronicsshopapp.model.Order;
import com.tuplv.electronicsshopapp.model.Product;
import com.tuplv.electronicsshopapp.model.User;
import com.tuplv.electronicsshopapp.singleton.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAPI {
    public static final String DOMAIN = "http://192.168.1.41:8080/ElectronicsShopBackend/api";
    public static final String DOMAIN_IMAGE = "http://192.168.1.41:8080/ElectronicsShopBackend/template/images";
    Context context;

    public GetAPI(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String result);
    }

    public interface VolleyGetListDataListener<T> {
        void onError(String message);

        void onResponse(List<T> list);
    }

    public interface VolleyGetSingleDataListener<T> {
        void onError(String message);

        void onResponse(T t);
    }

    public void getAllCategory(VolleyGetListDataListener<Category> volleyLoadDataListener) {
        List<Category> categories = new ArrayList<>();

        Category categoryDefault = new Category();
        categoryDefault.setName("Tất cả");
        categories.add(categoryDefault);

        String url = DOMAIN + "/category/get-all";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        Category category = new Category();
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            category.setId(jsonObject.getInt("id"));
                            category.setName(jsonObject.getString("name"));
                            category.setCode(jsonObject.getString("code"));
//                            category.setAvatar(jsonObject.getString("avatar"));

                            JSONArray listProduct = jsonObject.getJSONArray("listProduct");
                            List<Product> products = new ArrayList<>();
                            for (int j = 0; j < listProduct.length(); j++) {
                                Product product = new Product();
                                JSONObject jsonObjectProduct = listProduct.getJSONObject(j);
                                product.setId(jsonObjectProduct.getInt("id"));
                                product.setCategoryId(jsonObjectProduct.getInt("categoryId"));
                                product.setName(jsonObjectProduct.getString("name"));
                                product.setAvatar(jsonObjectProduct.getString("avatar"));
                                product.setPrice(jsonObjectProduct.getDouble("price"));
                                product.setDescription(jsonObjectProduct.getString("description"));
                                products.add(product);
                            }
                            category.setProducts(products);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (category.getProducts().size() != 0) {
                            categories.add(category);
                        }
                        volleyLoadDataListener.onResponse(categories);
                    }
                },
                error -> volleyLoadDataListener.onError(error.toString()));

        MySingleton.getInstance(context).addToRequestQueue(arrayRequest);
    }

    public void getAllCategoryAndProduct(VolleyGetListDataListener<Category> volleyLoadDataListener) {
        List<Category> categories = new ArrayList<>();

        String url = DOMAIN + "/category/get-all";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        Category category = new Category();
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            category.setId(jsonObject.getInt("id"));
                            category.setName(jsonObject.getString("name"));
                            category.setCode(jsonObject.getString("code"));
//                            category.setAvatar(jsonObject.getString("avatar"));

                            JSONArray listProduct = jsonObject.getJSONArray("listProduct");
                            List<Product> products = new ArrayList<>();
                            for (int j = 0; j < listProduct.length(); j++) {
                                Product product = new Product();
                                JSONObject jsonObjectProduct = listProduct.getJSONObject(j);
                                product.setId(jsonObjectProduct.getInt("id"));
                                product.setCategoryId(jsonObjectProduct.getInt("categoryId"));
                                product.setName(jsonObjectProduct.getString("name"));
                                product.setAvatar(jsonObjectProduct.getString("avatar"));
                                product.setPrice(jsonObjectProduct.getDouble("price"));
                                product.setDescription(jsonObjectProduct.getString("description"));
                                products.add(product);
                            }
                            category.setProducts(products);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (category.getProducts().size() != 0) {
                            categories.add(category);
                        }
                        volleyLoadDataListener.onResponse(categories);
                    }
                },
                error -> volleyLoadDataListener.onError(error.toString()));

        MySingleton.getInstance(context).addToRequestQueue(arrayRequest);
    }

    public void loadDataProductByIdCategory(long categoryId, VolleyGetSingleDataListener<Category> volleyLoadDataListener) {
        Category category = new Category();
        String url = DOMAIN + "/category/get-by-id?id=" + categoryId;

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Product> products = new ArrayList<>();
                        try {
                            category.setId(response.getInt("id"));
                            category.setName(response.getString("name"));
                            category.setCode(response.getString("code"));
//                            category.setAvatar(response.getString("avatar"));
                            JSONArray listProduct = response.getJSONArray("listProduct");
                            for (int i = 0; i < listProduct.length(); i++) {
                                Product product = new Product();
                                JSONObject jsonObject = listProduct.getJSONObject(i);
                                product.setId(jsonObject.getInt("id"));
                                product.setCategoryId(jsonObject.getInt("categoryId"));
                                product.setName(jsonObject.getString("name"));
                                product.setAvatar(jsonObject.getString("avatar"));
                                product.setPrice(jsonObject.getDouble("price"));
                                product.setDescription(jsonObject.getString("description"));
                                products.add(product);
                            }
                            category.setProducts(products);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        volleyLoadDataListener.onResponse(category);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", error.toString());
                        volleyLoadDataListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(objectRequest);
    }

    public void findProductByName(String name, VolleyGetListDataListener<Product> volleyGetListDataListener) {
        List<Product> products = new ArrayList<>();
        String url = DOMAIN + "/product/get-by-name?name=" + name;
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() == 0) {
                            volleyGetListDataListener.onResponse(products);
                        } else {
                            for (int i = 0; i < response.length(); i++) {
                                Product product = new Product();
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    product.setId(jsonObject.getInt("id"));
                                    product.setCategoryId(jsonObject.getInt("categoryId"));
                                    product.setQuantity(jsonObject.getLong("quantity"));
                                    product.setName(jsonObject.getString("name"));
                                    product.setAvatar(jsonObject.getString("avatar"));
                                    product.setPrice(jsonObject.getDouble("price"));
                                    product.setDescription(jsonObject.getString("description"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                products.add(product);
                                volleyGetListDataListener.onResponse(products);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyGetListDataListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(arrayRequest);
    }

    public void loadDataCommentByIdProduct(long productId, VolleyGetListDataListener<Comment> volleyLoadDataListener) {
        String url = DOMAIN + "/product/get-by-id?id=" + productId;
        List<Comment> comments = new ArrayList<>();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arrayComment = response.getJSONArray("comments");
                            for (int i = 0; i < arrayComment.length(); i++) {
                                JSONObject jsonObject = arrayComment.getJSONObject(i);
                                Comment comment = new Comment();
                                comment.setId(jsonObject.getInt("id"));
                                comment.setUserId(jsonObject.getLong("userId"));
                                comment.setAvatarUser(jsonObject.getString("userAvatar"));
                                comment.setFullNameUser(jsonObject.getString("fullNameUser"));
                                comment.setContent(jsonObject.getString("content"));
                                comments.add(comment);
                            }
                            volleyLoadDataListener.onResponse(comments);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", error.toString());
                        volleyLoadDataListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(objectRequest);
    }

    public void login(String username, String password, VolleyGetSingleDataListener<User> volleyResponseListener) {
        String url = DOMAIN + "/login?username=" + username + "&password=" + password;
        User user = new User();

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            user.setId(response.getLong("id"));
                            Log.d("AAA", String.valueOf(user.getId()));
                            if (user.getId() != -1 && user.getId() != 0) {
                                user.setUserName(response.getString("userName"));
                                user.setPassword(response.getString("password"));
                                user.setFullName(response.getString("fullName"));
                                user.setGender(response.getString("gender"));
                                user.setBirthday(response.getString("birthday"));
                                user.setPhone(response.getString("phone"));
                                user.setAddress(response.getString("address"));
                                user.setEmail(response.getString("email"));
                                user.setAvatar(response.getString("avatar"));
                                user.setJobs(response.getString("jobs"));
                                user.setFacebook(response.getString("facebook"));
                                user.setCartId(response.getLong("cartId"));
                            }
                            volleyResponseListener.onResponse(user);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError(error.toString());
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(objectRequest);
    }

    public void loadDataUser(long userId, VolleyGetSingleDataListener<User> volleyGetDataUserListener) {
        User user = new User();
        String url = DOMAIN + "/user?id=" + userId;

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            user.setId(response.getLong("id"));
                            user.setUserName(response.getString("userName"));
                            user.setPassword(response.getString("password"));
                            user.setFullName(response.getString("fullName"));
                            user.setGender(response.getString("gender"));
                            user.setBirthday(response.getString("birthday"));
                            user.setPhone(response.getString("phone"));
                            user.setAddress(response.getString("address"));
                            user.setEmail(response.getString("email"));
                            user.setAvatar(response.getString("avatar"));
                            user.setJobs(response.getString("jobs"));
                            user.setFacebook(response.getString("facebook"));
                            volleyGetDataUserListener.onResponse(user);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyGetDataUserListener.onError(error.toString());
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(objectRequest);
    }

    public void editUser(String id, String username, String email, String password, String fullName,
                         String gender, String birthday, String phone, String address,
                         String avatar, String jobs, String facebook, VolleyResponseListener volleyResponseListener) {
        String url = DOMAIN + "/user?id=" + id + "&userName=" + username + "&email=" + email
                + "&password=" + password + "&fullName=" + fullName + "&gender=" + gender
                + "&birthday=" + birthday + "&phone=" + phone + "&address=" + address
                + "&avatar=" + avatar + "&jobs=" + jobs + "&facebook=" + facebook;
        System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void loadDataCart(long cartId, VolleyGetListDataListener<Product> volleyGetDataCartListener) {
        List<Product> products = new ArrayList<>();
        String url = DOMAIN + "/product/get-by-id-cart?id=" + cartId;
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() == 0) {
                            volleyGetDataCartListener.onResponse(products);
                        } else {
                            for (int i = 0; i < response.length(); i++) {
                                Product product = new Product();
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    product.setId(jsonObject.getInt("id"));
                                    product.setCategoryId(jsonObject.getInt("categoryId"));
                                    product.setQuantity(jsonObject.getLong("quantity"));
                                    product.setName(jsonObject.getString("name"));
                                    product.setAvatar(jsonObject.getString("avatar"));
                                    product.setPrice(jsonObject.getDouble("price"));
                                    product.setDescription(jsonObject.getString("description"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                products.add(product);
                                volleyGetDataCartListener.onResponse(products);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", error.toString());
                        volleyGetDataCartListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(arrayRequest);
    }

    public void addProductToCart(long cartId, long productId, VolleyResponseListener volleyResponseListener) {
        String url = DOMAIN + "/cartProduct/insert?cartId=" + cartId + "&productId=" + productId + "&quantity=1" + "&status=0";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", error.toString());
                        volleyResponseListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void updateQuantityProductInCart(long cartId, long productId, long quantity, VolleyResponseListener volleyResponseListener) {
        String url = DOMAIN + "/cartProduct/update?cartId=" + cartId + "&productId=" + productId + "&quantity=" + quantity;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void deleteProductInCart(long cartId, long productId, VolleyResponseListener volleyResponseListener) {
        String url = DOMAIN + "/cartProduct/delete?cartId=" + cartId + "&productId=" + productId;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void addOder(long userId, String fullName, String address, String phone, String note,
                        double priceTotal, VolleyResponseListener volleyResponseListener) {
        String url = DOMAIN + "/order/insert?userId=" + userId
                + "&fullName=" + fullName + "&address=" + address
                + "&phone=" + phone + "&note=" + note
                + "&priceTotal=" + priceTotal;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void sendEmail(String email, String fullName, VolleyResponseListener volleyResponseListener) {
        String url = DOMAIN + "/email/send?email=" + email + "&fullName=" + fullName;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", error.toString());
                        volleyResponseListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void updateOder(long id, int status, VolleyResponseListener volleyResponseListener) {
        String url = DOMAIN + "/order/update?id=" + id + "&status=" + status;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void getOrderByUserId(long userId, VolleyGetListDataListener<Order> volleyGetListDataListener) {
        List<Order> orders = new ArrayList<>();
        String url = DOMAIN + "/user?id=" + userId;

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arrayOrder = response.getJSONArray("orders");
                            if (arrayOrder.length() == 0) {
                                volleyGetListDataListener.onResponse(orders);
                            } else {
                                for (int i = 0; i < arrayOrder.length(); i++) {
                                    JSONObject objectOrder = arrayOrder.getJSONObject(i);
                                    Order order = new Order();
                                    order.setId(objectOrder.getLong("id"));
                                    order.setFullName(objectOrder.getString("fullName"));
                                    order.setPhone(objectOrder.getString("phone"));
                                    order.setAddress(objectOrder.getString("address"));
                                    order.setEmail(objectOrder.getString("email"));
                                    order.setNote(objectOrder.getString("note"));
                                    order.setPriceTotal(objectOrder.getDouble("priceTotal"));
                                    order.setStatus(objectOrder.getInt("status"));
                                    order.setCreatedAt(objectOrder.getString("createdAt"));
                                    order.setUpdatedAt(objectOrder.getString("updatedAt"));

                                    JSONArray arrayOrderDetail = objectOrder.getJSONArray("orderDetails");
                                    List<Product> products = new ArrayList<>();
                                    for (int j = 0; j < arrayOrderDetail.length(); j++) {
                                        JSONObject objectOrderDetail = arrayOrderDetail.getJSONObject(j);
                                        Product product = new Product();
                                        product.setQuantity(objectOrderDetail.getLong("quantity"));
                                        JSONObject objectProduct = objectOrderDetail.getJSONObject("product");
                                        product.setId(objectProduct.getLong("id"));
                                        product.setName(objectProduct.getString("name"));
                                        product.setAvatar(objectProduct.getString("avatar"));
                                        product.setPrice(objectProduct.getDouble("price"));
                                        product.setDescription(objectProduct.getString("description"));
                                        products.add(product);
                                    }
                                    order.setListProduct(products);
                                    orders.add(order);
                                }
                                volleyGetListDataListener.onResponse(orders);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyGetListDataListener.onError(error.toString());
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(objectRequest);
    }

    /*
    public String checkLogin_2(String username, String pass) {
        String userId_2 = null;
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        RequestFuture<String> future = RequestFuture.newFuture();
        String url = "http://192.168.1.41:8080/ProductManagenmentAPI/check-login?username_email=" + username + "&password=" + pass;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, future, future);

        requestQueue.add(stringRequest);

        try {
            userId_2 = future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        Log.d("AAA", userId_2);
        return userId_2;
    }
    public void updateUser(String id, String firstName, String lastName, String birthday, String gender,
                           String phone, String address, String avatar, String jobs, String facebook,
                           VolleyResponseListener volleyResponseListener) {
        String url = DOMAIN + "update-user";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", error.toString());
                        volleyResponseListener.onError(error.toString());
                    }
                }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", id);
                map.put("firstName", firstName);
                map.put("lastName", lastName);
                map.put("birthday", birthday);
                map.put("gender", gender);
                map.put("phone", phone);
                map.put("address", address);
                map.put("avatar", avatar);
                map.put("jobs", jobs);
                map.put("facebook", facebook);
                Log.d("AAA", "map" + map);
                return map;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "multipart/form-data");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
*/
}
