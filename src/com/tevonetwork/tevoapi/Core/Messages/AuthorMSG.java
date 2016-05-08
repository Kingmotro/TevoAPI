package com.tevonetwork.tevoapi.Core.Messages;

import org.bukkit.command.CommandSender;

import com.tevonetwork.tevoapi.API.Util.CC;

public class AuthorMSG {
	/**
	 * Sends author mark to sender.
	 * 
	 * @param plugin Name of plugin
	 * @param version Version of plugin
	 * @param sender Sender of command
	 */
	public static void sendAuthorStamp(String plugin, String version, CommandSender sender)
	{
		sender.sendMessage("§8▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
		sender.sendMessage("§2§lTevo " + CC.cBlue + CC.fBold + plugin);
		sender.sendMessage("§eVersion: " + CC.tnValue + version);
		sender.sendMessage("§eAuthor: §7Thrusmyster");
		sender.sendMessage("§cPlease report bugs here:");
		sender.sendMessage("§ahttp://adf.ly/1GVXD6");
		sender.sendMessage("§8▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
	}
	
}
