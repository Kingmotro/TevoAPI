package com.tevonetwork.tevoapi.API.Portals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Configs.ConfigManager;
import com.tevonetwork.tevoapi.API.Regions.Region;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.LogLevel;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Travel.SendtoLocation;
import com.tevonetwork.tevoapi.Core.Travel.SendtoServer;
import com.tevonetwork.tevoapi.Core.Travel.SendtoWorld;
import com.tevonetwork.tevoapi.Core.common.JavaCommon;

public class PortalManager {

	private static TevoAPI main = TevoAPI.getInstance();
	private static ConfigManager cfm = main.getConfigManager();

	private static HashMap<String, Portal> portals = new HashMap<String, Portal>();
	private static HashMap<String, Location> playerminselection = new HashMap<String, Location>();
	private static HashMap<String, Location> playermaxselection = new HashMap<String, Location>();

	public static void load() {
		Set<String> portalsincf = cfm.getPortals().getKeys(false);
		Iterator<String> itr = portalsincf.iterator();

		while (itr.hasNext()) {
			String portalname = itr.next();
			String destination = null;
			main.getUtilLogger().logNormal("Portal> Loading portal: " + portalname.toUpperCase());
			if (cfm.getPortals().contains(portalname.toUpperCase() + ".destination.location")) {
				String world = cfm.getPortals().getString(portalname + ".destination.location.world");
				String x = String.valueOf(cfm.getPortals().getDouble(portalname + ".destination.location.x"));
				String y = String.valueOf(cfm.getPortals().getDouble(portalname + ".destination.location.y"));
				String z = String.valueOf(cfm.getPortals().getDouble(portalname + ".destination.location.z"));
				String yaw = String.valueOf(cfm.getPortals().getDouble(portalname + ".destination.location.yaw"));
				String pitch = String.valueOf(cfm.getPortals().getDouble(portalname + ".destination.location.pitch"));

				destination = "destination_location#" + world + "#" + x + "#" + y + "#" + z + "#" + yaw + "#" + pitch;
			}
			else if (cfm.getPortals().contains(portalname.toUpperCase() + ".destination.world")) {
				String world = cfm.getPortals().getString(portalname.toUpperCase() + ".destination.world");

				destination = "destination_world#" + world;

			}
			else if (cfm.getPortals().contains(portalname.toUpperCase() + ".destination.server")) {
				String server = cfm.getPortals().getString(portalname.toUpperCase() + ".destination.server");
				destination = "destination_server#" + server;
			}
			World world = Bukkit.getServer().getWorld(cfm.getPortals().getString(portalname + ".region.world"));
			double rminx = cfm.getPortals().getDouble(portalname.toUpperCase().toUpperCase() + ".region.min.x");
			double rminy = cfm.getPortals().getDouble(portalname.toUpperCase().toUpperCase() + ".region.min.y");
			double rminz = cfm.getPortals().getDouble(portalname.toUpperCase() + ".region.min.z");
			double rmaxx = cfm.getPortals().getDouble(portalname.toUpperCase() + ".region.max.x");
			double rmaxy = cfm.getPortals().getDouble(portalname.toUpperCase() + ".region.max.y");
			double rmaxz = cfm.getPortals().getDouble(portalname.toUpperCase() + ".region.max.z");
			boolean enabled = cfm.getPortals().getBoolean(portalname.toUpperCase() + ".enabled");

			Location rmax = new Location(world, rmaxx, rmaxy, rmaxz);
			Location rmin = new Location(world, rminx, rminy, rminz);

			loadPortal(portalname.toUpperCase(), destination, new Region(rmin, rmax), enabled);
		}

	}

