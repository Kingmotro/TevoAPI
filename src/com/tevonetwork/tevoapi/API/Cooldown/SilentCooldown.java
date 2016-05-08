package com.tevonetwork.tevoapi.API.Cooldown;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.tevonetwork.tevoapi.TevoAPI;

public class SilentCooldown {

	//Used for more precise cooldowns, does not send any messages or sounds.
	
	private static TevoAPI instance = TevoAPI.getInstance();
	
	private static Table<Player, String, Integer> SilentCooldowns = HashBasedTable.create();
	private static Table<Player, String, BukkitRunnable> Tasks = HashBasedTable.create();
	
	/**
	 * Setup new silent cooldown for player.
	 * 
	 * @param p The player to put on a cooldown
	 * @param ability The ability the cooldown affects
	 * @param time The duration of the cooldown in .1 of a second 10 being a full second.
	 */
	public static void addSilentCooldown(final Player p, final String ability, int time)
	{
		SilentCooldowns.put(p, ability, time);
		Tasks.put(p, ability, new BukkitRunnable()
		{
			public void run()
			{
				SilentCooldowns.put(p, ability, SilentCooldowns.get(p, ability) - 1);
				if (SilentCooldowns.get(p, ability) == 0)
				{
					SilentCooldowns.remove(p, ability);
					Tasks.remove(p, ability);
					cancel();
				}
			}
		});
		
		Tasks.get(p, ability).runTaskTimer(instance, 2, 2);
	}
	
	/**
	 * Check if a player has a certasin ability on cooldown.
	 * 
	 * @param p The player to check for cooldown
	 * @param ability The ability to check for cooldown
	 * @return Returns true if player has cooldown
	 */
	public static boolean isPlayeronSilentCooldown(Player p, String ability)
	{
		if (SilentCooldowns.contains(p, ability))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	 * Gets the remaining time in seconds that the player has to wait for the cooldown to expire.
	 * 
	 * @param p The player to get cooldown time for
	 * @param ability THe ability to get cooldown time for
	 */
	public static int getPlayerRemainingTime(Player p, String ability)
	{
		if (SilentCooldowns.contains(p, ability))
		{
			int timeremaining = SilentCooldowns.get(p, ability);
			return timeremaining;
		}
		else
		{
			return 0;
		}
	}
	
	
}
