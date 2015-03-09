package de.craftstuebchen.api.craftbukkit.entity;

import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.craftstuebchen.api.craftbukkit.packets.WorldBorderPacketPlayOutWorldBorder;

public class WorldBorderPlayer {

    private CraftPlayer player;

    public WorldBorderPlayer(Player player) {
        this.player = (CraftPlayer) player;
    }

    public void sendPacket(WorldBorderPacketPlayOutWorldBorder packetPlayOutWorldBorder) {
        player.getHandle().playerConnection.sendPacket(packetPlayOutWorldBorder
                .getHandle());
    }

}
