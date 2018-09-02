package de.tr7zw.entitylib.nms.v1_8_R3;

import de.tr7zw.entitylib.nms.inter.PathfinderWrapper;
import net.minecraft.server.v1_8_R3.PathfinderGoal;

public class Pathfinder extends PathfinderGoal implements PathfinderWrapper{

    private de.tr7zw.entitylib.nms.inter.PathfinderGoal goal;
    
    public Pathfinder(de.tr7zw.entitylib.nms.inter.PathfinderGoal goal) {
        this.goal = goal;
    }

    @Override     
    public boolean a() {
        return goal.shouldExecute();
    }     
    @Override     
    public boolean b() {
        return goal.continueExecuting();
    }     
    @Override     
    public boolean i() {
        return goal.isInterruptible();
    }     
    @Override
    public void c() { 
        goal.startExecuting();     
    }    
    @Override 
    public void d() {  
        goal.resetTask();  
    }  
    @Override  
    public void e() {   
        goal.updateTask();   
    }    
    

}
