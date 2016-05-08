package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.UtilServer;
import com.tevonetwork.tevoapi.Core.Chat.ChatManager;
import com.tevonetwork.tevoapi.Core.Vote.VoteMessenger;

public class IncomingTevoNetworkChannelListener implements
		PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) 
	{
		if (!channel.equals(TevoAPI.incoming_TevoNetwork_Channel))
		{
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		
		if (subchannel.equals("PrivateChat"))
		{
			ChatManager.sendPrivateChatMessage(in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), true);
		}
		if (subchannel.equals("GlobalChat"))
		{
			UtilServer.sendFakeChatMessage(in.readUTF(), in.readUTF());
		}
		if (subchannel.equals("StaffChat"))
		{
			ChatManager.sendStaffChatMessage(in.readUTF(), in.readUTF(), true);
		}
		if (subchannel.equals("NetworkBroadcast"))
		{
			ChatManager.sendNetworkBroadcast(in.readUTF(), true);
		}
		if (subchannel.equals("StaffNotification"))
		{
			ChatManager.sendStaffNotification(in.readUTF(), true);
		}
		if (subchannel.equals("Vote"))
		{
			VoteMessenger.sendVoteMSG(in.readUTF(), in.readInt());
		}
	}

}
