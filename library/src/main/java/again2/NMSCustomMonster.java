package again2;

import again2.pathfinder.PathfinderGoal;
import com.google.common.collect.Multimap;
import javafx.util.Pair;
import net.minecraft.server.v1_13_R1.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NMSCustomMonster extends EntityMonster {
    private UUID uuid;
    private EntityTypes<?> entityType;
    private float length, height;
    private Map<IAttribute, Double> genericAttributes;
    private Map<IAttribute, Double> customAttributes;
    private Map<AttributeModifier, Object> attributeModifiers;
    private Map<String, Pair<DataWatcherObject<Boolean>, Boolean>> booleanData;
    private Map<String, Pair<DataWatcherObject<Integer>, Integer>> integerData;
    private Multimap<Integer, PathfinderGoal> pathfinderGoals;
    private Runnable tickHandler;

    /* Zombie specific attributes */
    //protected static final IAttribute c = (new AttributeRanged((IAttribute)null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).a("Spawn Reinforcements Chance");
    //private static final UUID a = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    //private static final AttributeModifier b;
    //private static final DataWatcherObject<Boolean> bC;
    //private static final DataWatcherObject<Integer> bD;
    //private static final DataWatcherObject<Boolean> bE;
    //private static final DataWatcherObject<Boolean> bF;
    //private final PathfinderGoalBreakDoor bG;
    //private boolean bH;
    private int bI; // Tick counters!
    private int bJ;
    //private float bK;
    //private float bL;

    /*
    static {
        b = new AttributeModifier(a, "Baby speed boost", 0.5D, 1);
        bC = DataWatcher.a(EntityZombie.class, DataWatcherRegistry.i);
        bD = DataWatcher.a(EntityZombie.class, DataWatcherRegistry.b);
        bE = DataWatcher.a(EntityZombie.class, DataWatcherRegistry.i);
        bF = DataWatcher.a(EntityZombie.class, DataWatcherRegistry.i);
    }
    */

    public NMSCustomMonster(EntityTypes<?> entityType, World world) {
        super(entityType, world);
        //this.bG = new PathfinderGoalBreakDoor(this);
        //this.bK = -1.0F;
        // Set entity size
        this.setSize(length, height);
    }

    // Register pathfinder goals with priorities
    @Override
    protected void n() {
        pathfinderGoals.forEach((priority, goal) -> goalSelector.a(priority, goal));
        //this.l();
    }

    /* Zombie specific goals
    protected void l() {
        this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, 1.0D, false));
        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[]{EntityPigZombie.class}));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        if (this.world.spigotConfig.zombieAggressiveTowardsVillager) {
            this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, false));
        }

        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, true));
        this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget(this, EntityTurtle.class, 10, true, false, EntityTurtle.bC));
    }
    */

    // Register
    @Override
    protected void initAttributes() {
        // Init Monster attributes
        super.initAttributes();
        // Set generic attributes default values
        genericAttributes.forEach((attribute, defaultValue) -> getAttributeInstance(attribute).setValue(defaultValue));
        //this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(35.0D);
        //this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.23000000417232513D);
        //this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(3.0D);
        //this.getAttributeInstance(GenericAttributes.h).setValue(2.0D);
        // Register custom customAttributes and set defaults
        customAttributes.forEach((attribute, defaultValue) -> getAttributeMap().b(attribute).setValue(defaultValue));
        //this.getAttributeMap().b(c).setValue(this.random.nextDouble() * 0.10000000149011612D);
    }

    // Register data watcher objects
    @Override
    protected void x_() {
        // Register EntityInsentient data watcher objects
        super.x_();
        // Register custom data watcher objects + set default values
        booleanData.forEach((name, data) -> getDataWatcher().register(data.getKey(), data.getValue()));
        integerData.forEach((name, data) -> getDataWatcher().register(data.getKey(), data.getValue()));
        //this.getDataWatcher().register(bC, false);
        //this.getDataWatcher().register(bD, 0);
        //this.getDataWatcher().register(bE, false);
        //this.getDataWatcher().register(bF, false);
    }

    // Getter for boolean data
    public Boolean getBooleanData(String name) {
        Pair<DataWatcherObject<Boolean>, Boolean> data = booleanData.get(name);
        if(data == null) {
            return null; // TODO: throw exception? optionals?
        }
        return getDataWatcher().get(data.getKey());
    }

    // Getter for integer data
    public Integer getIntegerData(String name) {
        Pair<DataWatcherObject<Integer>, Integer> data = integerData.get(name);
        if(data == null) {
            return null; // TODO: throw exception? optionals?
        }
        return getDataWatcher().get(data.getKey());
    }

    // Setter for boolean data
    public void setBooleanData(String name, Boolean value) {
        Pair<DataWatcherObject<Boolean>, Boolean> data = booleanData.get(name);
        if(data == null) {
            return; // TODO: throw exception? optionals?
        }
        getDataWatcher().set(data.getKey(), value);
    }

    // Setter for integer data
    public void setIntegerData(String name, Integer value) {
        Pair<DataWatcherObject<Integer>, Integer> data = integerData.get(name);
        if(data == null) {
            return; // TODO: throw exception? optionals?
        }
        getDataWatcher().set(data.getKey(), value);
    }

    /* Zombie specific data getter/setters
    public boolean dH() {
        return (Boolean)this.getDataWatcher().get(bF);
    }

    public void s(boolean flag) {
        this.getDataWatcher().set(bE, flag);
    }
    */

    /* WTF, zombie goal selection logic
    public boolean dI() {
        return this.bH;
    }

    public void t(boolean flag) {
        if (this.dA()) {
            if (this.bH != flag) {
                this.bH = flag;
                ((Navigation)this.getNavigation()).a(flag);
                if (flag) {
                    this.goalSelector.a(1, this.bG);
                } else {
                    this.goalSelector.a(this.bG);
                }
            }
        } else if (this.bH) {
            this.goalSelector.a(this.bG);
            this.bH = false;
        }

    }
    */

    /* WTF, zombie specific
    protected boolean dA() {
        return true;
    }
    */

    /* Zombie baby flag
    public boolean isBaby() {
        return (Boolean)this.getDataWatcher().get(bC);
    }
    */

    // Dropped experience
    @Override
    protected int getExpValue(EntityHuman entityhuman) {
        /* Zombie exp drop logic
        if (this.isBaby()) {
            this.b_ = (int)((float)this.b_ * 2.5F);
        }
        */
        return super.getExpValue(entityhuman);
    }

    /* Zombie baby logic
    public void setBaby(boolean flag) {
        this.getDataWatcher().set(bC, flag);
        if (this.world != null && !this.world.isClientSide) {
            AttributeInstance attributeinstance = this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
            attributeinstance.c(b);
            if (flag) {
                attributeinstance.b(b);
            }
        }

        this.v(flag);
    }
    */

    /* TODO: what is that? it interacts with the entity inventory
    @Override
    public void a(DataWatcherObject<?> datawatcherobject) {
        if (bC.equals(datawatcherobject)) {
            this.v(this.isBaby());
        }

        super.a(datawatcherobject);
    }
    */

    /* WTF, zombie specific
    protected boolean dD() {
        return true;
    }
    */

    // Entity tick handler
    @Override
    public void tick() {
        tickHandler.run();

        /* Zombie specific tick logic
        if (!this.world.isClientSide) {
            if (this.dH()) {
                --this.bJ;
                if (this.bJ < 0) {
                    this.dF();
                }
            } else if (this.dD()) {
                if (this.a((Tag)TagsFluid.a)) {
                    ++this.bI;
                    if (this.bI >= 600) {
                        this.a(300);
                    }
                } else {
                    this.bI = -1;
                }
            }
        }
        */

        super.tick();
    }

    // Entity logic tick handler
    @Override
    public void k() {
        /* Zombie daylight burn logic
        boolean flag = this.L_() && this.dr();
        if (flag) {
            ItemStack itemstack = this.getEquipment(EnumItemSlot.HEAD);
            if (!itemstack.isEmpty()) {
                if (itemstack.e()) {
                    itemstack.setDamage(itemstack.getDamage() + this.random.nextInt(2));
                    if (itemstack.getDamage() >= itemstack.h()) {
                        this.c(itemstack);
                        this.setSlot(EnumItemSlot.HEAD, ItemStack.a);
                    }
                }

                flag = false;
            }

            if (flag) {
                EntityCombustEvent event = new EntityCombustEvent(this.getBukkitEntity(), 8);
                this.world.getServer().getPluginManager().callEvent(event);
                if (!event.isCancelled()) {
                    this.setOnFire(event.getDuration());
                }
            }
        }
        */

        super.k();
    }

    /* Other WTF zombie logic
    private void a(int i) {
        this.bJ = i;
        this.getDataWatcher().set(bF, true);
    }

    protected void dF() {
        this.a((EntityZombie)(new EntityDrowned(this.world)));
        this.world.a((EntityHuman)null, 1040, new BlockPosition((int)this.locX, (int)this.locY, (int)this.locZ), 0);
    }

    // Zombie clone method?
    protected void a(EntityZombie entityzombie) {
        if (!this.world.isClientSide && !this.dead) {
            entityzombie.u(this);
            entityzombie.a(this.dk(), this.dI(), this.isBaby(), this.isNoAI());
            EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
            int i = aenumitemslot.length;

            for(int j = 0; j < i; ++j) {
                EnumItemSlot enumitemslot = aenumitemslot[j];
                ItemStack itemstack = this.getEquipment(enumitemslot);
                if (!itemstack.isEmpty()) {
                    entityzombie.setSlot(enumitemslot, itemstack);
                }
            }

            if (this.hasCustomName()) {
                entityzombie.setCustomName(this.getCustomName());
                entityzombie.setCustomNameVisible(this.getCustomNameVisible());
            }

            this.world.addEntity(entityzombie);
            this.die();
        }

    }

    // Can burn?
    protected boolean L_() {
        return true;
    }
    */

    // TODO: Attack handler?
    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        if (super.damageEntity(damagesource, f)) {
            EntityLiving entityliving = this.getGoalTarget();
            if (entityliving == null && damagesource.getEntity() instanceof EntityLiving) {
                entityliving = (EntityLiving)damagesource.getEntity();
            }

            if (entityliving != null && this.world.getDifficulty() == EnumDifficulty.HARD && (double)this.random.nextFloat() < this.getAttributeInstance(c).getValue() && this.world.getGameRules().getBoolean("doMobSpawning")) {
                int i = MathHelper.floor(this.locX);
                int j = MathHelper.floor(this.locY);
                int k = MathHelper.floor(this.locZ);
                EntityZombie entityzombie = new EntityZombie(this.world);

                for(int l = 0; l < 50; ++l) {
                    int i1 = i + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    int j1 = j + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    int k1 = k + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    if (this.world.getType(new BlockPosition(i1, j1 - 1, k1)).q() && this.world.getLightLevel(new BlockPosition(i1, j1, k1)) < 10) {
                        entityzombie.setPosition((double)i1, (double)j1, (double)k1);
                        if (!this.world.isPlayerNearby((double)i1, (double)j1, (double)k1, 7.0D) && this.world.b(entityzombie, entityzombie.getBoundingBox()) && this.world.getCubes(entityzombie, entityzombie.getBoundingBox()) && !this.world.containsLiquid(entityzombie.getBoundingBox())) {
                            this.world.addEntity(entityzombie, CreatureSpawnEvent.SpawnReason.REINFORCEMENTS);
                            entityzombie.setGoalTarget(entityliving, EntityTargetEvent.TargetReason.REINFORCEMENT_TARGET, true);
                            entityzombie.prepare(this.world.getDamageScaler(new BlockPosition(entityzombie)), (GroupDataEntity)null, (NBTTagCompound)null);
                            this.getAttributeInstance(c).b(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
                            entityzombie.getAttributeInstance(c).b(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
                            break;
                        }
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    // TODO: Entity collision hadler?
    @Override
    public boolean B(Entity entity) {
        boolean flag = super.B(entity);
        if (flag) {
            float f = this.world.getDamageScaler(new BlockPosition(this)).b();
            if (this.getItemInMainHand().isEmpty() && this.isBurning() && this.random.nextFloat() < f * 0.3F) {
                EntityCombustByEntityEvent event = new EntityCombustByEntityEvent(this.getBukkitEntity(), entity.getBukkitEntity(), 2 * (int)f);
                this.world.getServer().getPluginManager().callEvent(event);
                if (!event.isCancelled()) {
                    entity.setOnFire(event.getDuration());
                }
            }
        }

        return flag;
    }

    // Inactive sound
    @Override
    protected SoundEffect D() {
        return SoundEffects.ENTITY_ZOMBIE_AMBIENT;
    }

    // Damage sound
    @Override
    protected SoundEffect d(DamageSource damagesource) {
        return SoundEffects.ENTITY_ZOMBIE_HURT;
    }

    // Death sound
    @Override
    protected SoundEffect cs() {
        return SoundEffects.ENTITY_ZOMBIE_DEATH;
    }

    /* Zombie walk sound
    protected SoundEffect dB() {
        return SoundEffects.ENTITY_ZOMBIE_STEP;
    }
    */

    // Walk sound handler
    @Override
    protected void a(BlockPosition blockposition, IBlockData iblockdata) {
        this.a(this.dB(), 0.15F, 1.0F);
    }

    // Monster type
    @Override
    public EnumMonsterType getMonsterType() {
        return EnumMonsterType.UNDEAD;
    }

    // Loot table key
    @Nullable
    @Override
    protected MinecraftKey G() {
        return LootTables.at;
    }

    // Random equipment handler
    @Override
    protected void a(DifficultyDamageScaler difficultydamagescaler) {
        super.a(difficultydamagescaler);
        if (this.random.nextFloat() < (this.world.getDifficulty() == EnumDifficulty.HARD ? 0.05F : 0.01F)) {
            int i = this.random.nextInt(3);
            if (i == 0) {
                this.setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            } else {
                this.setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
            }
        }
    }

    // NBT save handler
    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.isBaby()) {
            nbttagcompound.setBoolean("IsBaby", true);
        }

        nbttagcompound.setBoolean("CanBreakDoors", this.dI());
        nbttagcompound.setInt("InWaterTime", this.isInWater() ? this.bI : -1);
        nbttagcompound.setInt("DrownedConversionTime", this.dH() ? this.bJ : -1);
    }

    // NBT load event
    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.getBoolean("IsBaby")) {
            this.setBaby(true);
        }

        this.t(nbttagcompound.getBoolean("CanBreakDoors"));
        this.bI = nbttagcompound.getInt("InWaterTime");
        if (nbttagcompound.hasKeyOfType("DrownedConversionTime", 99) && nbttagcompound.getInt("DrownedConversionTime") > -1) {
            this.a(nbttagcompound.getInt("DrownedConversionTime"));
        }

    }

    // Kill other entity handler
    @Override
    public void b(EntityLiving entityliving) {
        super.b(entityliving);
        if ((this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) && entityliving instanceof EntityVillager) {
            if (this.world.getDifficulty() != EnumDifficulty.HARD && this.random.nextBoolean()) {
                return;
            }

            EntityVillager entityvillager = (EntityVillager)entityliving;
            EntityZombieVillager entityzombievillager = new EntityZombieVillager(this.world);
            entityzombievillager.u(entityvillager);
            this.world.kill(entityvillager);
            entityzombievillager.prepare(this.world.getDamageScaler(new BlockPosition(entityzombievillager)), new EntityZombie.GroupDataZombie(false, (Object)null), (NBTTagCompound)null);
            entityzombievillager.setProfession(entityvillager.getProfession());
            entityzombievillager.setBaby(entityvillager.isBaby());
            entityzombievillager.setNoAI(entityvillager.isNoAI());
            if (entityvillager.hasCustomName()) {
                entityzombievillager.setCustomName(entityvillager.getCustomName());
                entityzombievillager.setCustomNameVisible(entityvillager.getCustomNameVisible());
            }

            this.world.addEntity(entityzombievillager, CreatureSpawnEvent.SpawnReason.INFECTION);
            this.world.a((EntityHuman)null, 1026, new BlockPosition(this), 0);
        }

    }

    // Head height getter
    @Override
    public float getHeadHeight() {
        float f = 1.74F;
        if (this.isBaby()) {
            f = (float)((double)f - 0.81D);
        }

        return f;
    }

    // Pickup handler
    @Override
    protected boolean d(ItemStack itemstack) {
        return itemstack.getItem() == Items.EGG && this.isBaby() && this.isPassenger() ? false : super.d(itemstack);
    }

    @Nullable
    @Override
    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
        Object object = super.prepare(difficultydamagescaler, groupdataentity, nbttagcompound);
        float f = difficultydamagescaler.d();
        this.p(this.random.nextFloat() < 0.55F * f);
        if (object == null) {
            object = new EntityZombie.GroupDataZombie(this.world.random.nextFloat() < 0.05F, (Object)null);
        }

        if (object instanceof EntityZombie.GroupDataZombie) {
            EntityZombie.GroupDataZombie entityzombie_groupdatazombie = (EntityZombie.GroupDataZombie)object;
            if (entityzombie_groupdatazombie.a) {
                this.setBaby(true);
                if ((double)this.world.random.nextFloat() < 0.05D) {
                    List list = this.world.a(EntityChicken.class, this.getBoundingBox().grow(5.0D, 3.0D, 5.0D), IEntitySelector.b);
                    if (!list.isEmpty()) {
                        EntityChicken entitychicken = (EntityChicken)list.get(0);
                        entitychicken.s(true);
                        this.startRiding(entitychicken);
                    }
                } else if ((double)this.world.random.nextFloat() < 0.05D) {
                    EntityChicken entitychicken1 = new EntityChicken(this.world);
                    entitychicken1.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, 0.0F);
                    entitychicken1.prepare(difficultydamagescaler, (GroupDataEntity)null, (NBTTagCompound)null);
                    entitychicken1.s(true);
                    this.world.addEntity(entitychicken1, CreatureSpawnEvent.SpawnReason.MOUNT);
                    this.startRiding(entitychicken1);
                }
            }

            this.t(this.dA() && this.random.nextFloat() < f * 0.1F);
            this.a(difficultydamagescaler);
            this.b((DifficultyDamageScaler)difficultydamagescaler);
        }

        if (this.getEquipment(EnumItemSlot.HEAD).isEmpty()) {
            LocalDate localdate = LocalDate.now();
            int i = localdate.get(ChronoField.DAY_OF_MONTH);
            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F) {
                this.setSlot(EnumItemSlot.HEAD, new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                this.dropChanceArmor[EnumItemSlot.HEAD.b()] = 0.0F;
            }
        }

        this.a(f);
        return (GroupDataEntity)object;
    }

    /*
    protected void a(boolean flag, boolean flag1, boolean flag2, boolean flag3) {
        this.p(flag);
        this.t(this.dA() && flag1);
        this.a(this.world.getDamageScaler(new BlockPosition(this)).d());
        this.setBaby(flag2);
        this.setNoAI(flag3);
    }

    protected void a(float f) {
        this.getAttributeInstance(GenericAttributes.c).b(new AttributeModifier("Random spawn bonus", this.random.nextDouble() * 0.05000000074505806D, 0));
        double d0 = this.random.nextDouble() * 1.5D * (double)f;
        if (d0 > 1.0D) {
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).b(new AttributeModifier("Random zombie-spawn bonus", d0, 2));
        }

        if (this.random.nextFloat() < f * 0.05F) {
            this.getAttributeInstance(c).b(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 0.25D + 0.5D, 0));
            this.getAttributeInstance(GenericAttributes.maxHealth).b(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 3.0D + 1.0D, 2));
            this.t(this.dA());
        }

    }

    public void v(boolean flag) {
        this.v(flag ? 0.5F : 1.0F);
    }
    */

    /*
    public final void setSize(float f, float f1) {
        boolean flag = this.bK > 0.0F && this.bL > 0.0F;
        this.bK = f;
        this.bL = f1;
        if (!flag) {
            this.v(1.0F);
        }
    }

    protected final void v(float f) {
        super.setSize(this.bK * f, this.bL * f);
    }
    */

    @Override
    public double aI() {
        return this.isBaby() ? 0.0D : -0.45D;
    }

    @Override
    public void die(DamageSource damagesource) {
        if (damagesource.getEntity() instanceof EntityCreeper) {
            EntityCreeper entitycreeper = (EntityCreeper)damagesource.getEntity();
            if (entitycreeper.isPowered() && entitycreeper.canCauseHeadDrop()) {
                entitycreeper.setCausedHeadDrop();
                ItemStack itemstack = this.dC();
                if (!itemstack.isEmpty()) {
                    this.a_(itemstack);
                }
            }
        }

        super.die(damagesource);
    }

    /*
    protected ItemStack dC() {
        return new ItemStack(Items.ZOMBIE_HEAD);
    }

    public class GroupDataZombie implements GroupDataEntity {
        public boolean a;

        private GroupDataZombie(boolean flag) {
            this.a = flag;
        }

        GroupDataZombie(boolean flag, Object object) {
            this(flag);
        }
    }

    class a extends PathfinderGoalRemoveBlock {
        a(Block block, EntityCreature entitycreature, double d0, int i) {
            super(block, entitycreature, d0, i);
        }

        public void a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
            generatoraccess.a((EntityHuman)null, blockposition, SoundEffects.ENTITY_ZOMBIE_DESTROY_EGG, SoundCategory.HOSTILE, 0.5F, 0.9F + EntityZombie.this.random.nextFloat() * 0.2F);
        }

        public void a(World world, BlockPosition blockposition) {
            world.a((EntityHuman)null, blockposition, SoundEffects.ENTITY_TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
        }

        public double g() {
            return 1.3D;
        }
    }
    */
}
