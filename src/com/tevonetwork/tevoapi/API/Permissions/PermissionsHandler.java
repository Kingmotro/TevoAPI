package com.tevonetwork.tevoapi.API.Permissions;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.Core.Rank;

public class PermissionsHandler {

	public static boolean checkPlayerPerm(Player p, String subcat, String value)
	{
		if (p.hasPermission("tevo." + subcat + "." + value))
		{
			return true;
		}
		return false;
	}
	
	public static boolean hasRankSender(CommandSender s, Rank rank)
	{
		boolean hasrank = false;
		switch(rank)
		{
			case OWNER:
				if ((s.hasPermission("tevo.owner")) || (s.isOp()))
				{
					hasrank = true;
				}
				break;
			case DEVELOPER:
				if (s.hasPermission("tevo.dev"))
				{
					hasrank = true;
				}
				break;
			case ADMIN:
				if (s.hasPermission("tevo.admin"))
				{
					hasrank = true;
				}
				break;
			case BUILDER:
				if (s.hasPermission("tevo.builder"))
				{
					hasrank = true;
				}
				break;
			case MODERATOR:
				if (s.hasPermission("tevo.mod"))
				{
					hasrank = true;
				}
				break;
			case CRYSTAL:
				if (s.hasPermission("tevo.crystal"))
				{
					hasrank = true;
				}
				break;
			case LOYALIST:
				if (s.hasPermission("tevo.loyalist"))
				{
					hasrank = true;
				}
				break;
			case MYSTIC:
				if (s.hasPermission("tevo.mystic"))
				{
					hasrank = true;
				}
				break;
			case DEFAULT:
				if (s.hasPermission("tevo.default"))
				{
					hasrank = true;
				}
				break;
		}
		return hasrank;
	}
	
}
