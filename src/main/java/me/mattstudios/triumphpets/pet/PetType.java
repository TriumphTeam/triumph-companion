package me.mattstudios.triumphpets.pet;

import java.util.Base64;

public enum PetType {

    PET_FOX_SNOW("Pet Fox", Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/41436377eb4c4b4e39fb0e1ed8899fb61ee1814a9169b8d08729ef01dc85d1ba").getBytes()));

    private String defaultName;
    private byte[] texture;

    PetType(String defaultName, byte[] texture) {
        this.defaultName = defaultName;
        this.texture = texture;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public byte[] getTexture() {
        return texture;
    }
}
