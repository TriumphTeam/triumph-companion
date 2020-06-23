package me.mattstudios.triumphpets.pet.v1_15.goals

import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.utils.PetUtils.distance
import net.minecraft.server.v1_15_R1.BlockCrops
import net.minecraft.server.v1_15_R1.BlockPosition
import net.minecraft.server.v1_15_R1.BlockSoil
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.PathfinderGoal
import net.minecraft.server.v1_15_R1.World
import org.bukkit.block.data.Ageable
import org.bukkit.craftbukkit.v1_15_R1.block.CraftBlock
import org.bukkit.util.Vector
import kotlin.system.measureTimeMillis

/**
 * @author Matt
 */
class FarmGoal(
        private val petInsentient: EntityInsentient,
        private val MOVEMENT_SPEED: Double
) : PathfinderGoal() {

    private val pet = petInsentient as Pet

    private var controller = 0

    private var start = 0L

    var currentBlockPos: BlockPosition? = null

    override fun a(): Boolean {
        //if (!petInsentient.navigation.m()) return true

        farm(currentBlockPos)

        if (!shouldRun()) return true

        val blockPosList = BlockPosition.MutableBlockPosition(petInsentient)
        val timeNMS = measureTimeMillis {
            for (x in -10..10) {
                for (y in -5..5) {
                    for (z in -10..10) {
                        blockPosList.c(petInsentient.locX() + x.toDouble(), petInsentient.locY() + y.toDouble(), petInsentient.locZ() + z.toDouble())
                        if (isFarmable(blockPosList as BlockPosition, petInsentient.world)) {

                            if (currentBlockPos == null) {
                                currentBlockPos = BlockPosition(blockPosList)
                                continue
                            }

                            val current = BlockPosition(blockPosList)

                            val petPos = Vector(petInsentient.locX(), petInsentient.locY(), petInsentient.locZ())
                            val blockPos = Vector(currentBlockPos!!.x, currentBlockPos!!.y, currentBlockPos!!.z)
                            val currentPos = Vector(current.x, current.y, current.z)

                            if (distance(petPos, blockPos) >= distance(petPos, currentPos)) {
                                println("found new")
                                currentBlockPos = current
                            }
                        }
                    }
                }
            }
        }

        println("done in ${timeNMS}ms")

        return true
    }

    private fun farm(blockPosition: BlockPosition?) {
        if (blockPosition == null) return

        val block = CraftBlock.at(petInsentient.world, blockPosition)

        val ageable = block.blockData as Ageable

        petInsentient.navigation.a(block.x.toDouble(), block.y.toDouble(), block.z.toDouble(), MOVEMENT_SPEED)

        val dist = distance(pet.entity.location.toVector(), block.location.toVector())
        if (dist <= 2) {
            for (itemStack in block.drops) {
                pet.entity.world.dropItemNaturally(block.location, itemStack)
            }

            ageable.age = 0
            block.blockData = ageable
            currentBlockPos = null
        }
    }

    private fun isFarmable(blockPos: BlockPosition, world: World): Boolean {
        val data = world.getType(blockPos)
        val block = data.block
        val block1 = world.getType(blockPos.down()).block
        return block is BlockCrops && block.isRipe(data) || data.isAir && block1 is BlockSoil
    }


    private fun shouldRun(): Boolean {
        if (controller <= 20) {
            controller++
            return false
        }

        controller = 0
        return true
    }

}