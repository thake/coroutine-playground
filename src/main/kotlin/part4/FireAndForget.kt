package part4

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val logger = KotlinLogging.logger {}

fun main() = runBlocking {
    globalScope()
    newScope()
    logger.info { "Finished runBlocking scope" }
}
suspend fun globalScope() = GlobalScope.launch {
    logger.info { "Starting global scope background process" }
    delay(2000L)
    logger.info { "Finished global scope background process" }
    throw RuntimeException()
}
suspend fun newScope() = CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
   logger.info { "Starting new scope background process" }
   delay(5000L)
   logger.info { "Finished new scope background process" }
    throw RuntimeException()
}