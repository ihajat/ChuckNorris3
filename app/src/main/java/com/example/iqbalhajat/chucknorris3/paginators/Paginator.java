package com.example.iqbalhajat.chucknorris3.paginators;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.iqbalhajat.chucknorris3.apis.JokeService;
import com.example.iqbalhajat.chucknorris3.models.Response;
import com.example.iqbalhajat.chucknorris3.adapters.MyAdapter;
import com.example.iqbalhajat.chucknorris3.models.CardJoke;
import com.example.iqbalhajat.chucknorris3.models.Joke;
import com.example.iqbalhajat.chucknorris3.utils.StringExtensionFunctionsKt;
import com.srx.widget.PullCallback;
import com.srx.widget.PullToLoadView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Paginator {

    Disposable disposable;

    Context c;
    private PullToLoadView pullToLoadView;
    RecyclerView rv;
    private MyAdapter adapter;
    private boolean isLoading = false;
    private boolean hasLoadedAll = false;
    private int nextPage;


    public Paginator(Context c, PullToLoadView pullToLoadView) {
        this.c = c;
        this.pullToLoadView = pullToLoadView;


        //RECYCLERVIEW
        RecyclerView rv=pullToLoadView.getRecyclerView();
        rv.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.VERTICAL,false));

        adapter=new MyAdapter(c,new ArrayList<CardJoke>());
        rv.setAdapter(adapter);

        initializePaginator();
    }

    /*
    PAGE DATA
     */
    public Paginator initializePaginator()
    {
        pullToLoadView.isLoadMoreEnabled(true);
        pullToLoadView.setPullCallback(new PullCallback() {

            //LOAD MORE DATA
            @Override
            public void onLoadMore() {
                loadData(nextPage);
            }

            //REFRESH AND TAKE US TO FIRST PAGE
            @Override
            public void onRefresh() {
                adapter.clear();
                hasLoadedAll=false;
                loadData(1);
            }

            //IS LOADING
            @Override
            public boolean isLoading() {
                return isLoading;
            }

            //CURRENT PAGE LOADED
            @Override
            public boolean hasLoadedAllItems() {
                return hasLoadedAll;
            }
        });

        pullToLoadView.initLoad();

        return this;
    }

    /*
     LOAD MORE DATA
     SIMULATE USING HANDLERS
     */
    public void loadData(final int page)
    {
       isLoading=true;

        disposable = JokeService.Companion.create().getJokes()
                .delay(2, TimeUnit.SECONDS)  // Fake, not required, but, imitates slow pagination
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listResponse -> addAll(page,listResponse));
    }


    public void addAll(int page,Response<List<Joke>> response){
        if(response.getValue() != null) {
            int i=1;
            for (Joke f : response.getValue()) {
                adapter.add( new CardJoke(String.format("Joke %d , Page %d",i++ , page),formatJokeText(f.getJoke())));
            }
        }

        pullToLoadView.setComplete();
        isLoading = false;
        nextPage = page + 1;
    }

    public void onPause(){
        disposable.dispose();
    }

    private String formatJokeText(String original)
    {
        String temp = StringExtensionFunctionsKt.removeQuotes(original);
        temp = StringExtensionFunctionsKt.highlightChuckNorris(temp);
        return temp;
    }
}
