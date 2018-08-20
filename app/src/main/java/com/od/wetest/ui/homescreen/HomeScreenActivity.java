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

import com.od.wetest.R;
import com.od.wetest.adapter.RecyclerViewAdapter;
import com.od.wetest.model.DataModel;

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
        homeScreenPresenter = new HomeScreenPresenter(getApplicationContext(), this);
        homeScreenPresenter.fetchDataFromJson();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeScreenPresenter.fetchDataFromJson();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

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
}
