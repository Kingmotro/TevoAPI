package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;

public class PlayerCommandPreProcessListener implements Listener {

	@EventHandler
	public void onCMDProcess(PlayerCommandPreprocessEvent e)
	{
		if ((e.getMessage().equalsIgnoreCase("/pl")) || (e.getMessage().equalsIgnoreCase("/plugins")))
		{
			if (!e.getPlayer().isOp())
			{
				e.setCancelled(true);
				Player p = e.getPlayer();
				UtilPlayer.message(Category.SERVER, p, CC.tnInfo + "About 90% of the plugins we use here are custom coded by " + CC.tnOwner + "Owner " + CC.cGray + "Thrusmyster" + CC.cYellow + " and are not available to the public.");
			}
		}
	}
	
}
