package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.ItemStackFactory;
import com.tevonetwork.tevoapi.Core.Items;
import com.tevonetwork.tevoapi.Core.Travel.WarpManager;

public class InventoryOpenListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onInvOpen(InventoryOpenEvent e)
	{
		if (e.getInventory().getName().contains("Warps"))
		{
			Inventory inv = e.getInventory();
			ItemStack loading = new ItemStackFactory().createItemStack(Items.WHITESTAINEDGLASS, CC.tnInfo + "Loading Warps...");
			inv.setItem(0, loading);
			if (inv.getName().contains("Public"))
			{
				inv.setContents(WarpManager.constructPublicWarpArray());
				if (WarpManager.getPublicWarpCount() >= 50)
				{
					inv.setItem(51, new ItemStackFactory().createItemStack(Items.REDWOOL, CC.tnError + "Public warps list is full!"));
				}
			}
			else
			{
				String[] title = inv.getName().split("'");
				inv.setContents(WarpManager.constructPlayerWarpArray(title[0]));
				if (WarpManager.getWarpCount(title[0]) >= 32)
				{
					inv.setItem(33, new ItemStackFactory().createItemStack(Items.REDWOOL, CC.tnError + "Warps list is full!"));
				}
			}
		}
	}
}
