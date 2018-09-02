package de.tr7zw.entitylib;

import de.tr7zw.entitylib.nms.ICustomCreature;
import org.bukkit.entity.EntityType;

public abstract class CustomCreature implements ICustomCreature {
    private final EntityType type;

    public CustomCreature(EntityType type) {
        this.type = type;
    }

    @Override
    public EntityType getType() {
        return type;
    }
}
