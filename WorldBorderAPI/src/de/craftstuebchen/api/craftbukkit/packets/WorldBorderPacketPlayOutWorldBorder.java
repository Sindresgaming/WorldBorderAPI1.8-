package de.craftstuebchen.api.craftbukkit.packets;

import de.craftstuebchen.api.craftbukkit.world.WorldBorder_Border;
import de.craftstuebchen.api.craftbukkit.world.WorldBorderAction;
import net.minecraft.server.v1_8_R2.PacketPlayOutWorldBorder;

public class WorldBorderPacketPlayOutWorldBorder {
	private PacketPlayOutWorldBorder borderPacket;

	public WorldBorderPacketPlayOutWorldBorder(WorldBorder_Border border, WorldBorderAction action) {

		this.borderPacket = new PacketPlayOutWorldBorder(
				border.getHandle(), action.getHandle());

	}

	public PacketPlayOutWorldBorder getHandle() {
		return borderPacket;
	}

}