	public static boolean createNewPortal(String name, String destination, Region region) {
		portals.put(name.toUpperCase(), new Portal(name.toUpperCase(), destination, region));
		String[] dest = destination.split("_");
		if (dest[1].startsWith("location")) {
			String[] location = dest[1].split("#");
			String world = location[1];
			double x = Double.valueOf(location[2]);
			double y = Double.valueOf(location[3]);
			double z = Double.valueOf(location[4]);
			double yaw = Double.valueOf(location[5]);
			double pitch = Double.valueOf(location[6]);

			cfm.getPortals().set(name.toUpperCase() + ".destination.location.world", world);
			cfm.getPortals().set(name.toUpperCase() + ".destination.location.x", x);
			cfm.getPortals().set(name.toUpperCase() + ".destination.location.y", y);
			cfm.getPortals().set(name.toUpperCase() + ".destination.location.z", z);
			cfm.getPortals().set(name.toUpperCase() + ".destination.location.yaw", yaw);
			cfm.getPortals().set(name.toUpperCase() + ".destination.location.pitch", pitch);
			cfm.getPortals().set(name.toUpperCase() + ".region.world", region.getMin().getWorld().getName());
			cfm.getPortals().set(name.toUpperCase() + ".region.min.x", region.getMin().getX());
			cfm.getPortals().set(name.toUpperCase() + ".region.min.y", region.getMin().getY());
			cfm.getPortals().set(name.toUpperCase() + ".region.min.z", region.getMin().getZ());
			cfm.getPortals().set(name.toUpperCase() + ".region.max.x", region.getMax().getX());
			cfm.getPortals().set(name.toUpperCase() + ".region.max.y", region.getMax().getY());
			cfm.getPortals().set(name.toUpperCase() + ".region.max.z", region.getMax().getZ());
			cfm.getPortals().set(name.toUpperCase() + ".enabled", true);
			cfm.savePortals();
			return true;
		}
		else if (dest[1].startsWith("world")) {
			String[] location = dest[1].split("#");
			String world = location[1];
			cfm.getPortals().set(name.toUpperCase() + ".destination.world", world);
			cfm.getPortals().set(name.toUpperCase() + ".region.world", region.getMin().getWorld().getName());
			cfm.getPortals().set(name.toUpperCase() + ".region.min.x", region.getMin().getX());
			cfm.getPortals().set(name.toUpperCase() + ".region.min.y", region.getMin().getY());
			cfm.getPortals().set(name.toUpperCase() + ".region.min.z", region.getMin().getZ());
			cfm.getPortals().set(name.toUpperCase() + ".region.max.x", region.getMax().getX());
			cfm.getPortals().set(name.toUpperCase() + ".region.max.y", region.getMax().getY());
			cfm.getPortals().set(name.toUpperCase() + ".region.max.z", region.getMax().getZ());
			cfm.getPortals().set(name.toUpperCase() + ".enabled", true);
			cfm.savePortals();
			return true;
		}
		else if (dest[1].startsWith("server")) {
			String[] location = dest[1].split("#");
			String server = location[1];

			cfm.getPortals().set(name.toUpperCase() + ".destination.server", server);
			cfm.getPortals().set(name.toUpperCase() + ".region.world", region.getMin().getWorld().getName());
			cfm.getPortals().set(name.toUpperCase() + ".region.min.x", region.getMin().getX());
			cfm.getPortals().set(name.toUpperCase() + ".region.min.y", region.getMin().getY());
			cfm.getPortals().set(name.toUpperCase() + ".region.min.z", region.getMin().getZ());
			cfm.getPortals().set(name.toUpperCase() + ".region.max.x", region.getMax().getX());
			cfm.getPortals().set(name.toUpperCase() + ".region.max.y", region.getMax().getY());
			cfm.getPortals().set(name.toUpperCase() + ".region.max.z", region.getMax().getZ());
			cfm.getPortals().set(name.toUpperCase() + ".enabled", true);
			cfm.savePortals();
			return true;
		}
		else {
			return false;
		}
	}

	public static void loadPortal(String name, String destination, Region region, boolean enabled) {
		portals.put(name.toUpperCase(), new Portal(name.toUpperCase(), destination, region));
		portals.get(name.toUpperCase()).setEnabled(enabled);
	}

