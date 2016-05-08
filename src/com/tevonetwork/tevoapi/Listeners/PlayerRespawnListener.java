package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.tevonetwork.tevoapi.TevoAPI;


public class PlayerRespawnListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onRespawn(PlayerRespawnEvent e)
	{
		Player p = e.getPlayer();
		e.setRespawnLocation(main.getWorldManager().getWorldSpawn(p.getWorld().getName()));
	}
	
}
