package com.tevonetwork.tevoapi.Core.Messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.LogLevel;

public class CategoryMSG {

	private static String start_Prefix = CC.cD_Gray + "[" + CC.tnPrefix + CC.cGray + ">";
	private static String end_Prefix = CC.cD_Gray + "] ";
	
	public static String getMSGPrefix(Category category)
	{
		if (category == null)
		{
			TevoAPI.getInstance().getUtilLogger().logLevel(LogLevel.WARNING, "A category was passed as null in Category MSG!");
			return start_Prefix + ChatColor.RED + "ERROR GETTING CATEGORY" + end_Prefix;
		}
		else
		{
			return start_Prefix + Category.getCategoryString(category) + end_Prefix;
		}
	}
	
	public static void senderMessage(CommandSender s, Category category, String msg)
	{
		s.sendMessage(getMSGPrefix(category) + msg);
	}
	
	public static void senderHeader(CommandSender s, Category category, String header)
	{
		s.sendMessage(getMSGPrefix(category) + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + header + CC.cD_Gray + CC.fBold + ":");
	}
	
	public static void senderFooter(CommandSender s)
	{
		s.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "•••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••");
	}
	
	public static void senderMessageNoCategory(CommandSender s, String msg)
	{
		s.sendMessage(msg);
	}
	
	public static void senderMessagePlayersOnly(CommandSender s, Category category)
	{
		s.sendMessage(getMSGPrefix(category) + ChatColor.RED + "Only players can do this!");
	}
	
	public static void senderArgsErr(CommandSender s, Category category, String usage)
	{
		s.sendMessage(getMSGPrefix(category) + CC.tnError + "Not enough Arguments! " + CC.tnInfo + " Usage: " + CC.tnUse + usage);
	}
	
	public static void senderInvArgsErr(CommandSender s, Category category, String usage)
	{
		s.sendMessage(getMSGPrefix(category) + CC.tnError + "Invalid Arguments! " + CC.tnUse + usage);
	}
	
	public static void senderTMArgs(CommandSender s, Category category, String usage)
	{
		s.sendMessage(getMSGPrefix(category) + CC.tnError + "Too many Arguments! " + CC.tnUse + usage);
	}
	
}
