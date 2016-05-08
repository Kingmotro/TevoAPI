package com.tevonetwork.tevoapi.Listeners;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class EntityExplodeListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	private WorldManager wm = main.getWorldManager();
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityExplode(EntityExplodeEvent e)
	{
		if (wm.getWorldProperty(e.getEntity().getWorld().getName(), WorldProperty.BLOCKREGEN))
		{
			e.setYield(0.0F);
			List<BlockState> torestore = new ArrayList<BlockState>(); 
			
			for (Block blocks : e.blockList())
			{
				if ((blocks.getType() == Material.SAND) || (blocks.getType() == Material.GRAVEL))
				{
					try
					{
						e.blockList().remove(blocks);
					}
					catch (ConcurrentModificationException exception)
					{
					}
				}
				else
				{
					torestore.add(blocks.getState());
				}
			}
			
			restoreExplodedBlocks(torestore);
			
			for (Block blocks : e.blockList())
			{
				if ((!blocks.isLiquid()) && (blocks.getType() != Material.SAND) && (blocks.getType() != Material.GRAVEL))
				{
					Random random = new Random();
					boolean dofb = random.nextBoolean();
					if (dofb)
					{
						FallingBlock fb = blocks.getWorld().spawnFallingBlock(blocks.getLocation(), blocks.getType(), blocks.getData());
						fb.setDropItem(false);
						double x = new Random().nextDouble() * (1.0D - -1.0D) + -1.0D;
						double z = new Random().nextDouble() * (1.0D - -1.0D) + -1.0D;
						double y = new Random().nextDouble() * (0.7D - -0.7D) + -0.7D;
						Vector vector = new Vector(x, y, z);
						fb.setVelocity(vector);
					}
				}
			}
			return;
		}
		if (e.getEntity() != null)
		{
			Entity ent = e.getEntity();
			if (e.getEntity() instanceof Creeper)
			{
				if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.CREEPERBLOCKDAMAGE))
				{
					e.blockList().clear();
				}
			}
			else if (e.getEntity() instanceof EnderDragon)
			{
				if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.DRAGONBLOCKDAMAGE))
				{
					e.blockList().clear();
				}
			}
			else if ((e.getEntity() instanceof Fireball) || (e.getEntity() instanceof SmallFireball))
			{
				if (!wm.getWorldProperty(e.getEntity().getWorld().getName(), WorldProperty.FIREBALLBLOCKDAMAGE))
				{
					e.setCancelled(true);
				}
			}
			else if (e.getEntity() instanceof TNTPrimed)
			{
				if (!wm.getWorldProperty(ent.getWorld().getName(), WorldProperty.TNTBLOCKDAMAGE))
				{
					e.blockList().clear();
				}
			}
			else
			{
				if (e.getEntity() instanceof WitherSkull)
				{
					e.blockList().clear();
				}
			}
		}
		
	}
	
	private int restoreID = 0;

	public void restoreExplodedBlocks(final List<BlockState> blocks)
	{
		if (blocks.size() <= 0)
		{
			return;
		}
		restoreID++;
		final int ID = Integer.valueOf(restoreID);
		main.getUtilLogger().logNormal("BlockRegen> Started restoring " + blocks.size() + " blocks with regen ID: " + ID);
		
		new BukkitRunnable()
		{
			public void run()
			{
				Random random = new Random();
				int choice = random.nextInt(blocks.size());
				BlockState blockstate = blocks.get(choice);
				blockstate.update(true);
				blockstate.getWorld().playSound(blockstate.getLocation(), Sound.ITEM_PICKUP, 1.0F, 0.5F);
				blockstate.getWorld().spigot().playEffect(blockstate.getLocation(), Effect.HAPPY_VILLAGER, 0, 0, 0.5F, 0.5F, 0.5F, 0F, 2, 50);
				blocks.remove(choice);
				if (blocks.isEmpty())
				{
					cancel();
					main.getUtilLogger().logNormal("BlockRegen> Finished regen task with ID: " + ID);
				}
			}
		}.runTaskTimer(main, 20L, 3L);
		
	}
	
}
