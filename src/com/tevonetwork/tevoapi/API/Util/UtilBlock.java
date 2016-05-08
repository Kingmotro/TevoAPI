package com.tevonetwork.tevoapi.API.Util;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class UtilBlock {

	public static ArrayList<Block> getSurrounding(Block center, int diameter)
	{
		ArrayList<Block> blocks = new ArrayList<Block>();
		int rad = diameter / 2;
		for (int x = -rad; x <= rad; x++)
		{
			for (int z = -rad; z <= rad; z++)
			{
				blocks.add(center.getRelative(x, 0, z));
			}
		}
		return blocks;
	}
	
	public static ArrayList<Block> getShroud(Block block)
	{
		return getShroud(block, false);
	}
	
	public static ArrayList<Block> getShroud(Block block, boolean diagonals)
	{
		ArrayList<Block> blocks = new ArrayList<Block>();
		if (diagonals)
	    {
	      for (int x = -1; x <= 1; x++) {
	        for (int y = -1; y <= 1; y++) {
	          for (int z = -1; z <= 1; z++) {
	            if ((x != 0) || (y != 0) || (z != 0)) {
	              blocks.add(block.getRelative(x, y, z));
	            }
	          }
	        }
	      }
	    }
	    else
	    {
	      blocks.add(block.getRelative(BlockFace.UP));
	      blocks.add(block.getRelative(BlockFace.DOWN));
	      blocks.add(block.getRelative(BlockFace.NORTH));
	      blocks.add(block.getRelative(BlockFace.SOUTH));
	      blocks.add(block.getRelative(BlockFace.EAST));
	      blocks.add(block.getRelative(BlockFace.WEST));
	    }
	    return blocks;
	}
	
}
