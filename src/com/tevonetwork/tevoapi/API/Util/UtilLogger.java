package com.tevonetwork.tevoapi.API.Util;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import com.tevonetwork.tevoapi.Core.LogColor;
import com.tevonetwork.tevoapi.Core.LogLevel;

public class UtilLogger {

	private Logger logger;
	private JavaPlugin plugin;
	
	public UtilLogger(JavaPlugin plugin)
	{
		this.logger = plugin.getLogger();
		this.plugin = plugin;
	}
	
	public void logEnableDisable(boolean enabled)
	{
		if (enabled)
		{
			logger.info(LogColor.getLogColor(LogColor.DEFAULT) + plugin.getDescription().getVersion() + LogColor.getLogColor(LogColor.GREEN) + " Has been Enabled!");
		}
		else
		{
			logger.info(LogColor.getLogColor(LogColor.DEFAULT) + plugin.getDescription().getVersion() + LogColor.getLogColor(LogColor.RED) + " Has been Disabled!");
		}
	}
	
	public void logNormal(String logmsg)
	{
		logger.info(logmsg);
	}
	
	public void logLevel(LogLevel level, String logmsg)
	{
		if (level.equals(LogLevel.INFO))
		{
			logger.info(LogColor.getLogColor(LogColor.DEFAULT) + logmsg);
		}
		else if (level.equals(LogLevel.WARNING))
		{
			logger.warning(LogColor.getLogColor(LogColor.YELLOW) + logmsg);
		}
		else if (level.equals(LogLevel.SEVERE))
		{
			logger.severe(LogColor.getLogColor(LogColor.RED) + logmsg);
		}
	}
	
	public void logColor(LogColor color, String logmsg)
	{
		logger.info(LogColor.getLogColor(color) + logmsg);
	}
}
