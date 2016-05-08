package com.tevonetwork.tevoapi.Core.Networking;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.Iterables;
import com.tevonetwork.tevoapi.TevoAPI;

public class TevoNetworkMessageTask extends BukkitRunnable{

	private final byte[] bytes;
	
	public TevoNetworkMessageTask(byte[] bytes)
	{
		this.bytes = bytes;
	}
	
	@Override
	public void run() 
	{
		Player p = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
		if (p != null)
		{
			p.sendPluginMessage(TevoAPI.getInstance(), TevoAPI.outgoing_TevoNetwork_Channel, bytes);
		}
	}

}
