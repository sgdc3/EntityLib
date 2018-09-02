package de.tr7zw.entitylib;

import org.bukkit.entity.EntityType;

public abstract class AbstractEntityRegister {

    public abstract void register(EntityType type, String internalName, Class<?> clazz, String displayName);
}
