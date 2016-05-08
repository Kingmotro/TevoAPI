package com.tevonetwork.tevoapi.GameAPI;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class GameManager {

	
	//This is literally it for now just checks for players in game...
	private static ArrayList<String> players = new ArrayList<String>();
	
	public static void setPlayerinGame(Player p, boolean ingame)
	{
		if (ingame)
		{
			if (!players.contains(p.getName()))
			{
				players.add(p.getName());
			}
		}
		else
		{
			if (players.contains(p.getName()))
			{
				players.remove(p.getName());
			}
		}
	}
	
	public static boolean isPlayerInGame(Player p)
	{
		return players.contains(p.getName());
	}
	
}
