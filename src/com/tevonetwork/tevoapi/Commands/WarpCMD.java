package com.tevonetwork.tevoapi.Commands;

import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
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

public class WarpCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.BUILDER))
			{
				if (args.length <= 0)
				{
					UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Loading...");
					WarpManager.playerOpenWarps(p);
				}
				if (args.length > 0)
				{
					if (args[0].equalsIgnoreCase("-p"))
					{
						UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Loading...");
						WarpManager.openPublicWarps(p);
					}
					else if (args[0].equalsIgnoreCase("-t"))
					{
						StringBuilder builder = new StringBuilder();
						List<String> warps = WarpManager.getPlayerWarpList(p.getName());
						for (Iterator<String> itr = warps.iterator(); itr.hasNext();)
						{
							builder.append(ChatColor.GREEN + itr.next());
							if (itr.hasNext())
							{
								builder.append(ChatColor.YELLOW + ", ");
							}
						}
						UtilPlayer.messageHeader(Category.TRAVEL, p, CC.tnPlayer + p.getName() + "'s" + CC.tnHead + " Warps");
						UtilPlayer.messageNoCategory(p, builder.toString());
						UtilPlayer.messageFooter(p);
					}
					else if (args[0].equalsIgnoreCase("help"))
					{
						UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Doing " + CC.tnUse + "/warp" + CC.tnInfo + " by itself opens your warp menu. Adding -p will open the public warp menu, adding -t will show your warps in the chat.");
					}
					else
					{
						if (args.length == 1)
						{
							WarpManager.openPlayerWarps(args[0], p);
						}
					}
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
