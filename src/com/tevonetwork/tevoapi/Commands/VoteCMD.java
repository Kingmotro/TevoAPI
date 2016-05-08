package com.tevonetwork.tevoapi.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

public class VoteCMD implements CommandExecutor{

	TevoAPI main = TevoAPI.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			UtilPlayer.message(Category.VOTE, p, CC.tnValue + "http://vote.tevonetwork.com");
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.VOTE);
		}
		return false;
	}
	

}
