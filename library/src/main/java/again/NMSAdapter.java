package again;

import de.tr7zw.itemnbtapi.utils.MinecraftVersion;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_13_R1.CraftWorld;
import org.bukkit.entity.EntityType;

import java.lang.reflect.InvocationTargetException;

public final class NMSAdapter {

    private NMSAdapter() {
    }

    public static String getNMSBase() {
        return MinecraftVersion.getVersion().name().replace("MC", "v");
    }

    public static Class<?> getNMSEntityClass(EntityType type) {
        try {
            return Class.forName(getNMSBase() + ".Entity" + StringUtils.capitalize(type.name().toLowerCase()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getNMSWorld(World world) {
        return ((CraftWorld) world).getHandle();
    }

    public static Object createNMSEntity(EntityType type, Location location) {
        Object nmsWorld = getNMSWorld(location.getWorld());
        try {
            Object nmsEntity = ConstructorUtils.invokeConstructor(getNMSEntityClass(type), getNMSWorld(location.getWorld()));
            MethodUtils.invokeMethod(nmsEntity, "setLocation", location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
            MethodUtils.invokeMethod(nmsEntity, "setHeadRotation", location.getYaw());
            return nmsEntity;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object
}
