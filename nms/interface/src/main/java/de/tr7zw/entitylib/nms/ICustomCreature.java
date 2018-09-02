package de.tr7zw.entitylib.nms;

import de.tr7zw.entitylib.CustomCreature;
import org.bukkit.World;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.Random;

public interface ICustomCreature {

    Creature getHandle();

    void setLocation(double x, double y, double z, float yaw, float pitch);

    void addToWorld(World world);

    EntityType getType();

    CustomCreature getWrapper();

    Navigation getNavigator();

    // Goals, Targets
    void addGoalSelector(int priority, PathfinderGoal goal);

    void addTargetSelector(int priority, PathfinderGoal goal);

    Entity getBukkitGoalTarget();

    void addNMSGoalSelector(int priority, Object selector);

    void addNMSTargetSelector(int priority, Object selector);

    void addGoalRandomLookaround(int priority);

    void addGoalMoveThroughVillage(int priority, double speed);

    void addGoalMeleeAttack(int priority, double dmg);

    void addGoalMoveIndoors(int priority);

    void addGoalPanic(int priority, double speed);

    void addGoalRestrictSun(int priority);

    void addGoalRestrictOpenDoor(int priority);

    void addGoalOpenDoor(int priority);

    void addGoalFleeSun(int priority, double speed);

    void addGoalBreakDoor(int priority);

    void addGoalEatTile(int priority);

    void addGoalFloat(int priority);

    void addGoalAvoidTarget(int priority, EntityType type, float dist, double speed);

    void addGoalMoveTowardsRestriction(int priority, double speed);

    /*
     * new PathfinderGoalFollowEntity(arg0, arg1, arg2, arg3)
     * new PathfinderGoalHurtByTarget(entitycreature, flag, aclass)
     * new PathfinderGoalLeapAtTarget(arg0, arg1)
     * new PathfinderGoalLookAtPlayer(arg0, arg1, arg2, arg3)
     * new PathfinderGoalMoveTowardsRestriction(arg0, arg1)
     * new PathfinderGoalMoveTowardsTarget(arg0, arg1, arg2)
     * new PathfinderGoalTargetNearestPlayer(entityinsentient)
     */

    void addGoalRandomStroll(int priority, double speed);

    void setSize(float length, float height);

    // Control
    void jump();

    boolean inWater();

    void setPersistent(boolean persistent);

    // ETC
    Random getRandom();
}
