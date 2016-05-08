package com.tevonetwork.tevoapi.Commands;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tevonetwork.tevoapi.API.Portals.PortalManager;
import com.tevonetwork.tevoapi.API.Regions.Region;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.ItemStackFactory;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Items;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;
import com.tevonetwork.tevoapi.Core.common.JavaCommon;

public class PortalCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.DEVELOPER))
			{
				if (args.length > 0)
				{
					if (args[0].equalsIgnoreCase("create"))
					{
						if (PortalManager.hasSelection(p))
						{
							if (!(args.length < 3))
							{
								if (!PortalManager.portalExist(args[1]))
								{
									if (args[2].equalsIgnoreCase("location"))
									{
										Location ploc = p.getLocation();
										String world = ploc.getWorld().getName();
										String x = Double.toString(ploc.getX());
										String y = Double.toString(ploc.getY());
										String z = Double.toString(ploc.getZ());
										String yaw = Double.toString(ploc.getYaw());
										String pitch = Double.toString(ploc.getPitch());
										String destination = "destination_location#" + world + "#" + x + "#" + y + "#" + z + "#" + yaw + "#" + pitch;
										Location rmin = PortalManager.getPlayerMINSelection(p);
										Location rmax = PortalManager.getPlayerMAXSelection(p);
										if (PortalManager.createNewPortal(args[1], destination, new Region(rmin, rmax)))
										{
											UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully created portal with name " + CC.tnValue + args[1].toUpperCase() + CC.end);
										}
										else
										{
											UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to create portal, its probably because the portal already exists.");	
										}
									}
									else if (args[2].equalsIgnoreCase("world"))
									{
										World world = Bukkit.getServer().getWorld(args[3]);
										if (world != null)
										{
											String worldname = args[3];
											String destination = "destination_world#" + worldname;
											Location rmin = PortalManager.getPlayerMINSelection(p);
											Location rmax = PortalManager.getPlayerMAXSelection(p);
											if (PortalManager.createNewPortal(args[1], destination, new Region(rmin, rmax)))
											{
												UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully created portal with name " + CC.tnValue + args[1].toUpperCase() + CC.end);
											}
											else
											{
												UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to create portal, its probably because the portal already exists.");
											}
										}
										else
										{
											UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "The world you specified as the destination is invalid.");
										}
									}
									else if (args[2].equalsIgnoreCase("server"))
									{
										String servername = args[3];
										String destination = "destination_server#" + servername;
										Location rmin = PortalManager.getPlayerMINSelection(p);
										Location rmax = PortalManager.getPlayerMAXSelection(p);
										if (PortalManager.createNewPortal(args[1], destination, new Region(rmin, rmax)))
										{
											UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully created portal with name " + CC.tnValue + args[1].toUpperCase() + CC.end);
										}
										else
										{
											UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to create portal, its probably because the portal already exists.");
										}
									}
									else
									{
										UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Invalid destination type. " + CC.tnInfo + "Valid destination types are: " + CC.tnValue + "location, world, server");
									}
								}
								else
								{
									UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Portal already exists, try a different name!");
								}
							}
							else
							{
								UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Not enough Arguments!" + CC.tnInfo + " Usage: " + CC.tnUse + "/portal create <portalname> <destination type> <destination>");
							}
						}
						else
						{
							UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "You must use the wand to make a selection first.");
						}
					}
					else if (args[0].equalsIgnoreCase("remove"))
					{
						if (args.length >= 1)
						{
							if (PortalManager.portalExist(args[1]))
							{
								if (PortalManager.removePortal(args[1]))
								{
									UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully removed portal.");
								}
								else
								{
									UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to remove portal. Check the console for errors.");
								}
							}
							else
							{
								UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Portal does not exist.");
							}
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.TRAVEL, "/portal remove <portalname>");
						}
					}
					else if (args[0].equalsIgnoreCase("dest"))
					{
						if (args.length >= 4)
						{
							if (PortalManager.portalExist(args[1]))
							{
								if (args[2].equalsIgnoreCase("location"))
								{
									Location ploc = p.getLocation();
									String world = ploc.getWorld().getName();
									String x = Double.toString(ploc.getX());
									String y = Double.toString(ploc.getY());
									String z = Double.toString(ploc.getZ());
									String yaw = Double.toString(ploc.getYaw());
									String pitch = Double.toString(ploc.getPitch());
									String destination = "destination_location#" + world + "#" + x + "#" + y + "#" + z + "#" + yaw + "#" + pitch;
									Location rmin = PortalManager.getPlayerMINSelection(p);
									Location rmax = PortalManager.getPlayerMAXSelection(p);
									if (PortalManager.createNewPortal(args[1], destination, new Region(rmin, rmax)))
									{
										UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully set destination on portal with name " + CC.tnValue + args[1].toUpperCase() + CC.end);
									}
									else
									{
										UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to set destination, check the console for any errors!");	
									}
								}
								else if (args[2].equalsIgnoreCase("world"))
								{
									World world = Bukkit.getServer().getWorld(args[3]);
									if (world != null)
									{
										String worldname = world.getName();
										String destination = "destination_world#" + worldname;
										if (PortalManager.setPortalDestination(args[1], destination))
										{
											UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully set destination on portal with name " + CC.tnValue + args[1].toUpperCase() + CC.end);
										}
										else
										{
											UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to set destination, check the console for any errors!");
										}
									}
									else
									{
										UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "The world you specified as the destination is invalid.");
									}
								}
								else if (args[2].equalsIgnoreCase("server"))
								{
									String servername = args[3];
									String destination = "destination_server#" + servername;
									if (PortalManager.setPortalDestination(args[1], destination))
									{
										UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully set destination on portal with name " + CC.tnValue + args[1].toUpperCase() + CC.end);
									}
									else
									{
										UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to set destination, check the console for any errors!");
									}
								}
								else
								{
									UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Invalid destination type! " + CC.tnInfo + "Valid destination types are: " + CC.tnValue + "location, world, server");
								}
							}
							else
							{
								UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Portal does not exist!");
							}
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.TRAVEL, "/portal dest <portalname> <destinationtype> <destination>");
						}
					}
					else if (args[0].equalsIgnoreCase("wand"))
					{
						ItemStackFactory isf = new ItemStackFactory();
						ItemStack wand = isf.createItemStack(Items.BLAZEROD, CC.tnValue + "Wand of Portalyness");
						UtilPlayer.addItem(p, wand);
					}
					else if (args[0].equalsIgnoreCase("info"))
					{
						if (args.length >= 2)
						{
							if (PortalManager.portalExist(args[1]))
							{
								if (!PortalManager.sendPortalInfo(sender, args[1]))
								{
									UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to get info, check console.");
								}
							}
							else
							{
								UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Portal does not exist!");
							}
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.TRAVEL, "/portal info <portalname>");
						}
					}
					else if (args[0].equalsIgnoreCase("enabled"))
					{
						if (args.length >= 3)
						{
							if (PortalManager.portalExist(args[1]))
							{
								if (PortalManager.setPortalEnabled(args[1], Boolean.valueOf(args[2])))
								{
									UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Successfully set the portal to " + JavaCommon.booleantoEnabledDisabled(Boolean.valueOf(args[2])));	
								}
								else
								{
									UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Failed to set portal state, check the console for any errors.");
								}
							}
							else
							{
								UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Portal does not exist!");
							}
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.TRAVEL, "/portal enabled <portalname> <true/false>");
						}
					}
					else if (args[0].equalsIgnoreCase("list"))
					{
						Set<String> portals = PortalManager.getPortalList();
						CategoryMSG.senderHeader(sender, Category.TRAVEL, "Portal List");
						for (String portal : portals)
						{
							CategoryMSG.senderMessageNoCategory(sender, CC.tnValue + portal);
						}
						CategoryMSG.senderFooter(sender);
					}
					else
					{
						UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Invalid arguments!");
						showHelp(p);
					}
				}
				else
				{
					UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Not enough arguments!");
					showHelp(p);
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.DEVELOPER);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.TRAVEL);
		}
		return false;
	}
	
	private void showHelp(Player p)
	{
		UtilPlayer.messageHeader(Category.TRAVEL, p, "Portal command usage");
		UtilPlayer.messageNoCategory(p, CC.tnUse + "/portal create <portalname> <destinationtype> <destination>" + CC.tnInfo + " Create a portal.");
		UtilPlayer.messageNoCategory(p, CC.tnUse + "/portal remove <portalname>" + CC.tnInfo + " Removes a portal.");
		UtilPlayer.messageNoCategory(p, CC.tnUse + "/portal dest <portalname> <destinationtype> <destination>" + CC.tnInfo + " Set the destination of an existing portal.");
		UtilPlayer.messageNoCategory(p, CC.tnUse + "/portal wand" + CC.tnInfo + " Get the portal wand.");
		UtilPlayer.messageNoCategory(p, CC.tnUse + "/portal info <portalname>" + CC.tnInfo + " Shows some useful information about a portal.");
		UtilPlayer.messageNoCategory(p, CC.tnUse + "/portal enabled <portalname> <true/false>" + CC.tnInfo + " Enabled or disable a portal.");
		UtilPlayer.messageNoCategory(p, CC.tnUse + "/portal list" + CC.tnInfo + " List portals.");
		UtilPlayer.messageFooter(p);
	}

}
