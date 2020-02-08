package me.mattstudios.triumphpets.pet_1_15_r1.goal

import net.minecraft.server.v1_15_R1.BehaviorController
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.PathfinderGoal
import net.minecraft.server.v1_15_R1.PathfinderGoalSelector
import java.util.EnumMap
import java.util.EnumSet
import java.util.TreeMap

object ClearGoal {

    /**
     * Clears the path finders of an entity
     */
    fun clearPathfinders(entity: EntityInsentient) {
        try {
            val memoriesField = BehaviorController::class.java.getDeclaredField("memories")
            memoriesField.isAccessible = true
            memoriesField[entity.behaviorController] = HashMap<Any, Any>()

            val sensorsField = BehaviorController::class.java.getDeclaredField("sensors")
            sensorsField.isAccessible = true
            sensorsField[entity.behaviorController] = LinkedHashMap<Any, Any>()

            val cField = BehaviorController::class.java.getDeclaredField("c")
            cField.isAccessible = true
            cField[entity.behaviorController] = TreeMap<Any, Any>()

            val dField = PathfinderGoalSelector::class.java.getDeclaredField("d")
            dField.isAccessible = true
            dField[entity.goalSelector] = LinkedHashSet<Any>()
            dField[entity.targetSelector] = LinkedHashSet<Any>()


            val cPathField = PathfinderGoalSelector::class.java.getDeclaredField("c")
            cPathField.isAccessible = true
            cPathField[entity.targetSelector] = EnumMap<PathfinderGoal.Type, Any>(PathfinderGoal.Type::class.java)

            val fField = PathfinderGoalSelector::class.java.getDeclaredField("f")
            fField.isAccessible = true
            dField[entity.goalSelector] = LinkedHashSet<Any>()
            fField[entity.targetSelector] = EnumSet.noneOf(PathfinderGoal.Type::class.java)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }

}