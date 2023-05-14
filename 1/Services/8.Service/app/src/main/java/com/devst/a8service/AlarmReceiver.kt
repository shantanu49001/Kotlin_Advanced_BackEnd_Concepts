package com.devst.a8service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver:BroadcastReceiver() {

    //just captures the alrm
    override fun onReceive(context: Context?, intent: Intent?) {
        //main ui thread-->pr work
        //work to do when alrm triggered


        val i=  Intent(context,DestinationActivity::class.java)
        intent!!.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent=PendingIntent.getActivity(context,0,i,0)

        val builder=NotificationCompat.Builder(context!!,"100")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("xxxxx")
            .setContentText("naughty america")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val ntoficationManager=NotificationManagerCompat.from(context)
        ntoficationManager.notify(123,builder.build())


    }

}