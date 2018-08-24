package com.od.wetest.communication;

import com.od.wetest.communication.requests.FetchJsonDataRequest;
import com.od.wetest.interfaces.IResponseListener;
import com.od.wetest.interfaces.IServerManager;

public class ServerManager implements IServerManager{

    private static ServerManager instance;
    private FetchJsonDataRequest fetchJsonDataRequest;
    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized ServerManager getInstance() {
        if (instance == null) {
            instance = new ServerManager();
        }
        return instance;
    }

    @Override
    public void fetchData(IResponseListener iResponseListener) {
        fetchJsonDataRequest = new FetchJsonDataRequest(iResponseListener);
        ServerCommunicationManager.getInstance().executeRequest(fetchJsonDataRequest);
    }

    @Override
    public void cancelFetchData() {

    }
}
