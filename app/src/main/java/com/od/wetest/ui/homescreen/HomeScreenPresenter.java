package com.od.wetest.ui.homescreen;

import com.od.wetest.communication.ServerManager;
import com.od.wetest.util.Utils;

/**
 * The type Home screen presenter.
 */
public class HomeScreenPresenter implements HomeScreenService {
    private HomeScreenServiceImpl homeScreenService;
    private HomeScreenView homeScreenView;
    private static final String TAG = HomeScreenPresenter.class.getSimpleName();

    /**
     * Instantiates a new Home screen presenter.
     *
     * @param homeScreenService the context
     * @param homeScreenView    the home screen view
     */
    public HomeScreenPresenter(HomeScreenView homeScreenView, HomeScreenServiceImpl homeScreenService) {
        this.homeScreenService = homeScreenService;
        this.homeScreenView = homeScreenView;
        homeScreenService.setService(this);
    }

    void fetchJsonDataFromUrl() {
        ServerManager.getInstance().fetchData(homeScreenService.jsonDataResponse);
    }

    @Override
    public void checkStatusCode() {
        if (homeScreenService.getStatusCode() == Utils.HTTP_STATUS_OK) {
            homeScreenView.getDataModel(homeScreenService.getResponse());
        } else if (homeScreenService.getStatusCode() == Utils.HTTP_STATUS_UNAUTHORIZED) {
            homeScreenView.setUnauthorizedMessage();
        } else if (homeScreenService.getStatusCode() == Utils.HTTP_STATUS_EMPTY) {
            homeScreenView.noDataStatus(null);
        } else {
            // Connection error or connection timeout error
            homeScreenView.setErrorMessage();
        }
    }
}

