package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Scoreboards.ScoreboardManager;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.ItemStackFactory;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Gamemodes;
import com.tevonetwork.tevoapi.Core.Items;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class PlayerChangedWorldListener implements Listener {

	private WorldManager wm = TevoAPI.getInstance().getWorldManager();
	
	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent e)
	{
		Player p = e.getPlayer();
		if (e.getPlayer().getWorld().getName().equalsIgnoreCase("hub-borderline"))
		{
			ScoreboardManager.setupScoreboard(p, Gamemodes.BORDERLINE);
		}
		else if (e.getPlayer().getWorld().getName().equalsIgnoreCase("hub-main"))
		{
			ScoreboardManager.setupScoreboard(p, Gamemodes.HUB);
		}
		else if (e.getPlayer().getWorld().getName().startsWith("kitpve"))
		{
			ScoreboardManager.setupScoreboard(p, Gamemodes.KITPVE);
		}
		else
		{
			ScoreboardManager.setupScoreboard(p, null);
		}
		World world = p.getWorld();
		switch(wm.getWorldDefaultGamemode(world.getName()))
		{
			case SURVIVAL:
				p.setGameMode(GameMode.SURVIVAL);
				break;
			case ADVENTURE:
				p.setGameMode(GameMode.ADVENTURE);
				break;
			case CREATIVE:
				p.setGameMode(GameMode.CREATIVE);
				break;
			case SPECTATOR:
				p.setGameMode(GameMode.SPECTATOR);
				break;
		}
		if (world.getName().startsWith("Staff"))
		{
			UtilPlayer.clearInv(p);
			ItemStackFactory isf = new ItemStackFactory();
			ItemStack wand = isf.createItemStack(Items.WOODENAXE, CC.tnValue + "Wand of Justice");
			ItemStack compass = isf.createItemStack(Items.COMPASS, CC.tnValue + "Teleporty Thingy");
			UtilPlayer.addItem(p, compass);
			UtilPlayer.addItem(p, wand);
		}
		
	}
	
}
