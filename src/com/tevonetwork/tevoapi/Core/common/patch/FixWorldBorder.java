package com.tevonetwork.tevoapi.Core.common.patch;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Util.UtilServer;

import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_8_R3.WorldBorder;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder.EnumWorldBorderAction;

public class FixWorldBorder {

	public FixWorldBorder(World excludeworld)
	{
		for (Player p : UtilServer.getPlayers())
		{
			if (!p.getWorld().getName().equalsIgnoreCase(excludeworld.getName()))
			{
				WorldBorder nms_border = ((CraftWorld)p.getWorld()).getHandle().getWorldBorder();
				nms_border.setCenter(0.5, 0.5);
				nms_border.setSize(60000000);
				PacketPlayOutWorldBorder packet = new PacketPlayOutWorldBorder(nms_border, EnumWorldBorderAction.SET_SIZE);
				((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
				packet = new PacketPlayOutWorldBorder(nms_border, EnumWorldBorderAction.SET_CENTER);
				((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
			}
		}
	}
}
