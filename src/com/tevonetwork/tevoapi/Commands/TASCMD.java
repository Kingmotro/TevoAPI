package com.tevonetwork.tevoapi.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Configs.ConfigManager;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

public class TASCMD implements CommandExecutor {

	private TevoAPI main = TevoAPI.getInstance();
	private ConfigManager cfm = main.getConfigManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length > 0)
		{
			if (args[0].equalsIgnoreCase("add"))
			{
				if (args.length >= 2)
				{
					if (!cfm.getConfig().getStringList("swearblacklist").contains(args[1].toLowerCase()))
					{
						List<String> list = cfm.getConfig().getStringList("swearblacklist");
						list.add(args[1].toLowerCase());
						cfm.getConfig().set("swearblacklist", list);
						cfm.saveConfig();
						CategoryMSG.senderMessage(sender, Category.CHAT, CC.tnInfo + "Successfully added the word " + CC.tnValue + args[1].toLowerCase() + CC.tnInfo + " to the swear list.");
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.CHAT, CC.tnError + "The word " + CC.tnValue + args[1].toLowerCase() + CC.tnError + " is already on the swear list.");
					}
				}
				else
				{
					CategoryMSG.senderArgsErr(sender, Category.CHAT, "/tas add <word>");
				}
			}
			else if (args[0].equalsIgnoreCase("remove"))
			{
				if (args.length >= 2)
				{
					if (cfm.getConfig().getStringList("swearblacklist").contains(args[1].toLowerCase()))
					{
						List<String> list = cfm.getConfig().getStringList("swearblacklist");
						list.remove(args[1].toLowerCase());
						cfm.getConfig().set("swearblacklist", list);
						cfm.saveConfig();
						CategoryMSG.senderMessage(sender, Category.CHAT, CC.tnInfo + "Successfully removed the word " + CC.tnValue + args[1].toLowerCase() + CC.tnInfo + " from the swear list.");
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.CHAT, CC.tnError + "The word " + CC.tnValue + args[1].toLowerCase() + CC.tnError + " is not on the swear list.");
					}
				}
				else
				{
					CategoryMSG.senderArgsErr(sender, Category.CHAT, "/tas remove <word>");
				}
			}
			else
			{
				CategoryMSG.senderMessage(sender, Category.CHAT, CC.tnError + "Invalid Arguments! " + CC.tnInfo + "Usage: " + CC.tnUse + "/tas <add/remove> <word>");
			}
		}
		else
		{
			CategoryMSG.senderMessage(sender, Category.CHAT, CC.tnError + "Not enough Arguments! " + CC.tnInfo + "Usage: " + CC.tnUse + "/tas <add/remove> <word>");
		}
		
		return false;
	}

}
