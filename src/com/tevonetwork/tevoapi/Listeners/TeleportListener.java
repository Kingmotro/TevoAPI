package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.BossBar.BossBar;
import com.tevonetwork.tevoapi.API.BossBar.BossBarAPI;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;

public class TeleportListener implements Listener{
	
	TevoAPI main = TevoAPI.getInstance();
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e)
	{
		if (BossBarAPI.hasBar(e.getPlayer()))
		{
		    final BossBar bar = BossBarAPI.getBossBar(e.getPlayer());
		    bar.setVisible(false);
		    new BukkitRunnable()
		    {
		      public void run()
		      {
		        bar.setVisible(true);
		      }
		    }.runTaskLater(main, 2L);
		}
		if (e.getCause() == TeleportCause.END_PORTAL)
		{
			e.setCancelled(true);
		}
	   UtilPlayer.setBackLoc(e.getPlayer(), e.getFrom());
	}
}
