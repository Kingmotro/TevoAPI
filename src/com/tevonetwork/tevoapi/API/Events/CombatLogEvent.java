package com.tevonetwork.tevoapi.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CombatLogEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player combatlogger;

	public CombatLogEvent(Player player)
	{
		this.combatlogger = player;
	}
	
	public Player getPlayer()
	{
		return this.combatlogger;
	}
	
	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}
}
