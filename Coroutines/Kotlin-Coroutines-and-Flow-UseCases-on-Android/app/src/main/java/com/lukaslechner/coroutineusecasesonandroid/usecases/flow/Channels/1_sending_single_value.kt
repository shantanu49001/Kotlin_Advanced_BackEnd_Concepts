package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.Channels

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main():Unit= coroutineScope {
    val deffered=async { //special jo holding a value
        delay(100)
        10
    }
    launch {->
        val result=deffered.await()//ths cr waits fr the async
        println(result)
    }
}