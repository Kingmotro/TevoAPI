package com.tevonetwork.tevoapi.API.Events;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KillEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player killer;
	private Player victim;
	private ArrayList<Player> assists = new ArrayList<Player>();
	private String cause;
	private boolean instantrespawn = true;
	private boolean blood = true;

	public KillEvent(Player killer, Player victim, String cause) {
		this.killer = killer;
		this.victim = victim;
		this.cause = cause;
	}

	public KillEvent(Player killer, Player victim, ArrayList<Player> assists, String cause) {
		this.killer = killer;
		this.victim = victim;
		this.assists = assists;
		this.cause = cause;
	}

	public Player getKiller() {
		return this.killer;
	}

	public Player getVictim() {
		return this.victim;
	}

	public ArrayList<Player> getAssists() {
		return this.assists;
	}

	public String getCause() {
		return this.cause;
	}

	public boolean isInstantRespawn() {
		return this.instantrespawn;
	}

	public void setInstantRespawn(boolean instantrespawn) {
		this.instantrespawn = instantrespawn;
	}

	public boolean isBloodEnabled() {
		return this.blood;
	}

	public void setBloodEnabled(boolean blood) {
		this.blood = blood;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
