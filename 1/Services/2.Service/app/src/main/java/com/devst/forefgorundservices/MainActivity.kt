package com.devst.forefgorundservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.devst.forefgorundservices.databinding.ActivityMainBinding
import java.nio.channels.spi.AbstractSelectionKey

//service was running even when we mimnimised our app
//it is using unnecessary resoirces and poorly utilized


//so there are limitations on service applied
//if any service is running in bg user must know anout it
//andorid os wants thr services to schedule on a time instant
//from android 8 we have to start the servce as foreground and not ust the service-->this was te reason why app ws crshing in devices


//when app goes in bg service is stopped after sme time
//so we have two methods to implment the service one is through foregrouund or if we are jus using service give it to work managaer that schedues it
//permmisson needed


//now also minimise krne pr bhi run hga nut user kopta hoga
//stop playing se notofi nhi remive hoga
//stop service se hoga

//download of project required !!!!1--->uske build versions use
//explaantion from here



const val ACTION_KEY="ACTION_KEY"
const val PLAY_KEY="PLAY_KEY"    //only rop level pr const val allowed hai

enum class MusicAction{
    STOP
}

enum class MusicPlay{
    RINGTONE,
    ALARM
}

class MainActivity : AppCompatActivity() {

     lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonPlay.setOnClickListener {
        startForegroundService(PLAY_KEY,MusicPlay.RINGTONE.name)
        }
        binding.buttonAlram.setOnClickListener {
        startForegroundService(PLAY_KEY,MusicPlay.ALARM.name)
        }
        binding.buttonpause.setOnClickListener {
        startForegroundService(ACTION_KEY,MusicAction.STOP.name)
        }
        binding.buttonStop.setOnClickListener {
            //service class function
         stopService(Intent(this,MyService::class.java))
        }



    }

    private fun startForegroundService(key: String,value:String){
                   //agar less hai api 26 se to normally call
                       //this cheks api and then calls accordingly
        ContextCompat.startForegroundService(this, Intent(this,MyService::class.java).apply {
            putExtra(key,value)
        })
    }


}