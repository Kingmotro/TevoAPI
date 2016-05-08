package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreakDoorEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class EntityBreakDoorListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBreakDoor(EntityBreakDoorEvent e)
	{
		if (!wm.getWorldProperty(e.getEntity().getWorld().getName(), WorldProperty.ZOMBIEDOORDAMAGE))
		{
			e.setCancelled(true);
		}
	}
	
}
