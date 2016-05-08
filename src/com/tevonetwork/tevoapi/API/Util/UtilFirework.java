package com.tevonetwork.tevoapi.API.Util;

import net.minecraft.server.v1_8_R3.EntityFireworks;
import net.minecraft.server.v1_8_R3.WorldServer;

import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class UtilFirework {

	public static void playFirework(Location loc, FireworkEffect... effects)
	{
		WorldServer nms_world = ((CraftWorld) loc.getWorld()).getHandle();
		EntityFireworks nms_fw = new EntityFireworks(((CraftWorld)loc.getWorld()).getHandle());
		Firework fw = (Firework) nms_fw.getBukkitEntity();
		FireworkMeta data = (FireworkMeta) fw.getFireworkMeta();
		data.clearEffects();
		data.setPower(1);
		data.addEffects(effects);
		fw.setFireworkMeta(data);
		nms_fw.setPosition(loc.getX(), loc.getY(), loc.getZ());
		nms_world.addEntity(nms_fw);
		nms_fw.setInvisible(true);
		nms_world.broadcastEntityEffect(nms_fw, (byte) 17);
		nms_fw.die();
	}
	
	public static void launchFirework(Location loc, FireworkEffect... effects)
	{
		Firework fw = (Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		FireworkMeta data = fw.getFireworkMeta();
		data.setPower(1);
		data.addEffects(effects);
		fw.setFireworkMeta(data);
	}
	
	public static void launchFirework(Location loc, int power, FireworkEffect... effects)
	{
		Firework fw = (Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		FireworkMeta data = fw.getFireworkMeta();
		data.setPower(power);
		data.addEffects(effects);
		fw.setFireworkMeta(data);
	}
	
}
