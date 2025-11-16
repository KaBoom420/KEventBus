package com.kaboom.eventbus

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.concurrent.Volatile

object EventBus {

    @PublishedApi
    internal val defaultScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _events = MutableSharedFlow<Any>(
        replay = 0,
        extraBufferCapacity = 64
    )

    val events: SharedFlow<Any> = _events.asSharedFlow()

    @Volatile
    private var loggingEnabled = false

    @Volatile
    private var customLogger: ((String) -> Unit)? = null


    fun enableLogging(enable: Boolean, logger: ((String) -> Unit)? = null) {
        loggingEnabled = enable
        customLogger = logger
        log("Logging ${if (enable) "enabled" else "disabled"}")
    }


    fun post(event: Any) = defaultScope.launch {
        log("Post → ${event::class.simpleName}")
        _events.emit(event)
    }


    inline fun <reified T> on(scope: CoroutineScope? = null, noinline onEvent: suspend (T) -> Unit): Job
    = (scope ?: defaultScope).launch {
        events.filterIsInstance<T>().collect { onEvent(it) }
    }


    fun clear() {
        defaultScope.coroutineContext.cancelChildren()
        log("Cleared all subscriptions")
    }


    private fun log(msg: String) {
        if (!loggingEnabled) return
        customLogger?.invoke("[KEventBus] $msg") ?: println("[KEventBus] $msg")
    }
}
