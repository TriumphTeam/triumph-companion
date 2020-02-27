package me.mattstudios.triumphpets.pet.components

class PetExperience(xp: Int) {

    var xp = xp
        private set

    var level: Short = 1
        private set

    init {
        level = when {
            xp > 1024 -> 2
            xp > 3000 -> 3
            else -> 1
        }
    }

    fun addXp(xp: Int) {
        this.xp += xp
    }

}