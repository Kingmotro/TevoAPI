package com.tevonetwork.tevoapi.Core;

public enum LogColor {

	GREEN ("§a"),
	RED ("§c"),
	BLUE ("§9"),
	YELLOW ("§e"), 
	PINK ("§d"), 
	CYAN ("§b"), 
	DEFAULT ("§f");
	
	private final String color;
	
	LogColor(String color)
	{
		this.color = color;
	}
	
	public static String getLogColor(LogColor color)
	{
		return color.color;
	}
}
