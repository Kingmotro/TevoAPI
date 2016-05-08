package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
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

public class DayCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.ADMIN))
			{
				if (args.length > 0)
				{
					World target = Bukkit.getWorld(args[0]);
					if (target != null)
					{
						target.setTime(6000);
						UtilPlayer.message(Category.WORLD, p, CC.tnInfo + "Set time of " + CC.tnValue + target.getName() + CC.tnInfo + " to " + CC.tnValue + "6000" + CC.end);
					}
					else
					{
						UtilPlayer.message(Category.WORLD, p, CC.tnError + "Invalid world!");
					}
				}
				else
				{
					p.getWorld().setTime(6000);
					UtilPlayer.message(Category.WORLD, p, CC.tnInfo + "Set time of " + CC.tnValue + p.getWorld().getName() + CC.tnInfo + " to " + CC.tnValue + "6000" + CC.end);
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.ADMIN);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.WORLD);
		}
		return true;
	}

	
	
}
