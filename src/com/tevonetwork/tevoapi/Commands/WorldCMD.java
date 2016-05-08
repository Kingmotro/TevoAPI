package com.tevonetwork.tevoapi.Commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.WorldProperty;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;
import com.tevonetwork.tevoapi.Core.Travel.SendtoLocation;
import com.tevonetwork.tevoapi.Core.World.WorldManager;

public class WorldCMD implements CommandExecutor {

	TevoAPI main = TevoAPI.getInstance();
	WorldManager w = TevoAPI.getInstance().getWorldManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.isOp())
		{
			if (args.length > 0)
			{
				if (args[0].equalsIgnoreCase("create"))
				{
					if (args.length > 1)
					{
						if (w.newWorld(args[1]))
						{
							CategoryMSG.senderMessage(sender, Category.WORLD, CC.cYellow + "Successfully created a new world with name: " + CC.tnValue + args[1] + CC.cYellow + ".");
						}
						else
						{
							CategoryMSG.senderMessage(sender, Category.WORLD, CC.cRED + "Failed to create new world! The world probably already exists!");
						}
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.WORLD, CC.cRED + "Not enough Arguments!" + CC.cYellow + " Usage: " + CC.tnUse + "/world create <worldname>");
					}
				}
				else if (args[0].equalsIgnoreCase("remove"))
				{
					if (args.length > 1)
					{
						if (w.deleteWorld(args[1]))
						{
							CategoryMSG.senderMessage(sender, Category.WORLD, CC.cYellow + "Successfully removed world with name: " + CC.tnValue + args[1] + CC.cYellow + ".");
						}
						else
						{
							CategoryMSG.senderMessage(sender, Category.WORLD, CC.cRED + "Failed to remove world! The world probably doesn't exist!");
						}
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.WORLD, CC.cRED + "Not enough Arguments!" + CC.cYellow + " Usage: " + CC.tnUse + "/world remove <worldname>");
					}
				}
				else if (args[0].equalsIgnoreCase("unload"))
				{
					if (args.length > 1)
					{
						if (w.unloadWorld(args[1]))
						{
							CategoryMSG.senderMessage(sender, Category.WORLD, CC.cYellow + "Successfully unloaded world with name: " + CC.tnValue + args[1] + CC.cYellow + ".");
						}
						else
						{
							CategoryMSG.senderMessage(sender, Category.WORLD, CC.cRED + "Failed to unload world! The world probably doesn't exist!");
						}
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.WORLD, CC.cRED + "Not enough Arguments!" + CC.cYellow + " Usage: " + CC.tnUse + "/world unload <worldname>");
					}
				}
				else if (args[0].equalsIgnoreCase("import"))
				{
					if (args.length > 1)
					{
						if (w.importWorld(args[1]))
						{
							CategoryMSG.senderMessage(sender, Category.WORLD, CC.tnInfo + "Successfully imported world " + CC.tnValue + args[1] + CC.end);
						}
						else
						{
							CategoryMSG.senderMessage(sender, Category.WORLD, CC.tnError + "Failed to import world! The world folder does not exist or the world is already imported.");
						}
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.WORLD, CC.tnError + "Not enough Arguments!"+ CC.cYellow + " Usage: " + CC.tnUse + "/world import <worldname>");
					}
				}
				else if (args[0].equalsIgnoreCase("property"))
				{
					if (args.length == 2)
					{
						CategoryMSG.senderMessage(sender, Category.WORLD, CC.cRED + "Not enough Arguments!" + CC.cYellow + " Usage: " + CC.tnUse + "/world property <worldname> <property> <boolean>");
					}
					if (args.length == 3)
					{
						CategoryMSG.senderMessage(sender, Category.WORLD, CC.cRED + "Not enough Arguments!" + CC.cYellow + " Usage: " + CC.tnUse + "/world property <worldname> <property> <boolean>");
					}
					if (args.length >= 4)
					{
						World world = Bukkit.getServer().getWorld(args[1]);
						if (world != null)
						{
							if (setWorldPropertyFromString(world.getName(), args[2], Boolean.valueOf(args[3])))
							{
								CategoryMSG.senderMessage(sender, Category.WORLD, CC.cYellow + "Successfully set the world property " + CC.tnValue + args[2].toUpperCase() + CC.tnInfo + " of world " + CC.tnValue + args[1] + CC.cYellow + ".");
							}
							else
							{
								CategoryMSG.senderMessage(sender, Category.WORLD, CC.tnError + "Failed to set world property either an internal error or invalid property, valid properties:");
								List<String> properties = new ArrayList<String>();
								for (WorldProperty property : WorldProperty.values())
								{
									properties.add(property.toString().toUpperCase());
								}
								
								StringBuilder builder = new StringBuilder();
								
								for (Iterator<String> itr = properties.iterator(); itr.hasNext();)
								{
									builder.append(CC.cGreen + itr.next());
									if (itr.hasNext())
									{
										builder.append(CC.cYellow + ", ");
									}
								}
								
								CategoryMSG.senderHeader(sender, Category.WORLD, "World Properties");
								CategoryMSG.senderMessageNoCategory(sender, builder.toString());
								CategoryMSG.senderFooter(sender);
							}
						}
						else
						{
							CategoryMSG.senderMessage(sender, Category.WORLD, CC.tnError + "World does not exist!");
						}
					}
				}
				else if (args[0].equalsIgnoreCase("list"))
				{
					List<String> worldlist = w.getLoadedWorlds();
					CategoryMSG.senderHeader(sender, Category.WORLD, "World List");
					for (String worldnames : worldlist)
					{
						CategoryMSG.senderMessageNoCategory(sender, CC.tnValue + worldnames);
					}
					CategoryMSG.senderFooter(sender);
				}
				else if (args[0].equalsIgnoreCase("setspawn"))
				{
					if (sender instanceof Player)
					{
						Player p = (Player)sender;
						if (w.setWorldSpawn(p.getWorld().getName(), p.getLocation()))
						{
							UtilPlayer.message(Category.WORLD, p, CC.tnInfo + "Successfully set world spawn to your position.");
						}
						else
						{
							UtilPlayer.message(Category.WORLD, p, CC.tnError + "An error occured while trying to set the spawn, check the console for errors.");
						}
					}
					else
					{
						CategoryMSG.senderMessagePlayersOnly(sender, Category.WORLD);
					}
				}
				else if (args[0].equalsIgnoreCase("info"))
				{
					if (args.length >= 1)
					{
						if (sender instanceof Player)
						{
							Player p = (Player)sender;
							if (args.length == 1)
							{

								if (!w.sendAllUtilWorldsInfo(p.getWorld().getName(), sender))
								{
									UtilPlayer.message(Category.WORLD, p, CC.tnError + "An error occured while getting info about your current world perhaps it isnt't registered? Check console.");
								}
							}
							else
							{
								if (!w.sendAllUtilWorldsInfo(args[1], sender))
								{
									UtilPlayer.message(Category.WORLD, p, CC.tnError + "An error occured while getting info about your current world perhaps it isnt't registered? Check console.");
								}
							}
						}
						else
						{
							if (args.length >= 2)
							{
								if (!w.sendAllUtilWorldsInfo(args[1], sender))
								{
									CategoryMSG.senderMessage(sender, Category.WORLD, CC.tnError + "An error occured while getting info about your current world perhaps it isnt't registered? Check console.");
								}
							}
							else
							{
								CategoryMSG.senderMessage(sender, Category.WORLD, CC.tnError + "Not enough Arguments! " + CC.tnInfo + "Usage: " + CC.tnUse + "/world info <worldname>");
							}
						}
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.WORLD, CC.tnError + "Not enough Arguments! " + CC.tnInfo + "Usage: " + CC.tnUse + "/world info <worldname>");
					}
				}
				else if (args[0].equalsIgnoreCase("reload"))
				{
					if (w.reloadWorldProperties())
					{
						CategoryMSG.senderMessage(sender, Category.WORLD, CC.tnInfo + "Successfully reloaded world properties!");
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.WORLD, CC.tnError + "Failed to reload world properties!");
					}
				}
				else if (args[0].equalsIgnoreCase("tp"))
				{
					if (sender instanceof Player)
					{
						Player p = (Player)sender;
						if (args.length >= 2)
						{
							Location loc = w.getWorldSpawn(args[1]);
							if (loc != null)
							{
								UtilPlayer.message(Category.WORLD, p, CC.tnInfo + "Teleporting " + CC.tnPlayer + "You " + CC.tnInfo + "to " + CC.tnValue + args[1] + CC.end + "..");
								new SendtoLocation(p, loc);
							}
							
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.WORLD, "/world tp <worldname>");
						}
					}
					else
					{
						CategoryMSG.senderMessagePlayersOnly(sender, Category.WORLD);
					}
				}
				else
				{
					CategoryMSG.senderMessage(sender, Category.WORLD, CC.cRED + "Invalid Arguments!");
					showHelp(sender);
				}
			}
			else
			{
				showHelp(sender);
			}
		}
		else
		{
			PermMSG.noPerm(sender, Rank.OWNER);
		}
		return false;
	}
	
	private void showHelp(CommandSender sender)
	{
		CategoryMSG.senderHeader(sender, Category.WORLD, "World command usage");
		CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/world create <worldname>" + CC.tnInfo + " Create a new world with the specfied name. Default settings apply.");
		CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/world remove <worldname>" + CC.tnInfo + " Remove a world from the plugin. DOES NOT DELETE THE WORLD FOLDER!");
		CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/world unload <worldname>" + CC.tnInfo + " Unloads the specified world from the server. Will be loaded again on next restart.");
		CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/world import <worldname>" + CC.tnInfo + " Import a world. Default properties will apply.");
		CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/world property <worldname> <property> <boolean>" + CC.cYellow + " Set a world property.");
		CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/world list" + CC.cYellow + " Get a list of worlds.");
		CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/world setspawn <worldname>" + CC.cYellow + " Sets the spawn of the world to your current location.");
		CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/world info <worldname>" + CC.cYellow + " Get some useful information about a world.");
		CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/world reload" + CC.cYellow + " Reload world settings from config.");
		CategoryMSG.senderMessageNoCategory(sender, CC.tnUse + "/world tp <worldname>" + CC.cYellow + " Teleport to a world.");
		CategoryMSG.senderFooter(sender);
	}
	
	private boolean setWorldPropertyFromString(String worldname, String property, boolean enable)
	{
		if ((enable != true) && (enable != false))
		{
			return false;
		}
		boolean valid = false;
		switch(property.toLowerCase())
		{
		case("blockregen"):
			if (w.setWorldProperty(worldname, WorldProperty.BLOCKREGEN, enable))
			{
				valid = true;
			}
			break;
		case("build"):
			if (w.setWorldProperty(worldname, WorldProperty.BUILD, enable))
			{
				valid = true;
			}
			break;
		case("chunksinram"):
			if (w.setWorldProperty(worldname, WorldProperty.CHUNKSINRAM, enable))
			{
				valid = true;
			}
			break;
		case("contactdamage"):
			if (w.setWorldProperty(worldname, WorldProperty.CONTACTDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("creeperblockdamage"):
			if (w.setWorldProperty(worldname, WorldProperty.CREEPERBLOCKDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("croptrampling"):
			if (w.setWorldProperty(worldname, WorldProperty.CROPTRAMPLING, enable))
			{
				valid = true;
			}
			break;
		case("dragonblockdamage"):
			if (w.setWorldProperty(worldname, WorldProperty.DRAGONBLOCKDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("drowndamage"):
			if (w.setWorldProperty(worldname, WorldProperty.DROWNDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("endermengriefing"):
			if (w.setWorldProperty(worldname, WorldProperty.ENDERMENGRIEFING, enable))
			{
				valid = true;
			}
			break;
		case("explodedamage"):
			if (w.setWorldProperty(worldname, WorldProperty.EXPLODEDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("falldamage"):
			if (w.setWorldProperty(worldname, WorldProperty.FALLDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("fireballblockdamage"):
			if (w.setWorldProperty(worldname, WorldProperty.FIREBALLBLOCKDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("firedamage"):
			if (w.setWorldProperty(worldname, WorldProperty.FIREDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("hunger"):
			if (w.setWorldProperty(worldname, WorldProperty.HUNGER, enable))
			{
				valid = true;
			}
		case("icesnowmelt"):
			if (w.setWorldProperty(worldname, WorldProperty.ICESNOWMELT, enable))
			{
				valid = true;
			}
			break;
		case("lavadamage"):
			if (w.setWorldProperty(worldname, WorldProperty.LAVADAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("leafdecay"):
			if (w.setWorldProperty(worldname, WorldProperty.LEAFDECAY, enable))
			{
				valid = true;
			}
			break;
		case("lightningdamage"):
			if (w.setWorldProperty(worldname, WorldProperty.LIGHTNINGDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("minecraftdeathmsgs"):
			if (w.setWorldProperty(worldname, WorldProperty.MINECRAFTDEATHMSGS, enable))
			{
				valid = true;
			}
			break;
		case("mobdamage"):
			if (w.setWorldProperty(worldname, WorldProperty.MOBDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("naturalmobspawn"):
			if (w.setWorldProperty(worldname, WorldProperty.NATURALMOBSPAWN, enable))
			{
				valid = true;
			}
			break;
		case("physics"):
			if (w.setWorldProperty(worldname, WorldProperty.PHYSICS, enable))
			{
				valid = true;
			}
			break;
		case("pvp"):
			if (w.setWorldProperty(worldname, WorldProperty.PVP, enable))
			{
				valid = true;
			}
			break;
		case("ropeladders"):
			if (w.setWorldProperty(worldname, WorldProperty.ROPELADDERS, enable))
			{
				valid = true;
			}
			break;
		case("staffbuild"):
			if (w.setWorldProperty(worldname, WorldProperty.STAFFBUILD, enable))
			{
				valid = true;
			}
			break;
		case("suffocationdamage"):
			if (w.setWorldProperty(worldname, WorldProperty.SUFFOCATIONDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("tntblockdamage"):
			if (w.setWorldProperty(worldname, WorldProperty.TNTBLOCKDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("voiddamage"):
			if (w.setWorldProperty(worldname, WorldProperty.VOIDDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("waterdamage"):
			if (w.setWorldProperty(worldname, WorldProperty.WATERDAMAGE, enable))
			{
				valid = true;
			}
			break;
		case("weatherchange"):
			if (w.setWorldProperty(worldname, WorldProperty.WEATHERCHANGE, enable))
			{
				valid = true;
			}
			break;
		case("zombiedoordamage"):
			if (w.setWorldProperty(worldname, WorldProperty.ZOMBIEDOORDAMAGE, enable))
			{
				valid = true;
			}
			break;
		default:
			valid = false;
		}
		return valid;
			
	}

}
