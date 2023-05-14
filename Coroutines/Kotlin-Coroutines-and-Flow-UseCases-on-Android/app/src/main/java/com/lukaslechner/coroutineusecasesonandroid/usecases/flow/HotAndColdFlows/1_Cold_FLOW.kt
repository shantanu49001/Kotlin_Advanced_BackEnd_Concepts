package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.HotAndColdFlows

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

fun coldFlow()= flow {
    println("Emitting 1")
    emit(1)
    kotlinx.coroutines.delay(1000)
    println("Emitting 2")
    emit(2)
    kotlinx.coroutines.delay(1000)
    println("Emitting 3")
    emit(3)

}

suspend fun main():Unit= coroutineScope {   //property 1
    coldFlow()
        .collect{
            println("Collector 1 collects $it")
        }

    //prop 2
    var job=launch {
        coldFlow()
            .onCompletion {
                println("Flow of Collector 1 completed")
            }
            .collect{
                println("Collector 1 collects")
            }
    }
    delay(1000)
    job.cancelAndJoin()   //wait till all in cr get finsihed


    //property 3
    launch {
        coldFlow()
            .collect{
                println("Collector 1 collects: $it")
            }
    }
    launch {
        coldFlow()
            .collect{
                println("Collector 2 collects: $it")
            }
    }



}

