package com.example.serviceexample
//HERE BR IS MONITORING TH SERVIVE STATTUS
//  SERVICE   --->BR<-----UI
//very very simple like propvius fg service is called inside the service types we send the string to a function that gets  the instance of local br created in main activty
//flow-->ui -->service-->local br intent set-->main activty me anynmous objec se data set
//local br -->dunamicaaly create hua isne br ka object sluye liya coz uska funcion min ui ka caess tha uske paas

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*
const val ACTION_KEY = "ACTION_KEY"
enum class MusicAction{
    STOP
}

const val PLAY_KEY = "PLAY_KEY"
enum class MusicPlay{
    RINGTONE,
    ALARM
}

const val BROADCAST_ACTION_PLAY_STATUS = "BROADCAST_ACTION_PLAY_STATUS"
const val BROADCAST_ACTION_PLAY_MESSAGE = "BROADCAST_ACTION_PLAY_MESSAGE"

class MainActivity : AppCompatActivity() {

    //2.PREVOUS BRADCAST CKASS KO ANAONUMOUSLY ETEND
    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {//on main ui
            when (intent?.action) {//CHANGING THE ON RECIVE METHOD
                BROADCAST_ACTION_PLAY_STATUS -> play_status.text =
                    //first firld is to sene the local br that just requires ui text as there might be many
                    getString(R.string.play_status_text, intent.getStringExtra(BROADCAST_ACTION_PLAY_MESSAGE))

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1.cretes lbr -->requires intent and br obj-->this onj is he eciver od local br
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadcastReceiver, IntentFilter(BROADCAST_ACTION_PLAY_STATUS))//this is sent just of different te the local br


        play_ringtone.setOnClickListener{
            ContextCompat.startForegroundService(this, Intent(this, MyService::class.java).apply {
                putExtra(PLAY_KEY, MusicPlay.RINGTONE.name)
               Log.d("test1","1")
            })
        }

        play_alarm.setOnClickListener{
            ContextCompat.startForegroundService(this, Intent(this, MyService::class.java).apply {
                putExtra(PLAY_KEY, MusicPlay.ALARM.name)
            })
        }

        stop_playing.setOnClickListener{
            ContextCompat.startForegroundService(this, Intent(this, MyService::class.java).apply {
                putExtra(ACTION_KEY, MusicAction.STOP.name)
            })
        }

        stop_service.setOnClickListener{
            stopService(Intent(this, MyService::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//unregistre the local br tat was using br clas
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(broadcastReceiver)
    }
}
