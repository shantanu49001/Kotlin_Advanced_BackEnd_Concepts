package com.devst.a5reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.devst.a5reciver.databinding.ActivityMainBinding
import java.io.File

//till now -->plain repaeting services[deprecated]
//fg service [that ntofies]
//br[sustem changes minitor]
//lbr[how it uses br inreciver bebfit to be main safe to snt data betwen service to br and br classifies it sets the text through aaumous classs

//here intent service-->improved service
//since service me hume br implment krke scope change krna pad rha tha
//now this service is by defalut bacgrund scoped
//requests are also queueable

//button se data fetch -->br reciver on recive sent -->ui upadte karega
//using local br
//earlier jab service heavy work krte tha to br ke functio ko async krna ad rga tha
//here there is no need of that

//all the limitation of fgs also apply here
const val ACTION_DOWNLOAD="ACTION_DOWNLOAD_URL"
const val FILE_NAME_KEY="FILE_NAME_KEY"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    //service to ui
    //acessing the anumous br -->that hanles the rigger-->dos the ui owrk on loacl br triggers
    //3.local br used br class trigger captured hee
    private val broadcastReceiver=object :BroadcastReceiver(){
        override fun onReceive(p0: Context?, intent: Intent?) {
            when(intent?.action){
                ACTION_DOWNLOAD->{//classify the intent
                    val fileNmae=intent.getStringExtra(FILE_NAME_KEY)
                    fileNmae?.let {
                        binding.data.text=File(this@MainActivity.filesDir,fileNmae).readText()

                    }

                }
            }
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //regitser the lbr     classify the intent it montirs

        //1.service all
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, IntentFilter(
            ACTION_DOWNLOAD))
        binding.buttongoogle.setOnClickListener {
            startIntentService("https://www.google.com/","google.txt")
        }

        binding.buttonfacebook.setOnClickListener {
            startIntentService("https://www.facebook.com/","facebook.txt")
        }

    }

    private fun startIntentService(url:String,file:String){


        //ui to service intent
        Intent(this,MyIntentService::class.java).apply {
            action= ACTION_DOWNLOAD
            data= Uri.parse(url)
            putExtra(FILE_NAME_KEY,file)
            startService(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }
}