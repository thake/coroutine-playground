package part1

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

private val logger = KotlinLogging.logger{}
@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun main(): Unit = runBlocking {
    logger.info { "Hello from blocking" }
    launch {
        logAndDelay("Inherited from parent")
    }
    launch(Dispatchers.Default) {
        logAndDelay("Default")
    }
    launch (newSingleThreadContext("Special Thread")){
        logAndDelay("Special Thread")
    }
    val unconfined = launch(Dispatchers.Unconfined) {
        logAndDelay("Unconfined")
    }
    unconfined.join()
    val asyncResult = async {
        logAndDelay("Inherited from parent (async)")
        42L
    }
    val result = asyncResult.await()
    logger.info { "Async result is $result" }
    //launch(Dispatchers.IO) {
    //    logAndDelay("IO")
    //}
}
suspend fun logAndDelay(dispatcherName: String) {
    logger.info { "Hello from $dispatcherName" }
    delay(1000L)
    logger.info { "Hello again from $dispatcherName after delay" }
}