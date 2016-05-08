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

public class TimeCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (!UtilPlayer.hasRank(p, Rank.ADMIN))
			{
				PermMSG.noPerm(sender, Rank.ADMIN);
				return true;
			}
			if (args.length > 0)
			{
				if (args.length >= 2)
				{
					long time = 6000;
					try
					{
						time = Long.parseLong(args[0]);
					}
					catch(NumberFormatException e)
					{
						UtilPlayer.message(Category.WORLD, p, CC.tnError + "Invalid time!");
					}
					World target = Bukkit.getWorld(args[1]);
					if (target != null)
					{
						target.setTime(time);
						UtilPlayer.message(Category.WORLD, p, CC.tnInfo + "Set time of " + CC.tnValue + target.getName() + CC.tnInfo + " to " + CC.tnValue + time + CC.end);
					}
					else
					{
						UtilPlayer.message(Category.WORLD, p, CC.tnError + "Invalid time!");
					}
				}
				else
				{
					long time = 6000;
					try
					{
						time = Long.parseLong(args[0]);
					}
					catch(NumberFormatException e)
					{
						UtilPlayer.message(Category.WORLD, p, CC.tnError + "Invalid time!");
					}
					p.getWorld().setTime(time);
					UtilPlayer.message(Category.WORLD, p, CC.tnInfo + "Set time of " + CC.tnValue + p.getWorld().getName() + CC.tnInfo + " to " + CC.tnValue + time + CC.end);
				}
			}
			else
			{
				CategoryMSG.senderArgsErr(sender, Category.WORLD, "/time <time> [world]");
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.WORLD);
		}
		return false;
	}

}
