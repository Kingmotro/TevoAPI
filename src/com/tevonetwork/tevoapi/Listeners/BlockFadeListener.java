package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class BlockFadeListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockFade(BlockFadeEvent e) {
		if ((e.getBlock().getTypeId() == 79) || (e.getBlock().getTypeId() == 78)) {
			if (!wm.getWorldProperty(e.getBlock().getWorld().getName(), WorldProperty.ICESNOWMELT)) {
				e.setCancelled(true);
				return;
			}
		}
	}
}
