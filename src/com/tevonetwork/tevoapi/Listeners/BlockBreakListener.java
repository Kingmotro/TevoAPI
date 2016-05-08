package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class BlockBreakListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD) {
			if (e.getPlayer().getItemInHand().hasItemMeta()) {
				if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.cGreen + "Wand of Portalyness")) {
					e.setCancelled(true);
				}
			}
		}
		if (UtilPlayer.hasRank(p, Rank.BUILDER)) {
			if ((!wm.getWorldProperty(p.getWorld().getName(), WorldProperty.STAFFBUILD)) && (!UtilPlayer.hasRank(p, Rank.ADMIN))) {
				e.setCancelled(true);
			}
		}
		else if (!wm.getWorldProperty(p.getWorld().getName(), WorldProperty.BUILD)) {
			e.setCancelled(true);
		}
	}

}
