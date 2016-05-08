package com.tevonetwork.tevoapi.API.Util;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.Core.Chat.ChatManager;

public class UtilServer {

	public static Collection<Player> getStaff() {
		Collection<Player> onlinestaff = new ArrayList<Player>();
		for (Player staff : Bukkit.getOnlinePlayers()) {
			if (UtilPlayer.isStaff(staff)) {
				onlinestaff.add(staff);
			}
		}
		return onlinestaff;
	}

	public static Collection<? extends Player> getPlayers() {
		return Bukkit.getOnlinePlayers();
	}

	// Used for the global chat channel
	public static void sendFakeChatMessage(String displayname, String msg) {
		Bukkit.getServer().broadcastMessage(displayname + ChatManager.getNameMessageSeperator() + msg);
	}

}
