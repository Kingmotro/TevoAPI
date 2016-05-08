package com.tevonetwork.tevoapi.Core.Chat;

import java.util.Arrays;
import java.util.List;

import com.tevonetwork.tevoapi.TevoAPI;

public class AntiSwearCheck {

	private static TevoAPI main = TevoAPI.getInstance();
	
	public static boolean scanMessage(String msg)
	{
		List<String> msgsplit = Arrays.asList(msg.toLowerCase().split(" "));
		List<String> swearwords = main.getConfigManager().getConfig().getStringList("swearblacklist");
		for (String cens : swearwords)
		{
			if (msgsplit.contains(cens.toLowerCase())) 
			{
				return false;
			}
		}
		return true;
	}
	
}
