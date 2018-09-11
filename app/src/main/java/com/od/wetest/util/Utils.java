package com.od.wetest.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
    /**
     * The constant URL.
     */
    public static final String URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";

    /**
     * The http status OK
     */
    public static final int HTTP_STATUS_OK = 200;

    /**
     * The constant HTTP_STATUS_NULL.
     */
    public static final int HTTP_STATUS_NULL = 0;

    /**
     * The http status created
     */
    public static final int HTTP_STATUS_CREATED = 201;

    /**
     * The http status bad request
     */
    public static final int HTTP_STATUS_UNKNOWN_REQUEST = 400;

    /**
     * The http status unauthorized
     */
    public static final int HTTP_STATUS_UNAUTHORIZED = 401;

    /**
     * The http status invalid attemps error
     */
    public static final int HTTP_STATUS_WRONGATTEMPTS_LIMIT = 403;

    /**
     * The http status not found
     */
    public static final int HTTP_STATUS_NOT_FOUND = 404;

    /**
     * The http status user account locked
     */
    public static final int HTTP_STATUS_USER_LOCKED = 423;

    /**
     * The http status service unavailable
     */
    public static final int HTTP_STATUS_SERVICE_UNAVAILABLE = 503;

    /**
     * The http status redirect for initial config
     */
    public static final int HTTP_STATUS_REDIRECT = 409;

    /**
     * The constant HTTP_STATUS_RESET_CONTENT.
     */
    public static final int HTTP_STATUS_RESET_CONTENT = 205;

    /**
     * The constant HTTP_STATUS_EMPTY.
     */
    public static final int HTTP_STATUS_EMPTY = 204;

    /**
     * The constant HTTP_STATUS_ERROR.
     */
    public static final int HTTP_STATUS_ERROR = 500;

    /**
     * The constant HTTP_STATUS_GONE.
     */
    public static final int HTTP_STATUS_GONE = 410;

    /**
     * The constant HTTP_STATUS_REDIRECT_LOGOUT.
     */
    public static final int HTTP_STATUS_REDIRECT_LOGOUT = 302;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
