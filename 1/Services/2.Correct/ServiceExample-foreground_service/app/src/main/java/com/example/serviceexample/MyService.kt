package com.example.serviceexample

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat


class MyService: Service(){
    private val CHANNEL_ID = "1000"
    private val CHANNEL_NAME = ""

    private var player: MediaPlayer? = null

    //For bound service
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        // API 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.my_service_running))
            .setContentText(getString(R.string.app_name))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .build()
        //needs to be called within 5 seconds of call to foreground service
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
        val uri = when(action){
            MusicPlay.ALARM -> {
                Settings.System.DEFAULT_ALARM_ALERT_URI
            }
            else -> {
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


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val mNotificationManager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val name: CharSequence = getString(R.string.app_name)
        // The user-visible name of the channel
        // The user-visible description of the channel
        val description = "Service example notification channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(
            CHANNEL_ID,
            name,
            importance
        )
        // Configure the notification channel.
        mChannel.description = description
        mChannel.setShowBadge(false)
        mChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        mNotificationManager.createNotificationChannel(mChannel)
    }
}