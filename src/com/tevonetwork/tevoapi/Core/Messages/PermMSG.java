package com.tevonetwork.tevoapi.Core.Messages;

import org.bukkit.command.CommandSender;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;

public class PermMSG {

	private static String perm_Prefix = CategoryMSG.getMSGPrefix(Category.PERMISSIONS);

	public static void noPerm(CommandSender s, Rank rankrequired) {
		if (rankrequired.equals(Rank.OWNER)) {
			s.sendMessage(perm_Prefix + CC.cRED + "The rank: " + Rank.getRankPrefix(Rank.OWNER) + CC.cRED + " is required to do that!");
		}
		else if (rankrequired.equals(Rank.DEVELOPER)) {
			s.sendMessage(perm_Prefix + CC.cRED + "The rank: " + Rank.getRankPrefix(Rank.DEVELOPER) + CC.cRED + " is required to do that!");
		}
		else if (rankrequired.equals(Rank.ADMIN)) {
			s.sendMessage(perm_Prefix + CC.cRED + "The rank: " + Rank.getRankPrefix(Rank.ADMIN) + CC.cRED + " is required to do that!");
		}
		else if (rankrequired.equals(Rank.BUILDER)) {
			s.sendMessage(perm_Prefix + CC.cRED + "The rank: " + Rank.getRankPrefix(Rank.BUILDER) + CC.cRED + " is required to do that!");
		}
		else if (rankrequired.equals(Rank.MODERATOR)) {
			s.sendMessage(perm_Prefix + CC.cRED + "The rank: " + Rank.getRankPrefix(Rank.MODERATOR) + CC.cRED + " is required to do that!");
		}
		else if (rankrequired.equals(Rank.CRYSTAL)) {
			s.sendMessage(perm_Prefix + CC.cRED + "The rank: " + Rank.getRankPrefix(Rank.CRYSTAL) + CC.cRED + " is required to do that!");
		}
		else if (rankrequired.equals(Rank.LOYALIST)) {
			s.sendMessage(perm_Prefix + CC.cRED + "The rank: " + Rank.getRankPrefix(Rank.LOYALIST) + CC.cRED + " is required to do that!");
		}
		else if (rankrequired.equals(Rank.MYSTIC)) {
			s.sendMessage(perm_Prefix + CC.cRED + "The rank: " + Rank.getRankPrefix(Rank.MYSTIC) + CC.cRED + " is required to do that!");
		}
		else {
			s.sendMessage(perm_Prefix + CC.cRED + "You do not have sufficient permissions to do that!");
		}
	}
}
