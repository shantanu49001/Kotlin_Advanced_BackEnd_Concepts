package com.devst.a13room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devst.a13room.Api.QuoteService
import com.devst.a13room.Api.RetrofitHelper
import com.devst.a13room.Repository.QuoteRepository
import com.devst.a13room.Vm.MainVm

//inegrate room and api service as different daa soce


class MainActivity : AppCompatActivity() {

    //ab hr class ko herichy se oaram paas
    lateinit var mainVm: MainVm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service=RetrofitHelper.getInstance().create(QuoteService::class.java)

        val repository=QuoteRepository(service)
        mainVm=ViewModelProvider(this,MainVmProviders(repository)).get(MainVm::class.java)

        //it has the data that it feched from repo
        mainVm.quotes.observe(this, Observer {
            Log.d("Data Api","{${it.results.toString()}")
        })

    }
}