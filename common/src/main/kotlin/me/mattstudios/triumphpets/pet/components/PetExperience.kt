package me.mattstudios.triumphpets.pet.components

class PetExperience(xp: Int) {

    var xp = xp
        private set

    var level = 0
        private set

    fun addXp(xp: Int) {
        this.xp += xp
    }

}