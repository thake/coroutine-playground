package part2

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private val logger = KotlinLogging.logger {  }
fun main(): Unit = runBlocking {
    logger.info { "Hello from blocking" }
    val waitFor = launch(Dispatchers.Default) {
        logger.info { "Hello from Default" }
        delay(1000L)
        logger.info { "aftert delay" }
    }
    waitFor.join()
    delay(500L)
    logger.info { "Hello again from blocking" }
}