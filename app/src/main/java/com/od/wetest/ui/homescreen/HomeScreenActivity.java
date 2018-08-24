package com.od.wetest.ui.homescreen;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.od.wetest.R;
import com.od.wetest.adapter.RecyclerViewAdapter;
import com.od.wetest.model.DataModel;

/**
 * The type Home screen activity.
 */
public class HomeScreenActivity extends AppCompatActivity implements HomeScreenView {

    private static final String TAG = HomeScreenActivity.class.getSimpleName();
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeContainer;
    private HomeScreenPresenter homeScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setSupportActionBar(toolbar);
        //Initializing presenter class
        homeScreenPresenter = new HomeScreenPresenter(this, new HomeScreenServiceImpl());
        homeScreenPresenter.fetchJsonDataFromUrl();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //Enabling Pull to refresh feature
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeScreenPresenter.fetchJsonDataFromUrl();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    //Received data from HomeScreenPresenter
    @Override
    public void getDataModel(DataModel dataModel) {
        if (dataModel != null) {
            Log.d(TAG, "Title Name " + dataModel.getActionBarTitle());
            toolbar.setTitle(dataModel.getActionBarTitle());
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dataModel.getRows());
            recyclerView.setAdapter(adapter);
            if(swipeContainer != null){
                swipeContainer.setRefreshing(false);
            }
        }
    }

    @Override
    public void setUnauthorizedMessage() {
        Toast.makeText(getApplicationContext(), "Not Authorized", Toast.LENGTH_LONG).show();
    }

    @Override
    public void noDataStatus(DataModel model) {
        if(model == null) {
            Toast.makeText(getApplicationContext(), "Data Not available", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setErrorMessage() {
        Toast.makeText(getApplicationContext(), "Timeout error", Toast.LENGTH_LONG).show();
    }
}
