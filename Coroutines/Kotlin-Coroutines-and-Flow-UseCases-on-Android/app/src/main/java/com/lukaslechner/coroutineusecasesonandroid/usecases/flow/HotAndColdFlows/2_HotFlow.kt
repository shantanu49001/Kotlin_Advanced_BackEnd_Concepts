package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.HotAndColdFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

fun main() {
    val sharedFlow = MutableSharedFlow<Int>()
    val scope = CoroutineScope(Dispatchers.Default)

    scope.launch {
        repeat(5) {
            println("ShreadFlow emits $it")
            sharedFlow.emit(it)//suspend function
            delay(200)
        }

    }
    Thread.sleep(1500)
}
//no collector so far
