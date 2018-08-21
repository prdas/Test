package com.od.wetest.communication;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * This class is used to create Volley Request queue.
 */
public class VolleyRequestQueue {
    private static VolleyRequestQueue volleyRequestQueue;
    private Context mContext;
    private RequestQueue mRequestQueue;

    private VolleyRequestQueue(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static synchronized VolleyRequestQueue getInstance(Context context) {
        if (volleyRequestQueue == null) {
            volleyRequestQueue = new VolleyRequestQueue(context);
        }
        return volleyRequestQueue;
    }

    /**
     * Gets request queue.
     *
     * @return the request queue
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }
}
