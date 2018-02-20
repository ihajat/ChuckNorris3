package com.example.iqbalhajat.chucknorris3.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.iqbalhajat.chucknorris3.*
import com.example.iqbalhajat.chucknorris3.presenters.MainPresenter
import com.example.iqbalhajat.chucknorris3.views.MainActivityView
//import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainActivityView {

    private lateinit var presenter: MainPresenter

    override fun displayError() {
        Log.i("myapp","displayError")
        this.toast(getString(R.string.error_getting_joke_from_server))
    }

    override fun displayJoke(s: String) {
        Log.i("myapp","displayJoke")
        showDialog(s)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button3.setOnClickListener {
            //            beginJokeSearch("Chuck","Norris")
            presenter.getJoke("Chuck","Norris")
        }

        button2.setOnClickListener {
            // list
            val intent = Intent(this, NeverEndingListActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            // text input
            val intent = Intent(this, TextInputActivity::class.java)
            startActivity(intent)
        }

        presenter = MainPresenter(
                this
        )
    }
}
