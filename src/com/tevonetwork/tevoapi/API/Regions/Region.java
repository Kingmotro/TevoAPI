package com.tevonetwork.tevoapi.API.Regions;

import org.bukkit.Location;

public class Region {

	private Location minblock;
	private Location maxblock;
	
	public Region(Location min, Location max)
	{
		this.minblock = min;
		this.maxblock = max;
	}
	
	public boolean containsBlock(Location loc)
	{
		if (loc.getWorld() != this.minblock.getWorld())
		{
			return false;
		}
		
		int x1 = Math.min(this.minblock.getBlockX(), this.maxblock.getBlockX());
		int y1 = Math.min(this.minblock.getBlockY(), this.maxblock.getBlockY());
		int z1 = Math.min(this.minblock.getBlockZ(), this.maxblock.getBlockZ());
		int x2 = Math.max(this.minblock.getBlockX(), this.maxblock.getBlockX());
		int y2 = Math.max(this.minblock.getBlockY(), this.maxblock.getBlockY());
		int z2 = Math.max(this.minblock.getBlockZ(), this.maxblock.getBlockZ());
		Location l1 = new Location(this.minblock.getWorld(), x1, y1, z1);
		Location l2 = new Location(this.minblock.getWorld(), x2, y2, z2);
		return loc.getBlockX() >= l1.getBlockX() && loc.getBlockX() <= l2.getBlockX()
			      && loc.getBlockY() >= l1.getBlockY() && loc.getBlockY() <= l2.getBlockY()
			      && loc.getBlockZ() >= l1.getBlockZ() && loc.getBlockZ() <= l2.getBlockZ();
	}
	
	public Location getMin()
	{
		return this.minblock;
	}
	
	public Location getMax()
	{
		return this.maxblock;
	}
}
