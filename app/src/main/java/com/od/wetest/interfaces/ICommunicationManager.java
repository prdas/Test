package com.od.wetest.interfaces;


import com.od.wetest.communication.requests.BaseRequest;

/**
 * Interface ICommunicationManager
 *
 * @param <T> the type parameter
 */
public interface ICommunicationManager<T> {

    /**
     * Execute the request
     *
     * @param request BaseRequest
     */
    void executeRequest(BaseRequest request);

    /**
     * Cancel the request
     *
     * @param request BaseRequest
     */
    void cancelRequest(BaseRequest request);
}
