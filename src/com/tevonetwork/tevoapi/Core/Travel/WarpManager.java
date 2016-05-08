package com.tevonetwork.tevoapi.Core.Travel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.ItemStackFactory;
import com.tevonetwork.tevoapi.API.Util.UUIDFetcher;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Items;

public class WarpManager {

	private static TevoAPI main = TevoAPI.getInstance();
	private static FileConfiguration warps = main.getConfigManager().getWarps();
	private static HashMap<String, String> pendingdeletion = new HashMap<String, String>();
	private static ItemStackFactory isf = new ItemStackFactory();
	
	public static void setup()
	{
		if (!warps.contains("Public"))
		{
			warps.createSection("Public");
		}
	}
	
	public static void playerOpenWarps(Player p)
	{
		if (warps.contains(getConfigUUID(p.getUniqueId())))
		{
			Inventory inv = Bukkit.createInventory(null, 36, p.getName() + "'s Warps");
			p.openInventory(inv);
		}
		else
		{
			UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "You have not set any warps yet!");
		}
	}
	
	public static void openPlayerWarps(String playername, Player p)
	{
		UUID puuid = UUIDFetcher.getUUID(playername);
		if (puuid != null)
		{
			if (warps.contains(getConfigUUID(puuid)))
			{
				Inventory inv = Bukkit.createInventory(null, 36, playername + "'s Warps");
				p.openInventory(inv);
			}
		}
	}
	
	public static void openPublicWarps(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 36, "Public Warps");
		p.openInventory(inv);
	}
	
	public static List<String> getPlayerWarpList(String playername)
	{
		String path = getConfigUUID(UUIDFetcher.getUUID(playername));
		Set<String> pwarps = warps.getConfigurationSection(path).getKeys(false);
		Iterator<String> itr = pwarps.iterator();
		List<String> returnedwarps = new ArrayList<String>();
		while (itr.hasNext())
		{
			returnedwarps.add(itr.next());
		}
		return returnedwarps;
	}
	
	public static List<String> getPublicWarpList()
	{
		String path = "Public";
		Set<String> pwarps = warps.getConfigurationSection(path).getKeys(false);
		Iterator<String> itr = pwarps.iterator();
		List<String> returnedwarps = new ArrayList<String>();
		while (itr.hasNext())
		{
			returnedwarps.add(itr.next());
		}
		return returnedwarps;
	}
	
	public static int getWarpCount(String playername)
	{
		String warppath = getConfigUUID(UUIDFetcher.getUUID(playername));
		if (warps.contains(warppath))
		{
			Set<String> warpscount = warps.getConfigurationSection(warppath).getKeys(false);
			return warpscount.size();
		}
		return 0;
	}
	
	public static int getPublicWarpCount()
	{
		if (warps.contains("Public"))
		{
			Set<String> warpscount = warps.getConfigurationSection("Public").getKeys(false);
			return warpscount.size();
		}
		return 0;
	}
	
	public static boolean addNewWarp(Player p, String name)
	{
		if (!(getWarpCount(p.getName()) >= 32))
		{
			String path = getConfigUUID(p.getUniqueId()) + "." + name;
			if (!warps.contains(path)) 
			{
				warps.createSection(path);
				setLocationinConf(path, p.getLocation());
				return true;
			}
		}
		return false;
	}
	
	public static boolean addNewPublicWarp(Player p, String name)
	{
		if (!(getPublicWarpCount() >= 50))
		{
			String path = "Public." + name;
			if (!warps.contains(path))
			{
				setLocationinConf(path, p.getLocation());
				return true;
			}
		}
		return false;
	}
	
	public static Location getPlayerWarp(String playername, String name)
	{
		String warppath = getConfigUUID(UUIDFetcher.getUUID(playername)) + "." + name;
		if (warps.contains(warppath))
		{
			return getLocationinConf(warppath);
		}
		return null;
	}
	
	public static Location getPublicWarp(String name)
	{
		String warppath = "Public." + name;
		if (warps.contains(warppath))
		{
			return getLocationinConf(warppath);
		}
		return null;
	}
	
	public static boolean deletePlayerWarp(String playername, String name)
	{
		String path = getConfigUUID(UUIDFetcher.getUUID(playername)) + "." + name;
		if (warps.contains(path))
		{
			warps.set(path, null);
			main.getConfigManager().saveWarps();
			return true;
		}
		return false;
	}
	
	public static boolean deletePublicWarp(String name)
	{
		String path = "Public." + name;
		if (warps.contains(path))
		{
			warps.set(path, null);
			main.getConfigManager().saveWarps();
			return true;
		}
		return false;
	}
	
	public static boolean hasPendingDelete(Player p)
	{
		if (pendingdeletion.containsKey(p.getName()))
		{
			return true;
		}
		return false;
	}
	
	public static void removePendingDelete(Player p)
	{
		if (pendingdeletion.containsKey(p.getName()))
		{
			pendingdeletion.remove(p.getName());
		}
	}
	
	
	public static String getPendingDeletion(Player p)
	{
		if (pendingdeletion.containsKey(p.getName()))
		{
			return pendingdeletion.get(p.getName());
		}
		return null;
	}
	
	public static void setPendingDelete(String name, Player p)
	{
		pendingdeletion.put(p.getName(), name);
	}
	
	
	public static String getConfigUUID(UUID uuid)
	{
		return uuid.toString().replace("-", "");
	}
	
	public static ItemStack[] constructPlayerWarpArray(String playername)
	{
		List<String> warps = getPlayerWarpList(playername);
		List<ItemStack> is = new ArrayList<ItemStack>();
		Iterator<String> itr = warps.iterator();
		while(itr.hasNext())
		{
			is.add(isf.createItemStackwithLore(Items.WOOL, CC.tnValue + itr.next(), new String[]{CC.tnUse + "Left Click " + CC.tnInfo + "to teleport" + " to this warp!", CC.tnUse + "Right Click " + CC.tnInfo + "to delete" + " this warp!"}));
		}
		return is.toArray(new ItemStack[is.size()]);
	}
	
	public static ItemStack[] constructPublicWarpArray()
	{
		List<String> warps = getPublicWarpList();
		List<ItemStack> is = new ArrayList<ItemStack>();
		Iterator<String> itr = warps.iterator();
		while(itr.hasNext())
		{
			is.add(isf.createItemStackwithLore(Items.WOOL, CC.tnValue + itr.next(), new String[]{CC.tnUse + "Left Click " + CC.tnInfo + "to teleport" + " to this warp!", CC.tnUse + "Right Click " + CC.tnInfo + "to delete" + " this warp!"}));
		}
		return is.toArray(new ItemStack[is.size()]);
	}
	
	private static void setLocationinConf(String path, Location loc)
	{
		String world = loc.getWorld().getName();
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		double yaw = loc.getYaw();
		double pitch = loc.getPitch();
		warps.set(path + ".world", world);
		warps.set(path + ".x", x);
		warps.set(path + ".y", y);
		warps.set(path + ".z", z);
		warps.set(path + ".yaw", yaw);
		warps.set(path + ".pitch", pitch);
		main.getConfigManager().saveWarps();
	}
	
	private static Location getLocationinConf(String path)
	{
		String worldname = warps.getString(path + ".world");
		double x = warps.getDouble(path + ".x");
		double y = warps.getDouble(path + ".y");
		double z = warps.getDouble(path + ".z");
		double yaw = warps.getDouble(path + ".yaw");
		double pitch = warps.getDouble(path + ".pitch");
		World world = Bukkit.getServer().getWorld(worldname);
		if (world != null)
		{
			return new Location(world, x, y, z, (float)yaw, (float)pitch);
		}
		return null;
	}
	
}
