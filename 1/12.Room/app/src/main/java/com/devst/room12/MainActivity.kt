package com.devst.room12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


//combing the remote and local room db-->reposity handles it all
//api-->quotable io
//json to kotlin plugin
//call back parent wali hogi lekin adapter and dc and room me inner wali sore


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //object se api ka function [end point] call krke repinse get
        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        GlobalScope.launch {
            //getting the result from the api in bg
            val result = quotesApi.getQuotes(1)
            if (result != null) {

            }
        }

    }
}