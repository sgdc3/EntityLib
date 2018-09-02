package de.tr7zw.entitylib.nms;

public abstract class Navigation {

    public Navigation(Object object) {
    }

    public abstract boolean tryWalkTo(double x, double y, double z, double speed);

    public abstract boolean hasPath();

    public abstract void setSpeed(double speed);

    public abstract void updatePath();
}
