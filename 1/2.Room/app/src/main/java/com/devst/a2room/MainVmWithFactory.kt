package com.devst.a2room

import androidx.lifecycle.ViewModel


class MainVmWithFactory(val initialValue:Int) :ViewModel(){
var count:Int=initialValue
   fun increment(){
       count++
   }
}