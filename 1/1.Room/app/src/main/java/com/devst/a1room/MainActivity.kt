package com.devst.a1room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//componets include activity,frgament,etc that are not managed by us bu by the android os
//we write a part of code that needs to get execufed within the scope f te comenets

//acitty ka kaam ui dikhanau wo activty kare bas
//data dikahne ka kaam sources ka hai wo ui files me hina hi nhi chahiye

//if data componets are se[erate then it doesn't depend on ui lifecycles
//seperation of concerns
//data is the priority everything must happen based on the data -->application mjst be data driven


//conatains everything
//jetpack os the collection of classified lbaraies-->ui achetecture foundation  behaviour

//serach jetpackk libaraies to get info abput any builds etc

//lifecyle aware compenets -->  taks in bg stop resue based on activity-->lifecyel owner and observer are two parts
//owners-->activity frgaments
//observer -->netween data and screen that fetches data based on event of activity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(Observer())
    }
}