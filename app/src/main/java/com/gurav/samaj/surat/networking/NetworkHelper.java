package com.gurav.samaj.surat.networking;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.DataProccessor;

import java.util.HashMap;
import java.util.Map;

public class NetworkHelper {
    String baseUrl;
    Context context;
    RequestQueue requestQueue;

    public NetworkHelper(Context context2, String baseUrl2) {
        this.context = context2;
        this.baseUrl = baseUrl2;
        this.requestQueue = Volley.newRequestQueue(context2);
    }

    public void sendRequest(final String url, HashMap map, final NetworkCallback callback, Activity activity) {
        if (CommonObject.isNetworkConnected(activity)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.baseUrl);
            sb.append(url);
            final HashMap hashMap = map;
            StringRequest r1 = new StringRequest(1, sb.toString(), new Listener<String>() {
                public void onResponse(String response) {
                    callback.onNetworkResult(response);
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    try {
                        Log.e("error", error.getMessage());
                        callback.onNetworkError();
                    } catch (Exception e) {
                    
                    }
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    Log.d("TAG", "getParams: "+url+" "+hashMap.toString());
                    return hashMap;
                }

                public Map<String, String> getHeaders() {
                    HashMap hashMap = new HashMap();
                    hashMap.put("Content-Type", "application/x-www-form-urlencoded");
                    hashMap.put("X-Requested-With", "XMLHttpRequest");
                    return hashMap;
                }
            };
            r1.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0f));
            this.requestQueue.add(r1);
            return;
        }
        Toast.makeText(this.context, "No Internet connection!!!", Toast.LENGTH_SHORT).show();
    }

    public void sendGetRequest(String url, final NetworkCallback callback, Activity activity) {
        if (CommonObject.isNetworkConnected(activity)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.baseUrl);
            sb.append(url);
            StringRequest stringRequest = new StringRequest(0, sb.toString(), new Listener<String>() {
                public void onResponse(String response) {
                    callback.onNetworkResult(response);
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    Log.e("error", error.getMessage());
                    callback.onNetworkError();
                }
            });
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0f));
            this.requestQueue.add(stringRequest);
            return;
        }
        Toast.makeText(this.context, "No Internet connection!!!", Toast.LENGTH_SHORT).show();
    }

    public void sendWithToekRequest(String url, HashMap map, final NetworkCallback callback, Activity activity) {
        if (CommonObject.isNetworkConnected(activity)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.baseUrl);
            sb.append(url);
            final HashMap hashMap = map;
            StringRequest r1 = new StringRequest(1, sb.toString(), new Listener<String>() {
                public void onResponse(String response) {
                    callback.onNetworkResult(response);
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    callback.onNetworkError();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    return hashMap;
                }

                public Map<String, String> getHeaders() {
                    HashMap hashMap = new HashMap();
                    hashMap.put("Content-Type", "application/x-www-form-urlencoded");
                    hashMap.put("X-Requested-With", "XMLHttpRequest");
                    StringBuilder sb = new StringBuilder();
                    sb.append(DataProccessor.getStr("token_type"));
                    sb.append(" ");
                    sb.append(DataProccessor.getStr("token"));
                    hashMap.put("Authorization", sb.toString());
                    return hashMap;
                }
            };
            r1.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0f));
            this.requestQueue.add(r1);
            return;
        }
        Toast.makeText(this.context, "No Internet connection!!!", Toast.LENGTH_SHORT).show();
    }
}
