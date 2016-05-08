package com.tevonetwork.tevoapi.API.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Cooldown.Cooldown;
import com.tevonetwork.tevoapi.API.Titles.ActionBar;
import com.tevonetwork.tevoapi.API.Titles.Title;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.ChatChannel;
import com.tevonetwork.tevoapi.Core.Gamemodes;
import com.tevonetwork.tevoapi.Core.Items;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

public class UtilPlayer {

	private static HashMap<String, ChatChannel> chatchannel = new HashMap<String, ChatChannel>();
	private static HashMap<String, String> msgreplycache = new HashMap<String, String>(); 
	private static HashMap<String, Location> backloc = new HashMap<String, Location>();
	private static HashMap<String, List<String>> damagers = new HashMap<String, List<String>>();
	private static HashMap<String, String> damagecause = new HashMap<String, String>();
	private static HashMap<String, BukkitTask> combatlog = new HashMap<String, BukkitTask>();
	private static TevoAPI main = TevoAPI.getInstance();
	
	//Cache Manage
	public static void clearCaches(Player p)
	{
		chatchannel.remove(p.getName());
		msgreplycache.remove(p.getName());
		backloc.remove(p.getName());
		damagers.remove(p.getName());
		damagecause.remove(p.getName());
	}
	
	//Utils
	public static List<Player> getinRadius(Player p, int distance)
	{
		List<Player> players = new ArrayList<Player>();
		for (Player player : UtilServer.getPlayers())
		{
			if (player.getWorld() == p.getWorld())
			{
				if (player != p)
				{
					if (player.getLocation().distance(p.getLocation()) <= distance)
					{
						players.add(player);
					}
				}
			}
		}
		return players;
	}
	
	//Messaging
	public static void message(Category category, Player p, String msg)
	{
		p.sendMessage(CategoryMSG.getMSGPrefix(category) + msg);
	}
	
	public static void transaction(Player p, int amount, String reason, String[] multiplier)
	{
		StringBuilder builder = new StringBuilder();
		if (amount >= 0)
		{
			builder.append(CC.tnValue + "+" + amount + " Tevo Tokens ");
			if (reason != null)
			{
				builder.append(CC.tnInfo + "for " + reason + CC.end);
			}
			if (multiplier != null)
			{
				builder.append(CC.tnInfo + " Multipliers(");
				for (int index = 0; index < multiplier.length; index ++)
				{
					if (index == 0)
					{
						builder.append(CC.tnValue + multiplier[index]);
					}
					else
					{
						builder.append(CC.tnInfo + ", " + CC.tnValue + multiplier[index]);
					}
				}
				builder.append(CC.tnInfo + ")");
			}
		}
		else
		{
			builder.append(CC.tnError + amount + CC.tnValue + " Tevo Tokens ");
			if (reason != null)
			{
				builder.append(CC.tnInfo + "for " + reason + CC.end);
			}
		}
		message(Category.TOKENS, p, builder.toString());
	}
	
	public static void onc(Player p, String ability)
	{
		message(Category.COOLDOWN, p, CC.tnInfo + "You cannot use " + CC.tnValue + ability + CC.tnInfo + " for " + CC.tnValue + String.valueOf(Cooldown.getPlayerRemainingTime(p, ability)) + " seconds" + CC.end);
		sound(p, playerSounds.ONCOOLDOWN);
	}
	
	public static void messageNoCategory(Player p, String msg)
	{
		p.sendMessage(msg);
	}
	
	public static void messageHeader(Category category, Player p, String header)
	{
		p.sendMessage(CategoryMSG.getMSGPrefix(category) + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + header + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + ":");
	}
	
