package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.Concurrency

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

suspend fun main() = coroutineScope {
    val flow = flow {
        repeat(5) {
            val index=it+1
            println("Emitter: Start Cooking Pancake $index")
            kotlinx.coroutines.delay(100)
            println("Emitter : pancake $index is ready")
            emit(index)
        }
    }.buffer(1, onBufferOverflow = BufferOverflow.SUSPEND)   //params -->caacity and onBufferOverlflow
    //drop oldest drop recent etc

    //now the collector collects from buffer rather than the emitter directly
    //emitter send it to buffer and not to collector idependently


    flow.collect {
        println("Collector : Starts eating pancake $it")
        delay(300)
        println("Collecter : Finished eating pancake $it")
    }
}