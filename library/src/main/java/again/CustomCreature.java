package again;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class CustomCreature {
    private NMSAdapter handler;
    //private NMSCraftCreature craftHandler;

    private EntityType type;

    public CustomCreature(EntityType type) {
        this.type = type;
        handler.getNMSEntityClass(type).get().getConstructor()
    }

    public void spawn(Location location) {
        try {
            Class.forName("net.minecraft.server.v1_13_R1.Entity" + StringUtils.capitalize(type.name().toLowerCase()))
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
