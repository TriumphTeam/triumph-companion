package me.mattstudios.triumphpets.pet.nms.v1_14_R1;

import me.mattstudios.triumphpets.pet.nms.v1_14_R1.pets.PetFox;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EnumCreatureType;
import net.minecraft.server.v1_14_R1.IRegistry;

@SuppressWarnings("unused")
public enum PetRegistry_1_14_R1 {

    PET_FOX_BABY(121, PetFox::new, EnumCreatureType.AMBIENT);

    private int entityId;
    private EntityTypes.a<?> entityTypes;

    <T extends Entity> PetRegistry_1_14_R1(int entityId, EntityTypes.b<T> entityTypes, EnumCreatureType type) {
        this.entityId = entityId;
        this.entityTypes = EntityTypes.a.a(entityTypes, type);
    }

    /**
     * Registers all entities declared.
     */
    public static void registerEntities() {
        for (PetRegistry_1_14_R1 entity : values()) {
            registerPet(entity);
        }
    }

    /**
     * Gets the entity ID of the entity to register.
     * @return The ID.
     */
    private int getEntityId() {
        return entityId;
    }

    /**
     * Gets the entity type passed in the constructor.
     * @return The entitytypes.
     */
    private EntityTypes.a<?> getEntityTypes() {
        return entityTypes;
    }

    /**
     * Register the entity in the world.
     * @param entityRegistry The registry to be used.
     * @param <T> Fuck if I know.
     */
    private static <T extends Entity> void registerPet(PetRegistry_1_14_R1 entityRegistry) {
        String entityId = String.valueOf(entityRegistry.getEntityId());

        EntityTypes.a<?> entity = entityRegistry.getEntityTypes();
        entity.b();

        IRegistry.a(IRegistry.ENTITY_TYPE, entityId, entity.a(entityId));
    }
}
