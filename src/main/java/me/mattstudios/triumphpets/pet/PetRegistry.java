package me.mattstudios.triumphpets.pet;

import me.mattstudios.triumphpets.pet.pets.PetFox;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EnumCreatureType;
import net.minecraft.server.v1_14_R1.IRegistry;

public enum PetRegistry {

    PET_FOX_BABY(121, PetFox.class, PetFox::new, EnumCreatureType.AMBIENT);

    private int entityId;
    private Class<? extends Entity> entity;
    private EntityTypes.a<?> entityTypes;

    <T extends Entity> PetRegistry(int entityId, Class<? extends Entity> entity, EntityTypes.b<T> entityTypes, EnumCreatureType type) {
        this.entityId = entityId;
        this.entity = entity;
        this.entityTypes = EntityTypes.a.a(entityTypes, type);
    }

    public int getEntityId() {
        return entityId;
    }

    public EntityTypes.a<?> getEntityTypes() {
        return entityTypes;
    }

    public Class<? extends Entity> getEntity() {
        return entity;
    }

    /**
     * Register our entities.
     */
    public static void registerEntities() {
        for (PetRegistry entity : values()) {
            // a(entityTypes.getCustomClass(), entityTypes.getName(), entityTypes.getID());
            registerPet(entity);
        }
    }

    private static <T extends Entity> void registerPet(PetRegistry petRegistry) {
        String entityId = String.valueOf(petRegistry.getEntityId());

        EntityTypes.a<?> entity = petRegistry.getEntityTypes();
        entity.b();

        IRegistry.a(IRegistry.ENTITY_TYPE, entityId, entity.a(entityId));
    }
}
