package com.tevonetwork.tevoapi.Core.Chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer.playerSounds;
import com.tevonetwork.tevoapi.Core.Messages.AnnounceMSG;
import com.tevonetwork.tevoapi.Core.Networking.BungeeMessageTask;
import com.tevonetwork.tevoapi.Core.Networking.TevoNetworkMessageTask;

public class ChatManager {
	
	private static TevoAPI main = TevoAPI.getInstance();
	
	public static String getNameMessageSeperator()
	{
		return ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " » " + ChatColor.RESET;
	}
	
	public static String getPrivateMessageFormat(String senderdispname, String recipientdispname)
	{
		return ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Private MSG " + senderdispname + getNameMessageSeperator() + recipientdispname + getNameMessageSeperator(); 
	}
	
	public static String getStaffMessageFormat(String senderdispname)
	{
		return ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Staff Chat " + senderdispname + getNameMessageSeperator(); 
	}
	
	public static void sendNetworkBroadcast(String msg, boolean fromincomingchannel)
	{
		AnnounceMSG.toServer(msg); 
		
		if (!fromincomingchannel)
		{
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("NetworkBroadcast");
			out.writeUTF(msg);

			new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
		}
	}
	
	public static void sendStaffChatMessage(String senderdispname, String msg, boolean fromincomingchannel)
	{
		for (Player players : Bukkit.getServer().getOnlinePlayers())
		{
			if (UtilPlayer.isStaff(players))
			{
				players.sendMessage(getStaffMessageFormat(senderdispname) + ChatColor.WHITE + msg);
			}
		}
		if (!fromincomingchannel)
		{
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("StaffChat");
			out.writeUTF(senderdispname);
			out.writeUTF(msg);
		
			new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
		}
	}
	
	public static void sendStaffNotification(String msg, boolean fromincomingchannel)
	{
		String prefix = CC.tnHead + "Staff Notification" + CC.tnChatDiv;
		for (Player players : Bukkit.getServer().getOnlinePlayers())
		{
			if (UtilPlayer.isStaff(players))
			{
				players.sendMessage(prefix + msg);
			}
		}
		main.getUtilLogger().logNormal("StaffNotification> " + ChatColor.translateAlternateColorCodes('§', msg));
		if (!fromincomingchannel)
		{
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("StaffNotification");
			out.writeUTF(msg);
		
			new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
		}
	}
	
	public static void sendGlobalChatMessage(String senderdispname, String msg)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("GlobalChat");
		out.writeUTF(senderdispname);
		out.writeUTF(msg);
		
		new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
	}
	
	public static void sendPrivateChatMessage(String sendername, String senderdispname, String subjectname, String msg, boolean fromincomingchannel)
	{
		Player subject = Bukkit.getPlayer(subjectname);
		if (subject != null)
		{
			Player sender = Bukkit.getServer().getPlayer(sendername);
			Player recipient = Bukkit.getServer().getPlayer(subjectname);
			if (sender != null)
			{
				UtilPlayer.messageNoCategory(sender, getPrivateMessageFormat(CC.tnPlayer + "You", recipient.getDisplayName()) + msg);
			}
			else
			{
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("Message");
				out.writeUTF(sendername);
				out.writeUTF(getPrivateMessageFormat(CC.tnPlayer + "You", recipient.getDisplayName()) + ChatColor.RESET + msg);
				new BungeeMessageTask(out.toByteArray()).runTaskAsynchronously(main);
			}
			if (recipient != null)
			{
				recipient.sendMessage(getPrivateMessageFormat(senderdispname, CC.tnPlayer + "You") + msg);
				UtilPlayer.sound(recipient, playerSounds.PRIVATEMSG);
				UtilPlayer.setReplyCache(recipient, sendername);
			}
		}
		else
		{
			if (!fromincomingchannel)
			{
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("PrivateChat");
				out.writeUTF(sendername);
				out.writeUTF(senderdispname);
				out.writeUTF(subjectname);
				out.writeUTF(msg);
			
				new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
			}
		}
	}
	
	
	

	
}
