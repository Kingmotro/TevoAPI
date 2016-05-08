package com.tevonetwork.tevoapi.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Permissions.OPSync;
import com.tevonetwork.tevoapi.API.Scoreboards.ScoreboardManager;
import com.tevonetwork.tevoapi.API.Stats.StatCallback;
import com.tevonetwork.tevoapi.API.Stats.StatManager;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.ItemStackFactory;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Gamemodes;
import com.tevonetwork.tevoapi.Core.Items;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Networking.ServerPlayerCounter;
import com.tevonetwork.tevoapi.Economy.EconomyManager;

public class JoinListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent e)
	{
		final Player p = e.getPlayer();
		StatCallback statcall = new StatCallback()
		{
			@Override
			public void execute()
			{
				if (p.getWorld().getName().equalsIgnoreCase("kitpve"))
				{
					ScoreboardManager.setupScoreboard(p, Gamemodes.KITPVE);
				}
				else
				{
					ScoreboardManager.setupScoreboard(p, Gamemodes.HUB);
				}
				ScoreboardManager.addPlayerRank(p);
				EconomyManager.addtoCache(p);
			}
		};
		StatManager.addToCache(p, statcall);
		
		Rank rank = UtilPlayer.getRank(p);
		switch(rank)
		{
			case OWNER:
				p.setDisplayName(CC.tnOwner + "Owner " + CC.cWhite + p.getName());
				break;
			case DEVELOPER:
				p.setDisplayName(CC.tnOwner + "Owner " + CC.cWhite + p.getName());
				break;
			case ADMIN:
				p.setDisplayName(CC.tnAdmin + "Admin " + CC.cWhite + p.getName());
				break;
			case BUILDER:
				p.setDisplayName(CC.tnBuilder + "Builder " + CC.cWhite + p.getName());
				break;
			case MODERATOR:
				p.setDisplayName(CC.tnMod + "Moderator " + CC.cWhite + p.getName());
				break;
			case CRYSTAL:
				p.setDisplayName(CC.tnCrystal + "Crystal " + CC.cWhite + p.getName());
				break;
			case LOYALIST:
				p.setDisplayName(CC.tnLoyalist + "Loyalist " + CC.cWhite + p.getName());
				break;
			case MYSTIC:
				p.setDisplayName(CC.tnMystic + "Mystic " + CC.cWhite + p.getName());
				break;
			case DEFAULT:
				p.setDisplayName(CC.cGray + p.getName());
				break;
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable()
		{
			public void run()
			{
				ServerPlayerCounter.requestServerList();
			}
		}, 5L);
		OPSync.checkOP(p);
		if (p.getWorld().getName().startsWith("Staff"))
		{
			UtilPlayer.clearInv(p);
			p.updateInventory();
			ItemStackFactory isf = new ItemStackFactory();
			ItemStack wand = isf.createItemStack(Items.WOODENAXE, CC.tnValue + "Wand of Justice");
			ItemStack compass = isf.createItemStack(Items.COMPASS, CC.tnValue + "Teleporty Thingy");
			UtilPlayer.addItem(p, compass);
			UtilPlayer.addItem(p, wand);
		}
	}
	
}
