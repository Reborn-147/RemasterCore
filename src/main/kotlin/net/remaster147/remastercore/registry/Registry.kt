package net.remaster147.remastercore.registry

import net.minecraft.utils.Identifier
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

abstract class Registry<T> {
    protected val registry = mutableMapOf<Identifier, T>()
    private val lock = ReentrantLock()

    private val rawIdMap = mutableMapOf<Identifier, Int>()

    fun register(identifier: Identifier, value: T) {
        lock.withLock {
            if(registry.containsKey(identifier)) {
                throw IllegalArgumentException("Identifier $identifier is already registered")
            }

            registry[identifier] = value
        }
    }

    fun get(identifier: Identifier): T? {
        return lock.withLock {
            registry[identifier]
        }
    }

    fun getAll(): Map<Identifier, T> {
        return lock.withLock {
            registry.toMap()
        }
    }

    fun getRawId(identifier: Identifier): Int? {
        return lock.withLock {
            rawIdMap[identifier]
        }
    }

    fun getIdentifier(rawId: Int): Identifier? {
        return lock.withLock {
            rawIdMap.entries.find { it.value == rawId }?.key
        }
    }

    fun get(rawId: Int): T? {
        return lock.withLock {
            getIdentifier(rawId)?.let { get(it) }
        }
    }
}