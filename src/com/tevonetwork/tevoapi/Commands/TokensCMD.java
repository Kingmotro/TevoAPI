package com.tevonetwork.tevoapi.Commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Permissions.PermissionsHandler;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;
import com.tevonetwork.tevoapi.Economy.EconomyManager;

public class TokensCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label ,String[] args) {
		if (PermissionsHandler.hasRankSender(sender, Rank.ADMIN))
		{
			if (args.length > 0)
			{
				if (args.length == 1)
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target != null)
					{
						CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "Balance of " + CC.tnPlayer + target.getName() + "'s" + CC.tnInfo + " account is " + CC.tnValue + EconomyManager.getTokensBal(target) + " TevoTokens" + CC.end);
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "Balance of " + CC.tnPlayer + args[0] + "'s" + CC.tnInfo + " account is " + CC.tnValue + EconomyManager.getTokensOffline(args[0]) + " TevoTokens" + CC.end);
					}
				}
				else if (args[0].equalsIgnoreCase("add"))
				{
					if (args.length >= 3)
					{
						if (StringUtils.isNumeric(args[2]))
						{
							Player target = Bukkit.getServer().getPlayer(args[1]);
							if (target != null)
							{
								EconomyManager.addTokens(target, Integer.valueOf(args[2]));
								CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnValue + Integer.valueOf(args[2]) + " TevoTokens" + CC.tnInfo + " were added to " + CC.tnPlayer + target.getName() + "'s" + CC.tnInfo + " account.");
								CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "New balance: " + CC.tnValue + Integer.valueOf(EconomyManager.getTokensBal(target)) + " TevoTokens" + CC.end);
							}
							else
							{
								if (EconomyManager.addTokensOffline(args[1], Integer.valueOf(args[2])))
								{
									CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnValue + Integer.valueOf(args[2]) + " TevoTokens" + CC.tnInfo + " were added to " + CC.tnPlayer + args[1] + "'s" + CC.tnInfo + " account.");
									CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "New balance: " + CC.tnValue + Integer.valueOf(EconomyManager.getTokensOffline(args[1])) + " TevoTokens" + CC.end);
								}
								else
								{
									CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnError + "Invalid player!");
								}
							}
						}
						else
						{
							CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnError + "Please put a numeric value!");
						}
					}
					else
					{
						CategoryMSG.senderArgsErr(sender, Category.TOKENS, "/tokens add <player> <amount>");
					}
				}
				else if (args[0].equalsIgnoreCase("set"))
				{
					if (args.length >= 3)
					{
						if (StringUtils.isNumeric(args[2]))
						{
							Player target = Bukkit.getServer().getPlayer(args[1]);
							if (target != null)
							{
								EconomyManager.setTokens(target, Integer.valueOf(args[2]));
								CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnPlayer + target.getName() + "'s" + CC.tnInfo + " account balance was set to " + CC.tnValue + Integer.valueOf(args[2]) + " TevoTokens" + CC.end);
								CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "New balance: " + CC.tnValue + Integer.valueOf(EconomyManager.getTokensBal(target)) + " TevoTokens" + CC.end);
							}
							else
							{
								if (EconomyManager.setTokensOffline(args[1], Integer.valueOf(args[2])))
								{
									CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnPlayer + args[1] + "'s" + CC.tnInfo + " account balance was set to " + CC.tnValue + Integer.valueOf(args[2]) + " TevoTokens" + CC.end);
									CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "New balance: " + CC.tnValue + Integer.valueOf(EconomyManager.getTokensOffline(args[1])) + " TevoTokens" + CC.end);
								}
								else
								{
									CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnError + "Invalid player!");
								}
							}
						}
						else
						{
							CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnError + "Please put a numeric value!");
						}
					}
					else
					{
						CategoryMSG.senderArgsErr(sender, Category.TOKENS, "/tokens set <player> <amount>");
					}
				}
				else if (args[0].equalsIgnoreCase("remove"))
				{
					if (args.length >= 3)
					{
						if (StringUtils.isNumeric(args[2]))
						{
							Player target = Bukkit.getServer().getPlayer(args[1]);
							if (target != null)
							{
								EconomyManager.takeTokens(target, Integer.valueOf(args[2]));
								CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnValue + Integer.valueOf(args[2]) + " TevoTokens" + CC.tnInfo + " were removed from " + CC.tnPlayer + target.getName() + "'s" + CC.tnInfo + " account.");
								CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "New balance: " + CC.tnValue + Integer.valueOf(EconomyManager.getTokensBal(target)) + " TevoTokens" + CC.end);
							}
							else
							{
								if (EconomyManager.removeTokensOffline(args[1], Integer.valueOf(args[2])))
								{
									CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnValue + Integer.valueOf(args[2]) + " TevoTokens" + CC.tnInfo + " were removed from " + CC.tnPlayer + args[1] + "'s" + CC.tnInfo + " account.");
									CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "New balance: " + CC.tnValue + Integer.valueOf(EconomyManager.getTokensOffline(args[1])) + " TevoTokens" + CC.end);
								}
								else
								{
									CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnError + "Invalid player!");
								}
							}
						}
						else
						{
							CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnError + "Please put a numeric value!");
						}
					}
					else
					{
						CategoryMSG.senderArgsErr(sender, Category.TOKENS, "/tokens remove <player> <amount>");
					}
				}
				else if (args[0].equalsIgnoreCase("reset"))
				{
					if (args.length >= 2)
					{
						Player target = Bukkit.getServer().getPlayer(args[1]);
						if (target != null)
						{
							EconomyManager.resetTokens(target);
							CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "Reset the balance of " + CC.tnPlayer + target.getName() + "'s" + CC.tnInfo + " account.");
							CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "New balance: " + CC.tnValue + Integer.valueOf(EconomyManager.getTokensBal(target)) + " TevoTokens" + CC.end);
						}
						else
						{
							if (EconomyManager.resetTokensOffline(args[1]))
							{
								CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "Reset the balance of " + CC.tnPlayer + args[1] + "'s" + CC.tnInfo + " account.");
								CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnInfo + "New balance: " + CC.tnValue + Integer.valueOf(EconomyManager.getTokensOffline(args[1])) + " TevoTokens" + CC.end);
							}
							else
							{
								CategoryMSG.senderMessage(sender, Category.TOKENS, CC.tnError + "Invalid Player!");
							}
						}
					}
					else
					{
						CategoryMSG.senderArgsErr(sender, Category.TOKENS, "/tokens reset <player>");
					}
				}
				else
				{
					CategoryMSG.senderInvArgsErr(sender, Category.TOKENS, "");
					CategoryMSG.senderHeader(sender, Category.TOKENS, "Tokens Command Usage");
					CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/tokens <player>" + CC.tnInfo + " Displays specified balance.");
					CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/tokens add <player> <amount>" + CC.tnInfo + " Add tokens to specified account.");
					CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/tokens set <player> <amount>" + CC.tnInfo + " Set the tokens balance of specified account.");
					CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/tokens remove <player> <amount>" + CC.tnInfo + " Remove tokens from specified account.");
					CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/tokens reset <player>" + CC.tnInfo + " Reset specified account balance to 0.");
					CategoryMSG.senderFooter(sender);
				}
			}
			else
			{
				CategoryMSG.senderArgsErr(sender, Category.TOKENS, "");
				CategoryMSG.senderHeader(sender, Category.TOKENS, "Tokens Command Usage");
				CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/tokens <player>" + CC.tnInfo + " Displays specified balance.");
				CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/tokens add <player> <amount>" + CC.tnInfo + " Add tokens to specified account.");
				CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/tokens set <player> <amount>" + CC.tnInfo + " Set the tokens balance of specified account.");
				CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/tokens remove <player> <amount>" + CC.tnInfo + " Remove tokens from specified account.");
				CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/tokens reset <player>" + CC.tnInfo + " Reset specified account balance to 0.");
				CategoryMSG.senderFooter(sender);
			}
		}
		else
		{
			PermMSG.noPerm(sender, Rank.ADMIN);
		}
		return false;
	}

}
