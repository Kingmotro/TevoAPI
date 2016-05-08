package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

public class LoginListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onLogin(PlayerLoginEvent e)
	{
		Player p = e.getPlayer();
		if (UtilPlayer.hasRank(p, Rank.MYSTIC))
		{
			e.setResult(Result.ALLOWED);
			e.allow();
			return;
		}
		if (e.getResult() == Result.KICK_FULL)
		{
			e.setKickMessage(CategoryMSG.getMSGPrefix(Category.SERVER) + CC.tnInfo + CC.fBold + "The server is currently full! \n" + CC.tnInfo + CC.fBold + " Purchase " + Rank.getRankPrefix(Rank.MYSTIC) + CC.tnInfo + CC.fBold + " rank to join when full!");
		}
	}
	
}
