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

public class SpeedCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.OWNER))
			{
				if (args.length > 0)
				{
					if (args[0].equalsIgnoreCase("walk"))
					{
						if (args.length > 1)
						{
							double in = 0;
							try
							{
								in = Double.parseDouble(args[1]);
							}
							catch (NumberFormatException e)
							{
								UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Argument is not numeric!");
								return true;
							}
							float speed = (float)(in / 10);
							if ((speed > 1.0F) || (speed < 0.1F))
							{
								UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Please enter a value between 1 and 10!");
								return true;
							}
							p.setWalkSpeed(speed);
							UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "Set your walk speed to " + CC.tnValue + speed + CC.end);
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.PLAYER, "/speed walk <speed>");
						}
					}
					else
					{
						double in = 0;
						try
						{
							in = Double.parseDouble(args[0]);
						}
						catch (NumberFormatException e)
						{
							UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Argument is not numeric!");
							return true;
						}
						float speed = (float)(in / 10);
						if ((speed > 1.0F) || (speed < 0.1F))
						{
							UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Please enter a value between 1 and 10! (Default walking speed is " + CC.tnValue + "2" + CC.tnError + ")");
							return true;
						}
						p.setFlySpeed(speed);
						UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "Set your flying speed to " + CC.tnValue + speed + CC.end);
					}
				}
				else
				{
					CategoryMSG.senderArgsErr(sender, Category.PLAYER, "/speed [walk] <speed>");
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.OWNER);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.PLAYER);
		}
		return false;
	}

}
