package com.od.wetest.communication;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;
import com.od.wetest.communication.requests.BaseRequest;
import com.od.wetest.interfaces.ICommunicationManager;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that's responsible for creating the Volley RequestQueue and adding the incoming requests into the volley queue
 */
public class ServerCommunicationManager implements ICommunicationManager {

    /**
     * The TAG.
     */
    private static final String TAG = ServerCommunicationManager.class.getSimpleName();

    /**
     * The Server Communication Manager.
     */
    private static ServerCommunicationManager sInstance;

    /**
     * The request queue.
     */
    private RequestQueue mRequestQueue;

    /**
     * The constructor ServerCommunicationManager
     */
    private ServerCommunicationManager() {
        initializeVolleyQueue();
    }

    /**
     * Gets the ServerCommunicationManager instance
     *
     * @return ServerCommunicationManager instance
     */
    public static synchronized ServerCommunicationManager getInstance() {
        if (sInstance == null) {
            sInstance = new ServerCommunicationManager();
        }
        return sInstance;
    }


    /**
     * Initializes Volley request queue
     */
    private void initializeVolleyQueue() {
        if (mRequestQueue == null) {

            // Set up the network to use HttpURLConnection as the HTTP client.
            Network network = new BasicNetwork(new HurlStack());

            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = new RequestQueue(new NoCache(), network);

            // CookieStore is just an interface, you can implement it and do things like
            // save the cookies to disk or what ever.
            final CookieStore cookieStore = new CookieStore() {
                @Override
                public void add(URI uri, HttpCookie cookie) {

                }

                @Override
                public List<HttpCookie> get(URI uri) {
                    List<HttpCookie> cookies = new ArrayList<>();
                    return cookies;
                }

                @Override
                public List<HttpCookie> getCookies() {
                    //add the cookie stored in application class
                    List<HttpCookie> cookies = new ArrayList<>();
                    return cookies;
                }

                @Override
                public List<URI> getURIs() {
                    return new ArrayList<>();
                }

                @Override
                public boolean remove(URI uri, HttpCookie cookie) {
                    return false;
                }

                @Override
                public boolean removeAll() {
                    return false;
                }
            };
            CookieManager manager = new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            // Start the queue
            mRequestQueue.start();
        }
    }

    /**
     * Execute request
     *
     * @param request BaseRequest
     */
    @Override
    public synchronized void executeRequest(BaseRequest request) {
        mRequestQueue.add(request);
    }

    /**
     * Cancel request
     *
     * @param request BaseRequest
     */
    @Override
    public void cancelRequest(BaseRequest request) {
        request.cancel();
    }
}
