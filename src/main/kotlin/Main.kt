import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val logger = KotlinLogging.logger {}
fun main() = runBlocking {
    newCoroutineScope()
    logger.info { "World!" }
}

suspend fun newCoroutineScope() = coroutineScope {
    launch {
        delay(1000L)
        logger.info { "Team" }
    }
    launch {
        delay(2000L)
        logger.info { "Identity" }
    }
    logger.info { "Hello" }
}