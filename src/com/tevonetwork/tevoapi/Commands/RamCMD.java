package com.tevonetwork.tevoapi.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tevonetwork.tevoapi.API.Permissions.PermissionsHandler;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;


public class RamCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (PermissionsHandler.hasRankSender(sender, Rank.DEVELOPER))
		{
			long max = Runtime.getRuntime().maxMemory() / 1024L / 1024L;
			long use = Runtime.getRuntime().totalMemory() / 1024L / 1024L - Runtime.getRuntime().freeMemory() / 1024L / 1024L;
			CategoryMSG.senderMessage(sender, Category.SERVER, CC.tnInfo + "Ram Usage: " + CC.tnValue + use + CC.tnInfo + "/" + CC.tnValue + max + "MB");
		}
		else
		{
			PermMSG.noPerm(sender, Rank.DEVELOPER);
		}
		return false;
	}
}
