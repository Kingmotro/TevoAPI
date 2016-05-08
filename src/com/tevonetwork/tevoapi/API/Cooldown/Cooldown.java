package com.tevonetwork.tevoapi.API.Cooldown;

import java.util.Map;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;

public class Cooldown {

	private static TevoAPI instance = TevoAPI.getInstance();

	private static Table<Player, String, Integer> Cooldowns = HashBasedTable.create();
	private static Table<Player, String, BukkitRunnable> Tasks = HashBasedTable.create();

	/**
	 * Setup new cooldown for player.
	 * 
	 * @param p
	 *            The player to put on a cooldown
	 * @param ability
	 *            The ability the cooldown affects
	 * @param time
	 *            The duration of the cooldown in seconds.
	 */
	public static void addCooldown(final Player p, final String ability, int time) {
		Cooldowns.put(p, ability, time);
		Tasks.put(p, ability, new BukkitRunnable() {
			public void run() {
				Cooldowns.put(p, ability, Cooldowns.get(p, ability) - 1);
				if (Cooldowns.get(p, ability) == 0) {
					Cooldowns.remove(p, ability);
					Tasks.remove(p, ability);
					UtilPlayer.message(Category.COOLDOWN, p, CC.tnInfo + "You can now use " + CC.tnAbility + ability + CC.end);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1F, 1.6F);
					cancel();
				}
			}
		});

		Tasks.get(p, ability).runTaskTimer(instance, 20, 20);
	}

	/**
	 * Check if a player has a certain ability on cooldown.
	 * 
	 * @param p
	 *            The player to check for cooldown
	 * @param ability
	 *            The ability to check for cooldown
	 * @return Returns true if player has cooldown for the ability.
	 */
	public static boolean isPlayeronCooldown(Player p, String ability) {
		if (Cooldowns.contains(p, ability)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Gets the remaining time in seconds that the player has to wait for the
	 * cooldown to expire.
	 * 
	 * @param p
	 *            The player to get the cooldown time for.
	 * @param ability
	 *            The ability to get the cooldown time for.
	 * @return Amount of time in seconds remaining.
	 */
	public static int getPlayerRemainingTime(Player p, String ability) {
		if (Cooldowns.contains(p, ability)) {
			return Cooldowns.get(p, ability);
		}
		return 0;
	}

	/**
	 * Remove a players cooldown for the specified ability.
	 * 
	 * @param p
	 * @param ability
	 */
	public static void removePlayerCooldown(Player p, String ability) {
		if (Tasks.contains(p, ability)) {
			Tasks.get(p, ability).cancel();
			Tasks.remove(p, ability);
			if (Cooldowns.contains(p, ability)) {
				Cooldowns.remove(p, ability);
			}
		}
	}

	public static Map<String, Integer> getPlayerCooldowns(Player p) {
		return Cooldowns.row(p);
	}

}
