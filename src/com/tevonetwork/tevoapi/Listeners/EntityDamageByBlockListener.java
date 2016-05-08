package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

public class EntityDamageByBlockListener implements Listener {
	
	@EventHandler
	public void onEntityDamagebyBlock(EntityDamageByBlockEvent e)
	{
		e.setCancelled(true);
	}
	
}
