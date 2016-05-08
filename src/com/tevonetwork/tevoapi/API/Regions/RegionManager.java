package com.tevonetwork.tevoapi.API.Regions;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RegionManager {
	
	private static HashMap<String, Region> regions = new HashMap<String, Region>();

	public static void createRegion(String regionname, Location blockmin, Location blockmax)
	{
		if ((blockmin.getWorld() != blockmax.getWorld()) || (blockmax.getWorld() != blockmin.getWorld()))
		{
			return;
		}
		
		regions.put(regionname, new Region(blockmin, blockmax));
		
	}
	
	public boolean isPlayerinRegion(String regionname, Player p)
	{
		if ((p == null) || (regionname == null))
		{
			return false;
		}
		if (regions.get(regionname).containsBlock(p.getLocation()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void removeRegion(String regionname)
	{
		if (regionname == null)
		{
			return;
		}
		
		regions.remove(regionname);
	}
	
}
