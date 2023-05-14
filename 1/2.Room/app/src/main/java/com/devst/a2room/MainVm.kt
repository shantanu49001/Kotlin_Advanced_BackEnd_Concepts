package com.devst.a2room

import androidx.lifecycle.ViewModel

//all data comes here   --->only data vie se lena dena nhi
class MainVm:ViewModel() {
    var count:Int=0

    fun incement(){
        count++
    }
}