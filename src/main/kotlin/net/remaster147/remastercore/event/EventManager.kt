package net.remaster147.remastercore.event

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.reflect.full.safeCast

object EventManager {
    private val listeners = mutableMapOf<KClass<out Event>, MutableList<Pair<EventListener<Any>, EventPriority>>>()

    fun <T : Event> call(event: T) {
        listeners.filter { it.key == event::class }.forEach {
            it.value.sortedBy { it.second.value }.forEach {
                println(it.second)

                it.first.listener(event)
            }
        }
    }

    fun <T : Event> addListener(event: KClass<out T>, listener: EventListener<T>, priority: EventPriority) {
        if(!listeners.containsKey(event)) {
            listeners[event] = mutableListOf()
        }

        listeners[event]!!.add(listener as EventListener<Any> to priority)
    }
}