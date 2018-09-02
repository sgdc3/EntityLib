package de.tr7zw.entitylib;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class EntityLib extends JavaPlugin {

    public static final HashMap<String, Integer> MAPPING = new HashMap<>();
    public static EntityLib instance;

    @Override
    public void onLoad() {
        NMSUtil.registerCustomEntity();
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new EntityListener(), this);
    }

    public static void spawn(CustomEntity entity, Location location) {
        entity.getHandler().setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        entity.getHandler().addtoWorld(location.getWorld());
    }
}
