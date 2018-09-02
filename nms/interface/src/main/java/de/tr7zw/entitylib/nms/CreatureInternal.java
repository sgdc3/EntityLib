package de.tr7zw.entitylib.nms;

import de.tr7zw.entitylib.CustomCreature;
import de.tr7zw.entitylib.EntityLib;
import de.tr7zw.itemnbtapi.NBTCompound;
import org.bukkit.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface CreatureInternal {

    void setWrapper(CustomCreature wrap);

    CustomCreature getWrapper();

    int getId();

    void die();

    default void loadEntity(World world, NBTCompound compound) {
        System.out.println("Loading");
        String className = compound.getString("wrapper");
        try {
            if (className != null) {
                Class<? extends CustomCreature> wc = (Class<? extends CustomCreature>) Class.forName(className);
                if (wc != null) {
                    Constructor<? extends CustomCreature> constructor = null;
                    try {
                        constructor = wc.getConstructor(org.bukkit.World.class, ICustomCreature.class);
                    } catch (Exception ignored) {
                    }
                    if (constructor != null) {
                        setWrapper(constructor.newInstance(world, this));
                        EntityLib.MAPPING.put(world.getName() + "_" + this.getId(), (int) getWrapper().getMobType().getTypeId());
                    } else {
                        System.out.println("Tried loading an unknown custom entity of type '" + className + "', but the Class has no (World, CCreature) constructor! Removing.");
                    }
                } else {
                    System.out.println("Tried loading an unknown custom entity of type '" + className + "'! Removing.");
                }
            } else {
                System.out.println("Tried loading an unknown custom entity! Removing.");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
            e.printStackTrace();
        }
        if (getWrapper() == null) {
            die();
        }
    }
}
