package com.devst.a7room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "contact")    //now it will be called by this naname only
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id:Long,  //auto genrated
    val name:String,
    val phone:String,
    val createdDate:Date,   //apme aap change kr dega dat tpe var to lon in the db

   //migrations is just to notify the version chane
    val isActive:Int
    )
