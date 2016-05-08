package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.tevonetwork.tevoapi.Core.Networking.ServerPlayerCounter;

public class IncomingBungeeChannelListener implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) 
	{
		if (!channel.equals("BungeeCord"))
		{
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if (subchannel.equals("PlayerCount"))
		{
			String server = in.readUTF();
			int playercount = in.readInt();
			
			ServerPlayerCounter.updateServerCount(server, playercount);
			
		}
		if (subchannel.equals("GetServers"))
		{
			String[] serverlist = in.readUTF().split(", ");
			ServerPlayerCounter.updateServerList(serverlist);
		}
		

	}

}
