package com.tevonetwork.tevoapi.Core.Networking;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.Iterables;
import com.tevonetwork.tevoapi.TevoAPI;

public class BungeeMessageTask extends BukkitRunnable {

	private final byte[] bytes;

	public BungeeMessageTask(byte[] bytes) {

		this.bytes = bytes;
	}

	@Override
	public void run() {
		Player p = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
		if (p != null) {
			p.sendPluginMessage(TevoAPI.getInstance(), TevoAPI.outgoing_Bungee_Channel, bytes);
		}

	}

}
