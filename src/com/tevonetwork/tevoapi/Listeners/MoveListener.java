package com.tevonetwork.tevoapi.Listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.BossBar.BossBar;
import com.tevonetwork.tevoapi.API.BossBar.BossBarAPI;
import com.tevonetwork.tevoapi.API.Portals.PortalManager;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class MoveListener implements Listener{

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	private HashMap<String, Integer> waterdmgtasks = new HashMap<String, Integer>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e)
	{
		final Player p = e.getPlayer();
		Location from = e.getFrom();
		Location to = e.getTo();
		if (from.distance(to) >= 0.2)
		{
			PortalManager.checkPlayerinPortal(p);
		}
		if (e.getPlayer().getLocation().getY() <= -60D)
		{
			if (!wm.getWorldProperty(p.getWorld().getName(), WorldProperty.VOIDDAMAGE))
			{
				Location spawn = wm.getWorldSpawn(p.getWorld().getName());
				p.teleport(spawn);
			}
		}
		if ((p.getLocation().getBlock().getType().equals(Material.WATER)) || (p.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER)))
		{
			if (wm.getWorldProperty(p.getWorld().getName(), WorldProperty.WATERDAMAGE))
			{
				if ((p.getGameMode() == GameMode.ADVENTURE) || (p.getGameMode() == GameMode.SURVIVAL))
				{
					if (!waterdmgtasks.containsKey(p.getName()))
					{
						waterdmgtasks.put(p.getName(), Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable()
						{
							public void run()
							{
								p.damage(3.0);
								p.playSound(p.getLocation(), Sound.SPLASH, 2F, 1F);
								if (p.getHealth() <= 0.0)
								{
									Bukkit.getScheduler().cancelTask(waterdmgtasks.get(p.getName()));
									waterdmgtasks.remove(p.getName());
								}
							}
						}, 0L, 16L)));
					}
				}
			}
		}
		else
		{
			if (waterdmgtasks.containsKey(p.getName()))
			{
				Bukkit.getScheduler().cancelTask(waterdmgtasks.get(p.getName()));
				waterdmgtasks.remove(p.getName());
			}
		}
		final BossBar bar = BossBarAPI.getBossBar(e.getPlayer());
		if (bar != null) {
			new BukkitRunnable() {

				@Override
				public void run() {
					if (!p.isOnline()) return;
					bar.updateMovement();
				}
			}.runTaskLater(main, 0);
		}
	}
	
}
