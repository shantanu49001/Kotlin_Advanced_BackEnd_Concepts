package com.devst.a5reciver

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.File

class MyIntentService:IntentService("MyIntentService") {
    //ib
    override fun onHandleIntent(intent: Intent?) {
        //deafult me bg htread

       //JUST TO CLASSIFY THE INTENT
        if (intent?.action==ACTION_DOWNLOAD){
           //work to do in bg-->quueable work
            val url=intent?.dataString
            val fileName=intent?.getStringExtra(FILE_NAME_KEY)
            if (url!=null&&fileName!=null){
                val content= getDtaFromNetwork(url)

                File(this.filesDir,fileName).writeText(content)
                //2.after bg work service sends t lbr
                Intent(ACTION_DOWNLOAD).also{
                    it.putExtra(FILE_NAME_KEY,fileName)
                    //after bg work serach for local br to notifu changes
                    LocalBroadcastManager.getInstance(this).sendBroadcast(it)
                }
            }
        }
    }

}