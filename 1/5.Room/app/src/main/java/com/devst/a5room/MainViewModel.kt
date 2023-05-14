package com.devst.a5room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    val quoteLiveData=MutableLiveData("what you give is what u get")
    fun updateQuote(){
        //changes the data
        quoteLiveData.value="you'll see it lter"
    }
}