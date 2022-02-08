package dev.triumphteam.companion.entity

interface CompanionController<E> {

    fun spawn()

    fun despawn()

    fun isPet(entity: E): Boolean

}