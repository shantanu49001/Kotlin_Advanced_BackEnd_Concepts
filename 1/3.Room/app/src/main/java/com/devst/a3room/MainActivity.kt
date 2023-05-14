package com.devst.a3room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

//using the concept of viewmodel in or app
//we can add font famie in drwae reource file font type


//we are fetching quotes from json   https://type.fit/api/quotes
//json wil be in new folder->assess folder

//ui ko index and quote chahiy-->vm me

//now vm me param-->context to asses th e assests -->vm me view nhi aas krte

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(this)).get(MainViewModel::class.java)

        text = findViewById(R.id.text_quotes)
        setQuote(mainViewModel.getindex())   //retuens lisy[index ]

    }

    fun setQuote(quote: Quote) {
        //set the text into ui
        text.text = quote.text
    }



}