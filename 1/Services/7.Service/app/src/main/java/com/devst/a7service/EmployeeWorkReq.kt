package com.devst.a7service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class EmployeeWorkReq(ctx:Context,params:WorkerParameters):Worker(ctx,params) {
    override fun doWork(): Result {
        //bg work karta hai-->non ui hread by deafult

        //any bg work
        if (runAttemptCount>5){//workr can't run
            return Result.failure()
        }
        try {
            val name="ABC"
            showNotif(name)
        }catch (e:java.lang.Exception){
            return Result.retry()
        }
        return Result.success()

    }

    private fun showNotif(name:String){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            createChannel() //sirf idhar hi req hai
        }
        val intent=Intent(applicationContext,MainActivity::class.java)
        val pendingIntent:PendingIntent=PendingIntent.getActivity(applicationContext,1000,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val builder=NotificationCompat.Builder(applicationContext,"10")
            .setContentText("WorkManager")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)){
            notify(100,builder.build())
        }
    }
    private fun createChannel(){
        val notificatificationManager=applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val name:CharSequence="Employee "
        val description="cvcv"
        val importance=NotificationManager.IMPORTANCE_DEFAULT
        val channel=NotificationChannel("10",name,importance)
            channel.description=description
            channel.setShowBadge(false)
        notificatificationManager.createNotificationChannel(channel)

    }

}