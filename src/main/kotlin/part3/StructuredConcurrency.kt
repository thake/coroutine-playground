package part3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    try {
        scoped()
    } catch (e: Exception) {
        println("Caught exception")
    }
    println("After scope")
}
suspend fun scoped() = coroutineScope {
    println("Created new coroutineScope")
    launch {
        delay(1000L)
        println("1st from coroutineScope")
    }
    launch {
        delay(1000L)
        println("2nd from coroutineScope")
    }
    println("Ended coroutineScope")
}