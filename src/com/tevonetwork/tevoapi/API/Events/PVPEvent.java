package com.tevonetwork.tevoapi.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PVPEvent extends PlayerEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private Player attacker;
	private boolean cancelled = false;
	private String cancelmsg;

	public PVPEvent(Player who, Player attacker) {
		super(who);
		this.attacker = attacker;
	}

	public Player getAttacker() {
		return this.attacker;
	}

	public String getCancelMessage() {
		return this.cancelmsg;
	}

	public void setCancelMessage(String msg) {
		this.cancelmsg = msg;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
