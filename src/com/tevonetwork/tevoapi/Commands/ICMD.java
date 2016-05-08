package com.tevonetwork.tevoapi.Commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Items;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;

public class ICMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if ((UtilPlayer.hasRank(p, Rank.ADMIN)) || ((UtilPlayer.hasRank(p, Rank.BUILDER)) && (TevoAPI.getInstance().getWorldManager().getWorldProperty(p.getWorld().getName(), WorldProperty.STAFFBUILD))))
			{
				if(args.length > 0)
				{
					if (args.length == 1)
					{
						ItemStack is = Items.fromUserString(args[0]);
						if (is != null)
						{
							UtilPlayer.addItem(p, is);
							CategoryMSG.senderMessage(sender, Category.UTILS, CC.tnInfo + "You were given " + CC.tnValue + "1 " + is.getType().toString().toUpperCase());
						}
						else
						{
							CategoryMSG.senderMessage(sender, Category.UTILS, CC.tnError + "Invalid Item Name!");
						}
											
					}
					else if (args.length == 2)
					{
						ItemStack is = Items.fromUserString(args[0]);
						if (is != null)
						{
							if (StringUtils.isNumeric(args[1]))
							{
								int amount = Integer.valueOf(args[1]);
								if (amount > 64)
								{
									amount = 64;
								}
								is.setAmount(amount);
								UtilPlayer.addItem(p, is);
								CategoryMSG.senderMessage(sender, Category.UTILS, CC.tnInfo + "You were given " + CC.tnValue + String.valueOf(amount) + " " + is.getType().toString().toUpperCase());
							}
							else
							{
								CategoryMSG.senderMessage(sender, Category.UTILS, CC.tnError + "Invalid amount!");
							}
						}
						else
						{
							CategoryMSG.senderMessage(sender, Category.UTILS, CC.tnError + "Invalid Item Name!");
						}
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.UTILS, CC.tnError + "Too many arguments!" + CC.tnInfo + " Usage: " + CC.tnUse + "/i <itemname> [amount]");
					}
				}
				else
				{
					CategoryMSG.senderMessage(sender, Category.UTILS, CC.tnError + "Not enough arguments!" + CC.tnInfo + " Usage: " + CC.tnUse + "/i <itemname> [amount]");
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.BUILDER);
			}
		
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.UTILS);
		}
		return false;
	}

	
}
