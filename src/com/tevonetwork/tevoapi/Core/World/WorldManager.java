package com.tevonetwork.tevoapi.Core.World;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Configs.ConfigManager;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilWorld;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.LogLevel;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Networking.WorldPlayerCounter;
import com.tevonetwork.tevoapi.Core.common.JavaCommon;

public class WorldManager {

	private HashMap<String, UtilWorld> worlds = new HashMap<String, UtilWorld>();
	private TevoAPI main = TevoAPI.getInstance();
	private ConfigManager cfm = main.getConfigManager();

	public void initialize() {
		List<World> defaultworld = Bukkit.getServer().getWorlds();
		String defaultworldname = "world";
		if (!cfm.getWorlds().contains(defaultworld.get(0).getName())) {
			defaultworldname = defaultworld.get(0).getName();
			newWorld(defaultworldname);
		}
		Set<String> confworlds = cfm.getWorlds().getKeys(false);
		Iterator<String> itr = confworlds.iterator();
		while (itr.hasNext()) {
			String worldname = itr.next();
			Bukkit.getServer().createWorld(new WorldCreator(worldname));
			UtilWorld world = new UtilWorld(worldname);
			main.getUtilLogger().logNormal("WorldManager> Loading world: " + worldname);
			for (WorldProperty property : WorldProperty.values()) {
				world.setWorldProperty(property, cfm.getWorlds().getBoolean(worldname + "." + property.toString().toLowerCase()));
			}
			String defgm = cfm.getWorlds().getString(worldname + ".defaultgamemode");
			switch (defgm.toUpperCase()) {
				case ("SURVIVAL"):
					world.setDefaultGamemode(GameMode.SURVIVAL);
					break;
				case ("CREATIVE"):
					world.setDefaultGamemode(GameMode.CREATIVE);
					break;
				case ("ADVENTURE"):
					world.setDefaultGamemode(GameMode.ADVENTURE);
					break;
				case ("SPECTATOR"):
					world.setDefaultGamemode(GameMode.SPECTATOR);
					break;
			}
			cfm.saveWorlds();
			worlds.put(worldname, world);
		}

	}

	public boolean reloadWorldProperties() {
		cfm.reloadWorlds();
		Collection<UtilWorld> loadedworlds = worlds.values();
		Iterator<UtilWorld> itr = loadedworlds.iterator();
		while (itr.hasNext()) {
			UtilWorld loadedworld = itr.next();
			for (WorldProperty property : WorldProperty.values()) {
				loadedworld.setWorldProperty(property, cfm.getWorlds().getBoolean(loadedworld.getWorldName() + "." + property.toString().toLowerCase()));
			}
			String defgm = cfm.getWorlds().getString(loadedworld.getWorldName() + ".defaultgamemode");
			switch (defgm.toUpperCase()) {
				case ("SURVIVAL"):
					loadedworld.setDefaultGamemode(GameMode.SURVIVAL);
					break;
				case ("CREATIVE"):
					loadedworld.setDefaultGamemode(GameMode.CREATIVE);
					break;
				case ("ADVENTURE"):
					loadedworld.setDefaultGamemode(GameMode.ADVENTURE);
					break;
				case ("SPECTATOR"):
					loadedworld.setDefaultGamemode(GameMode.SPECTATOR);
					break;
			}
		}
		return true;

	}

