package com.tevonetwork.tevoapi.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.tevonetwork.tevoapi.API.Permissions.PermissionsHandler;
import com.tevonetwork.tevoapi.API.Stats.StatManager;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Gamemodes;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;

public class StatresetCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender instanceof ConsoleCommandSender)
		{
			if (!PermissionsHandler.hasRankSender(sender, Rank.DEVELOPER))
			{
				PermMSG.noPerm(sender, Rank.DEVELOPER);
				return true;
			}
			if (args.length > 1)
			{
				String player = args[0];
				String mode = args[1];
				if (mode.equalsIgnoreCase("borderline"))
				{
					if (StatManager.resetStats(player, Gamemodes.BORDERLINE))
					{
						CategoryMSG.senderMessage(sender, Category.PLAYER, CC.tnInfo + "Wiped stats for " + CC.tnPlayer + player + CC.tnInfo + " in gamemode: " + CC.tnValue + "Borderline" + CC.end);
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.PLAYER, CC.tnError + "Invalid player!");
					}
				}
				else if (mode.equalsIgnoreCase("kitpve"))
				{
					if (StatManager.resetStats(player, Gamemodes.KITPVE))
					{
						CategoryMSG.senderMessage(sender, Category.PLAYER, CC.tnInfo + "Wiped stats for " + CC.tnPlayer + player + CC.tnInfo + " in gamemode: " + CC.tnValue + "KitPVE" + CC.end);
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.PLAYER, CC.tnError + "Invalid player!");
					}
				}
				else
				{
					CategoryMSG.senderMessage(sender, Category.PLAYER, CC.tnError + "Invalid game mode!");
				}
			}
			else
			{
				CategoryMSG.senderArgsErr(sender, Category.PLAYER, "/statreset <player> <gamemode>");
			}
		}
		return false;
	}

}
