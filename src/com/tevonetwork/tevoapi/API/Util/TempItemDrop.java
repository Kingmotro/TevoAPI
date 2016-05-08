package com.tevonetwork.tevoapi.API.Util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.LogLevel;

public class TempItemDrop {
	
	
	public TempItemDrop(ItemStack is, Location droploc, long lifeticks)
	{
		if ((is != null) && (lifeticks > 0))
		{
			final Item drop = droploc.getWorld().dropItem(droploc, is);
			int delay = (int) lifeticks + 20;
			drop.setPickupDelay(delay);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TevoAPI.getInstance(), new Runnable()
			{
				public void run()
				{
					drop.remove();
				}
			}, lifeticks);
			
		}
		else
		{
			TevoAPI.getInstance().getUtilLogger().logLevel(LogLevel.WARNING, "TempItemDrop> Why the fuck is there null being passed to a temp item drop?");
			return;
		}
	}
	
	
}
