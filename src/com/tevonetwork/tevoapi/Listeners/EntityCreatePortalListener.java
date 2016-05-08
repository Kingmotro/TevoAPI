package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCreatePortalEvent;

public class EntityCreatePortalListener implements Listener {
	
	@EventHandler
	public void onEntityPortalCreate(EntityCreatePortalEvent e)
	{
		if (e.getEntityType() == EntityType.ENDER_DRAGON)
		{
			e.setCancelled(true);
			return;
		}
	}
	
}
