package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;

public class FoodLevelChangeListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player p = (Player)e.getEntity();
			if (!main.getWorldManager().getWorldProperty(p.getWorld().getName(), WorldProperty.HUNGER))
			{
				e.setCancelled(true);
				if (p.getFoodLevel() < 20)
				{
					p.setFoodLevel(20);
				}
			}
		}
	}
}
