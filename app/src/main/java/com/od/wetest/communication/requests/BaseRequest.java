package com.od.wetest.communication.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;
import com.od.wetest.interfaces.IParser;
import com.od.wetest.interfaces.IResponseListener;
import com.od.wetest.parser.ParserFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The abstract Class BaseRequest
 *
 * @param <T> the type parameter
 */
public abstract class BaseRequest<T extends com.od.wetest.communication.responses.Response> extends Request<T> implements Response.ErrorListener {

    /**
     * The TAG.
     */
    private static final String TAG = BaseRequest.class.getSimpleName();

    /**
     * The PROTOCOL CHARSET.
     */
    private static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * The PROTOCOL CONTENT TYPE
     */
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    /**
     * The response class.
     */
    private final Class<T> mResponseClass;

    /**
     * The response listener.
     */
    private final IResponseListener mListener;

    /**
     * The status code.
     */
    private int mStatusCode = 0;

    /**
     * The Constructor BaseRequest
     *
     * @param method           HTTP method
     * @param url              String value
     * @param cls              Response class
     * @param responseListener IResponseListener instance
     */
    BaseRequest(int method, String url, Class<T> cls, IResponseListener responseListener) {
        super(method, url, null);
        mListener = responseListener;
        mResponseClass = cls;
        super.setRetryPolicy(new DefaultRetryPolicy(2*60*1000, 5, 5f));
        this.setShouldCache(false);
    }

    /**
     * Gets the http header
     *
     * @return Map The header content
     * @throws AuthFailureError
     */

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        return params;
    }

    /**
     * Gets the http body content type
     *
     * @return String value
     */
    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    /**
     * Parse the network response
     *
     * @param response Response from the network
     * @return Response object
     */
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            mStatusCode = getStatusCode(response);

            // Parse JSON response
            String jsonResponseData = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            IParser parser = ParserFactory.getParser();
            T jsonResponse = parser.fromJson(jsonResponseData, mResponseClass);
            return Response.success(jsonResponse, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    /**
     * Gets the status code
     *
     * @param response The NetworkResponse
     * @return int value
     */
    private int getStatusCode(NetworkResponse response) {
        int statusCode = 0;
        if (response != null) {
            statusCode = response.statusCode;
        }
        return statusCode;
    }

    /**
     * Parses the network response based on the collection type
     *
     * @param response       The NetworkResponse
     * @param collectionType The Type
     * @return List of objects
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws JsonSyntaxException          the json syntax exception
     */
    List<T> parseCollectionType(NetworkResponse response, Type collectionType)
            throws UnsupportedEncodingException, JsonSyntaxException {
        // Get http status code
        mStatusCode = getStatusCode(response);

        // Parse JSON response
        String jsonResponseData = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        IParser parser = ParserFactory.getParser();
        return parser.fromJson(jsonResponseData, collectionType);
    }

    /**
     * Parse the Network error
     *
     * @param error The VolleyError
     * @return The VolleyError
     */
    @Override
    protected VolleyError parseNetworkError(VolleyError error) {
        // Get http status code
        NetworkResponse response = error.networkResponse;
        mStatusCode = getStatusCode(response);
        return super.parseNetworkError(error);
    }

    /**
     * Deliver the response
     *
     * @param response The parsed response returned by
     */
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(mStatusCode, response, null);
    }

    /**
     * Deliver the error
     *
     * @param error Error details
     */
    @Override
    public void deliverError(VolleyError error) {
        // Get http status code
        NetworkResponse response = error.networkResponse;
        mStatusCode = getStatusCode(response);

        mListener.onResponse(mStatusCode, null, error);
    }

    /**
     * Sends the error response
     *
     * @param error Error details
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        // Get http status code
        NetworkResponse response = error.networkResponse;
        mStatusCode = getStatusCode(response);
        mListener.onResponse(mStatusCode, null, error);
    }
}
