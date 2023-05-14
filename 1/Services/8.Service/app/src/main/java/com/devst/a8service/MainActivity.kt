package com.devst.a8service

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devst.a8service.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar
import kotlin.system.measureNanoTime

//alarm manager-> work manager se bg work periodeic ho rha th abu tni t at given time instant
//deosn't matter app is unning or not-->firebase remote nitification-->api updation etc
//
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        crateNotficationChannel()

        binding.buttonselectime.setOnClickListener {
            showtimepicker()
        }
        binding.buttonsetalarm.setOnClickListener {
            setAlrm()
        }
        binding.Cancelalarm.setOnClickListener {
            vacnel()
        }

    }

    private fun vacnel() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        //isme br added hai
        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.cancel(pendingIntent)

    }

    private fun setAlrm() {
        //main thing is here
        //baaki pehle wala hi hai
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intnt = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setRepeating(,)//-->time add krdo frm icker

    }

    private fun showtimepicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select a time")
            .build()
        picker.show(supportFragmentManager, "time")
        picker.addOnPositiveButtonClickListener {
            if (picker.hour > 12) {
                binding.textTime.text = String.format("${picker.hour}" + ":" + "{${picker.minute}")

            } else {
                String.format("{${picker.hour}" + ":" + "${picker.minute}")

            }

        }


    }

    private fun crateNotficationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "cccc"
            val description = "vvmvm"
            val importsance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("fff", name, importsance)
            channel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

        }

    }
}