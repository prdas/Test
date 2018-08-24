package com.od.wetest.interfaces;

import com.android.volley.VolleyError;
import com.od.wetest.communication.responses.Response;

public interface IResponseListener {
    /**
     * On response received
     *
     * @param statusCode Http status code
     * @param response   The response received
     * @param error      The Volley error
     */
    void onResponse(int statusCode, Response response, VolleyError error);
}
