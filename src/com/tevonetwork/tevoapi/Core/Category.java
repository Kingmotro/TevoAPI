package com.tevonetwork.tevoapi.Core;

import org.bukkit.ChatColor;

public enum Category {
	
	GAME ("Game"),
	ABILITY("Ability"),
	SERVER ("Server"),
	TRAVEL("Travel"),
	UTILS("Utils"), 
	PLAYER("Player"), 
	ENCHANT("Enchant"),
	PUNISH("Punish"), 
	PERMISSIONS("Permissions"), 
	HELP("Help"), 
	PVP("PVP"), 
	VOTE("Vote"), 
	VANITY("Vanity"),
	HUB("Hub"),
	KIT("Kit"),
	BROADCAST("Broadcast"),
	DEATH("Death"),
	CHAT("Chat"),
	WORLD("World"),
	COOLDOWN("Cooldown"),
	TOKENS("Tokens");
	
	private final String category_String;
	
	Category(String category)
	{
		this.category_String = category;
	}
	
	public static String getCategoryString(Category category)
	{
		return ChatColor.BLUE + category.category_String;
	}
	
}
