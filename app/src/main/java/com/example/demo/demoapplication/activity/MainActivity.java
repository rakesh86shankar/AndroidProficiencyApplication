package com.example.demo.demoapplication.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.demo.demoapplication.NewsUtil;
import com.example.demo.demoapplication.R;
import com.example.demo.demoapplication.adapter.ListAdapter;
import com.example.demo.demoapplication.modal.NewsList;
import com.example.demo.demoapplication.modal.Row;
import com.example.demo.demoapplication.network.APIClient;
import com.example.demo.demoapplication.network.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    SwipeRefreshLayout swipeLayout;
    TextView textView;
    final String KEY_FOR_LAYOUT_MANAGER = "KEY_FOR_LAYOUT_MANAGER";
    LinearLayoutManager mLayoutManager;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeLayout.setOnRefreshListener(this);
        textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        downloadData();
    }

    /**
     * Method to download the data from web services url.
     */
    public void downloadData() {
        try {
            APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<NewsList> call = apiService.getNewsList();
            call.enqueue(new Callback<NewsList>() {
                @Override
                public void onResponse(@NonNull Call<NewsList> call, @NonNull Response<NewsList> response) {
                    NewsList newsList = response.body();
                    updateView(newsList);
                }

                @Override
                public void onFailure(@NonNull Call<NewsList> call, @NonNull Throwable t) {
                    updateViewOnFailure();
                }
            });
        } catch (Exception e) {
            Log.v("" + R.string.exception_download, e.getMessage());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Parcelable state = savedInstanceState.getParcelable(KEY_FOR_LAYOUT_MANAGER);
        mLayoutManager.onRestoreInstanceState(state);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(KEY_FOR_LAYOUT_MANAGER, mLayoutManager.onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRefresh() {
        downloadData();
    }

    /**
     * Method to update the recycler view with data.
     */
    public void updateView(NewsList newsList) {
        setTitle(newsList.getTitle());
        recyclerView.setVisibility(View.VISIBLE);
        if (listAdapter == null) {
            listAdapter = new ListAdapter(MainActivity.this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(listAdapter);
        }
        //recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        List<Row> updatedNewsListRows = NewsUtil.getNonNullData(newsList).getRows();
        if (updatedNewsListRows.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            listAdapter.setRows(updatedNewsListRows);
            listAdapter.notifyDataSetChanged();
            textView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            textView.setText(R.string.empty_data);
            textView.setVisibility(View.VISIBLE);
        }

        swipeLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }

    /**
     * Method to update the view during failure scenarios.
     */
    public void updateViewOnFailure() {
        textView.setText(R.string.connection_Issues);
        textView.setVisibility(View.VISIBLE);
        swipeLayout.setRefreshing(false);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

}