	public static boolean setPortalDestination(String name, String destination) {
		if (portals.containsKey(name.toUpperCase())) {
			String[] getdest = destination.split("_");
			if (getdest[1].startsWith("location")) {
				Portal portal = portals.get(name.toUpperCase());
				portal.setDestination(destination);
				String[] location = getdest[1].split("#");
				String world = location[1];
				double x = Double.valueOf(location[2]);
				double y = Double.valueOf(location[3]);
				double z = Double.valueOf(location[4]);
				double yaw = Double.valueOf(location[5]);
				double pitch = Double.valueOf(location[6]);

				if (cfm.getPortals().contains(name.toUpperCase() + ".destination.world")) {
					cfm.getPortals().set(name.toUpperCase() + ".destination.world", null);
				}
				if (cfm.getPortals().contains(name.toUpperCase() + ".destination.server")) {
					cfm.getPortals().set(name.toUpperCase() + ".destination.server", null);
				}

				cfm.getPortals().set(name.toUpperCase() + ".destination.location.world", world);
				cfm.getPortals().set(name.toUpperCase() + ".destination.location.x", x);
				cfm.getPortals().set(name.toUpperCase() + ".destination.location.y", y);
				cfm.getPortals().set(name.toUpperCase() + ".destination.location.z", z);
				cfm.getPortals().set(name.toUpperCase() + ".destination.location.yaw", yaw);
				cfm.getPortals().set(name.toUpperCase() + ".destination.location.pitch", pitch);
				cfm.savePortals();
				cfm.reloadPortals();
				return true;
			}
			else if (getdest[1].startsWith("world")) {
				Portal portal = portals.get(name.toUpperCase());
				portal.setDestination(destination);
				String[] location = getdest[1].split("#");
				String world = location[1];

				if (cfm.getPortals().contains(name.toUpperCase() + ".destination.location")) {
					cfm.getPortals().set(name.toUpperCase() + ".destination.location", null);
				}
				if (cfm.getPortals().contains(name.toUpperCase() + ".destination.server")) {
					cfm.getPortals().set(name.toUpperCase() + ".destination.server", null);
				}

				cfm.getPortals().set(name.toUpperCase() + ".destination.world", world);
				cfm.savePortals();
				cfm.reloadPortals();
				return true;
			}
			else if (getdest[1].startsWith("server")) {
				Portal portal = portals.get(name.toUpperCase());
				portal.setDestination(destination);
				String[] location = getdest[1].split("#");
				String server = location[1];

				if (cfm.getPortals().contains(name.toUpperCase() + ".destination.world")) {
					cfm.getPortals().set(name.toUpperCase() + ".destination.world", null);
				}
				if (cfm.getPortals().contains(name.toUpperCase() + ".destination.location")) {
					cfm.getPortals().set(name.toUpperCase() + ".destination.location", null);
				}

				cfm.getPortals().set(name.toUpperCase() + ".destination.server", server);
				cfm.savePortals();
				cfm.reloadPortals();
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	public static boolean removePortal(String name) {
		if (portals.containsKey(name.toUpperCase())) {
			portals.remove(name.toUpperCase());
			cfm.getPortals().set(name.toUpperCase(), null);
			cfm.savePortals();
			return true;
		}
		main.getUtilLogger().logLevel(LogLevel.WARNING, "Portal> Failed to remove portal with name: " + name);
		return false;
	}

	public static boolean setPortalEnabled(String name, boolean enabled) {
		if (portals.containsKey(name.toUpperCase())) {
			portals.get(name.toUpperCase()).setEnabled(enabled);
			return true;
		}
		else {
			main.getUtilLogger().logLevel(LogLevel.WARNING, "Portal> Failed to change state of portal: " + name);
			return false;
		}
	}

	private static ArrayList<String> playertriggered = new ArrayList<String>();

	public static void clearTriggered(Player p) {
		if (playertriggered.contains(p.getName())) {
			playertriggered.remove(p.getName());
		}
	}

	public static void checkPlayerinPortal(Player p) {
		if (playertriggered.contains(p.getName())) {
			return;
		}
		Collection<Portal> portal = portals.values();
		Iterator<Portal> itr = portal.iterator();

		while (itr.hasNext()) {
			Portal port = itr.next();
			if (port.getRegion().containsBlock(p.getLocation())) {
				if (port.isEnabled()) {
					String[] dest = port.getDestination().split("_");
					if (dest[1].startsWith("world")) {
						String[] getdest = dest[1].split("#");
						World world = Bukkit.getServer().getWorld(getdest[1]);
						if (world != null) {
							new SendtoWorld(getdest[1], p);
							playertriggered.add(p.getName());
						}
					}
					else if (dest[1].startsWith("server")) {
						String[] getdest = dest[1].split("#");
						new SendtoServer(p, getdest[1]);
						playertriggered.add(p.getName());

					}
					else if (dest[1].startsWith("location")) {
						String[] locationarray = dest[1].split("#");

						World world = Bukkit.getServer().getWorld(locationarray[1]);
						String x = locationarray[2];
						String y = locationarray[3];
						String z = locationarray[4];
						String yaw = locationarray[5];
						String pitch = locationarray[6];
						double pitchvalue = Double.valueOf(pitch);
						double yawvalue = Double.valueOf(yaw);

						new SendtoLocation(p, new Location(world, Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), (float) yawvalue, (float) pitchvalue));
						playertriggered.add(p.getName());
					}
					else {
						main.getUtilLogger().logLevel(LogLevel.WARNING, "Portal> Portal has invalid destination, Portal name: " + port.getName());
					}
				}
			}
		}

	}

	public static boolean sendPortalInfo(CommandSender sender, String portalname) {
		boolean success = false;
		if (portals.containsKey(portalname.toUpperCase())) {
			Portal portal = portals.get(portalname.toUpperCase());
			CategoryMSG.senderHeader(sender, Category.TRAVEL, "Portal Information for " + CC.tnValue + portalname.toUpperCase());
			CategoryMSG.senderMessageNoCategory(sender, CC.tnInfo + "Name: " + CC.tnValue + portalname.toUpperCase());
			CategoryMSG.senderMessageNoCategory(sender, CC.tnInfo + "World: " + CC.tnValue + portal.getRegion().getMin().getWorld().getName());
			CategoryMSG.senderMessageNoCategory(sender, CC.tnInfo + "State: " + JavaCommon.booleantoEnabledDisabled(portal.isEnabled()));
			CategoryMSG.senderMessageNoCategory(sender, CC.tnInfo + "Destination: " + CC.tnValue + portal.getDestination());
			CategoryMSG.senderFooter(sender);
			success = true;
		}

		return success;
	}

	public static boolean portalExist(String name) {
		return portals.containsKey(name.toUpperCase());
	}

	public static Set<String> getPortalList() {
		return portals.keySet();
	}

	public static void setPlayerMAXSelection(Player p, Location loc) {
		playermaxselection.put(p.getName(), loc);
	}

	public static void setPlayerMINSelection(Player p, Location loc) {
		playerminselection.put(p.getName(), loc);
	}

	public static Location getPlayerMINSelection(Player p) {
		return playerminselection.get(p.getName());
	}

	public static Location getPlayerMAXSelection(Player p) {
		return playermaxselection.get(p.getName());
	}

	public static boolean hasSelection(Player p) {
		return playermaxselection.containsKey(p.getName()) && playerminselection.containsKey(p.getName());
	}
}
