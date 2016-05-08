package com.tevonetwork.tevoapi.Core.Messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.Core.Chat.ChatManager;

public class AnnounceMSG {
	
	public static void toWorld(World w, String msg)
	{
		for (Player players : w.getPlayers())
		{
			players.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "" + ChatColor.translateAlternateColorCodes('&', msg));
		}
	}
	
	public static void toServer(String msg)
	{
		Bukkit.getServer().broadcastMessage("");
		Bukkit.getServer().broadcastMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "" + ChatColor.translateAlternateColorCodes('&', msg));
		Bukkit.getServer().broadcastMessage("");
	}
	
	public static void toNetwork(String msg)
	{
		ChatManager.sendNetworkBroadcast(ChatColor.BOLD + "" + ChatColor.AQUA + "" + ChatColor.translateAlternateColorCodes('&', msg), false);
	}
}
