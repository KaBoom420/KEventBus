package com.kaboom.eventbus

import kotlinx.coroutines.*

suspend inline fun <reified T : Any> postEvent(event: T) =
    EventBus.post(event)

inline fun <reified T : Any> on(
    scope: CoroutineScope? = null,
    noinline block: suspend (T) -> Unit
) = EventBus.on(scope, block)

fun clearEvents() = EventBus.clear()

fun setEventLogging(enable: Boolean, logger: ((String) -> Unit)? = null) =
    EventBus.enableLogging(enable, logger)
