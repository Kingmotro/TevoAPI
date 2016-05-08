package com.tevonetwork.tevoapi.GameAPI.core;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public abstract class Game {

	private ArrayList<Player> game_Players = new ArrayList<Player>();

	public int getPlayerCount() {
		return game_Players.size();
	}

}
