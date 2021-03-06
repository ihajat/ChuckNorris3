package com.example.iqbalhajat.chucknorris3.activities

import android.content.Intent
import android.os.Bundle
import com.example.iqbalhajat.chucknorris3.*
import com.example.iqbalhajat.chucknorris3.presenters.MainPresenter
import com.example.iqbalhajat.chucknorris3.views.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  Main activity, used for default name random jokes
 *
 *  When the ‘Random Joke’ button is pressed the app should request a
 *  single random joke from the server and then display it in a Dialog with a
 *  dismiss button.
 *
 */
class MainActivity : BaseActivity(), MainActivityView {

    private lateinit var presenter: MainPresenter

    override fun displayError() {
        this.toast(getString(R.string.error_getting_joke_from_server))
    }

    override fun displayJoke(s: String) {
        showDialog(s)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button3.setOnClickListener {
            presenter.getJoke("Chuck","Norris")
        }

        button2.setOnClickListener {
            val intent = Intent(this, NeverEndingListActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val intent = Intent(this, TextInputActivity::class.java)
            startActivity(intent)
        }

        presenter = MainPresenter(
                this
        )
    }
}
