package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Permissions.OPSync;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;

public class OPCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.isOp())
		{
			if (args.length > 0)
			{
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target != null)
				{
					if (target.isOp())
					{
						CategoryMSG.senderMessage(sender, Category.SERVER, CC.tnError + "Player is already op!");
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.SERVER, CC.tnInfo + "Opping " + target.getDisplayName() + CC.end + "..");
						OPSync.setOP(target);
						UtilPlayer.message(Category.SERVER, target, CC.tnInfo + "You are now a network operator, this status has been synced across all servers!");
						CategoryMSG.senderMessage(sender, Category.SERVER, target.getDisplayName() + CC.tnInfo + " is now a network operator, this status has been synced across all servers!");
					}
				}
			}
			else
			{
				CategoryMSG.senderArgsErr(sender, Category.SERVER, "/op <player>");
			}
		}
		else
		{
			PermMSG.noPerm(sender, Rank.OWNER);
		}
		return false;
	}

}
