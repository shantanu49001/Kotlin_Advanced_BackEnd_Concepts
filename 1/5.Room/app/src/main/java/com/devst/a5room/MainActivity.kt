package com.devst.a5room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devst.a5room.databinding.ActivityMainBinding

//data binding
//support libarary of jetpack to bind ui elements with the data sources
//hr baar hum text ke text ko set kr rhe hai
//we want that jab bhi live data me changes ho wo text view me reflect ho jaye
//wihout w setting the text

//xml me add code
//we don;t need to find the view ->no memory leaks performance and memory leaks improved
//vie ko xml me hi data sorce add

//we need to convert the layout to data binding layout
//xml right click->show context view ->convert layout to data b

//intergrating live data with the view m

class MainActivity : AppCompatActivity() {
   lateinit var  binding:ActivityMainBinding   //data b se ye abhi aa jti hai
   lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //    binding= ActivityMainBinding.inflate(layoutInflater)
      binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
      mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)


        binding.mainviewmodel=mainViewModel   //layout ko var paas krdo
        binding.lifecycleOwner=this
    }


}