package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class BlockPhysicsListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void onBlockPhysics(BlockPhysicsEvent e) {
		if ((e.getBlock().getType() == Material.SAND) || (e.getBlock().getType() == Material.GRAVEL)) {
			if (!wm.getWorldProperty(e.getBlock().getWorld().getName(), WorldProperty.PHYSICS)) {
				e.setCancelled(true);
				return;
			}
		}
		if ((wm.getWorldProperty(e.getBlock().getWorld().getName(), WorldProperty.ROPELADDERS)) && (e.getBlock().getType() == Material.LADDER)
				&& (e.getBlock().getRelative(0, 1, 0).getType() == Material.LADDER)) {
			e.setCancelled(true);
			return;
		}
	}

}
