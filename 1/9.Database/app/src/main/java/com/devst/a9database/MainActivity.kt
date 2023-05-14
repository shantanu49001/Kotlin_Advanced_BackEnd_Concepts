package com.devst.a9database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//REPOSITOYY PATTERN


/*
* repository pattern isn't android specific
* it is a common design pattern for desinginf any application
* reusage solutoj to design a good archetetcure for the applicatipn
* repositry connects different data sources one of which is room
* it also hanldes req of virmodel  to send the req data to it
* activity frgam  [observes -->] viewmodel but this vm also re data from a source
* earlier hum vm me hi data rakh rhe the
* we have t choices either connect api to vm or room db to vm
* by holding ref to botg the sources
* but thsi vm also now does more tha one task -->bring owner to data of actvity and interacting with the data sourcs
* to remove t excess work on vm me make reposiity whose sole task it is fetch connect differnt dat sources
* activyt/frag-->vm==>repo -->api/room
* all the vm acess data from repo and repo hanled the data sourves and data fetch ing work if of room and api
*api se data roo db me cache bhi repo hi krwata ha so that offilen case me local room se data return

*types of archeture patterns
* need : scalibilty and mainrtinlty of the application  new feature add krne me zyada changes na krne pade
* data base layer ka change sirf databse layer tak hi rahe
* mvc mvp mvvm-->work on sepeation of concern [jo kaam jiska hai wo ho kare ] and unit testing[]
*based on application we decide which on =e tio use
* mvc -->model view controller-->three layers xml =view activiy fragment =contrioller dataclass =model
* mvp=>model view presenter-->view mw layouts +activty+frrgament  presenter->simple class prsetation logic   model->data classes
* mvvm=>presenter brcomes he vm in previous
* */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}