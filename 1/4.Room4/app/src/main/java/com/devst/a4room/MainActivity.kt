package com.devst.a4room

import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
//running --->sil y eror of view
//live data concepts

//[observa"ble"]
//observable data holder class
//lifeycle aware
//live data observe emoji activity
//agar activity destroy -->wo us activity ko update nhi karega

//dependencies fr live data and vm
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button=findViewById<Button>(R.id.set)
        var text=findViewById<TextView>(R.id.show)
         var mainVm=ViewModelProvider(this).get(MainViewModel::class.java)
       //observe is the prop of object of live data that we made -->ui ko update karo jo zinda hai

      //2.live data sends noif to observer ki agar change krna chate ho to krlo

        mainVm.factsliveData.observe(this, Observer {//it get executed when data changes in live data
            //what to do when data changs
            //set the data is ld changes
           //3.this element observes the data
            text.text=it
        })

    button.setOnClickListener {//1.data chag
        mainVm.updateLiveData()    //acessing the vm to change th live data

    }


    }
}