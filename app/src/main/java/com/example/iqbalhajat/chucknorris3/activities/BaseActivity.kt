package com.example.iqbalhajat.chucknorris3.activities

import android.app.Activity
import android.content.Context
import android.widget.Toast

//import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.alert

abstract  class  BaseActivity : Activity() {

    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


    override fun onPause() {
        super.onPause()
    }

    protected fun showDialog(joke: String){
        alert(joke) {
            title = "Joke"
            positiveButton("Cool") {  }
        }.show()
    }

}