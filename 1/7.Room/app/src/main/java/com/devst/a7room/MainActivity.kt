package com.devst.a7room

//previous versions of db used seprate functon for each class
//and to retrive it used cursors to iterate over the tables

//it requires a very much boiler plate code
//room is a abstraction over the sql db

/*  CONTENS OF DB
whenever we make a entty think it as a table
* Entities[Tables]-->data class hi table ho =ti hai the ultimte class that stores the redundant data-->primary ley is any filed that is unique in th entitty
* Dao(Data Acess Objects)-->main sql functions to and methds to define and acess the entity data
* Db
* Type converters
* Migrations-->for making changs to the db that is already deployed in a live project
*
*

* */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //hr baar object apne khudka db bna sakte hau coz we didn't made it inglton
        database =
            Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "ContactDb")
                .build()

        //agar aise karega to nhi chalega coz function suspend tha and we r running it on main thread-->function se start kiya karo to understan it-->ya to ye coroutine se ya fir koi aur suspend function se hi chalega bascally non main enviornment se
//        database.contactDao().insertConact(Contact(0,"John","99999"))


        //room db non main me isliye chalta hai kabhi kabhi ho sajta hai db nammile t? ui nhi rukna chahye
        GlobalScope.launch {
            database.contactDao().insertConact(Contact(0, "John", "99999"))

        }


        //actovoty os the observer db is the sorce /onwer o data

        //sice it returned a liev data and the function retuning the live data is bg by default iso suspend se cal nhi krna hai
        fun getData(view: View) {
            database.contactDao().getContact().observe(this, Observer {
                Log.d("Cheezy Code", "{${it.toString()}")
            })

        }

    }
}