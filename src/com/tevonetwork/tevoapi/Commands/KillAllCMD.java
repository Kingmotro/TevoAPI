package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Boat;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Flying;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.WaterMob;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

public class KillAllCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.DEVELOPER))
			{
				if (args.length > 0)
				{
					if (!handleRemove(p, args[0]))
					{
						UtilPlayer.message(Category.UTILS, p, CC.tnError + "Invalid entity type!");
					}
				}
				else
				{
					CategoryMSG.senderArgsErr(sender, Category.UTILS, "/killall <drops/mobs/arrows/boats/minecarts/xp>");
				}
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.UTILS);
		}
		return false;
	}
	
	private boolean handleRemove(Player p, String type)
	{
		int removed = 0;
		boolean valid = false;
		for (Chunk chunk : p.getWorld().getLoadedChunks())
		{
			for (Entity e : chunk.getEntities())
			{
				if (!(e instanceof HumanEntity))
				{
					switch(type.toLowerCase())
					{
						case("drops"):
							if (e instanceof Item)
							{
								e.remove();
								removed++;
							}
							valid = true;
							break;
						case("arrows"):
							if (e instanceof Projectile)
							{
								e.remove();
								removed++;
							}
							valid = true;
							break;
						case("mobs"):
							if (((e instanceof Animals)) || ((e instanceof NPC)) || ((e instanceof Snowman)) || ((e instanceof WaterMob)) || ((e instanceof Monster)) || ((e instanceof ComplexLivingEntity)) || ((e instanceof Flying)) || ((e instanceof Slime)) || ((e instanceof Ambient)))
			                {
								if ((!e.hasMetadata("Tutorial")) && (!e.hasMetadata("NPC")))
								{
									e.remove();
					                removed++;
								}    
				            }
							valid = true;
							break;
						case("boats"):
							if (e instanceof Boat)
							{
								e.remove();
								removed++;
							}
							valid = true;
							break;
						case("minecarts"):
							if (e instanceof Minecart)
							{
								e.remove();
								removed++;
							}
							valid = true;
							break;
						case("xp"):
							if (e instanceof ExperienceOrb)
							{
								e.remove();
								removed++;
							}
							valid = true;
							break;
					}
				}
			}
		}
		if (valid)
		{
			UtilPlayer.message(Category.UTILS, p, CC.tnInfo + "Removed " + CC.tnValue + removed + CC.tnInfo + " entities!");
		}
		return valid;
	}

}
