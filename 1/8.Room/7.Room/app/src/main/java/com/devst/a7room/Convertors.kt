package com.devst.a7room

import androidx.room.TypeConverter
import java.util.Date


//storing different types of data to the data base

class Convertors {

    //as we ca't dieclty store datae in db
    @TypeConverter
    fun fromDatatoLong(value:Date):Long{
        return value.time
    }

    @TypeConverter
    fun fromLongtoDate(value:Long):Date{
        return Date(value)
    }
}