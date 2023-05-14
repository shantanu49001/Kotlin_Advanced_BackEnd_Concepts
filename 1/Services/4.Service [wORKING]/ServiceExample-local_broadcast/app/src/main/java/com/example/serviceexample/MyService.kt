package com.example.serviceexample

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class MyService: Service(){
    private val CHANNEL_ID = "1000"

    private var player: MediaPlayer? = null

    //For bound service
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("My Service Running")
            .setContentText(getString(R.string.app_name))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //service runs in Main Thread so for heavy work offload to a
        // coroutines or any other thread
        if(intent?.extras?.containsKey(ACTION_KEY) == true){
            val action = MusicAction.valueOf(intent.extras?.getString(ACTION_KEY)!!)
            if(action == MusicAction.STOP){
                player?.stop()
                // service can stop by itself
                //stopSelf()

                broadcastStatus("Stopped")  //SERVICE SENDS THIS STATUS
            }
        } else if(intent?.extras?.containsKey(PLAY_KEY) == true) {
            val play = MusicPlay.valueOf(intent.extras?.getString(PLAY_KEY)!!)
            playMusic(play)
        }

        //Restart the activity if system kills it for some reason
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        player?.stop()
        player?.release()
    }

    private fun playMusic(action: MusicPlay){
        val item: String
        val uri = when(action){
            MusicPlay.ALARM -> {
                item = "Alarm"
                Settings.System.DEFAULT_ALARM_ALERT_URI
            }
            else -> {
                item = "Ringtone"
                Settings.System.DEFAULT_RINGTONE_URI
            }
        }
        if(player?.isPlaying == true){
            player?.stop()
        }

        player = MediaPlayer.create(this,
                uri)
        player?.isLooping = true
        player?.start()
        Log.d("test1","2")
        broadcastStatus("Playing $item")//SERVIVE SENDS THIS STATUS

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val notificationManager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val name: CharSequence = getString(R.string.app_name)
        val description = "Service example notification channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(
            CHANNEL_ID,
            name,
            importance
        )
        channel.description = description
        channel.setShowBadge(false)
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        notificationManager.createNotificationChannel(channel)
    }

    private fun broadcastStatus(msg: String){
        Intent().also { intent ->            //THIS IS THE INTENT FROM SERVICE -->BR --->UI
            intent.action = BROADCAST_ACTION_PLAY_STATUS
            intent.putExtra(BROADCAST_ACTION_PLAY_MESSAGE, msg)//PREPARE THE INTENT

            //thid listenet has reciver of br usdhar ye intent paas
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)  //SEND THE INTENT AOG WITH TH LOCAL BR
            Log.d("test1","3")
        }
    }
}