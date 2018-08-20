package com.od.wetest.ui.homescreen;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.od.wetest.communication.VolleyRequestQueue;
import com.od.wetest.model.DataModel;
import com.od.wetest.util.Utils;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * The type Home screen presenter.
 */
class HomeScreenPresenter {
    private Context context;
    private HomeScreenView homeScreenView;
    private static final String TAG = HomeScreenPresenter.class.getSimpleName();

    /**
     * Instantiates a new Home screen presenter.
     *
     * @param context        the context
     * @param homeScreenView the home screen view
     */
    HomeScreenPresenter(Context context, HomeScreenView homeScreenView) {
        this.context = context;
        this.homeScreenView = homeScreenView;
    }

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof NetworkError) {
                Toast.makeText(context, "No network available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };

    /**
     * Fetch data from json.
     */
    void fetchDataFromJson(){
        RequestQueue queue = VolleyRequestQueue.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Utils.URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                DataModel dataModel = mGson.fromJson(response.toString(), DataModel.class);
                //Sending data to UI.
                homeScreenView.getDataModel(dataModel);
            }
        }, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        queue.add(jsonObjectRequest);


        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                try {
                    if (request.getCacheEntry() != null) {
                        String cacheValue = new String(request.getCacheEntry().data, "UTF-8");
                        Log.d(TAG, request.getCacheKey() + " " + cacheValue);

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

