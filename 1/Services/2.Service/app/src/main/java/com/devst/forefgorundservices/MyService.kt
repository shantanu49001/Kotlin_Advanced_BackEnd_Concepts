package com.devst.forefgorundservices

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MyService : Service() {
    private val CHANNEL_ID = "1000"  //for notification[pack of indiv notif setting it's props]
    private var player: MediaPlayer? = null
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {  //oncreate of service[whenever work manager creates ut based on sheduling]

        super.onCreate()

        //my appis deault 26 wrap it insde
        createChannel()   //for notification for higher version channel required hai directly nhi hota

        //direct notofi in lower versions
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Service running")
            .setContentText("APP NAME")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .build()

        //service start by startforeground service[in actiit ]-->this must get called within 5 sec no delay needed
        startForeground(1, notification)  //inbuilt

    }

    //button click -->function send service on create of service class we showed notif based on build verson and after that onstart commad execures service

    //when we called start service and passed the intent as param it is aceepted here
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         Toast.makeText(this,"Service ceated",Toast.LENGTH_SHORT).show()
        //just thr bg work -->if it is main haevy use coruitnes to shift to bg

        // return super.onStartCommand(intent, flags, startId)
        if (intent?.extras?.containsKey(ACTION_KEY) == true) {
            val action = MusicAction.valueOf(intent.getStringExtra(PLAY_KEY)!!)
            if (action == MusicAction.STOP) {
                player?.stop()
            }
        } else if (intent?.extras?.containsKey(PLAY_KEY) == true) {
            val play = MusicPlay.valueOf(intent.getStringExtra(PLAY_KEY)!!)
            playMusic(play)

        }
        return START_STICKY  //restart service if it gets killed by any reson

    }


    private fun playMusic(play: MusicPlay) {
        val uri = when (play) {
            MusicPlay.ALARM -> {
                Settings.System.ALARM_ALERT
            }
            else -> {
                Settings.System.RINGTONE
            }
        }
        if (player?.isPlaying == true) {
            player?.stop()
        }           //change hui button gar ato pehle eala music stop
        player = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        player?.isLooping = true  //just service saath play hota rhe
        player?.start()


    }


    //no need for onseatroy srvive here coz it is now a foreground itwon't unnecessarly run in bg
    private fun createChannel() {
        val mNotificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val name = "APPNAME"
        val description = "Service example "
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
        mChannel.description = description
        mChannel.setShowBadge(false)  //. on minising top bar
        mChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        mNotificationManager.createNotificationChannel(mChannel)

    }
}