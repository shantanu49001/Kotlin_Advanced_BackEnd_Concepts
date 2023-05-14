package com.devst.a1room

import android.media.metrics.Event
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

//now i can seperatly use thus wirh any activyty any fragment
//lifecyler observer
class Observer:LifecycleObserver {
    //observes the lifecyler owner


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
      Log.d("onCre","")
    }

    //now obsever is ready
    //ab owner ko bolna padega ki tum apna updates observer ko dete rehna
}
