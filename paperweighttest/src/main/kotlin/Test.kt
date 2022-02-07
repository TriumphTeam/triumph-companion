import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.goal.FollowMobGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.animal.Fox

class Test : Fox(EntityType.FOX, null) {

    fun test() {
        goalSelector.addGoal(5, FollowMobGoal(this, 1.0, 10.0f, 1.0f))
        navigation
    }

    override fun getNavigation(): PathNavigation {
        println("getNavigation")
        return super.getNavigation()
    }

}