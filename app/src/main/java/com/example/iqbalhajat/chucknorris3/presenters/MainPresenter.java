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
//import rx.android.schedulers.AndroidSchedulers;

//import io.reactivex.Scheduler;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.annotations.NonNull;
//import io.reactivex.schedulers.Schedulers;
//import rx.Scheduler;
//import rx.Scheduler;
//import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by IqbalHajat on 18/02/2018.
 */

public class MainPresenter {
    private static final String TAG = "MainPresenter";
    private MainActivityView view;
//    private final Scheduler processScheduler;
//    TestObserver testObserver;
//    private final Scheduler mainScheduler;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Repository repository = new Repository();
//    private final SchedulerProvider mSchedulerProvider;

//    public SchedulerProvider getSchedulerProvider() {
//        return mSchedulerProvider;
//    }

    public MainPresenter(MainActivityView view) {

        this.view = view;
//        this.processScheduler = processScheduler;
//        this.mainScheduler = mainScheduler;
//        this.mSchedulerProvider = schedulerProvider;
    }

    
    public void getJoke(String firstname, String lastname) {
        compositeDisposable.add(getObservable(firstname,lastname).subscribeWith(getObserver()));
    }


    public Observable<Model.Result> getObservable(String firstname, String lastname){
        return repository.getObservable(firstname,lastname)
                .subscribeOn(Schedulers.io() ) // Schedulers.io()  //processScheduler
                .observeOn(AndroidSchedulers.mainThread()
                );  // mainScheduler/
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
//                Log.d(TAG,"Error"+e);
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

    public static class NeverEndingListActivityPresenter {
        private static final String TAG = "NeverEndingListActivityPresenter";
        private NeverEndingListActivityView view;
        private Repository repository = new Repository();

        public NeverEndingListActivityPresenter(NeverEndingListActivityView view) {
            this.view = view;
        }
    }
}
