package com.example.vemar.myapplication.controller;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vemar.myapplication.ItemAdapter;
import com.example.vemar.myapplication.R;
import com.example.vemar.myapplication.api.AllUnitsInteractorImpl;
import com.example.vemar.myapplication.api.AllUnitsViewModel;
import com.example.vemar.myapplication.api.pojo;
import com.example.vemar.myapplication.model.Item;

import org.json.JSONArray;
import java.util.Collections;
import java.util.List;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;
    CompositeSubscription subscription = new CompositeSubscription();
    AllUnitsViewModel allUnitsViewModel;
    List<pojo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allUnitsViewModel = new AllUnitsViewModel(new AllUnitsInteractorImpl(), AndroidSchedulers.mainThread());

        initViews();
        swipeContainer = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(MainActivity.this,"Github Users Refreshed",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void filterdata(View view) {

        Collections.sort(data);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Github Users.... ");
        pd.setCancelable(false);
        pd.show();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();

    }
    private void loadJSON(){

        subscription.add(allUnitsViewModel.getCodes()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<pojo>>() {
            @Override
            public void onCompleted() {
                pd.hide();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<pojo> pojos) {
                data = pojos;
                recyclerView.setAdapter(new ItemAdapter(getApplicationContext(),pojos));
                recyclerView.smoothScrollToPosition(0);
                swipeContainer.setRefreshing(false);
            }
        }));

    }
}
