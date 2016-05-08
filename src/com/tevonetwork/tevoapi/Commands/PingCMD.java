package com.tevonetwork.tevoapi.Commands;

import net.minecraft.server.v1_8_R3.EntityPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

public class PingCMD implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length == 0)
		{
			if (sender instanceof Player)
			{
				Player p = (Player)sender;
				UtilPlayer.message(Category.UTILS, p, ChatColor.YELLOW + "Your ping is: " + ChatColor.GREEN + getPlayerPing(p) + "MS" + ChatColor.YELLOW + ".");
			}
			else
			{
				CategoryMSG.senderMessage(sender, Category.UTILS, ChatColor.RED + "Not enough Arguments! " + ChatColor.YELLOW + "Usage: " + ChatColor.GREEN + "/ping <player>");
			}
		}
		if (args.length >= 1)
		{
			if ((sender instanceof Player) || (sender instanceof ConsoleCommandSender))
			{
				Player subject = (Player)Bukkit.getServer().getPlayer(args[0]);
				if (subject == null)
				{
					CategoryMSG.senderMessage(sender, Category.UTILS, ChatColor.RED + "Player is not online!");
				}
				else
				{
					CategoryMSG.senderMessage(sender, Category.UTILS, ChatColor.YELLOW + "Ping of player " + ChatColor.GRAY + subject.getName() + ChatColor.YELLOW + " is: " + ChatColor.GREEN + getPlayerPing(subject) + "MS" + ChatColor.YELLOW + ".");
				}
			}
		}
		return false;
	}
	
	public int getPlayerPing(Player p)
	{
		CraftPlayer cp = (CraftPlayer) p;
		EntityPlayer ep = cp.getHandle();
		return ep.ping;
	}
	
}
