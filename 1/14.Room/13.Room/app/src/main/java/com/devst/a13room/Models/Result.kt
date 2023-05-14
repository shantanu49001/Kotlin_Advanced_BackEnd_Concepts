package com.devst.a13room.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote") //convention
data class Result(
    @PrimaryKey(autoGenerate = true)
    val quoteid:Int,  //we added extra coz we didn't knew _id will followe or not
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,

)