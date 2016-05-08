package com.tevonetwork.tevoapi.API.Util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class UtilEnt {

	//Utils
		public static List<Player> getinRadius(Entity ent, int distance)
		{
			List<Player> players = new ArrayList<Player>();
			for (Player player : UtilServer.getPlayers())
			{
				if (player.getWorld() == ent.getWorld())
				{
					if (player.getLocation().distance(ent.getLocation()) <= distance)
					{
						players.add(player);
					}
				}
			}
			return players;
		}
	
}
