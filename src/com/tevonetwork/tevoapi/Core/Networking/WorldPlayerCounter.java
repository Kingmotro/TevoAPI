package com.tevonetwork.tevoapi.Core.Networking;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldPlayerCounter implements Runnable {

private static HashMap<String, Integer>worlds = new HashMap<String, Integer>();
	
	public void run() 
	{
		worlds.clear();
		
		for (World world : Bukkit.getWorlds())
		{
			List<Player> players = world.getPlayers();
			int count = 0;
			for (Player player : players)
			{
				if (!player.hasMetadata("NPC"))
				{
					count++;
				}
			}
			worlds.put(world.getName(), Integer.valueOf(count));
		}
	}
	public static Integer getWorldCount(String world)
	{
		int none = 0;
		if (!worlds.containsKey(world))
		{
			return none;
		}
		else
		{
		Integer count = (Integer)worlds.get(world);
		return count;
		}
	}

}
