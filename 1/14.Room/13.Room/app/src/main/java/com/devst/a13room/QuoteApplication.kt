package com.devst.a13room

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.devst.a13room.Api.QuoteService
import com.devst.a13room.Api.RetrofitHelper
import com.devst.a13room.Db.QuoteDb
import com.devst.a13room.Repository.QuoteRepository
import java.util.concurrent.TimeUnit


//jisse db me jo context jaye wo var ajye
//just like oncrate poora aap star hone se pehle ye exexvute hoga

//idhar hi repo me db ad service link karo


//manifest me add krna padta hai

//awalys acess the repo linking the data sorces by repo ony where data are sent to repo in appilaction file

class QuoteApplication:Application() {

    lateinit var quoteRepository: QuoteRepository   //coz db ke liye vm ko repo


    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorkwr()
    }

    private fun setupWorkwr() {
        val constaints=Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()  //run tabhi karga
        val workreq=PeriodicWorkRequest.Builder(QuoteWorker::class.java,30,TimeUnit.MINUTES)
            .setConstraints(constaints)
            .build()
        WorkManager.getInstance().enqueue(workreq)
    }

    private fun initialize(){
     val quoteService=RetrofitHelper.getInstance().create(QuoteService::class.java)

      val database=QuoteDb.getDb(applicationContext)
        quoteRepository=QuoteRepository(quoteService,database,applicationContext)
    }
}