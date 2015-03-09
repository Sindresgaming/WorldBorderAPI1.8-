package de.craftstuebchen.api.craftbukkit.world;

import net.minecraft.server.v1_8_R2.WorldBorder;
import org.bukkit.craftbukkit.v1_8_R2.CraftWorld;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldBorder_Border implements IWorldBorder {

    private WorldBorder border;

    public WorldBorder_Border(World bukkitWorld) {
        this.border = ((CraftWorld) bukkitWorld).getHandle().getWorldBorder();
    }

    public WorldBorder_Border(Player player) {
        try {
            this.border = WorldBorder.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setRadius(double radius){

    }
    @Override
    public double getWidth(){
        //TODO Implement
        return 0.0;
    }

    @Override
    public double getLength(){
        //TODO implement
        return 0.0;
    }

    public WorldBorder getHandle() {
        return border;
    }

    public double getCenterX() {
        return border.getCenterX();
    }

    public double getCenterY() {
        return border.getCenterZ();
    }

    public double getMinX() {
        return border.b();
    }

    public double getMaxX() {
        return border.e();
    }

    public double getMinY() {
        return border.c();
    }

    public double getMaxY() {
        return border.e();
    }

    public void setCenter(double x, double z) {
        border.setCenter(x, z);
    }


    @Override
    public double getDamageBufferInBlocks() {
        return border.getDamageBuffer();
    }

    @Override
    public void setDamageBufferInBlocks(int blocks) {
        border.setDamageBuffer(blocks);
    }

    @Override
    public double getDamagePerSecondPerBlock() {
        return border.getDamageAmount();
    }

    @Override
    public void setDamagerPerSecondPerBlock(double damage) {
        border.setDamageAmount(damage);
    }

    public int getWarningTimerInSeconds() {
        return border.getWarningTime();
    }

    public void setWarningTimeInSeconds(int seconds) {
        border.setWarningTime(seconds);
    }

    public int getWarningDistanceInBlocks() {
        return border.getWarningDistance();
    }

    public void setWarningDistanceInBlocks(int blocks) {
        border.setWarningDistance(blocks);
    }

    public boolean isInBounds(Location location) {
        return border.isInBounds(location.getBlockX(), location.getBlockZ());
    }

    public void lerp(double oldSize, double newSize, long time) {
        border.transitionSizeBetween(oldSize, newSize, time);
    }
}
