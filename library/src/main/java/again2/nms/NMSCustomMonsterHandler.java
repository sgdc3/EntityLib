package again2.nms;

import net.minecraft.server.v1_13_R1.EntityMonster;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.World;
import org.bukkit.craftbukkit.v1_13_R1.CraftWorld;
import org.bukkit.entity.EntityType;

public class NMSCustomMonsterHandler extends EntityMonster implements ICustomMonsterHandler {

    private Runnable tickHandler;

    protected NMSCustomMonsterHandler(EntityType type, org.bukkit.World world) {
        type.getEntityClass()
        super(entityTypes, ((CraftWorld) world).getHandle());
    }

    @Override
    public void setTickHandler(Runnable runnable) {
        this.tickHandler = tickHandler;
    }
}
