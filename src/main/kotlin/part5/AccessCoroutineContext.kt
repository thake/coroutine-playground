package part5

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

private val logger = KotlinLogging.logger {  }
fun main(): Unit = runBlocking {
    withContext(MyContextElement("1234")) {
        mySuspendFunction()
    }
}
suspend fun mySuspendFunction() = coroutineScope {
    logTraceId()
    coroutineContext[MyContextElement]?.traceId = "5678"
    delay(1000L)
    logTraceId()
}
private suspend fun logTraceId() {
    val traceId = coroutineContext[MyContextElement]?.traceId
    logger.info { "Got an element from coroutine context: $traceId" }
}
private class MyContextElement(traceId: String) : AbstractCoroutineContextElement(Key) {
    var traceIdReference: AtomicReference<String> = AtomicReference(traceId)
    var traceId: String
        get() = traceIdReference.get()
        set(value) = traceIdReference.set(value)
    companion object Key : CoroutineContext.Key<MyContextElement>

}
