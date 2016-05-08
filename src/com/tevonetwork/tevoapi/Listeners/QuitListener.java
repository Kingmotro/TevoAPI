package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.tevonetwork.tevoapi.API.BossBar.BossBarAPI;
import com.tevonetwork.tevoapi.API.Events.CombatLogEvent;
import com.tevonetwork.tevoapi.API.Scoreboards.ScoreboardManager;
import com.tevonetwork.tevoapi.API.Stats.StatManager;
import com.tevonetwork.tevoapi.API.Util.UUIDFetcher;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Economy.EconomyManager;

public class QuitListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onQuit(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		if (UtilPlayer.isinCombat(p))
		{
			CombatLogEvent event = new CombatLogEvent(p);
			Bukkit.getServer().getPluginManager().callEvent(event);
			UtilPlayer.setoutCombat(p, true);
		}
		UtilPlayer.clearCaches(p);
		EconomyManager.removeCache(p);
		StatManager.removeFromCache(p);
		BossBarAPI.removeBar(p);
		ScoreboardManager.removeScoreboard(p);
		ScoreboardManager.removePlayerRank(p);
		UtilPlayer.clearBackLoc(p);
		UUIDFetcher.removeFromCache(p.getName());
	}
	
}
