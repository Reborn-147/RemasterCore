package net.remaster147.remastercore.event

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

object EventManager {
    private val listeners = ConcurrentHashMap<KClass<out Event>, Pair<EventListener<Any>, EventPriority>>()

    fun <T : Event> call(event: T) {
        listeners.entries.sortedBy { it.value.second.value }.forEach {
            it.value.first.listener(event)
        }
    }

    fun <T : Event> addListener(event: KClass<out T>, listener: EventListener<T>, priority: EventPriority) {
        listeners[event] = Pair(listener as EventListener<Any>, priority)
    }
}