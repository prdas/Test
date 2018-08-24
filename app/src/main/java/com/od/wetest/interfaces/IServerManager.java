package com.od.wetest.interfaces;

public interface IServerManager {
    void fetchData(IResponseListener iResponseListener);
    void cancelFetchData();
}
