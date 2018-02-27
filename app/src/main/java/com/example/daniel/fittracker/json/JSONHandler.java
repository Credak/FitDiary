package com.example.daniel.fittracker.json;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.daniel.fittracker.activity.DashboardActivity;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 27.02.2018.
 */

public class JSONHandler {
    JSONArray jsonArray;
    String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";


    public void makeJsonRequest(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("query", "brezel");
                params.put("timezone", "Europe/Berlin");
                params.put("locale", "de_DE");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-app-id", "32eadf65");
                params.put("x-app-key", "985621c1aa18090b9d6704aa6997f15f");
                return params;
            }
        };
        queue.add(postRequest);
    }
}
