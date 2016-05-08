package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.GUI.ConfirmationMenu;
import com.tevonetwork.tevoapi.Core.Travel.SendtoLocation;
import com.tevonetwork.tevoapi.Core.Travel.WarpManager;

public class InventoryClickListener implements Listener {

	TevoAPI main = TevoAPI.getInstance();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onInvClick(InventoryClickEvent e)
	{
		if (!(e.getWhoClicked() instanceof Player))
		{
			return;
		}
		if (e.getCurrentItem() == null)
		{
			return;
		}
		if (!e.getCurrentItem().hasItemMeta())
		{
			return;
		}
		if (e.getInventory().getName().startsWith("Confirmation"))
		{
			Player p = (Player)e.getWhoClicked();
			ItemStack is = e.getCurrentItem();
			if (WarpManager.hasPendingDelete(p))
			{
				if (is.getItemMeta().getDisplayName().equalsIgnoreCase("§aconfirm"))
				{
					String warpname = WarpManager.getPendingDeletion(p);
					if (warpname.startsWith("public§"))
					{
						String[] publicwarpname = warpname.split("§");
						if (WarpManager.deletePublicWarp(publicwarpname[1]))
						{
							UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully deleted public warp " + CC.tnValue + publicwarpname[1] + CC.end);
						}
						else
						{
							UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to delete " + CC.tnValue + publicwarpname[1] + CC.end);
						}
					}
					else
					{
						if (WarpManager.deletePlayerWarp(p.getName(), warpname))
						{
							UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully deleted warp " + CC.tnValue + warpname + CC.end);
						}
						else
						{
								UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to delete " + CC.tnValue + warpname + CC.end);
						}
					}
					p.closeInventory();
				}
				if (is.getItemMeta().getDisplayName().equalsIgnoreCase("§ccancel"))
				{
					String warpname = WarpManager.getPendingDeletion(p);
					if (warpname.startsWith("public§"))
					{
						String[] array = warpname.split("§");
						warpname = array[1];
					}
					UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Canceled deletion of " + CC.tnValue + warpname + CC.end);
					p.closeInventory();
				}
			}
			e.setCancelled(true);
		}
		if (e.getInventory().getName().contains("Warps"))
		{
			Inventory inv = e.getInventory();
			if (e.getClick() == ClickType.LEFT)
			{
				if (inv.getName().contains("Public"))
				{
					Player p = (Player)e.getWhoClicked();
					String itemname = e.getCurrentItem().getItemMeta().getDisplayName();
					String warpname = itemname.substring(2, itemname.length());
					Location dest = WarpManager.getPublicWarp(warpname);
					if (dest != null)
					{
						p.closeInventory();
						UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Warping " + CC.tnPlayer + "You " + CC.tnInfo + "to " + CC.tnValue + warpname + CC.end + "..");
						new SendtoLocation(p, dest);
					}

				}
				else
				{
					String[] title = inv.getName().split("'");
					String playername = title[0];
					Player p = (Player)e.getWhoClicked();
					String itemname = e.getCurrentItem().getItemMeta().getDisplayName();
					String warpname = itemname.substring(2, itemname.length());
					Location dest = WarpManager.getPlayerWarp(playername, warpname);
					if (dest != null)
					{
						UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Warping " + CC.tnPlayer + "You " + CC.tnInfo + "to " + CC.tnValue + warpname + CC.end);
						new SendtoLocation(p, dest);
					}

				}
			}
			if (e.getClick() == ClickType.RIGHT)
			{
					Player p = (Player)e.getWhoClicked();
					if (e.getInventory().getName().contains("Public"))
					{

						String itemname = e.getCurrentItem().getItemMeta().getDisplayName();
						String warpname = itemname.substring(2, itemname.length());
						WarpManager.setPendingDelete("public§" + warpname, p);
						ConfirmationMenu conf = new ConfirmationMenu();
						conf.setDescription("Are you sure you want to delete " + CC.tnValue + warpname + CC.tnInfo + "?");
						conf.open(p);
					}
					else
					{
						String itemname = e.getCurrentItem().getItemMeta().getDisplayName();
						String warpname = itemname.substring(2, itemname.length());
						WarpManager.setPendingDelete(warpname, p);
						ConfirmationMenu conf = new ConfirmationMenu();
						conf.setDescription("Are you sure you want to delete " + CC.tnValue + warpname + CC.tnInfo + "?");
						conf.open(p);
					}
			}
			e.setCancelled(true);
		}
	}
}
