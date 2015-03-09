package de.craftstuebchen.api.worldborder;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import de.craftstuebchen.api.craftbukkit.entity.WorldBorderPlayer;
import de.craftstuebchen.api.craftbukkit.packets.WorldBorderPacketPlayOutWorldBorder;
import de.craftstuebchen.api.craftbukkit.world.WorldBorder_Border;
import de.craftstuebchen.api.craftbukkit.world.WorldBorderAction;

public class WorldBorderAPI {

    private static WorldBorderAPI inst = new WorldBorderAPI();

    private HashSet<Player> customWorldBorder = new HashSet<Player>();

    public static WorldBorderAPI inst() {
        return inst;
    }

    public void sendRedScreen(Player player) {
        this.sendRedScreen(player, 10);
    }

    /**
     * Will send a red edge to the given player a certain Time
     *
     * @param player The player that will get the red edge on screen
     * @param time   The time the red edge disappears
     */
    public void sendRedScreen(Player player, int time) {

        WorldBorder_Border border = new WorldBorder_Border(player);

        border.setWarningDistanceInBlocks((int) border.getLength() / 2);

        WorldBorderPacketPlayOutWorldBorder borderpacket = new WorldBorderPacketPlayOutWorldBorder(
                border, WorldBorderAction.SET_WARNING_BLOCKS);

        border.setWarningDistanceInBlocks(0);

        WorldBorderPacketPlayOutWorldBorder WT = new WorldBorderPacketPlayOutWorldBorder(border,
                WorldBorderAction.SET_WARNING_BLOCKS);

        sentPacket(player, borderpacket);
        customWorldBorder.add(player);

        Bukkit.getScheduler().runTaskLater(WorldBorderPlugin.inst(),
                new Runnable() {

                    @Override
                    public void run() {
                        sentPacket(player, WT);
                        customWorldBorder.remove(player);
                    }
                }, time * 20L);

    }

    /**
     * Will set a custom border for player
     *
     * @param player The player who will get the border
     * @param radius The borderradius
     */
    public void setBorder(Player player, double radius) {
        setBorder(player, radius, player.getWorld().getSpawnLocation());
    }

    /**
     * Will set a custom border for player
     *
     * @param player   The player who will get the border
     * @param radius   The borderradius
     * @param location The center location of the worldborder
     */
    public void setBorder(Player player, double radius, Location location) {

        WorldBorder_Border border = new WorldBorder_Border(player);

        border.setRadius(radius);
        border.setCenter(location.getX(), location.getZ());

        sentPacket(player, new WorldBorderPacketPlayOutWorldBorder(border,
                WorldBorderAction.SET_SIZE));
        customWorldBorder.add(player);
    }

    /**
     * Will set the border for the given player centered at worldspawnlocation
     *
     * @param player  The player who will get the border
     * @param radius  the radius of the border
     * @param seconds The seconds until the border will reappear
     */
    public void setBorder(Player player, double radius, int seconds) {
        WorldBorder_Border border = new WorldBorder_Border(player);
        border.lerp(border.getLength(), radius, seconds);
        sentPacket(player, new WorldBorderPacketPlayOutWorldBorder(border,
                WorldBorderAction.LERP_SIZE));
    }

    /**
     * Will set the border for everyone after 10 seconds
     *
     * @param radius The radius of the border
     */
    public void setBorder(double radius) {
        setBorder(radius, 10);
    }

    /**
     * Will set the border for everyone
     *
     * @param radius  The radius of the border
     * @param seconds The seconds until border reappear
     */
    public void setBorder(double radius, int seconds) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            setBorder(p, radius, seconds);
        }
    }

    /**
     * Will set a border for given player
     *
     * @param player The player that gets the border
     * @param border The border that will be set
     * @param action The action of the border
     */
    public void setBorder(Player player, WorldBorder_Border border,
                          WorldBorderAction action) {
        sentPacket(player, new WorldBorderPacketPlayOutWorldBorder(border, action));
    }

    private void sentPacket(Player player, WorldBorderPacketPlayOutWorldBorder packet) {
        WorldBorderPlayer cplayer = (WorldBorderPlayer) player;
        cplayer.sendPacket(packet);
    }

    public WorldBorder_Border getWorldBorder(Player p) {
        return new WorldBorder_Border(p);
    }

}
