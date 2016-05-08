package com.tevonetwork.tevoapi.API.Configs;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A much more flexible configuration object to work with.
 * 
 */
public class ConfigFile {

	private JavaPlugin plugin;
	private File path;
	private File file;
	private String name;
	private FileConfiguration config;
	private boolean locatedinJar;
	
	/**
	 * Create a new config file.
	 * 
	 * @param p Owning plugin.
	 * @param path Path of the file.
	 * @param name Name of the file.
	 * @param locatedinJar Whether or not the config is located within the plugin jar.
	 */
	public ConfigFile(JavaPlugin p, File path, String name, boolean locatedinJar)
	{
		this.plugin = p;
		this.path = path;
		this.name = (name + ".yml");
		this.file = new File(path, this.name);
		this.locatedinJar = locatedinJar;
		
	}
	
	public void reload()
	{
		if (!this.path.exists())
		{
			this.path.mkdirs();
		}
		try
		{
			if (this.locatedinJar)
			{
				if (!this.file.exists())
				{
					Files.copy(this.plugin.getResource(this.name), this.file.toPath(), new CopyOption[0]);
				}
			}
			else if (!this.file.exists())
			{
				this.file.createNewFile();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	public void save()
	{
		try
		{
			this.config.save(this.file);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void backupToFolder(File folder, String name)
	{
		try
		{
			Files.copy(this.file.toPath(), new File(folder, name).toPath(), new CopyOption[0]);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void regenConfig()
	{
		if (!this.path.exists())
		{
			this.path.mkdirs();
		}
		if (this.file.exists())
		{
			this.file.delete();
		}
		try
		{
			if (this.locatedinJar)
			{
				if (!this.file.exists())
				{
					Files.copy(this.plugin.getResource(this.name), this.file.toPath(), new CopyOption[0]);
				}
			}
			else if (!this.file.exists())
			{
				this.file.createNewFile();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	public FileConfiguration getConfig()
	{
		return this.config;
	}
	
	public File getFile()
	{
		return this.file;
	}
	
	public FileConfiguration getCopy()
	{
		return YamlConfiguration.loadConfiguration(this.file);
	}
	
}
