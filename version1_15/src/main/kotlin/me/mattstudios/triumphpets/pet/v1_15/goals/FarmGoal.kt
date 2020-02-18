package me.mattstudios.triumphpets.pet.v1_15.goals

import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.utils.PetUtils
import net.minecraft.server.v1_15_R1.BlockPosition
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.PathfinderGoal
import org.bukkit.Material
import org.bukkit.block.data.Ageable
import org.bukkit.craftbukkit.v1_15_R1.block.CraftBlock
import kotlin.math.roundToInt

/**
 * @author Matt
 */
class FarmGoal(private val pet: Pet, private val petInsentient: EntityInsentient, private val MOVEMENT_SPEED: Double) : PathfinderGoal() {

    private var controller = 0

    private var start = 0L

    override fun a(): Boolean {
        if (!shouldRun()) return true

        val minX = (petInsentient.locX() - 10).roundToInt()
        val maxX = (petInsentient.locX() + 10).roundToInt()

        val minY = (petInsentient.locY() - 5).roundToInt()
        val maxY = (petInsentient.locY() + 5).roundToInt()

        val minZ = (petInsentient.locZ() - 10).roundToInt()
        val maxZ = (petInsentient.locZ() + 10).roundToInt()

        for (x in minX..maxX) {
            for (y in minY..maxY) {
                for (z in minZ..maxZ) {
                    val block = CraftBlock.at(petInsentient.world, BlockPosition(x, y, z))
                    if (block.type != Material.WHEAT) continue

                    val ageable = block.blockData as Ageable
                    if (ageable.age != ageable.maximumAge) continue

                    petInsentient.navigation.a(block.x.toDouble(), block.y.toDouble(), block.z.toDouble(), MOVEMENT_SPEED)

                    val dist = PetUtils.distance(pet.getEntity().location.toVector(), block.location.toVector())
                    if (dist <= 1.75) {
                        for (itemStack in block.drops) {
                            pet.getEntity().world.dropItemNaturally(block.location, itemStack)
                        }

                        ageable.age = 0
                        block.blockData = ageable
                    }
                }
            }
        }

        return true
    }


    private fun shouldRun(): Boolean {
        if (controller <= 100) {
            controller++
            return false
        }

        controller = 0
        return true
    }

}