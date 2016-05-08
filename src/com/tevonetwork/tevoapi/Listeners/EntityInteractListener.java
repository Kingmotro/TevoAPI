package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class EntityInteractListener implements Listener{

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityInteract(EntityInteractEvent e)
	{
		if ((e.getBlock().getTypeId() == 60) && (!wm.getWorldProperty(e.getBlock().getWorld().getName(), WorldProperty.CROPTRAMPLING)))
		{
			e.setCancelled(true);
		}
	}
	
}
