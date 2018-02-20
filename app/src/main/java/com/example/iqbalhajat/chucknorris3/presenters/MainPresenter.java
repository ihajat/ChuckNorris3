package com.example.iqbalhajat.chucknorris3.presenters;

import android.util.Log;

import com.example.iqbalhajat.chucknorris3.views.MainActivityView;
import com.example.iqbalhajat.chucknorris3.models.Model;
import com.example.iqbalhajat.chucknorris3.views.NeverEndingListActivityView;
import com.example.iqbalhajat.chucknorris3.repositories.Repository;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {
    private static final String TAG = "MainPresenter";
    private MainActivityView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Repository repository = new Repository();

    public MainPresenter(MainActivityView view) {

        this.view = view;
    }

    
    public void getJoke(String firstname, String lastname) {
        compositeDisposable.add(getObservable(firstname,lastname).subscribeWith(getObserver()));
    }


    public Observable<Model.Result> getObservable(String firstname, String lastname){
        return repository.getObservable(firstname,lastname)
                .subscribeOn(Schedulers.io() )
                .observeOn(AndroidSchedulers.mainThread()
                );
    }

    public DisposableObserver<Model.Result> getObserver(){
        return new DisposableObserver<Model.Result>() {

            @Override
            public void onNext( Model.Result res) {
//                Log.d(TAG,"OnNext"+res.getValue());
                view.displayJoke(res.getValue().getJoke());
            }

            @Override
            public void onError( Throwable e) {
                e.printStackTrace();
                view.displayError();
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }
    public void unsubscribe() {
        compositeDisposable.clear();
    }

}
