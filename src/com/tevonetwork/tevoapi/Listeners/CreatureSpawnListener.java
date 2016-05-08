package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class CreatureSpawnListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCreatureSpawn(CreatureSpawnEvent e)
	{
		if ((e.getSpawnReason() == SpawnReason.CUSTOM) || (e.getSpawnReason() == SpawnReason.SPAWNER_EGG) || (e.getSpawnReason() == SpawnReason.BUILD_SNOWMAN))
		{
			return;
		}
		if (!wm.getWorldProperty(e.getEntity().getWorld().getName(), WorldProperty.NATURALMOBSPAWN))
		{
			if ((e.getEntity() instanceof LivingEntity) && (!(e.getEntity() instanceof Player)))
			{
					e.setCancelled(true);
			}
		}
	}
}
