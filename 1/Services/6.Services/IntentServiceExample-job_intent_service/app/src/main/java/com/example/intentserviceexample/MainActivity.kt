package com.example.intentserviceexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File



const val ACTION_DOWNLOAD_URL = "ACTION_DOWNLOAD_URL"
const val FILE_NAME_KEY = "FILE_NAME_KEY"


class MainActivity : AppCompatActivity() {

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            when (intent?.action) {
                ACTION_DOWNLOAD_URL -> {  //when data us sent from servve to loca; br -->this is captured here
                    val fileName = intent.getStringExtra(FILE_NAME_KEY)
                    fileName?.let{
                        text_data.text = File(this@MainActivity.filesDir, fileName).readText()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadcastReceiver, IntentFilter(ACTION_DOWNLOAD_URL))  //local br works on this intent

        download_google.setOnClickListener{
            enqueueJobIntentService("https://www.google.com/", "google.txt")
        }

        download_facebook.setOnClickListener{
            enqueueJobIntentService("https://www.facebook.com/", "facebook.txt")
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(broadcastReceiver)
    }



    private fun enqueueJobIntentService(url: String, fileName: String){
        Intent(this, MyJobIntentService::class.java).apply {
            action = ACTION_DOWNLOAD_URL
            data = Uri.parse(url)
            putExtra(FILE_NAME_KEY, fileName)

            //companio n class ke function ko acess kara
            MyJobIntentService.enqueueWork(applicationContext, this)        //method to call job intent
        }
    }
}
