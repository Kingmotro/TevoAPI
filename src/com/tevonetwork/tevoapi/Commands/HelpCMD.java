package com.tevonetwork.tevoapi.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

import net.md_5.bungee.api.ChatColor;

public class HelpCMD implements CommandExecutor{

	private TevoAPI main = TevoAPI.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			List<String> helpmsg = main.getConfigManager().getConfig().getStringList("helpmsgdefault");
			UtilPlayer.messageHeader(Category.HELP, p, "Help");
			for (String msg : helpmsg)
			{
				UtilPlayer.messageNoCategory(p, ChatColor.translateAlternateColorCodes('&', msg));
			}
			UtilPlayer.messageFooter(p);
			if (UtilPlayer.hasRank(p, Rank.MODERATOR))
			{
				List<String> staffhelpmsg = main.getConfigManager().getConfig().getStringList("helpmsgstaff");
				UtilPlayer.messageHeader(Category.HELP, p, "Staff Help");
				for (String staffmsg : staffhelpmsg)
				{
					UtilPlayer.messageNoCategory(p, ChatColor.translateAlternateColorCodes('&', staffmsg));
				}
				UtilPlayer.messageFooter(p);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.HELP);
		}
		return false;
	}

}
