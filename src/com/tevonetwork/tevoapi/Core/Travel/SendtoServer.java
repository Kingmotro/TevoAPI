package com.tevonetwork.tevoapi.Core.Travel;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Portals.PortalManager;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer.playerSounds;

public class SendtoServer {

	TevoAPI main = TevoAPI.getInstance();
	
	public SendtoServer(Player p, String server)
	{
		UtilPlayer.sound(p, playerSounds.TELEPORT);
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		p.sendPluginMessage(main, "BungeeCord", out.toByteArray());
		final Player player = p;
		new BukkitRunnable() {
			
			@Override
			public void run() {
				PortalManager.clearTriggered(player);
				
			}
		}.runTaskLater(main, 40L);
		
	}
	
}
