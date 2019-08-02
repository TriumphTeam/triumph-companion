package me.mattstudios.triumphpets.pet;

import me.mattstudios.triumphpets.pet.pets.PetFox;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EnumCreatureType;
import net.minecraft.server.v1_14_R1.IRegistry;

public enum EntityRegistry_1_14_R1 {

    PET_FOX_BABY(121, PetFox::new, EnumCreatureType.AMBIENT);

    private int entityId;
    private EntityTypes.a<?> entityTypes;

    <T extends Entity> EntityRegistry_1_14_R1(int entityId, EntityTypes.b<T> entityTypes, EnumCreatureType type) {
        this.entityId = entityId;
        this.entityTypes = EntityTypes.a.a(entityTypes, type);
    }

    private int getEntityId() {
        return entityId;
    }

    private EntityTypes.a<?> getEntityTypes() {
        return entityTypes;
    }

    /**
     * Register our entities.
     */
    public static void registerEntities() {
        for (EntityRegistry_1_14_R1 entity : values()) {
            registerPet(entity);
        }
    }

    private static <T extends Entity> void registerPet(EntityRegistry_1_14_R1 entityRegistry114R2) {
        String entityId = String.valueOf(entityRegistry114R2.getEntityId());

        EntityTypes.a<?> entity = entityRegistry114R2.getEntityTypes();
        entity.b();

        IRegistry.a(IRegistry.ENTITY_TYPE, entityId, entity.a(entityId));
    }
}
