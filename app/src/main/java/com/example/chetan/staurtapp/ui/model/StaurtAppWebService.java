package com.example.chetan.staurtapp.ui.model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Constants;
import util.PreferenceHelper;

/**
 * Created by chetan on 11/4/2016.
 */
public class StaurtAppWebService {
    private static final String TAG = "Webservice";
    private static RequestQueue requestQueue;
    private static StaurtAppWebService INSTANCE;

    private StaurtAppWebService() {
    }

    public static StaurtAppWebService getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            requestQueue = Volley.newRequestQueue(context);
            INSTANCE = new StaurtAppWebService();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    /**
     *
     * @param password
     * @param username
     */

    public void AuthenticateUser(String password, String username, final Activity activity, final LoginCallBack callBack) {
        JSONObject userLoginParams = getLoginParams(password,username);
        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              callBack.onFail(error.toString());
            }
        };
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                try {
                    String status = response.getString("status");
                    StaurtDetail user;
                    if (status.equals("You have successfully logged in.")) {
                        String response_user = response.toString();
                        Log.d(TAG, response_user);
                        user = gson.fromJson(response_user, StaurtDetail.class);
                        String jsonUser = gson.toJson(user);
                        Log.e(TAG, jsonUser);
                        PreferenceHelper preferenceHelper = new PreferenceHelper(activity);
                        preferenceHelper.setPrefrance(Constants.KEY_PRF_STUART, jsonUser);
                        callBack.onSuccess(user);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };
        JsonObjectRequest postRequest = getJsonObjectPostRequest(userLoginParams,
                Constants.USER_LOGIN_URL,
                errorListener,
                responseListener);
        requestQueue.add(postRequest);
    }
    @NonNull
    private JSONObject getLoginParams(String password,String username) {
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("password",password);
        jsonParams.put("username",username);
        return new JSONObject(jsonParams);
    }





    @NonNull
    private JsonObjectRequest getJsonObjectPostRequest(final JSONObject params, final String guestLoginUrl, final Response.ErrorListener errorListener, final Response.Listener<JSONObject> responseListener) {
        int MY_SOCKET_TIMEOUT_MS = 30000;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                guestLoginUrl,
                params,
                responseListener,
                errorListener
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return request;
    }
    public interface LoginCallBack {
        void onSuccess(StaurtDetail user);
        void onFail(String msg);
    }

    public void StewardOrder(String restaurantId, final OrderCallBack callback){
        JSONObject userLoginParams = getOrderParams(restaurantId);
        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFail(error.toString());
            }
        };
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                OrderFoodList orderFoodList = gson.fromJson(response.toString(), OrderFoodList.class);
                String jsonUser = gson.toJson(orderFoodList);
                Log.e("gson", jsonUser);
                List<OrderList> orderList = orderFoodList.getOrderLists();
                callback.onSuccess(orderList);
                Log.d(TAG,response.toString());

            }
        };
        JsonObjectRequest postRequest = getJsonObjectPostRequest(userLoginParams,
                Constants.ORDER_FOOD_URL,
                errorListener,
                responseListener);
        requestQueue.add(postRequest);
    }
    public interface OrderCallBack{
        void onSuccess(List<OrderList> orderLists);
        void onFail(String msg);
    }


    @NonNull
    private JSONObject getOrderParams(String restaurantID) {
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("restaurant_id",restaurantID);
        return new JSONObject(jsonParams);
    }

}
