package com.od.wetest.communication.requests;

import com.od.wetest.interfaces.IResponseListener;
import com.od.wetest.model.DataModel;
import com.od.wetest.util.Utils;

public class FetchJsonDataRequest extends BaseRequest{


    /**
     * The Constructor BaseRequest
     *
     * @param responseListener IResponseListener instance
     */
    public FetchJsonDataRequest(IResponseListener responseListener) {
        super(Method.GET, Utils.URL, DataModel.class, responseListener);
    }
}
