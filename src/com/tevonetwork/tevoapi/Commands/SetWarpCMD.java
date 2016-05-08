package com.tevonetwork.tevoapi.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;
import com.tevonetwork.tevoapi.Core.Travel.WarpManager;

public class SetWarpCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.BUILDER))
			{
				if (args.length > 0)
				{
					if (args[0].equalsIgnoreCase("-p"))
					{
						if (args.length >= 2)
						{
							if (WarpManager.addNewPublicWarp(p, args[1]))
							{
								UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully added public warp " + CC.tnValue + args[1] + CC.end);
							}
							else
							{
								UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to add public warp " + CC.tnValue + args[1] + CC.tnError + " it probably already exists!");
							}
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.TRAVEL, "/setwarp -p <warpname>");
						}
					}
					else
					{
						if (WarpManager.addNewWarp(p, args[0]))
						{
							UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully added warp " + CC.tnValue + args[0] + CC.end);
						}
						else
						{
							UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to add warp " + CC.tnValue + args[0] + CC.tnError + " it probably already exists!");
						}
					}
				}
				else
				{
					UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Not enough Arguments!" + CC.tnInfo + " Usage: " + CC.tnUse + "/setwarp [-p] <warpname>");
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.BUILDER);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.TRAVEL);
		}
		
		return false;
	}
	

}
