package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class BlockPlaceListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreak(BlockPlaceEvent e)
	{
		Player p = e.getPlayer();
		if (UtilPlayer.hasRank(p, Rank.BUILDER))
		{
			if ((!wm.getWorldProperty(p.getWorld().getName(), WorldProperty.STAFFBUILD)) && (!UtilPlayer.hasRank(p, Rank.ADMIN)))
			{
				e.setCancelled(true);
			}
		}
		else if (!wm.getWorldProperty(p.getWorld().getName(), WorldProperty.BUILD))
		{
			e.setCancelled(true);
		}
	}
	
}
