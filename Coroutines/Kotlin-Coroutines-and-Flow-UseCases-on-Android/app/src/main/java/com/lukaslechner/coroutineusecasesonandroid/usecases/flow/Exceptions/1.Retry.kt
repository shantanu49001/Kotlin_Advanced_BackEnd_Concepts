package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.Exceptions

import com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionhandling.NetworkException
import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

suspend fun main(): Unit = coroutineScope {
    launch {
        stocksFlow()
            .catch {
                println("Handle Exception in catcg() operator $this")
            }
            .collect {
                println("Collected $it")
            }
    }
}

private fun stocksFlow(): kotlinx.coroutines.flow.Flow<String> =
    flow { //firt 4 times ke liye run hona chahiye
        repeat(5) { index ->
            delay(1000)
            if (index < 4) {
                emit("New stock data")
            } else {
                throw NetworkException("Newtowrk fialsed")   //5 se excetion throw honi changiye
            }
        }
    }.retry {
        println("Enter retry with $it")
        it is NetworkException  //restrt flow collection
    }

class NetworkException(message: String) : Exception(message)