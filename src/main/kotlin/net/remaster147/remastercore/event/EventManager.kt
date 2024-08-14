package net.remaster147.remastercore.event

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

object EventManager {
    private val listeners = ConcurrentHashMap<KClass<out Event>, Pair<EventListener, EventPriority>>()

    fun call(event: Event) {
        listeners.entries.sortedBy { it.value.second.value }.forEach {
            it.value.first.listener(event)
        }
    }

    fun addListener(event: KClass<out Event>, listener: EventListener, priority: EventPriority) {
        listeners[event] = Pair(listener, priority)
    }
}