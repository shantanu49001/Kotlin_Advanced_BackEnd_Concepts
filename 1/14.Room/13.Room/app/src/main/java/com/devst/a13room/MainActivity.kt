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


//now we are just adding thr fucntonality of offline and online
//saving the responses of api to a db


//connectivyt ke liye bas data sorce swap krne hinge to wo repo me hi higa



//work manager->app run ho ya nhi ye kaam chale bg me[15 min restriction]
//work manager use br -->br ko hum peroidic bansakte hai if we want
//or we can just define it live evertime
//regitser service on oncreate and deregter on indestroy


//error handling in mvvm: loading(when we make a request ) sucess eroor




class MainActivity : AppCompatActivity() {

    lateinit var mainVm: MainVm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  val service=RetrofitHelper.getInstance().create(QuoteService::class.java)

        //db ref -->we must acess th e reposit from n activites anf dfregments so db instance idhar nhi karege
    /*
      APPLICATION SE GLOBALLY REPO MILEGI
      val repository=QuoteRepository(service,)
        mainVm=ViewModelProvider(this,MainVmProviders(repository)).get(MainVm::class.java)
*/

        val repository=(application as QuoteApplication).quoteRepository
        mainVm=ViewModelProvider(this,MainVmProviders(repository)).get(MainVm::class.java)
        mainVm.quotes.observe(this, Observer {
            Log.d("Data Api","{${it.results.toString()}")
        })

    }
}