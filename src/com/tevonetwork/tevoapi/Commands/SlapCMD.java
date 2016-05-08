package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Cooldown.Cooldown;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;

public class SlapCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.CRYSTAL))
			{
				if (args.length > 0)
				{
					if (!Cooldown.isPlayeronCooldown(p, "Slap"))
					{
						Player subject = Bukkit.getServer().getPlayer(args[0]);
						if (subject != null)
						{
							subject.playSound(subject.getLocation(), Sound.FALL_SMALL, 3F, 1.9F);
							UtilPlayer.message(Category.PLAYER, subject, CC.tnInfo + "You have been slap 'slapped round 'da face by " + p.getDisplayName() + CC.end);
							UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnAbility + "slapped " + subject.getDisplayName() + CC.end);
							Cooldown.addCooldown(p, "Slap", 20);
						}
						else
						{
							UtilPlayer.message(Category.PLAYER, p, CC.tnError + "Invalid Player!");
						}
						
					}
					else
					{
						UtilPlayer.onc(p, "Slap");
					}
				}
				else
				{
					CategoryMSG.senderArgsErr(sender, Category.PLAYER, "/slap <player>");
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.CRYSTAL);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.PLAYER);
		}
		return false;
	}

}
