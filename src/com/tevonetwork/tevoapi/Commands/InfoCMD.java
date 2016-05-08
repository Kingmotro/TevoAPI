package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Permissions.PermissionsHandler;
import com.tevonetwork.tevoapi.API.Stats.StatManager;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Gamemodes;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;
import com.tevonetwork.tevoapi.Economy.EconomyManager;

public class InfoCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (PermissionsHandler.hasRankSender(sender, Rank.MODERATOR))
		{
			if (args.length > 0)
			{
				String player = args[0];
				Player target = Bukkit.getPlayer(player);
				if (target != null)
				{
					CategoryMSG.senderHeader(sender, Category.PLAYER, "Information for player " + CC.tnPlayer + CC.fBold + target.getName());
					sender.sendMessage(CC.tnInfo + "Tevo Tokens: " + CC.tnValue + EconomyManager.getTokensBal(target));
					sender.sendMessage(CC.tnInfo + "Logins: " + CC.tnValue + StatManager.getLogins(target));
					sender.sendMessage(CC.tnInfo + "Votes: " + CC.tnValue + StatManager.getVotes(target));
					String rank = UtilPlayer.getRank(target) == Rank.DEFAULT ? CC.cGray + CC.fBold + "No Rank" : Rank.getRankPrefix(UtilPlayer.getRank(target));
					sender.sendMessage(CC.tnInfo + "Rank: " + rank);
					sender.sendMessage(CC.tnInfo + CC.fBold + "KitPVP Stats:");
					sender.sendMessage(CC.tnInfo + "Kills: " + CC.tnValue + StatManager.getKills(target, Gamemodes.KITPVE));
					sender.sendMessage(CC.tnInfo + "Deaths: " + CC.tnValue + StatManager.getDeaths(target, Gamemodes.KITPVE));
					sender.sendMessage(CC.tnInfo + "K/D: " + CC.tnValue + StatManager.getKD(target));
					sender.sendMessage(CC.tnInfo + CC.fBold + "Borderline Stats:");
					sender.sendMessage(CC.tnInfo + "Kills: " + CC.tnValue + StatManager.getKills(target, Gamemodes.BORDERLINE));
					sender.sendMessage(CC.tnInfo + "Games: " + CC.tnValue + StatManager.getGames(target, Gamemodes.BORDERLINE));
					sender.sendMessage(CC.tnInfo + "Wins: " + CC.tnValue + StatManager.getWins(target, Gamemodes.BORDERLINE));
					CategoryMSG.senderFooter(sender);
				}
				else
				{
					CategoryMSG.senderMessage(sender, Category.PLAYER, CC.tnError + "Player not online!");
				}
			}
			else
			{
				CategoryMSG.senderArgsErr(sender, Category.PLAYER, "/info <player>");
			}
		}
		else
		{
			PermMSG.noPerm(sender, Rank.MODERATOR);
		}
		return true;
	}

	
}