	public boolean newWorld(String name) {
		if (!worlds.containsKey(name)) {
			Bukkit.getServer().createWorld(new WorldCreator(name));
			UtilWorld utilworld = new UtilWorld(name);
			cfm.getWorlds().createSection(name);
			for (WorldProperty property : WorldProperty.values()) {
				cfm.getWorlds().set(name + "." + property.toString().toLowerCase(), utilworld.getWorldProperty(property));
			}
			cfm.getWorlds().set(name + ".defaultgamemode", "SURVIVAL");
			cfm.saveWorlds();
			worlds.put(name, utilworld);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean importWorld(String name) {
		File worldfile = new File(Bukkit.getServer().getWorldContainer(), name);
		if ((worldfile.exists()) && (!worlds.containsKey(name))) {
			main.getUtilLogger().logNormal("WorldManager> Importing world " + name + "...");
			Bukkit.getServer().createWorld(new WorldCreator(name));
			UtilWorld utilworld = new UtilWorld(name);
			cfm.getWorlds().createSection(name);
			for (WorldProperty property : WorldProperty.values()) {
				cfm.getWorlds().set(name + "." + property.toString().toLowerCase(), utilworld.getWorldProperty(property));
			}
			cfm.getWorlds().set(name + ".defaultgamemode", "SURVIVAL");
			cfm.saveWorlds();
			worlds.put(name, utilworld);
			return true;
		}
		return false;
	}

	public boolean deleteWorld(String name) {
		if (worlds.containsKey(name)) {
			main.getUtilLogger().logNormal("WorldManager> Deleting world " + name + "...");
			Bukkit.getServer().unloadWorld(name, true);
			worlds.remove(name);
			if (cfm.getWorlds().contains(name)) {
				cfm.getWorlds().set(name, null);
				cfm.saveWorlds();
			}
			return true;
		}
		else {
			return false;
		}
	}

	public GameMode getWorldDefaultGamemode(String worldname) {
		if (worlds.containsKey(worldname)) {
			return worlds.get(worldname).getDefaultGamemode();
		}
		return null;
	}

	public boolean getWorldProperty(String worldname, WorldProperty property) {
		if (worlds.containsKey(worldname)) {
			UtilWorld world = worlds.get(worldname);
			return world.getWorldProperty(property);

		}
		else {
			return false;
		}
	}

	public boolean setWorldProperty(String worldname, WorldProperty property, boolean enable) {
		if (worlds.containsKey(worldname)) {
			worlds.get(worldname).setWorldProperty(property, enable);
			cfm.getWorlds().set(worldname + "." + property.toString().toLowerCase(), enable);
			cfm.saveWorlds();
			main.getUtilLogger().logNormal("WorldManager> Property " + property.toString().toUpperCase() + " of world " + worldname + " was set to " + String.valueOf(enable) + ".");
			return true;
		}
		else {
			main.getUtilLogger().logLevel(LogLevel.WARNING, "World property coudln't be set as world is not loaded!");
			return false;
		}

	}

	public boolean setWorldSpawn(String worldname, Location spawnloc) {
		if (worlds.containsKey(worldname)) {
			double x = spawnloc.getX();
			double y = spawnloc.getY();
			double z = spawnloc.getZ();
			double pitch = spawnloc.getPitch();
			double yaw = spawnloc.getYaw();

			cfm.getWorlds().set(worldname + ".spawn.x", x);
			cfm.getWorlds().set(worldname + ".spawn.y", y);
			cfm.getWorlds().set(worldname + ".spawn.z", z);
			cfm.getWorlds().set(worldname + ".spawn.pitch", pitch);
			cfm.getWorlds().set(worldname + ".spawn.yaw", yaw);
			cfm.saveWorlds();

			return true;
		}
		else {
			return false;
		}
	}

	public Location getWorldSpawn(String worldname) {
		if (cfm.getWorlds().contains(worldname + ".spawn")) {
			double x = cfm.getWorlds().getDouble(worldname + ".spawn.x");
			double y = cfm.getWorlds().getDouble(worldname + ".spawn.y");
			double z = cfm.getWorlds().getDouble(worldname + ".spawn.z");
			double pitch = cfm.getWorlds().getDouble(worldname + ".spawn.pitch");
			double yaw = cfm.getWorlds().getDouble(worldname + ".spawn.yaw");

			return new Location(Bukkit.getServer().getWorld(worldname), x, y, z, (float) yaw, (float) pitch);
		}
		else {
			World world = Bukkit.getServer().getWorld(worldname);
			if (world != null) {
				return world.getSpawnLocation();
			}
		}
		return null;
	}

	public boolean unloadWorld(String worldname) {
		if (Bukkit.getServer().getWorld(worldname) != null) {
			Bukkit.getServer().unloadWorld(worldname, true);
			return true;
		}
		else {
			return false;
		}
	}

	public List<String> getWorldConfigList() {
		Set<String> worldsconf = cfm.getWorlds().getKeys(false);
		List<String> worldlist = new ArrayList<String>();
		for (String worlds : worldsconf) {
			worldlist.add(worlds);
		}
		return worldlist;

	}

	public List<String> getLoadedWorlds() {
		List<String> worldnames = new ArrayList<String>();
		for (World worlds : Bukkit.getServer().getWorlds()) {
			worldnames.add(worlds.getName());
		}
		return worldnames;
	}

	public boolean sendAllUtilWorldsInfo(String worldname, CommandSender sender) {
		if (worlds.containsKey(worldname)) {
			UtilWorld utilworld = worlds.get(worldname);
			CategoryMSG.senderHeader(sender, Category.WORLD, "World Info for " + CC.cGreen + worldname);
			CategoryMSG.senderMessageNoCategory(sender, CC.cYellow + "Name: " + CC.cGreen + worlds.get(worldname).getWorldName());
			CategoryMSG.senderMessageNoCategory(sender, CC.cYellow + "Players in world: " + CC.tnValue + WorldPlayerCounter.getWorldCount(worldname).toString());
			CategoryMSG.senderMessageNoCategory(sender, CC.cYellow + "Chunks loaded: " + CC.cGreen + String.valueOf(Bukkit.getServer().getWorld(worldname).getLoadedChunks().length));
			CategoryMSG.senderMessageNoCategory(sender, CC.cYellow + "Entities: " + CC.cGreen + String.valueOf(Bukkit.getServer().getWorld(worldname).getEntities().size()));
			CategoryMSG.senderMessageNoCategory(sender, CC.cYellow + "Gamemode: " + CC.cGreen + utilworld.getDefaultGamemode().toString().toUpperCase());
			int tileentities = 0;
			try {
				for (Chunk chunk : Bukkit.getWorld(worldname).getLoadedChunks()) {
					tileentities += chunk.getTileEntities().length;
				}
			}
			catch (ClassCastException e) {
				main.getUtilLogger().logLevel(LogLevel.SEVERE, "Corrupted chunk data on world: " + worldname);
			}
			CategoryMSG.senderMessageNoCategory(sender, CC.cYellow + "Tiles: " + CC.cGreen + String.valueOf(tileentities));
			CategoryMSG.senderMessageNoCategory(sender, CC.cYellow + "Properties: ");
			for (WorldProperty property : WorldProperty.values()) {
				CategoryMSG.senderMessageNoCategory(sender,
						CC.tnValue + property.toString().toUpperCase() + CC.tnInfo + ": " + JavaCommon.booleantoEnabledDisabled(utilworld.getWorldProperty(property)));
			}
			CategoryMSG.senderFooter(sender);
			return true;
		}
		else {
			return false;
		}
	}

}
