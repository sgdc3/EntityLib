package de.tr7zw.entitylib;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.entity.EntityType;

import de.tr7zw.entitylib.nms.inter.CCreature;
import de.tr7zw.entitylib.nms.inter.EntityRegister;
import de.tr7zw.entitylib.nms.inter.PathfinderGoal;
import de.tr7zw.entitylib.nms.inter.PathfinderWrapper;
import de.tr7zw.entitylib.v1_11_R1.Creature1_11_R1;
import de.tr7zw.entitylib.v1_11_R1.EntityRegister1_11_R1;
import de.tr7zw.entitylib.v1_11_R1.Pathfinder1_11_R1;
import de.tr7zw.entitylib.nms.v1_12_R1.Creature1_12_R1;
import de.tr7zw.entitylib.nms.v1_12_R1.EntityRegister1_12_R1;
import de.tr7zw.entitylib.nms.v1_12_R1.Pathfinder1_12_R1;
import de.tr7zw.entitylib.nms.v1_8_3.Creature1_8_R3;
import de.tr7zw.entitylib.nms.v1_8_3.EntityRegister1_8_R3;
import de.tr7zw.entitylib.nms.v1_8_3.Pathfinder1_8_R3;
import de.tr7zw.itemnbtapi.utils.MinecraftVersion;

public class NMSUtil {

    private static HashMap<Class<?>, Class<?>> nmsClasses = new HashMap<>();

    static {
        //1.8.3
        if(MinecraftVersion.getVersion() == MinecraftVersion.MC1_8_R3){
            registerClass(MinecraftVersion.MC1_8_R3, CCreature.class, Creature1_8_R3.class);
            registerClass(MinecraftVersion.MC1_8_R3, PathfinderWrapper.class, Pathfinder1_8_R3.class);
            registerClass(MinecraftVersion.MC1_8_R3, EntityRegister.class, EntityRegister1_8_R3.class);
        }
        //1.11.2
        if(MinecraftVersion.getVersion() == MinecraftVersion.MC1_11_R1){
            registerClass(MinecraftVersion.MC1_11_R1, CCreature.class, Creature1_11_R1.class);
            registerClass(MinecraftVersion.MC1_11_R1, PathfinderWrapper.class, Pathfinder1_11_R1.class);
            registerClass(MinecraftVersion.MC1_11_R1, EntityRegister.class, EntityRegister1_11_R1.class);
        }
        //1.12.2
        if(MinecraftVersion.getVersion() == MinecraftVersion.MC1_12_R1){
            registerClass(MinecraftVersion.MC1_12_R1, CCreature.class, Creature1_12_R1.class);
            registerClass(MinecraftVersion.MC1_12_R1, PathfinderWrapper.class, Pathfinder1_12_R1.class);
            registerClass(MinecraftVersion.MC1_12_R1, EntityRegister.class, EntityRegister1_12_R1.class);
        }
    }

    protected static void registerCustomEntity(){
        if(MinecraftVersion.getVersion() == MinecraftVersion.MC1_8_R3){
            NMSUtil.getHandler(EntityRegister.class).register(EntityType.VILLAGER, "custommob", Creature1_8_R3.class, "CustomMob");
        }
        if(MinecraftVersion.getVersion() == MinecraftVersion.MC1_11_R1){
            NMSUtil.getHandler(EntityRegister.class).register(EntityType.VILLAGER, "custommob", Creature1_11_R1.class, "CustomMob");
        }
        if(MinecraftVersion.getVersion() == MinecraftVersion.MC1_12_R1){
            NMSUtil.getHandler(EntityRegister.class).register(EntityType.VILLAGER, "custommob", Creature1_12_R1.class, "CustomMob");
        }
    }

    public static <T> T registerClass(MinecraftVersion version, Class<T> base, Class<? extends T> handler) {
        if (MinecraftVersion.getVersion().equals(version)) {
            //T inst = handler.newInstance();
            nmsClasses.put(base, handler);
            return null;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getHandler(Class<T> base) {
        try {
            return (T) nmsClasses.get(base).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getClassHandler(Class<?> base){
        return nmsClasses.get(base);
    }

    public static CCreature getnewCreature(World w, CustomEntity wrap){
        try {
            return (CCreature) getClassHandler(CCreature.class).getConstructor(World.class, CustomEntity.class).newInstance(w, wrap);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getPathfinder(PathfinderGoal goal){
        try {
            return getClassHandler(PathfinderWrapper.class).getConstructor(PathfinderGoal.class).newInstance(goal);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
