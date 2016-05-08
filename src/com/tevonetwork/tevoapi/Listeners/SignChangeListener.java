package com.tevonetwork.tevoapi.Listeners;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Travel.WarpManager;

public class SignChangeListener implements Listener{

	@EventHandler
	public void onSignChange(SignChangeEvent e)
	{
		Player p = e.getPlayer();
		Sign sign = (Sign)e.getBlock().getState();
		if ((e.getLines().length > 0) && (e.getLine(0).equalsIgnoreCase("[Warp]")) && (UtilPlayer.hasRank(p, Rank.ADMIN)))
		{
			if (!(e.getLines().length > 1))
			{
				sign.getBlock().setType(Material.AIR);
				UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Enter the warp name on the next line, sign removed!");
				return;
			}
			String warpname = e.getLine(1);
			if (WarpManager.getPublicWarp(warpname) != null)
			{
				e.setLine(0, CC.tnHead + "Warp");
				e.setLine(1, CC.tnValue + warpname);
				e.setLine(2, CC.tnUse + "(Right Click)");
				sign.update();
				UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully created a warp sign for " + CC.tnValue + warpname + CC.end);
			}
			else
			{
				sign.getBlock().setType(Material.AIR);
				UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "That warp is invalid, sign removed!");
				return;
			}
		}
		if ((e.getLines().length > 0) && (UtilPlayer.hasRank(p, Rank.ADMIN)))
		{
			for (int index = 0; index < e.getLines().length; index++)
			{
				e.setLine(index, ChatColor.translateAlternateColorCodes('&', e.getLine(index)));
			}
		}
	}
	
}
