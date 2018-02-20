package com.example.iqbalhajat.chucknorris3.activities

import android.app.Activity
import android.os.Bundle
import com.example.iqbalhajat.chucknorris3.presenters.MainPresenter.NeverEndingListActivityPresenter
import com.example.iqbalhajat.chucknorris3.views.NeverEndingListActivityView
import com.example.iqbalhajat.chucknorris3.paginators.Paginator
import com.example.iqbalhajat.chucknorris3.R
import com.srx.widget.PullToLoadView

class NeverEndingListActivity : NeverEndingListActivityView,Activity() {
    private lateinit var presenter: NeverEndingListActivityPresenter

    private lateinit var paginator: Paginator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_never_ending_list)
        val pullToLoadView = findViewById(R.id.pullToLoadView) as PullToLoadView
        paginator = Paginator(this, pullToLoadView).initializePaginator()

        presenter = NeverEndingListActivityPresenter(
                this
        )
    }

    override fun onPause() {
        super.onPause()
        paginator.onPause()
    }
}
