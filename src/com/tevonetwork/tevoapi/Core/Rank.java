package com.tevonetwork.tevoapi.Core;

import com.tevonetwork.tevoapi.API.Util.CC;

public enum Rank {
	
	OWNER, DEVELOPER, ADMIN, BUILDER, MODERATOR, CRYSTAL, LOYALIST, MYSTIC, DEFAULT;
	
	public static String getRankPrefix(Rank rank)
	{
		String prefix = null;
		if (rank.equals(Rank.OWNER))
		{
			prefix = CC.tnOwner + "Owner"; 
		}
		if (rank.equals(Rank.DEVELOPER))
		{
			prefix = CC.cD_Aqua + CC.fBold + "Developer"; 
		}
		if (rank.equals(Rank.ADMIN))
		{
			prefix = CC.tnAdmin + "Admin"; 
		}
		if (rank.equals(Rank.BUILDER))
		{
			prefix = CC.tnBuilder + "Builder"; 
		}
		if (rank.equals(Rank.MODERATOR))
		{
			prefix = CC.tnMod + "Moderator"; 
		}
		if (rank.equals(Rank.CRYSTAL))
		{
			prefix = CC.tnCrystal + "Crystal"; 
		}
		if (rank.equals(Rank.LOYALIST))
		{
			prefix = CC.tnLoyalist + "Loyalist"; 
		}
		if (rank.equals(Rank.MYSTIC))
		{
			prefix = CC.tnMystic + "Mystic"; 
		}
		if (rank.equals(Rank.DEFAULT))
		{
			prefix = CC.cGray + ""; 
		}
		
		return prefix;
		
	}
	
}
