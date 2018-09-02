package de.tr7zw.entitylib.nms.v1_8_R3;

import de.tr7zw.entitylib.nms.AbstractEntityRegister;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.util.Map;

public class EntityRegister extends AbstractEntityRegister {

    private static Map<Class<?>, Integer> registeredEntityIds = null;

    @SuppressWarnings("unchecked")
    private static void init() {
        if (registeredEntityIds == null) {
            try {
                Field field = getNMSClass("EntityTypes").getDeclaredField("f");
                field.setAccessible(true);
                registeredEntityIds = (Map<Class<?>, Integer>) field.get(null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static final String craftBukkitVersion;

    static {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        craftBukkitVersion = packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public static Class<?> getNMSClass(String className) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + craftBukkitVersion + "." + className);
    }

    public void register(EntityType type, String internalname, Class<?> clazz, String displayname) {
        init();
        if (!registeredEntityIds.containsKey(clazz))
            addToMaps(clazz, type);
    }

    public static void addToMaps(Class<?> clazz, EntityType type) {
        init();
        try {
            @SuppressWarnings("deprecation")
            int entityId = type.getTypeId();
            registeredEntityIds.put(clazz, entityId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
