package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.Channels

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main():Unit= coroutineScope {
    val channel=produce<Int> {
        println("Sending 10")
        send(10)

        println("Sending 23")
        send(23)
    }

    channel.consumeEach {
        println("Consumed $it")
    }
}