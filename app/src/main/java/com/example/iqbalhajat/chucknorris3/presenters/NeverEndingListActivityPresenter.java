package com.example.iqbalhajat.chucknorris3.presenters;

import com.example.iqbalhajat.chucknorris3.repositories.Repository;
import com.example.iqbalhajat.chucknorris3.views.NeverEndingListActivityView;

/**
 * Created by IqbalHajat on 20/02/2018.
 */

public class NeverEndingListActivityPresenter {
    private static final String TAG = "NeverEndingListActivityPresenter";
    private NeverEndingListActivityView view;
    private Repository repository = new Repository();

    public NeverEndingListActivityPresenter(NeverEndingListActivityView view) {
        this.view = view;
    }
}
