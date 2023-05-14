package com.devst.a4room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


//we have to make vm object here -->av bhi data element hai it should not be in activity
//mutable ld immutable ld
//mutable ld is read on;y

class MainViewModel:ViewModel() {

val factsliveData=MutableLiveData<String>("This is a fact")


    fun updateLiveData(){  //changing the data insode dat source
        factsliveData.value="Another facts"
    }
}