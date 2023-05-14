package com.devst.a6room

import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.devst.a6room.databinding.ActivityMainBinding

//binding adapters
//part of data binding only


//earlier wr were just adding the data how what is we wan to add a logic o that as well
//here comes the role of the binding adapters

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
      //  binding= ActivityMainBinding.inflate(layoutInflater)
      //  val view=binding.root
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
       val post=Post("nnxnx","chexx","https://cdn.mos.cms.futurecdn.net/MT9a34g6tT7F3uDCS3BxCb-1280-80.jpg.webp")

        binding.post=post  //setting var of data b file


    }
}