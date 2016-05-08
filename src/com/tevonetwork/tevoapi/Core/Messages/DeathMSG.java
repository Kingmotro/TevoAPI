package com.tevonetwork.tevoapi.Core.Messages;

import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;

public class DeathMSG {

	private static TevoAPI main = TevoAPI.getInstance();
	private static String death_Prefix = CategoryMSG.getMSGPrefix(Category.DEATH);
	
	public static void withCause(Player victim, String cause)
	{
		if (!main.getConfigManager().getConfig().getBoolean("handledeathmsgs"))
		{
			return;
		}
		for (Player p : victim.getWorld().getPlayers())
		{
			if (p != victim)
			{
				p.sendMessage(death_Prefix + CC.tnPlayer + victim.getName() + CC.tnInfo + " was killed by " + CC.tnAbility + cause + CC.end);
			}
		}
		victim.sendMessage(death_Prefix + CC.tnInfo + "You were killed by " + CC.tnAbility + cause + CC.end);
	}
	
	public static void withKiller(Player victim, Player killer)
	{
		if (!main.getConfigManager().getConfig().getBoolean("handledeathmsgs"))
		{
			return;
		}
		for (Player p : victim.getWorld().getPlayers())
		{
			if ((p != victim) && (p != killer))
			{
				p.sendMessage(death_Prefix + CC.tnPlayer + victim.getName() + CC.tnInfo + " was killed by " + CC.tnPlayer + killer.getName() + CC.end);
			}
		}
		victim.sendMessage(death_Prefix + CC.tnInfo + "You were killed by " + CC.tnPlayer + killer.getName() + CC.end);
		killer.sendMessage(death_Prefix + CC.cYellow + "You killed " + CC.cGray + victim.getName() + CC.end);
	}
	
	public static void withKillerandAbility(Player victim, Player killer, String ability)
	{
		if (!main.getConfigManager().getConfig().getBoolean("handledeathmsgs"))
		{
			return;
		}
		for (Player p : victim.getWorld().getPlayers())
		{
			if ((p != victim) && (p != killer))
			{
				p.sendMessage(death_Prefix + CC.tnPlayer + victim.getName() + CC.tnInfo + " was killed by " + CC.tnPlayer + killer.getName() + CC.tnInfo + " using " + CC.tnAbility + ability + CC.end);
			}
		}
		victim.sendMessage(death_Prefix + CC.tnInfo + "You were killed by " + CC.tnPlayer + killer.getName() + CC.tnInfo + " using " + CC.tnAbility + ability + CC.end);
		killer.sendMessage(death_Prefix + CC.tnInfo + "You killed " + CC.tnPlayer + victim.getName() + CC.tnInfo + " using " + CC.tnAbility + ability + CC.end);
	}
}
