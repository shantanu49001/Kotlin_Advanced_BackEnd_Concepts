package com.devst.a2room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainVmFactory(val counter:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        //now we make instance of factry now in activty not of vm
        return MainVmWithFactory(counter) as T

    }
}