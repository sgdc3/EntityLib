package again2.api;

import again2.nms.ICustomMonsterHandler;
import org.bukkit.NamespacedKey;

import java.util.UUID;

public abstract class AbstractCustomMonster {
    private final NamespacedKey name;
    private final UUID uniqueId;
    private final ICustomMonsterHandler handler;

    public AbstractCustomMonster(NamespacedKey name) {
        this.name = name;
        this.uniqueId = UUID.nameUUIDFromBytes(name.toString().getBytes());
        this.handler = null; // TODO: How to get this?
        handler.setTickHandler(this::tick);
    }

    public final NamespacedKey getName() {
        return name;
    }

    public final UUID getUniqueId() {
        return uniqueId;
    }

    public final void setSize(float length, float height) {
        handler.setSize(length, height);
    }

    public final void addPathfinderGoal(float length, float height) {
        handler.setSize(length, height);
    }

    public abstract void tick();
}
