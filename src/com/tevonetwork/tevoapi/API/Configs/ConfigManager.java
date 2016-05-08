package com.tevonetwork.tevoapi.API.Configs;

import org.bukkit.configuration.file.FileConfiguration;

import com.tevonetwork.tevoapi.TevoAPI;

public class ConfigManager {

	private ConfigFile config;
	private ConfigFile worlds;
	private ConfigFile portals;
	private ConfigFile warps;
	
	public void load()
	{
		TevoAPI tevoapi = TevoAPI.getInstance();
		
		config = new ConfigFile(tevoapi, tevoapi.getDataFolder(), "config", true);
		worlds = new ConfigFile(tevoapi, tevoapi.getDataFolder(), "worlds", false);
		portals = new ConfigFile(tevoapi, tevoapi.getDataFolder(), "portals", false);
		warps = new ConfigFile(tevoapi, tevoapi.getDataFolder(), "warps", false);
		
		config.reload();
		worlds.reload();
		portals.reload();
		warps.reload();
	}
	
	public FileConfiguration getConfig()
	{
		return this.config.getConfig();
	}
	
	public FileConfiguration getWorlds()
	{
		return this.worlds.getConfig();
	}
	
	public FileConfiguration getPortals()
	{
		return this.portals.getConfig();
	}
	
	public FileConfiguration getWarps()
	{
		return this.warps.getConfig();
	}
	
	public void saveConfig()
	{
		this.config.save();
	}
	
	public void saveWorlds()
	{
		this.worlds.save();
	}
	
	public void savePortals()
	{
		this.portals.save();
	}
	
	public void saveWarps()
	{
		this.warps.save();
	}
	
	
	public void reloadConfig()
	{
		this.config.reload();
	}
	
	public void reloadWorlds()
	{
		this.worlds.reload();
	}
	
	public void reloadPortals()
	{
		this.portals.reload();
	}
	
	public void reloadWarps()
	{
		this.warps.reload();
	}
	
}
