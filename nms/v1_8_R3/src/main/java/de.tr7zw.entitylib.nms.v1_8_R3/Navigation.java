package de.tr7zw.entitylib.nms.v1_8_R3;

import de.tr7zw.entitylib.nms.inter.Navigation;

public class Navigation extends Navigation{

    private net.minecraft.server.v1_8_R3.Navigation navi;
    
    public Navigation(Object obj) {
        super(obj);
        if(obj instanceof net.minecraft.server.v1_8_R3.Navigation){
            navi = (net.minecraft.server.v1_8_R3.Navigation) obj;
        }else{
            System.err.println("Navigation Wrapper got a wrong Object!");
        }
    }
    
    public boolean trywalkto(double x, double y, double z, double speed){
        return navi.a(x, y, z, speed);
    }
    
    public boolean hasPath(){
        return navi.m();
    }
    
    public void setSpeed(double speed){
        navi.a(speed);
    }
    
    public void updatePath(){
        navi.k();
    }

}
