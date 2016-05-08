package com.tevonetwork.tevoapi.Core.Travel;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Portals.PortalManager;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer.playerSounds;

public class SendtoLocation {

	public SendtoLocation(Player p, Location loc)
	{
		UtilPlayer.sound(p, playerSounds.TELEPORT);
		p.teleport(loc);
		PortalManager.clearTriggered(p);
	}
	
}
