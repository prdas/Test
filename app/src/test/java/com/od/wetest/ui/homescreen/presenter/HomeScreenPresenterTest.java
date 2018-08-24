package com.od.wetest.ui.homescreen.presenter;

import com.od.wetest.ui.homescreen.HomeScreenPresenter;
import com.od.wetest.ui.homescreen.HomeScreenServiceImpl;
import com.od.wetest.ui.homescreen.HomeScreenView;
import com.od.wetest.util.Utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeScreenPresenterTest {

    private static final String TAG = HomeScreenPresenterTest.class.getSimpleName();

    @Mock
    private HomeScreenView view;
    @Mock
    private HomeScreenServiceImpl service;

    @Mock
    private HomeScreenPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new HomeScreenPresenter(view, service);
    }

    @Test
    public void shouldReturnHttpStatus200() throws Exception {
        when(service.getStatusCode()).thenReturn(Utils.HTTP_STATUS_OK);
        presenter.checkStatusCode();
        verify(view).getDataModel(service.getResponse());

    }

    @Test
    public void shouldReturnHttpStatus401() throws Exception {
        when(service.getStatusCode()).thenReturn(Utils.HTTP_STATUS_UNAUTHORIZED);
        presenter.checkStatusCode();
        verify(view).setUnauthorizedMessage();
    }

    @Test
    public void shouldReturnHttpStatus204() throws Exception {
        when(service.getStatusCode()).thenReturn(Utils.HTTP_STATUS_EMPTY);
        presenter.checkStatusCode();
        verify(view).noDataStatus(null);
    }

    @Test
    public void shouldReturnVolleyError() throws Exception {
        when(service.getStatusCode()).thenReturn(Utils.HTTP_STATUS_ERROR);
        presenter.checkStatusCode();
        verify(view).setErrorMessage();
    }
}