package com.example.iqbalhajat.chucknorris3.activities

import android.app.Activity
import android.content.Context
import android.widget.Toast
import org.jetbrains.anko.alert

/**
 * Base activity used for toast extension, and catching the onPause event and and dialog box reused by child activities
 */
abstract  class  BaseActivity : Activity() {

    /**
     * extension function for easy toast use, for activities inheriting from this base class
     */
    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


    override fun onPause() {
        super.onPause()
    }

    /**
     * Anko dialog box with a dismiss button
     */
    protected fun showDialog(joke: String){
        alert(joke) {
            title = "Joke"
            positiveButton("Cool") {  }
        }.show()
    }

}