package com.example.serviceexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play_ringtone.setOnClickListener{
            startForegroundService(PLAY_KEY, MusicPlay.RINGTONE.name)
        }

        play_alarm.setOnClickListener{
            startForegroundService(PLAY_KEY, MusicPlay.ALARM.name)
        }

        stop_playing.setOnClickListener{
            startForegroundService(ACTION_KEY, MusicAction.STOP.name)
        }

        stop_service.setOnClickListener{
            stopService(Intent(this, MyService::class.java))
        }
    }

    private fun startForegroundService(key: String, value: String){
        ContextCompat.startForegroundService(this, Intent(this, MyService::class.java).apply {
            putExtra(key, value)
        })
    }
}
