package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Location;
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
import com.tevonetwork.tevoapi.Core.Travel.SendtoLocation;

public class TPPOSCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.ADMIN))
			{
				if (args.length == 3)
				{
					World world = p.getWorld();
					double x = Double.valueOf(args[0]);
					double y = Double.valueOf(args[1]);
					double z = Double.valueOf(args[2]);
					UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Teleporting you to " + CC.tnValue + x  + " " + y + " " + z + CC.end + "..");
					new SendtoLocation(p, new Location(world, x, y, z));
				}
				else if (args.length >= 5)
				{
					World world = p.getWorld();
					double x = Double.valueOf(args[0]);
					double y = Double.valueOf(args[1]);
					double z = Double.valueOf(args[2]);
					double yaw = Double.valueOf(args[3]);
					double pitch = Double.valueOf(args[4]);
					UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Teleporting you to " + CC.tnValue + x  + " " + y + " " + z + " " + " " + yaw + " " + pitch + CC.end + "..");
					new SendtoLocation(p, new Location(world, x, y, z, (float)yaw, (float)pitch));
				}
				else
				{
					CategoryMSG.senderArgsErr(sender, Category.TRAVEL, "/tppos <x> <y> <z> [yaw] [pitch]");
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.ADMIN);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.TRAVEL);
		}
		return false;
	}

}
