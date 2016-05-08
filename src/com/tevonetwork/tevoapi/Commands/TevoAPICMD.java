package com.tevonetwork.tevoapi.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Chat.Announcer;
import com.tevonetwork.tevoapi.Core.Messages.AuthorMSG;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

public class TevoAPICMD implements CommandExecutor{

	TevoAPI main = TevoAPI.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length >= 1)
		{
			if ((args[0].equalsIgnoreCase("reload")) && (sender.isOp()))
			{
				main.getConfigManager().reloadConfig();
				Announcer.reload();
				CategoryMSG.senderMessage(sender, Category.SERVER, CC.tnInfo + "The main config has been reloaded!");
				main.getUtilLogger().logNormal("Config> Main config was reloaded!");
			}
			else
			{
				AuthorMSG.sendAuthorStamp("API", main.getDescription().getVersion(), sender);
			}
		}
		else
		{
			AuthorMSG.sendAuthorStamp("API", main.getDescription().getVersion(), sender);
		}
		return false;
	}
	
	

}
