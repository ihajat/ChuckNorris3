package com.example.iqbalhajat.chucknorris3.activities

import android.app.Activity
import android.os.Bundle
import com.example.iqbalhajat.chucknorris3.views.NeverEndingListActivityView
import com.example.iqbalhajat.chucknorris3.paginators.Paginator
import com.example.iqbalhajat.chucknorris3.R
import com.example.iqbalhajat.chucknorris3.presenters.NeverEndingListActivityPresenter
import com.srx.widget.PullToLoadView

/**
 *  Never-ending list activity, used for specifically inputted name random jokes
 *
    When the ‘Never-ending list button is pressed the app should open a new
    screen (can be a new Activity or a Fragment – your decision) that
    contains a list of random jokes. Jokes should be requested
    asynchronously, in batches of 20, from the server. When the user scrolls
    to the bottom of the list, the list should show a loading footer to indicate
    that more items are being requested.
    Note – since requested items are random, we don’t care if we have
    duplicates in the list.
 *
 */
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
