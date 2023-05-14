package com.devst.a1service

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devst.a1service.databinding.ActivityMainBinding

//4 ways to start a app-> activty,service,br,content provider
//service: we schedule a work in our app to run in rgular intervals
//br:these are the events roadcasted by our system to which our app subscribes

//all these 4 must be specified in the manifest so that our system knows about them
//out of these only activity has the ui


//services are the tasks that don't require user interaction hence are not visible to the user
//servies run on main thread by deafult so if any heavy tasks is invloved in the services we need to offload it to the bg thread
//single instance only


//just to differntiate between different services since service class will be single instance

//app miniise -->to bhi music play ho rha hai -->service run krti hai bg me
//app close krne pr nhi sto-->context destroy
//stop sevice call krne pr hi destro


//mimimise-->run hota hai
//

const val ACTION_KEY="ACTION_KEY"
enum class MusicAction{
    STOP
}
const val PLAY_KEY="PLAY_KEY"
enum class MusicPlay{
    RINGTONE,
    ALARM
}

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)


        //service  crated on on create
        binding.PLAYRINGTONE.setOnClickListener {
            startService(PLAY_KEY,MusicPlay.RINGTONE.name)
        }
        binding.PLAYALARM.setOnClickListener {
           startService(PLAY_KEY,MusicPlay.ALARM.name)
        }
        binding.stopPlaying.setOnClickListener {
            startService(ACTION_KEY,MusicAction.STOP.name)
        }


        //here the service function is called
        binding.stopService.setOnClickListener {
            stopService(Intent(this,MyService::class.java))
        }


    }

    //this is my function
    private fun startService(key:String,value:String){
        //object -->intent object passed to the start service                                     //content this return type context obj
        startService(Intent(this,MyService::class.java).apply {
            //obj return tyoe hai usme prop add  intent obj me add ho rha hai key value pair
            putExtra(key,value)

        })
    }
}