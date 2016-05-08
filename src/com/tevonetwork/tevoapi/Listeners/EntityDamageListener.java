package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Events.PlayerDamageEvent;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;
import com.tevonetwork.tevoapi.Core.common.JavaCommon;

public class EntityDamageListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e)
	{
		Entity ent = e.getEntity();
		EntityDamageEvent.DamageCause type = e.getCause();
		if (e.getEntity() instanceof Player)
		{
			Player p = (Player)e.getEntity();
			PlayerDamageEvent event = new PlayerDamageEvent(p, e.getCause(), e.getDamage());
			Bukkit.getServer().getPluginManager().callEvent(event);
			if (event.isCancelled())
			{
				e.setCancelled(true);
				return;
			}
			if (event.getDamage() != e.getDamage())
			{
				e.setDamage(event.getDamage());
			}
		}
		if (type == DamageCause.CONTACT)
		{
			if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.CONTACTDAMAGE))
			{
				e.setCancelled(true);
				return;
			}
		}
		if (type == DamageCause.DROWNING)
		{
			if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.DROWNDAMAGE))
			{
				e.setCancelled(true);
				if (ent instanceof LivingEntity)
				{
					((LivingEntity) ent).setRemainingAir(((LivingEntity) ent).getMaximumAir());
				}
				return;
			}
		}
		if ((type == DamageCause.BLOCK_EXPLOSION) || (type == DamageCause.ENTITY_EXPLOSION))
		{
			if (ent instanceof Player)
			{
				if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.EXPLODEDAMAGE))
				{
					e.setCancelled(true);
					return;
				}
			}
		}
		if ((type == DamageCause.FALL))
		{
			if (ent instanceof Player)
			{
				if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.FALLDAMAGE))
				{
					e.setCancelled(true);
					return;
				}
			}
		}
		if ((type == DamageCause.FIRE) || (type == DamageCause.FIRE_TICK))
		{
			if (ent instanceof Player)
			{
				if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.FIREDAMAGE))
				{
					e.setCancelled(true);
					return;
				}
			}
		}
		if (type == DamageCause.LIGHTNING)
		{
			if (ent instanceof Player)
			{
				if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.LIGHTNINGDAMAGE))
				{
					e.setCancelled(true);
					return;
				}
			}
		}
		if (type == DamageCause.SUFFOCATION)
		{
			if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.SUFFOCATIONDAMAGE))
			{
				e.setCancelled(true);
				return;
			}
		}
		if (ent instanceof Player)
		{
			if ((type != DamageCause.ENTITY_ATTACK) && (type != DamageCause.BLOCK_EXPLOSION) && (type != DamageCause.ENTITY_EXPLOSION) && (type != DamageCause.PROJECTILE) && (type != DamageCause.CUSTOM))
			{
				UtilPlayer.setLastDamageCause(((Player)ent), JavaCommon.convertEnum(type.toString()));
			}
		}
	}
}
