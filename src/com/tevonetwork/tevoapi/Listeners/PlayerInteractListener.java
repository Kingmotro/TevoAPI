package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Portals.PortalManager;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.Travel.SendtoLocation;
import com.tevonetwork.tevoapi.Core.Travel.WarpManager;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class PlayerInteractListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			if (e.getPlayer().getItemInHand() != null)
			{
				if (e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD)
				{
					if (e.getPlayer().getItemInHand().hasItemMeta())
					{
						if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.cGreen + "Wand of Portalyness"))
						{
							Block block = e.getClickedBlock();
							PortalManager.setPlayerMAXSelection(e.getPlayer(), block.getLocation());
							UtilPlayer.message(Category.TRAVEL, e.getPlayer(), CC.tnInfo + "Second Position set to " + block.getLocation().toString());
							return;
						}
					}
				}
			}
			if (e.getClickedBlock().getState() instanceof Sign)
			{
				Sign sign = (Sign)e.getClickedBlock().getState();
				if (sign.getLines().length <= 0)
				{
					return;
				}
				if (sign.getLine(0).equalsIgnoreCase(CC.tnHead + "Warp"))
				{
					String warpname = sign.getLine(1).substring(2);
					if (WarpManager.getPublicWarp(warpname) != null)
					{
						new SendtoLocation(e.getPlayer(), WarpManager.getPublicWarp(warpname));
						UtilPlayer.message(Category.TRAVEL, e.getPlayer(), CC.tnInfo + "Warping " + CC.tnPlayer + "You " + CC.tnInfo + "to " + CC.tnValue + warpname + CC.end);
					}
					else
					{
						sign.getBlock().setType(Material.AIR);
						UtilPlayer.message(Category.TRAVEL, e.getPlayer(), CC.tnError + "Warp is invalid, sign removed!");
					}
				}
			}
		}
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
		{
			if (e.getPlayer().getItemInHand() != null)
			{
				if (e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD)
				{
					if (e.getPlayer().getItemInHand().hasItemMeta())
					{
						if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.cGreen + "Wand of Portalyness"))
						{
							Block block = e.getClickedBlock();
							PortalManager.setPlayerMINSelection(e.getPlayer(), block.getLocation());
							UtilPlayer.message(Category.TRAVEL, e.getPlayer(), CC.tnInfo + "First Position set to " + block.getLocation().toString());
						}
					}
				}
			}
		}
		if (e.getClickedBlock() != null)
		{
			if ((e.getClickedBlock().getTypeId() == 60) && (!wm.getWorldProperty(e.getClickedBlock().getWorld().getName(), WorldProperty.CROPTRAMPLING)))
			{
				e.setCancelled(true);
			}
		}
	}
	
}
