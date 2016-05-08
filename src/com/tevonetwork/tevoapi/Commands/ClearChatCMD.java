package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.AnnounceMSG;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

public class ClearChatCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender)
		{
			for (int i = 0; i < 100; i++)
			{
				Bukkit.getServer().broadcastMessage(" ");
			}
			AnnounceMSG.toServer(CategoryMSG.getMSGPrefix(Category.CHAT) + CC.tnInfo + "The chat has been cleared.");
		}
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.MODERATOR))
			{
				for (int i = 0; i < 100; i++)
				{
					AnnounceMSG.toWorld(p.getWorld(), " ");
				}
				AnnounceMSG.toWorld(p.getWorld(), CategoryMSG.getMSGPrefix(Category.CHAT) + CC.tnInfo + "The chat has been cleared by " + p.getDisplayName() + CC.end);
			}
		}
		return false;
	}

}
