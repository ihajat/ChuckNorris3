package com.example.iqbalhajat.chucknorris3.repositories;

import com.example.iqbalhajat.chucknorris3.apis.JokeService;
import com.example.iqbalhajat.chucknorris3.models.Model;
import com.example.iqbalhajat.chucknorris3.apis.NetworkClient;

import io.reactivex.Observable;

public class Repository {

    public Observable<Model.Result> getObservable(String firstname, String lastname){
        return NetworkClient.getRetrofit().create(JokeService.class)
                .getJoke(firstname,lastname);
    }

}
