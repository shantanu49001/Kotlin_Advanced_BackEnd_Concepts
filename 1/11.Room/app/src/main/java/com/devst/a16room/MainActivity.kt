package com.devst.a16room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.RecoverySystem
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)=findViewById<RecyclerView>(R.id.rv)


        val adapter=ProgramingApater
        val p1=PrgrammingItem("1","Java")
        val p1=PrgrammingItem("2","Java")

        //ab adater apne aap hi krta hai comapre and update if required
        adapter.submitist(listOf(p1,p2,p3))

        //habler-->allows kuch second baad main pr kuch work
        Handler(Looper.getMainLooper()).postDelayed(Runnable{
            //form new lit and submit to the adapter
        },4000)
    }
}