package com.tevonetwork.tevoapi.API.Scoreboards;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Gamemodes;
import com.tevonetwork.tevoapi.Core.Rank;
/**
 * Manage the Tevo network scoreboard for players.
 * 
 * @author Thrusmyster
 *
 */
public class ScoreboardManager {

	private static HashMap<String, PlayerScoreboard> scoreboards = new HashMap<String, PlayerScoreboard>();
	
	/**
	 * Sets up the player scoreboard in accordance with the current gamemode.
	 * 
	 * @param p
	 * @param gamemode
	 */
	public static void setupScoreboard(Player p, Gamemodes gamemode)
	{
		scoreboards.put(p.getName(), new PlayerScoreboard(p, gamemode));
		for (Player players : Bukkit.getOnlinePlayers())
		{
			Rank rank = UtilPlayer.getRank(players);
			scoreboards.get(p.getName()).addPlayerRank(players, rank);
		}
	}
	
	/**
	 * To be used when a player stat gets updated.
	 * 
	 * @param p
	 */
	public static void forceUpdate(Player p)
	{
		if (scoreboards.containsKey(p.getName()))
		{
			scoreboards.get(p.getName()).update();
		}
	}
	
	public static void updateRank(Player p)
	{
		if (!scoreboards.containsKey(p.getName()))
		{
			return;
		}
		scoreboards.get(p.getName()).updateRank();
	}
	
	public static void updateTokens(Player p)
	{
		if (!scoreboards.containsKey(p.getName()))
		{
			return;
		}
		scoreboards.get(p.getName()).updateTokens();
	}
	
	public static void updateKills(Player p)
	{
		if (!scoreboards.containsKey(p.getName()))
		{
			return;
		}
		scoreboards.get(p.getName()).updateKills();
	}
	
	public static void updateDeaths(Player p)
	{
		if (!scoreboards.containsKey(p.getName()))
		{
			return;
		}
		scoreboards.get(p.getName()).updateDeaths();
	}
	
	public static void updateGames(Player p)
	{
		if (!scoreboards.containsKey(p.getName()))
		{
			return;
		}
		scoreboards.get(p.getName()).updateGames();
	}
	
	public static void updateWins(Player p)
	{
		if (!scoreboards.containsKey(p.getName()))
		{
			return;
		}
		scoreboards.get(p.getName()).updateWins();
	}
	
	public static void updateKD(Player p)
	{
		if (!scoreboards.containsKey(p.getName()))
		{
			return;
		}
		scoreboards.get(p.getName()).updateKD();
	}
	
	public static void updateGamemode(Player p, Gamemodes gamemode)
	{
		if (!scoreboards.containsKey(p.getName()))
		{
			return;
		}
		scoreboards.get(p.getName()).setGamemode(gamemode);
	}
	
	/**
	 * Checks if a player has the scoreboard.
	 * 
	 * @param p
	 * @return
	 */
	public static boolean hasScoreboard(Player p)
	{
		return scoreboards.containsKey(p.getName());
	}
	
	/**
	 * To be used on logout.
	 * 
	 * @param p
	 */
	public static void removeScoreboard(Player p)
	{
		if (!scoreboards.containsKey(p.getName()))
		{
			return;
		}
		scoreboards.get(p.getName()).end();
		scoreboards.remove(p.getName());
		
	}
	
	public static boolean addPlayerRank(Player p)
	{
		Collection<PlayerScoreboard> boards = scoreboards.values();
		Iterator<PlayerScoreboard> itr = boards.iterator();
		while(itr.hasNext())
		{
			Rank rank = UtilPlayer.getRank(p);
			itr.next().addPlayerRank(p, rank);
		}
		return true;
	}
	
	public static boolean removePlayerRank(Player p)
	{
		Collection<PlayerScoreboard> boards = scoreboards.values();
		Iterator<PlayerScoreboard> itr = boards.iterator();
		while(itr.hasNext())
		{
			itr.next().removePlayerRank(p);
		}
		return true;
	}
}
