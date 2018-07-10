package com.example.vemar.myapplication.api;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;



public class AllUnitsInteractorImpl implements AllUnitsInteractor {



    AllUnitsService service;
    Retrofit retrofit;

    public AllUnitsInteractorImpl(){

        retrofit = new Retrofit.Builder().baseUrl("https://api.github.com").
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        service  = retrofit.create(AllUnitsService.class);

    }

    @Override
    public Observable<List<pojo>>getCodes() {
        return service.getCodes();
    }
}