package com.example.vemar.myapplication.api;

import java.util.List;

import rx.Observable;
import rx.Scheduler;


public class AllUnitsViewModel {

    private AllUnitsInteractor interactor;
    private Scheduler scheduler;

    public AllUnitsViewModel(AllUnitsInteractor interactor, Scheduler scheduler) {
        this.interactor = interactor;
        this.scheduler = scheduler;
    }

    public Observable<List<pojo>> getCodes(){
        return interactor.getCodes().observeOn(scheduler);
    }
}