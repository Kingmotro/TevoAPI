package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;

public class GamemodeCMD implements CommandExecutor {

	TevoAPI main = TevoAPI.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.BUILDER))
			{
				if (UtilPlayer.hasRank(p, Rank.ADMIN))
				{
					if (args.length > 0)
					{
						if (args.length == 1)
						{
							if ((args[0].equalsIgnoreCase("0")) || (args[0].equalsIgnoreCase("survival")))
							{
								p.setGameMode(GameMode.SURVIVAL);
								UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed your gamemode to " + CC.tnValue + "SURVIVAL" + CC.end);
							}
							else if ((args[0].equalsIgnoreCase("1")) || (args[0].equalsIgnoreCase("creative")))
							{
								p.setGameMode(GameMode.CREATIVE);
								UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed your gamemode to " + CC.tnValue + "CREATIVE" + CC.end);
							}
							else if ((args[0].equalsIgnoreCase("2")) || (args[0].equalsIgnoreCase("adventure")))
							{
								p.setGameMode(GameMode.ADVENTURE);
								UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed your gamemode to " + CC.tnValue + "ADVENTURE" + CC.end);
							}
							else if ((args[0].equalsIgnoreCase("3")) || (args[0].equalsIgnoreCase("spectator")))
							{
								p.setGameMode(GameMode.SPECTATOR);
								UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed your gamemode to " + CC.tnValue + "SPECTATOR" + CC.end);
							}
							else
							{
								UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Invalid gamemode choice!");
							}
						}
						if (args.length >= 2)
						{
							Player target = Bukkit.getServer().getPlayer(args[1]);
							if (target != null)
							{
								if ((args[0].equalsIgnoreCase("0")) || (args[0].equalsIgnoreCase("survival")))
								{
									target.setGameMode(GameMode.SURVIVAL);
									UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed the gamemode of " + target.getDisplayName() + CC.tnInfo + " to " + CC.tnValue + "SURVIVAL" + CC.end);
								}
								else if ((args[0].equalsIgnoreCase("1")) || (args[0].equalsIgnoreCase("creative")))
								{
									target.setGameMode(GameMode.CREATIVE);
									UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed the gamemode of " + target.getDisplayName() + CC.tnInfo + " to " + CC.tnValue + "CREATIVE" + CC.end);
								}
								else if ((args[0].equalsIgnoreCase("2")) || (args[0].equalsIgnoreCase("adventure")))
								{
									target.setGameMode(GameMode.ADVENTURE);
									UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed the gamemode of " + target.getDisplayName() + CC.tnInfo + " to " + CC.tnValue + "ADVENTURE" + CC.end);
								}
								else if ((args[0].equalsIgnoreCase("3")) || (args[0].equalsIgnoreCase("spectator")))
								{
									target.setGameMode(GameMode.SPECTATOR);
									UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed the gamemode of " + target.getDisplayName() + CC.tnInfo + " to " + CC.tnValue + "SPECTATOR" + CC.end);
								}
								else
								{
									UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Invalid gamemode choice!");
								}
							}
							else
							{
								UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Invalid player!");
							}
									
						}
					}
					else
					{
						UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Not enough Arguments! " + CC.tnInfo + "Usage: " + CC.tnUse + "/gamemode <0/1/2/3> [player]");
					}
				}
				else
				{
					if ((UtilPlayer.hasRank(p, Rank.BUILDER)) && (main.getWorldManager().getWorldProperty(p.getWorld().getName(), WorldProperty.STAFFBUILD)))
					{
						if (args.length > 0)
						{
							if (args.length >= 1)
							{
								if ((args[0].equalsIgnoreCase("0")) || (args[0].equalsIgnoreCase("survival")))
								{
									p.setGameMode(GameMode.SURVIVAL);
									UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed your gamemode to " + CC.tnValue + "SURVIVAL" + CC.end);
								}
								else if ((args[0].equalsIgnoreCase("1")) || (args[0].equalsIgnoreCase("creative")))
								{
									p.setGameMode(GameMode.CREATIVE);
									UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed your gamemode to " + CC.tnValue + "CREATIVE" + CC.end);
								}
								else if ((args[0].equalsIgnoreCase("2")) || (args[0].equalsIgnoreCase("adventure")))
								{
									p.setGameMode(GameMode.ADVENTURE);
									UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed your gamemode to " + CC.tnValue + "ADVENTURE" + CC.end);
								}
								else if ((args[0].equalsIgnoreCase("3")) || (args[0].equalsIgnoreCase("spectator")))
								{
									p.setGameMode(GameMode.SPECTATOR);
									UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnInfo + "changed your gamemode to " + CC.tnValue + "SPECTATOR" + CC.end);
								}
								else
								{
									UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Invalid gamemode choice!");
								}
							}
						}
						else
						{
							UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Not enough Arguments! " + CC.tnInfo + "Usage: " + CC.tnUse + "/gamemode <0/1/2/3> [player]");
						}
					}
					else
					{
						PermMSG.noPerm(sender, Rank.ADMIN);
					}
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.ADMIN);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.PLAYER);
		}
		return false;
	}

}
