package com.devst.a3service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//till now we learnt how backfground service work
//how we make use of fg service in higher versions

//here we are learning br -->news about the even t
//implict -->all the apps --> system send info
//exlicity specif ti app or within the app
//but the implicit br through manifest is restructed -->app must not satrt from impict or else it will delay all the apps or call all the apps at once

//permissions br and service add krdo manifest me
//br is used with fg s
//type if event to mnitor -->iside the intent filter
//fg is used with br -->our moto is fulfilled to not run srvice innecesaryly

//running well--->this was system broadcast
//nex we will study local bc -->compennets reacting with each other

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}