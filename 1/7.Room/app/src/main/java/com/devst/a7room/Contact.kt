package com.devst.a7room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")    //now it will be called by this naname only
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id:Long,  //auto genrated
    val name:String,
    val phone:String
)
