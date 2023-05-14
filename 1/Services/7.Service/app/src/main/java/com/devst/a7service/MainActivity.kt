package com.devst.a7service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

//br me bas intents sense krne hote hai sent by local br
//scheudle a bg task -->br system event handle kr eha tha and service events handle kr rha tha
//this time we are scheduling the tasks

//genrally these type of servie are saved into application class oncraet and onstart functons-->coz application app destro y hone pr hi hogi khatam
//

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runWorjer()
    }

    private fun runWorjer() {
                                              //class conating bg work
        val work= OneTimeWorkRequest.Builder(EmployeeWorkReq::class.java).build()

        //work manager application mecraeted hai
        WorkManager.getInstance(this).enqueue(work)
    }
}