package com.devst.a2room

import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

//1.ViewModel is also like a observer
//2.activity is the lifecyle owner
//3.ViewModel-->ek particualr activty ke liye model
//4.usme sirf data jo activity me aaa hai ahi hoga
//5.viewmoel destroys nly when activity gets parmanntly destroyed
///6.finish hone pr hi vm destroy
//7.vm is just like activity specific model/data class
//8.we need dependency


//now we need the use of factoryVm
//whener we passed argument to constructor-->counter zero se na start hoke  pehle ji va;ue se hua wahan se start
//aagctory hume vm models banake deta hai based on custom arguments

class MainActivity : AppCompatActivity() {
    //activity gets recreted from here not from oncrete
//var count:Int=0  //eahc tome counter =0 se start-->in vm now
    lateinit var vm: MainVm  //without factoy
    lateinit var vmFactory: MainVmWithFactory  //vm with factory
    lateinit var button: Button
    lateinit var textCounter: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //lf owner      lc observer--->inbuilt
        vm = ViewModelProvider(this).get(MainVm::class.java)
        //this time provoider also needs the factory
        vmFactory = ViewModelProvider(this, MainVmFactory(10)).get(MainVmWithFactory::class.java)
        textCounter = findViewById(R.text_incr)
        button = findViewById(R.id.button)

        setText()   //called on oncrate-->lideculer dependent

    }

    private fun setText() {
        //   textCounter.text=count.toString()   //text view gets re creaed
        textCounter.text = vm.count.toString()
    }

    fun increment(v: View) {
        vm.incement()
        setText()
    }

}