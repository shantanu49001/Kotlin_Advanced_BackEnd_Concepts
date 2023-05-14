package com.example.intentserviceexample

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.File


class MyJobIntentService : JobIntentService() {

    companion object {
        private const val JOB_ID = 1000

        fun enqueueWork(context: Context, work: Intent) {//from main actovoty
            enqueueWork(//job intent me wok add kr rhe hau
                context,
                MyJobIntentService::class.java,
                JOB_ID,
                work
            )   //main functio of jb inteny work is the intent sent
        }
    }

    override fun onHandleWork(intent: Intent) {//main function that handles bg work
        if (intent.action == ACTION_DOWNLOAD_URL) {//classify the intent type
            val url = intent.dataString   //bg work
            val fileName = intent.getStringExtra(FILE_NAME_KEY)

            if (url != null && fileName != null) {
                val content = getDataFromNetwork(url)

                File(this.filesDir, fileName).writeText(content)

                Intent(ACTION_DOWNLOAD_URL).also {
                    it.putExtra(FILE_NAME_KEY, fileName)     //sending the data to locab br manager
                    LocalBroadcastManager.getInstance(this).sendBroadcast(it)
                }
            }
        }
    }

}