	public static void messageFooter(Player p)
	{
		p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "•••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••");
	}
	
	public static ChatChannel getChatChannel(Player p)
	{
		if (chatchannel.containsKey(p.getName()))
		{
			return chatchannel.get(p.getName());
		}
		else
		{
			return ChatChannel.DEFAULT;
		}
	}
	
	public static boolean hasChatChannel(Player p, ChatChannel channel)
	{
		if (channel.equals(ChatChannel.DEFAULT))
		{
			if (!chatchannel.containsKey(p.getName()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if ((chatchannel.containsKey(p.getName())) && (chatchannel.get(p.getName()).equals(channel)))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
	}
	
	public static void setChatChannel(Player p, ChatChannel channel)
	{
		if (!channel.equals(ChatChannel.DEFAULT))
		{
			chatchannel.put(p.getName(), channel);
		}
		else
		{
			chatchannel.remove(p.getName());
		}
	}
	
	public static void setChatChannelDefault(Player p)
	{
		chatchannel.remove(p.getName());
	}
	
	public static String getReplyCache(Player p)
	{
		return msgreplycache.get(p.getName());
	}
	
	public static void setReplyCache(Player p, String replyplayer)
	{
		msgreplycache.put(p.getName(), replyplayer);
	}
	
	public static boolean hasReplyCache(Player p)
	{
		return msgreplycache.containsKey(p.getName());
	}
	
	//Player changes
	public static void addItem(Player p, ItemStack is)
	{
		if (is != null)
		{
			p.getInventory().addItem(is);
			p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 2F, 1F);
		}
	}
	
	public static void setItem(Player p, ItemStack is, int index)
	{
		if (is != null)
		{
			p.getInventory().setItem(index, is);
			p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 2F, 1F);
		}
		else
		{
			p.getInventory().setItem(index, Items.getItemStack(Items.NOTHING));
		}
	}
	
	public static void clearInv(Player p)
	{
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
	}
	
	public static void changeGamemode(Player p, GameMode gamemode)
	{
		p.setGameMode(gamemode);
		message(Category.PLAYER, p, CC.tnPlayer + "You " + CC.tnInfo + "changed the gamemode of " + CC.tnPlayer + "You" + CC.tnInfo + " to " + CC.tnValue + gamemode.toString().toUpperCase() + CC.end);
	}
	
	public static void changeGamemode(Player p, Player actor, GameMode gamemode)
	{
		p.setGameMode(gamemode);
		message(Category.PLAYER, actor, CC.tnPlayer + "You " + CC.tnInfo + "changed the gamemode of " + CC.tnPlayer + p.getDisplayName() + CC.tnInfo + " to " + CC.tnValue + gamemode.toString().toUpperCase() + CC.end);
	}
	
	public static Gamemodes getPlayerGamemode(Player p)
	{
		if (p.getWorld().getName().startsWith("hub"))
		{
			return Gamemodes.HUB;
		}
		if (p.getWorld().getName().startsWith("borderline"))
		{
			return Gamemodes.BORDERLINE;
		}
		if (p.getWorld().getName().startsWith("kitpve"))
		{
			return Gamemodes.KITPVE;
		}
		return null;
		
	}

	//Damagers
	public static void addDamager(final Player p, final String damager)
	{
		if (damagers.containsKey(p.getName()))
		{
			Iterator<String> itr = damagers.get(p.getName()).iterator();
			while (itr.hasNext())
			{
				String predamager = itr.next();
				if (predamager.equalsIgnoreCase(damager))
				{
					itr.remove();
				}	
			}
			damagers.get(p.getName()).add(damager);
		}
		else
		{
			List<String> damagerlist = new ArrayList<String>();
			damagerlist.add(damager);
			damagers.put(p.getName(), damagerlist);
		}
		new BukkitRunnable() {
			
			@Override
			public void run()
			{
				if (damagers.containsKey(p.getName()))
				{
					damagers.get(p.getName()).remove(damager);
				}
			}
		}.runTaskLater(main, 120L);
	}
	
	public static List<String> getDamagers(Player p)
	{
		List<String> damagerlist = new ArrayList<String>();
		if (damagers.containsKey(p.getName()))
		{
			for (String damager : damagers.get(p.getName()))
			{
				damagerlist.add(damager);
			}
		}
		return damagerlist;
	}
	
	public static void setLastDamageCause(Player p, String cause)
	{
		damagecause.put(p.getName(), cause);
	}
	
	public static String getLastDamageCause(Player p)
	{
		return damagecause.get(p.getName());
	}
	
	public static boolean isinCombat(Player p)
	{
		return combatlog.containsKey(p.getName());
	}
	
	public static void setinCombat(final Player p, String entermsg)
	{
		if (combatlog.containsKey(p.getName()))
		{
			combatlog.get(p.getName()).cancel();
			combatlog.put(p.getName(), new BukkitRunnable() {
				@Override
				public void run()
				{
					combatlog.remove(p.getName());
					UtilPlayer.message(Category.PVP, p, CC.tnInfo + "You are now out of combat mode and you may safely logout.");
					Title msg = new Title("", CC.cGreen + CC.fBold + "Out of combat mode, you can now logout safely!", 0, 40, 0);
					msg.send(p);
					UtilPlayer.sound(p, playerSounds.UNEQUIP);
				}
			}.runTaskLater(main, 100L));
		}
		else
		{
			combatlog.put(p.getName(), new BukkitRunnable() {
				@Override
				public void run()
				{
					combatlog.remove(p.getName());
					UtilPlayer.message(Category.PVP, p, CC.tnInfo + "You are now out of combat mode and you may safely logout.");
					Title msg = new Title("", CC.cGreen + CC.fBold + "Out of combat mode, you can now logout safely!", 0, 40, 0);
					msg.send(p);
					UtilPlayer.sound(p, playerSounds.UNEQUIP);
				}
			}.runTaskLater(main, 100L));
			message(Category.PVP, p, CC.cRED + CC.fBold + entermsg);
			ActionBar msg = new ActionBar(CC.cRED + CC.fBold + "You entered combat mode!");
			msg.send(p);
			sound(p, playerSounds.EQUIP);
		}
	}
	
	public static void setoutCombat(Player p, boolean silent)
	{
		if (combatlog.containsKey(p.getName()))
		{
			combatlog.get(p.getName()).cancel();
			combatlog.remove(p.getName());
			if (!silent)
			{
				message(Category.PVP, p, CC.tnInfo + "You are now out of combat mode and you may safely logout.");
				Title msg = new Title(null, CC.cGreen + CC.fBold + "Out of combat, you can now logout safely!", 0, 40, 0);
				msg.send(p);
				sound(p, playerSounds.UNEQUIP);
			}
		}
	}
	
	//Teleportation
	public static void setBackLoc(Player p, Location loc)
	{
		backloc.put(p.getName(), loc);
	}
	
	public static Location getBackLoc(Player p)
	{
		if (backloc.containsKey(p.getName()))
		{
			return backloc.get(p.getName());
		}
		return null;
	}
	
	public static void clearBackLoc(Player p)
	{
		if (backloc.containsKey(p.getName()))
		{
			backloc.remove(p.getName());
		}
	}
	
	public static boolean hasBackLoc(Player p)
	{
		return backloc.containsKey(p.getName());
	}
	
	//Sounds
	public enum playerSounds
	{
		EQUIP,
		UNEQUIP,
		TRANSACTIONSUCCESS,
		TRANSACTIONFAILED,
		TELEPORT,
		TPAACCEPT,
		TPADENY,
		TPAREQUEST,
		KILL,
		PRIVATEMSG,
		COUNTDOWN,
		GAMESTART,
		GUI_PAGECHANGE,
		GUI_NULL,
		ONCOOLDOWN;
	}
	
	public static void sound(final Player p, playerSounds play)
	{
		if (play.equals(playerSounds.EQUIP))
		{
			p.playSound(p.getLocation(), Sound.NOTE_PLING, 5F, 2F);
		}
		else if (play.equals(playerSounds.KILL))
		{
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2F, 1F);
			new BukkitRunnable() {
				@Override
				public void run()
				{
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2F, 1.6F);
				}
			}.runTaskLater(main, 2L);
		}
		else if (play.equals(playerSounds.ONCOOLDOWN))
		{
			p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 2F, 0.5F);
		}
		else if (play.equals(playerSounds.PRIVATEMSG))
		{
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2F, 2F);
		}
		else if (play.equals(playerSounds.TELEPORT))
		{
			p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3F, 1F);
		}
		else if (play.equals(playerSounds.TPAACCEPT))
		{
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 3F, 1F);
		}
		else if (play.equals(playerSounds.TPADENY))
		{
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 2F, 1F);
		}
		else if (play.equals(playerSounds.TPAREQUEST))
		{
			p.playSound(p.getLocation(), Sound.VILLAGER_HAGGLE, 2F, 1F);
		}
		else if (play.equals(playerSounds.TRANSACTIONFAILED))
		{
			p.playSound(p.getLocation(), Sound.ITEM_BREAK, 2F, 0.5F);
		}
		else if (play.equals(playerSounds.TRANSACTIONSUCCESS))
		{
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 2F, 1.8F);
		}
		else if (play.equals(playerSounds.UNEQUIP))
		{
			p.playSound(p.getLocation(), Sound.NOTE_PLING, 2F, 1F);
		}
		else if (play.equals(playerSounds.GUI_PAGECHANGE))
		{
			p.playSound(p.getLocation(), Sound.CLICK, 1F, 2F);
		}
		else if (play.equals(playerSounds.GUI_NULL))
		{
			p.playSound(p.getLocation(), Sound.WOOD_CLICK, 2F, 0.6F);
		}
		else if (play.equals(playerSounds.COUNTDOWN))
		{
			p.playSound(p.getLocation(), Sound.NOTE_PLING, 2F, 1.2F);
		}
	}
	
	//Ranks
	public static boolean isStaff(Player p)
	{
		return (hasRank(p, Rank.OWNER)) || (hasRank(p, Rank.DEVELOPER)) || (hasRank(p, Rank.ADMIN)) || (hasRank(p, Rank.BUILDER)) || (hasRank(p, Rank.MODERATOR));
	}
	
	public static Rank getRank(Player p)
	{
		if (p.hasPermission("tevo.owner"))
		{
			return Rank.OWNER;
		}
		else if (p.hasPermission("tevo.dev"))
		{
			return Rank.DEVELOPER;
		}
		else if (p.hasPermission("tevo.admin"))
		{
			return Rank.ADMIN;
		}
		else if (p.hasPermission("tevo.builder"))
		{
			return Rank.BUILDER;
		}
		else if (p.hasPermission("tevo.mod"))
		{
			return Rank.MODERATOR;
		}
		else if (p.hasPermission("tevo.crystal"))
		{
			return Rank.CRYSTAL;
		}
		else if (p.hasPermission("tevo.loyalist"))
		{
			return Rank.LOYALIST;
		}
		else if (p.hasPermission("tevo.mystic"))
		{
			return Rank.MYSTIC;
		}
		else 
		{
			return Rank.DEFAULT;
		}
	}
	
	public static boolean hasRank(Player p, Rank rank)
	{
		boolean hasrank = false;
		switch(rank)
		{
			case OWNER:
				if ((p.hasPermission("tevo.owner")) || (p.isOp()))
				{
					hasrank = true;
				}
				break;
			case DEVELOPER:
				if (p.hasPermission("tevo.dev"))
				{
					hasrank = true;
				}
				break;
			case ADMIN:
				if (p.hasPermission("tevo.admin"))
				{
					hasrank = true;
				}
				break;
			case BUILDER:
				if (p.hasPermission("tevo.builder"))
				{
					hasrank = true;
				}
				break;
			case MODERATOR:
				if (p.hasPermission("tevo.mod"))
				{
					hasrank = true;
				}
				break;
			case CRYSTAL:
				if (p.hasPermission("tevo.crystal"))
				{
					hasrank = true;
				}
				break;
			case LOYALIST:
				if (p.hasPermission("tevo.loyalist"))
				{
					hasrank = true;
				}
				break;
			case MYSTIC:
				if (p.hasPermission("tevo.mystic"))
				{
					hasrank = true;
				}
				break;
			case DEFAULT:
				if (p.hasPermission("tevo.default"))
				{
					hasrank = true;
				}
				break;
		}
		return hasrank;
		
	}
	
	public static int multiplier(Player p)
	{
		if (getRank(p) == Rank.MYSTIC)
		{
			return 2;
		}
		else if (getRank(p) == Rank.LOYALIST)
		{
			return 3;
		}
		else if (hasRank(p, Rank.CRYSTAL))
		{
			return 4;
		}
		else
		{
			return 0;
		}
	}
	
	//Class methods
	public static void clearAllData()
	{
		damagers.clear();
		damagecause.clear();
		chatchannel.clear();
		msgreplycache.clear();
		backloc.clear();
	}
	
}
