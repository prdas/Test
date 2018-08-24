package com.od.wetest.ui.homescreen;

import com.android.volley.VolleyError;
import com.od.wetest.interfaces.IResponseListener;
import com.od.wetest.model.DataModel;
import com.od.wetest.util.Utils;

/**
 * ActionCardMainServiceImpl
 */
public class HomeScreenServiceImpl {

    private static final String TAG = HomeScreenServiceImpl.class.getSimpleName();

    private int statusCode;
    private DataModel response;

    public DataModel getResponse() {
        return response;
    }

    public void setResponse(DataModel response) {
        this.response = response;
    }

    private VolleyError error;
    private HomeScreenService service;


    /**
     * Sets service.
     *
     * @param service the service
     */
    public void setService(HomeScreenService service) {
        this.service = service;
    }


    public final IResponseListener jsonDataResponse = new IResponseListener() {
        @Override
        public void onResponse(int statusCode, com.od.wetest.communication.responses.Response response, VolleyError error) {
            setStatusCode(statusCode);
            if (statusCode == Utils.HTTP_STATUS_OK) {
                if (response instanceof DataModel) {
                    DataModel dataModel = (DataModel) response;
                    if (dataModel.getRows() != null) {
                        setResponse(dataModel);
                    }
                }
            } else if (statusCode == Utils.HTTP_STATUS_UNKNOWN_REQUEST) {

            } else if (statusCode == Utils.HTTP_STATUS_UNAUTHORIZED) {

            }

            if (error != null) {
                setError(error);
            }

            service.checkStatusCode();
        }
    };



    /**
     * Gets status code.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets status code.
     *
     * @param statusCode the status code
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }



    /**
     * Gets error.
     *
     * @return the error
     */
    public VolleyError getError() {
        return error;
    }

    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(VolleyError error) {
        this.error = error;
    }
}
