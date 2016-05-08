package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class WeatherChangeListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onWeatherChange(WeatherChangeEvent e)
	{
		if (!wm.getWorldProperty(e.getWorld().getName(), WorldProperty.WEATHERCHANGE))
		{
			e.setCancelled(true);
		}
	}
	
}
