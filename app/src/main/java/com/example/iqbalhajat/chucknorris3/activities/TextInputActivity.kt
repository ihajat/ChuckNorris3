package com.example.iqbalhajat.chucknorris3.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.iqbalhajat.chucknorris3.*
import com.example.iqbalhajat.chucknorris3.presenters.TextInputActivityPresenter
import com.example.iqbalhajat.chucknorris3.utils.containsSpace
import com.example.iqbalhajat.chucknorris3.utils.getIndexOfFirstNonWhitespace
import com.example.iqbalhajat.chucknorris3.utils.getIndexOfLastNonWhitespace
import com.example.iqbalhajat.chucknorris3.views.TextInputActivityView
import kotlinx.android.synthetic.main.activity_text_input.*

class TextInputActivity :  TextInputActivityView, BaseActivity() {
    override fun displayError() {
        this.toast(getString(R.string.error_getting_joke_from_server))
    }

    override fun displayJoke(s: String) {
        showDialog(s)
    }

    private lateinit var presenter: TextInputActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_input)

        btn_search.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                val raw = edit_search.text.toString()
                val inner_string = raw.substring(raw.getIndexOfFirstNonWhitespace(), raw.getIndexOfLastNonWhitespace()+1)
                Log.i("myapp","inner_string:" + inner_string)
                if(inner_string.containsSpace()) {
                    val firstSpace = inner_string.indexOf(" ") // detect the first space character, leading space not considered
                    val firstName = inner_string.substring(0, firstSpace).trim()  // get everything upto the first space character
                    val lastName = inner_string.substring(firstSpace).trim() // get everything after the first space, trimming the spaces off
                    Log.i("myapp","lastName:" + lastName)
                    presenter.getJoke(firstName,lastName)
                }
                else
                {
                    Toast.makeText(this, "First and last name required", Toast.LENGTH_SHORT).show()
                }
            }
        }

        presenter = TextInputActivityPresenter(
                this
        )
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }
}
