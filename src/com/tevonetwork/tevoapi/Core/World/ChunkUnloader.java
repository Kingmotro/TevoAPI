package com.tevonetwork.tevoapi.Core.World;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.WorldProperty;

public class ChunkUnloader implements Runnable {

	private WorldManager w = TevoAPI.getInstance().getWorldManager();

	@Override
	public void run() {
		for (World world : Bukkit.getServer().getWorlds()) {
			if (world.getPlayers().size() == 0) {
				if (!w.getWorldProperty(world.getName(), WorldProperty.CHUNKSINRAM)) {
					Chunk[] chunkarray;
					int chunks = (chunkarray = world.getLoadedChunks()).length;
					for (int x = 0; x < chunks; x++) {
						Chunk c = chunkarray[x];
						if (c.getWorld().getPlayers().size() == 0) {
							world.unloadChunk(c);
						}
					}
				}
			}
		}
	}

}
