package com.example.vemar.myapplication.api;


import java.util.List;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface AllUnitsService {


    @GET("/repos/square/retrofit/contributors")
    rx.Observable<List<pojo>> getCodes();



}
