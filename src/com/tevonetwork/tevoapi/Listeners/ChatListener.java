package com.tevonetwork.tevoapi.Listeners;

import java.util.ConcurrentModificationException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.ChatChannel;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Chat.AntiSwearCheck;
import com.tevonetwork.tevoapi.Core.Chat.ChatManager;

public class ChatListener implements Listener{

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent e)
	{
		if (UtilPlayer.hasChatChannel(e.getPlayer(), ChatChannel.STAFF))
		{
			ChatManager.sendStaffChatMessage(e.getPlayer().getDisplayName(), ChatColor.translateAlternateColorCodes('&', e.getMessage()), false);
			e.setCancelled(true);
		}
		if (UtilPlayer.hasChatChannel(e.getPlayer(), ChatChannel.GLOBAL))
		{
			UtilPlayer.message(Category.CHAT, e.getPlayer(), ChatColor.YELLOW + "Your chat channel is set to: " + ChatColor.GREEN + "GLOBAL" + ChatColor.YELLOW + ", therefore the message was sent to the whole network.");
			ChatManager.sendGlobalChatMessage(e.getPlayer().getDisplayName(), ChatColor.translateAlternateColorCodes('&', e.getMessage()));
			if (UtilPlayer.isStaff(e.getPlayer()))
			{
				e.setFormat(e.getPlayer().getDisplayName() + ChatManager.getNameMessageSeperator() + ChatColor.YELLOW + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
			}
			else
			{
				e.setFormat(e.getPlayer().getDisplayName() + ChatManager.getNameMessageSeperator() + ChatColor.WHITE + e.getMessage());
			}
		}
		if (UtilPlayer.hasChatChannel(e.getPlayer(), ChatChannel.DEFAULT))
		{
			for (World world : Bukkit.getWorlds())
			{
				if (world != e.getPlayer().getWorld())
				{
					for (Player players : world.getPlayers())
					{
						try
						{
							if (e.getRecipients().contains(players))
							{
								e.getRecipients().remove(players);
							}
						}
						catch(ConcurrentModificationException ex){}
					}
				}
			}
			if (UtilPlayer.isStaff(e.getPlayer()))
			{
				e.setFormat(e.getPlayer().getDisplayName() + ChatManager.getNameMessageSeperator() + ChatColor.YELLOW + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
			}
			else if (UtilPlayer.hasRank(e.getPlayer(), Rank.CRYSTAL))
			{
				e.setFormat(e.getPlayer().getDisplayName() + ChatManager.getNameMessageSeperator() + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
				if (!AntiSwearCheck.scanMessage(e.getMessage()))
				{
					e.setCancelled(true);
					UtilPlayer.message(Category.CHAT, e.getPlayer(), CC.tnError + "Please don't say that!");
				}
			}
			else if (UtilPlayer.hasRank(e.getPlayer(), Rank.MYSTIC))
			{
				e.setFormat(e.getPlayer().getDisplayName() + ChatManager.getNameMessageSeperator() + ChatColor.WHITE + e.getMessage());
				if (!AntiSwearCheck.scanMessage(e.getMessage()))
				{
					e.setCancelled(true);
					UtilPlayer.message(Category.CHAT, e.getPlayer(), CC.tnError + "Please don't say that!");
				}
			}
			else
			{
				e.setFormat(e.getPlayer().getDisplayName() + ChatManager.getNameMessageSeperator() + ChatColor.GRAY + e.getMessage());
				if (!AntiSwearCheck.scanMessage(e.getMessage()))
				{
					e.setCancelled(true);
					UtilPlayer.message(Category.CHAT, e.getPlayer(), CC.tnError + "Please don't say that!");
				}
			}
		}
	}
	
}
