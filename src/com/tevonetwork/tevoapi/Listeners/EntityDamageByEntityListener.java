package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Events.PVPEvent;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;
import com.tevonetwork.tevoapi.Core.common.JavaCommon;

public class EntityDamageByEntityListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDamagebyEntity(EntityDamageByEntityEvent e)
	{
		if ((e.getDamager() instanceof LivingEntity) && (!(e.getDamager() instanceof Player)))
		{
			if (!wm.getWorldProperty(e.getDamager().getWorld().getName(), WorldProperty.MOBDAMAGE))
			{
				e.setCancelled(true);
				return;
			}
		}
		if ((e.getDamager() instanceof Player) && (e.getEntity() instanceof Player))
		{
			if (!wm.getWorldProperty(e.getEntity().getWorld().getName(), WorldProperty.PVP))
			{
				e.setCancelled(true);
				return;
			}
			else
			{
				Player p = (Player)e.getEntity();
				Player attacker = (Player)e.getDamager();
				PVPEvent event = new PVPEvent(p, attacker);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if (event.isCancelled())
				{
					e.setCancelled(true);
					if (event.getCancelMessage() != null)
					{
						UtilPlayer.message(Category.PVP, attacker, event.getCancelMessage());
					}
				}
			}
		}
		if (e.getDamager() instanceof Projectile)
		{
			Projectile proj = (Projectile) e.getDamager();
			if ((proj.getShooter() instanceof LivingEntity) && (!(proj.getShooter() instanceof Player)))
			{
				if (!wm.getWorldProperty(e.getEntity().getWorld().getName(), WorldProperty.MOBDAMAGE))
				{
					e.setCancelled(true);
					return;
				}
			}
			if ((proj.getShooter() instanceof Player) && (e.getEntity() instanceof Player))
			{
				if (!wm.getWorldProperty(e.getEntity().getWorld().getName(), WorldProperty.PVP))
				{
					e.setCancelled(true);
					return;
				}
				else
				{
					Player p = (Player)e.getEntity();
					Player attacker = (Player)proj.getShooter();
					PVPEvent event = new PVPEvent(p, attacker);
					Bukkit.getServer().getPluginManager().callEvent(event);
					if (event.isCancelled())
					{
						e.setCancelled(true);
						if (event.getCancelMessage() != null)
						{
							UtilPlayer.message(Category.PVP, attacker, event.getCancelMessage());
						}
					}
				}
			}
		}
		
		if (e.getEntity() instanceof Player)
		{
			Player p = (Player)e.getEntity();
			if (e.getDamager().hasMetadata("Ability"))
			{
				UtilPlayer.setLastDamageCause(p, e.getDamager().getMetadata("Ability").get(0).asString());
			}
			else if (e.getDamager() instanceof Projectile)
			{
				if (e.getDamager() instanceof Arrow)
				{
					UtilPlayer.setLastDamageCause(p, "Archery");
				}
				else if (e.getDamager() instanceof EnderPearl)
				{
					UtilPlayer.setLastDamageCause(p, "Ender Pearl Teleport");
				}
				else if ((e.getDamager() instanceof Fireball) || (e.getDamager() instanceof SmallFireball))
				{
					UtilPlayer.setLastDamageCause(p, "Fireball");
				}
				else if (e.getDamager() instanceof WitherSkull)
				{
					UtilPlayer.setLastDamageCause(p, "Wither Skull");
				}
			}
			else if (e.getDamager() instanceof TNTPrimed)
			{
				UtilPlayer.setLastDamageCause(p, "TNT");
			}
			else if (e.getDamager() instanceof Creeper)
			{
				UtilPlayer.setLastDamageCause(p, "Creeper Explosion");
			}
			else if (e.getDamager() instanceof Player)
			{
				Player attacker = (Player)e.getDamager();
				String item = "Punch";
				if ((attacker.getItemInHand() != null) && (attacker.getItemInHand().getType() != Material.AIR))
				{
					if (attacker.getItemInHand().hasItemMeta())
					{
						if (attacker.getItemInHand().getItemMeta().getDisplayName() != null)
						{
							item = attacker.getItemInHand().getItemMeta().getDisplayName();
						}
						else
						{
							item = JavaCommon.convertEnum(attacker.getItemInHand().getType().toString());
						}
					}
					else
					{
						item = JavaCommon.convertEnum(attacker.getItemInHand().getType().toString());
					}
				}
				UtilPlayer.setLastDamageCause(p, item);
			}
			else
			{
				UtilPlayer.setLastDamageCause(p, e.getDamager().getName());
			}
			
			if (e.getDamager() instanceof Player)
			{
				UtilPlayer.addDamager(p, e.getDamager().getName());
			}
			else if (e.getDamager().hasMetadata("AbilityOwner"))
			{
				UtilPlayer.addDamager(p, e.getDamager().getMetadata("AbilityOwner").get(0).asString());
			}
			else if (e.getDamager() instanceof Projectile)
			{
				if (((Projectile)e.getDamager()).getShooter() instanceof Player)
				{
					Player shooter = (Player)((Projectile)e.getDamager()).getShooter();
					UtilPlayer.addDamager(p, shooter.getName());
				}
			}
			
		}
	}
	
}
