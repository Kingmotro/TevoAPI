package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class LeavesDecayListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@EventHandler
	public void onDecay(LeavesDecayEvent e)
	{
		if (!wm.getWorldProperty(e.getBlock().getWorld().getName(), WorldProperty.LEAFDECAY))
		{
			e.setCancelled(true);
			return;
		}
	}
	
}
