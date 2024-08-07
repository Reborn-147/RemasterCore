package net.remaster147.remastercore.registry

import net.minecraft.utils.Identifier
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

abstract class Registry<T> {
    protected val registry = mutableMapOf<Identifier, T>()
    private val lock = ReentrantLock()

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
}