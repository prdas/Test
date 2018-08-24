package com.od.wetest.ui.homescreen;

import com.od.wetest.model.DataModel;

/**
 * The interface Home screen view.
 */
public interface HomeScreenView {
    /**
     * Gets data model.
     *
     * @param dataModel the data model
     */
    void getDataModel(com.od.wetest.model.DataModel dataModel);

    void setUnauthorizedMessage();

    void noDataStatus(DataModel model);

    void setErrorMessage();
}
