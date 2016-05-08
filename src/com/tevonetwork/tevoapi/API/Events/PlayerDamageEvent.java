package com.tevonetwork.tevoapi.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerEvent;

public class PlayerDamageEvent extends PlayerEvent implements Cancellable{

	private static final HandlerList handlers = new HandlerList();
	private DamageCause cause;
	private double damage;
	private boolean cancelled = false;
	
	public PlayerDamageEvent(Player who, DamageCause cause, double damage)
	{
		super(who);
		this.cause = cause;
		this.damage = damage;
	}
	
	public DamageCause getCause()
	{
		return this.cause;
	}
	
	public double getDamage()
	{
		return this.damage;
	}
	
	public void setDamage(double damage)
	{
		this.damage = damage;
	}

	@Override
	public boolean isCancelled()
	{
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
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
