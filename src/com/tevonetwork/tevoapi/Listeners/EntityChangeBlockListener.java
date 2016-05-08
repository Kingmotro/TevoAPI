package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class EntityChangeBlockListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityChangeBlock(EntityChangeBlockEvent e)
	{
		Entity ent = e.getEntity();
		if (ent instanceof Enderman)
		{
			if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.ENDERMENGRIEFING))
			{
				e.setCancelled(true);
				return;
			}
		}
		if (ent instanceof FallingBlock)
		{
			FallingBlock fb = (FallingBlock) ent;
			if ((fb.getMaterial() != Material.GRAVEL) && (fb.getMaterial() != Material.SAND))
			{
				e.setCancelled(true);
				fb.getLocation().getWorld().playEffect(fb.getLocation(), Effect.STEP_SOUND, fb.getMaterial());
			}
		}
		
	}
	
}
