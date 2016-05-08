package com.tevonetwork.tevoapi.Core.Travel;

import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Portals.PortalManager;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer.playerSounds;

public class SendtoWorld {

	public SendtoWorld(String worldname, Player p)
	{
		UtilPlayer.sound(p, playerSounds.TELEPORT);
		p.teleport(TevoAPI.getInstance().getWorldManager().getWorldSpawn(worldname));
		PortalManager.clearTriggered(p);
	}
	
}
