package com.devst.a7service

import android.app.Application
import androidx.work.*
import java.util.concurrent.TimeUnit

//applicatio crate -->create jab tak poora app deto higa tab tak live rahega
class EmplyeeApplication:Application() {
    override fun onCreate() {
        super.onCreate()



        //work manager req bg task constaints
        //the worker class

        //whn do to the task
        val constaints= Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)//any of these become true
            .setRequiresCharging(true)
            .build()


        //add the bg work class
        val mywork=PeriodicWorkRequest.Builder(EmployeeWorkReq::class.java,10,TimeUnit.SECONDS)
            .setConstraints(constaints)
                //id it wad not able to do the task at t=2 --> next 4 6 8 pr try karega doabar
            .setBackoffCriteria(BackoffPolicy.LINEAR,PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,TimeUnit.SECONDS)
            .build()

        //just to differtiatte the work
        WorkManager.getInstance(this).enqueueUniquePeriodicWork("EmployeeOfTheDay",ExistingPeriodicWorkPolicy.KEEP,mywork)

    }
}