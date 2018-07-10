package com.example.vemar.myapplication.api;


import java.util.List;

import rx.Observable;

public interface AllUnitsInteractor {

    Observable<List<pojo>> getCodes();
}
