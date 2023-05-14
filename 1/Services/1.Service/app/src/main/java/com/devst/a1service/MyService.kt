package com.devst.a1service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

//first time service called-->oncreate->onstart command
//next time-->onstart only called [already created ]

//service is a aplication[activity br service contentprovider] compnent -->manifest me
//enale-->instance create kr sake ha ya nhi
//exported -->other other apps ke comneonts ise acess kr sakte hai ya hi


//memebr functions->onBind   onstart command

class MyService:Service() {

    private var player: MediaPlayer? = null

    override fun onBind(p0: Intent?): IBinder? { //for the service that needs to run at any cost onvce it is binded or initilaised to the package context passed to the intent of this service class
        //-->netwrok refresh etc
        return null

    }

    //when we called start service and passed the intent as param it is aceepted here
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //just thr bg work -->if it is main haevy use coruitnes to shift to bg

        // return super.onStartCommand(intent, flags, startId)
        if (intent?.extras?.containsKey(ACTION_KEY) == true) {
            val action = MusicAction.valueOf(intent.getStringExtra(PLAY_KEY)!!)
            if (action == MusicAction.STOP) {
                player?.stop()
            }
        }

        else if (intent?.extras?.containsKey(PLAY_KEY) == true) {
                val play = MusicPlay.valueOf(intent.getStringExtra(PLAY_KEY)!!)
                playMusic(play)

            }
            return START_STICKY  //restart service if it gets killed by any reson

        }




    private fun playMusic(play: MusicPlay) {
     val uri=when(play){
         MusicPlay.ALARM->{
             Settings.System.ALARM_ALERT
         }
         else->{
             Settings.System.RINGTONE
         }
     }
        if (player?.isPlaying==true){
            player?.stop()
        }           //change hui button gar ato pehle eala music stop
        player=MediaPlayer.create(this,Settings.System.DEFAULT_ALARM_ALERT_URI)
        player?.isLooping=true  //just service saath play hota rhe
        player?.stop()


    }

    //service function
    override fun onDestroy() {
        super.onDestroy()//jab ek service band ho jaye
        player?.stop()
        player?.release()
    }
}
