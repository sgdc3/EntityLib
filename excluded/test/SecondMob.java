package de.tr7zw.entitylib.test;

import org.bukkit.World;
import org.bukkit.entity.EntityType;

import de.tr7zw.entitylib.CustomEntity;
import de.tr7zw.entitylib.nms.inter.CCreature;

public class SecondMob extends CustomEntity{

    public SecondMob(World world) {
        super(world, EntityType.SQUID);
        init();
    }
    
    public SecondMob(World world, CCreature creature){
        super(world, EntityType.SQUID, creature);
        init();
    }
    
    public void init(){
        getHandler().addGoalRandomLookaround(0);
        getHandler().addGoalFleeSun(1, 0.8);
        getHandler().addGoalRandomStroll(2, 0.5);
        bukkit().setHealth(1);
        bukkit().setCustomName("TestMob");
        bukkit().setCustomNameVisible(true);
        getHandler().setPersistent(true);
    }

}
