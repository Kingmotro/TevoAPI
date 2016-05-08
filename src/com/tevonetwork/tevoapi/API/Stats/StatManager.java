package com.tevonetwork.tevoapi.API.Stats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.MySQL.SQLCallback;
import com.tevonetwork.tevoapi.API.MySQL.SQLManager;
import com.tevonetwork.tevoapi.API.MySQL.SQLRunnable;
import com.tevonetwork.tevoapi.API.Util.UUIDFetcher;
import com.tevonetwork.tevoapi.Core.Gamemodes;
import com.tevonetwork.tevoapi.Core.LogLevel;

public class StatManager {

	private static TevoAPI main = TevoAPI.getInstance();
	private static SQLManager sql = main.getSQLManager();
	private static String table_Borderline = "Borderline";
	private static String table_KitPVE = "KitPVE";
	private static String table_Logins = "Logins";
	private static String table_Votes = "Votes";
	private static String column_VoteCount = "Count";
	private static String column_Logins = "Logins";
	private static String column_Kills = "Kills";
	private static String column_Deaths = "Deaths";
	private static String column_Games = "Games";
	private static String column_Wins = "Wins";

	// Cache Stuff - If the data is already here allows stats to be saved
	// rapidly.
	private static Table<UUID, Gamemodes, Integer> kills = HashBasedTable.create();
	private static Table<UUID, Gamemodes, Integer> deaths = HashBasedTable.create();
	private static Table<UUID, Gamemodes, Integer> games = HashBasedTable.create();
	private static Table<UUID, Gamemodes, Integer> wins = HashBasedTable.create();
	private static HashMap<UUID, Integer> votes = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> logins = new HashMap<UUID, Integer>();

	public static int getLogins(Player p) {
		int login = 0;
		if (logins.containsKey(p.getUniqueId())) {
			login = logins.get(p.getUniqueId());
		}
		else {
			login = selectValue(p.getUniqueId(), table_Logins, column_Logins);
			logins.put(p.getUniqueId(), login);
		}
		return login;
	}

	public static int getVotes(Player p) {
		int vote = 0;
		if (votes.containsKey(p.getUniqueId())) {
			vote = votes.get(p.getUniqueId());
		}
		else {
			vote = selectValue(p.getUniqueId(), table_Votes, column_VoteCount);
			votes.put(p.getUniqueId(), vote);
		}
		return vote;
	}

	public static boolean addKills(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		if (amount <= 0) {
			return false;
		}
		switch (gamemode) {
			case BORDERLINE:
				if (kills.contains(p.getUniqueId(), gamemode)) {
					int currentkills = kills.get(p.getUniqueId(), gamemode);
					kills.put(p.getUniqueId(), gamemode, currentkills + amount);
					updateValue(p, table_Borderline, column_Kills, String.valueOf(currentkills + amount));
					success = true;
				}
				else {
					int currentkills = selectValue(p.getUniqueId(), table_Borderline, column_Kills);
					kills.put(p.getUniqueId(), gamemode, currentkills + amount);
					updateValue(p, table_Borderline, column_Kills, String.valueOf(currentkills + amount));
					success = true;
				}
				break;
			case KITPVE:
				if (kills.contains(p.getUniqueId(), gamemode)) {
					int currentkills = kills.get(p.getUniqueId(), gamemode);
					kills.put(p.getUniqueId(), gamemode, currentkills + amount);
					updateValue(p, table_KitPVE, column_Kills, String.valueOf(currentkills + amount));
					success = true;
				}
				else {
					int currentkills = selectValue(p.getUniqueId(), table_KitPVE, column_Kills);
					kills.put(p.getUniqueId(), gamemode, currentkills + amount);
					updateValue(p, table_KitPVE, column_Kills, String.valueOf(currentkills + amount));
					success = true;
				}
				break;
			default:
				break;
		}
		return success;

	}

	public static boolean addDeaths(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		if (amount <= 0) {
			return false;
		}
		switch (gamemode) {
			case KITPVE:
				if (deaths.contains(p.getUniqueId(), gamemode)) {
					int currentdeaths = deaths.get(p.getUniqueId(), gamemode);
					deaths.put(p.getUniqueId(), gamemode, currentdeaths + amount);
					updateValue(p, table_KitPVE, column_Deaths, String.valueOf(currentdeaths + amount));
					success = true;
				}
				else {
					int currentdeaths = selectValue(p.getUniqueId(), table_KitPVE, column_Deaths);
					deaths.put(p.getUniqueId(), gamemode, currentdeaths + amount);
					updateValue(p, table_KitPVE, column_Deaths, String.valueOf(currentdeaths + amount));
					success = true;
				}
				break;
			default:
				break;
		}
		return success;
	}

	public static boolean addGames(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		if (amount <= 0) {
			return false;
		}
		switch (gamemode) {
			case BORDERLINE:
				if (games.contains(p.getUniqueId(), gamemode)) {
					int currentgames = games.get(p.getUniqueId(), gamemode);
					games.put(p.getUniqueId(), gamemode, currentgames + amount);
					updateValue(p, table_Borderline, column_Games, String.valueOf(currentgames + amount));
					success = true;
				}
				else {
					int currentgames = selectValue(p.getUniqueId(), table_Borderline, column_Games);
					games.put(p.getUniqueId(), gamemode, currentgames + amount);
					updateValue(p, table_Borderline, column_Games, String.valueOf(currentgames + amount));
					success = true;
				}
				break;
			default:
				break;
		}
		return success;
	}

	public static boolean addWins(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		if (amount <= 0) {
			return false;
		}
		switch (gamemode) {
			case BORDERLINE:
				if (wins.contains(p.getUniqueId(), gamemode)) {
					int currentwins = wins.get(p.getUniqueId(), gamemode);
					wins.put(p.getUniqueId(), gamemode, currentwins + amount);
					updateValue(p, table_Borderline, column_Wins, String.valueOf(currentwins + amount));
					success = true;
				}
				else {
					int currentwins = selectValue(p.getUniqueId(), table_Borderline, column_Wins);
					wins.put(p.getUniqueId(), gamemode, currentwins + amount);
					updateValue(p, table_Borderline, column_Wins, String.valueOf(currentwins + amount));
					success = true;
				}
				break;
			default:
				break;
		}
		return success;
	}

	public static boolean removeKills(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		switch (gamemode) {
			case BORDERLINE:
				if (kills.contains(p.getUniqueId(), gamemode)) {
					int currentkills = kills.get(p.getUniqueId(), gamemode);
					int newkills = currentkills - amount;
					if (!(newkills <= 0)) {
						kills.put(p.getUniqueId(), gamemode, newkills);
						updateValue(p, table_Borderline, column_Kills, String.valueOf(newkills));
						success = true;
					}
					else {
						kills.put(p.getUniqueId(), gamemode, 0);
						updateValue(p, table_Borderline, column_Kills, String.valueOf(0));
						success = true;
					}
				}
				else {
					int currentkills = selectValue(p.getUniqueId(), table_Borderline, column_Kills);
					int newkills = currentkills - amount;
					if (!(newkills <= 0)) {
						kills.put(p.getUniqueId(), gamemode, newkills);
						updateValue(p, table_Borderline, column_Kills, String.valueOf(newkills));
						success = true;
					}
					else {
						kills.put(p.getUniqueId(), gamemode, 0);
						updateValue(p, table_Borderline, column_Kills, String.valueOf(0));
						success = true;
					}
				}
				break;
			case KITPVE:
				if (kills.contains(p.getUniqueId(), gamemode)) {
					int currentkills = kills.get(p.getUniqueId(), gamemode);
					int newkills = currentkills - amount;
					if (!(newkills <= 0)) {
						kills.put(p.getUniqueId(), gamemode, newkills);
						updateValue(p, table_KitPVE, column_Kills, String.valueOf(newkills));
						success = true;
					}
					else {
						kills.put(p.getUniqueId(), gamemode, 0);
						updateValue(p, table_KitPVE, column_Kills, String.valueOf(0));
						success = true;
					}
				}
				else {
					int currentkills = selectValue(p.getUniqueId(), table_KitPVE, column_Kills);
					int newkills = currentkills - amount;
					if (!(newkills <= 0)) {
						kills.put(p.getUniqueId(), gamemode, newkills);
						updateValue(p, table_KitPVE, column_Kills, String.valueOf(newkills));
						success = true;
					}
					else {
						kills.put(p.getUniqueId(), gamemode, 0);
						updateValue(p, table_KitPVE, column_Kills, String.valueOf(0));
						success = true;
					}
				}
				break;
			default:
				break;
		}
		return success;
	}

	public static boolean removeDeaths(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		switch (gamemode) {
			case KITPVE:
				if (deaths.contains(p.getUniqueId(), gamemode)) {
					int currentdeaths = deaths.get(p.getUniqueId(), gamemode);
					int newdeaths = currentdeaths - amount;
					if (!(newdeaths <= 0)) {
						deaths.put(p.getUniqueId(), gamemode, newdeaths);
						updateValue(p, table_KitPVE, column_Deaths, String.valueOf(newdeaths));
						success = true;
					}
					else {
						deaths.put(p.getUniqueId(), gamemode, 0);
						updateValue(p, table_KitPVE, column_Deaths, String.valueOf(0));
						success = true;
					}
				}
				else {
					int currentdeaths = selectValue(p.getUniqueId(), table_KitPVE, column_Deaths);
					int newdeaths = currentdeaths - amount;
					if (!(newdeaths <= 0)) {
						deaths.put(p.getUniqueId(), gamemode, newdeaths);
						updateValue(p, table_KitPVE, column_Deaths, String.valueOf(newdeaths));
						success = true;
					}
					else {
						deaths.put(p.getUniqueId(), gamemode, 0);
						updateValue(p, table_KitPVE, column_Deaths, String.valueOf(0));
						success = true;
					}
				}
			default:
				break;
		}
		return success;
	}

	public static boolean removeGames(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		switch (gamemode) {
			case BORDERLINE:
				if (games.contains(p.getUniqueId(), gamemode)) {
					int currentgames = games.get(p.getUniqueId(), gamemode);
					int newgames = currentgames - amount;
					if (!(newgames <= 0)) {
						games.put(p.getUniqueId(), gamemode, newgames);
						updateValue(p, table_Borderline, column_Games, String.valueOf(newgames));
						success = true;
					}
					else {
						games.put(p.getUniqueId(), gamemode, 0);
						updateValue(p, table_Borderline, column_Games, String.valueOf(0));
						success = true;
					}
				}
				else {
					int currentgames = selectValue(p.getUniqueId(), table_Borderline, column_Games);
					int newgames = currentgames - amount;
					if (!(newgames <= 0)) {
						games.put(p.getUniqueId(), gamemode, newgames);
						updateValue(p, table_Borderline, column_Games, String.valueOf(newgames));
						success = true;
					}
					else {
						games.put(p.getUniqueId(), gamemode, 0);
						updateValue(p, table_Borderline, column_Games, String.valueOf(0));
						success = true;
					}
				}
				break;
			default:
				break;
		}
		return success;
	}

	public static boolean removeWins(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		switch (gamemode) {
			case BORDERLINE:
				if (wins.contains(p.getUniqueId(), gamemode)) {
					int currentwins = wins.get(p.getUniqueId(), gamemode);
					int newwins = currentwins - amount;
					if (!(newwins <= 0)) {
						wins.put(p.getUniqueId(), gamemode, newwins);
						updateValue(p, table_Borderline, column_Wins, String.valueOf(newwins));
						success = true;
					}
					else {
						wins.put(p.getUniqueId(), gamemode, 0);
						updateValue(p, table_Borderline, column_Wins, String.valueOf(0));
						success = true;
					}
				}
				else {
					int currentwins = selectValue(p.getUniqueId(), table_Borderline, column_Wins);
					int newwins = currentwins - amount;
					if (!(newwins <= 0)) {
						wins.put(p.getUniqueId(), gamemode, newwins);
						updateValue(p, table_Borderline, column_Wins, String.valueOf(newwins));
						success = true;
					}
					else {
						wins.put(p.getUniqueId(), gamemode, 0);
						updateValue(p, table_Borderline, column_Wins, String.valueOf(0));
						success = true;
					}
				}
				break;
			default:
				break;
		}
		return success;
	}

	public static boolean setKills(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		int setkills = amount;
		if (amount <= 0) {
			setkills = 0;
		}
		switch (gamemode) {
			case BORDERLINE:
				kills.put(p.getUniqueId(), gamemode, setkills);
				updateValue(p, table_Borderline, column_Kills, String.valueOf(setkills));
				success = true;
				break;
			case KITPVE:
				kills.put(p.getUniqueId(), gamemode, setkills);
				updateValue(p, table_KitPVE, column_Kills, String.valueOf(setkills));
				success = true;
				break;
			default:
				break;
		}
		return success;
	}

	public static boolean setDeaths(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		int setdeaths = amount;
		if (amount <= 0) {
			setdeaths = 0;
		}
		switch (gamemode) {
			case KITPVE:
				deaths.put(p.getUniqueId(), gamemode, setdeaths);
				updateValue(p, table_KitPVE, column_Deaths, String.valueOf(setdeaths));
				success = true;
				break;
			default:
				break;
		}
		return success;
	}

	public static boolean setGames(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		int setgames = amount;
		if (amount <= 0) {
			setgames = 0;
		}
		switch (gamemode) {
			case BORDERLINE:
				games.put(p.getUniqueId(), gamemode, setgames);
				updateValue(p, table_Borderline, column_Games, String.valueOf(setgames));
				success = true;
				break;
			default:
				break;
		}
		return success;
	}

	public static boolean setWins(Player p, Gamemodes gamemode, int amount) {
		boolean success = false;
		int setwins = amount;
		if (amount <= 0) {
			setwins = 0;
		}
		switch (gamemode) {
			case BORDERLINE:
				wins.put(p.getUniqueId(), gamemode, setwins);
				updateValue(p, table_Borderline, column_Wins, String.valueOf(setwins));
				success = true;
				break;
			default:
				break;
		}
		return success;
	}

	public static int getKills(Player p, Gamemodes gamemode) {
		int returnedvalue = 0;
		switch (gamemode) {
			case BORDERLINE:
				if (kills.contains(p.getUniqueId(), gamemode)) {
					returnedvalue = kills.get(p.getUniqueId(), gamemode);
				}
				else {
					returnedvalue = selectValue(p.getUniqueId(), table_Borderline, column_Kills);
					kills.put(p.getUniqueId(), gamemode, returnedvalue);
				}
				break;
			case KITPVE:
				if (kills.contains(p.getUniqueId(), gamemode)) {
					returnedvalue = kills.get(p.getUniqueId(), gamemode);
				}
				else {
					returnedvalue = selectValue(p.getUniqueId(), table_KitPVE, column_Kills);
					kills.put(p.getUniqueId(), gamemode, returnedvalue);
				}
				break;
			default:
				break;
		}
		return returnedvalue;
	}

	public static int getDeaths(Player p, Gamemodes gamemode) {
		int returnedvalue = 0;
		switch (gamemode) {
			case KITPVE:
				if (deaths.contains(p.getUniqueId(), gamemode)) {
					returnedvalue = deaths.get(p.getUniqueId(), gamemode);
				}
				else {
					returnedvalue = selectValue(p.getUniqueId(), table_KitPVE, column_Deaths);
					deaths.put(p.getUniqueId(), gamemode, returnedvalue);
				}
				break;
			default:
				break;
		}
		return returnedvalue;
	}

	public static int getGames(Player p, Gamemodes gamemode) {
		int returnedvalue = 0;
		switch (gamemode) {
			case BORDERLINE:
				if (games.contains(p.getUniqueId(), gamemode)) {
					returnedvalue = games.get(p.getUniqueId(), gamemode);
				}
				else {
					returnedvalue = selectValue(p.getUniqueId(), table_Borderline, column_Games);
					games.put(p.getUniqueId(), gamemode, returnedvalue);
				}
				break;
			default:
				break;
		}
		return returnedvalue;
	}

	public static int getWins(Player p, Gamemodes gamemode) {
		int returnedvalue = 0;
		switch (gamemode) {
			case BORDERLINE:
				if (wins.contains(p.getUniqueId(), gamemode)) {
					returnedvalue = wins.get(p.getUniqueId(), gamemode);
				}
				else {
					returnedvalue = selectValue(p.getUniqueId(), table_Borderline, column_Wins);
					wins.put(p.getUniqueId(), gamemode, returnedvalue);
				}
				break;
			default:
				break;
		}
		return returnedvalue;
	}

	public static String getKD(Player p) {
		int kills = getKills(p, Gamemodes.KITPVE);
		int deaths = getDeaths(p, Gamemodes.KITPVE);
		if ((kills > 0) && (deaths > 0)) {
			double kd = kills / deaths;
			DecimalFormat dec = new DecimalFormat("#.##");
			return dec.format(kd);
		}
		return "0";
	}

	public static boolean resetStats(Player p, Gamemodes gamemode) {
		switch (gamemode) {
			case BORDERLINE:
				removeRecord(p, table_Borderline);
				return true;
			case KITPVE:
				removeRecord(p, table_KitPVE);
				return true;
			default:
				break;
		}
		return false;
	}

	public static boolean resetStats(String playername, Gamemodes gamemode) {
		if (UUIDFetcher.getUUID(playername) == null) {
			return false;
		}
		switch (gamemode) {
			case BORDERLINE:
				removeRecord(playername, table_Borderline);
				return true;
			case KITPVE:
				removeRecord(playername, table_KitPVE);
				return true;
			default:
				break;
		}
		return false;
	}

	public static void wipeStats(Player p) {
		removeRecord(p, table_Borderline);
		removeRecord(p, table_KitPVE);
		removeRecord(p, table_Logins);
		removeRecord(p, table_Votes);
	}

	private static void updateValue(Player p, final String table, final String value, final String newvalue) {
		final String uuid = getSQLUUID(p.getUniqueId());
		SQLCallback<Boolean> call = new SQLCallback<Boolean>() {

			@Override
			public void execute(Boolean response) {
				if (response) {
					main.getServer().getScheduler().runTaskAsynchronously(main,
							new SQLRunnable("UPDATE " + table + " SET " + value + " = '" + newvalue + "' WHERE UUID = '" + uuid + "';", "StatManager"));
				}
				else {
					main.getServer().getScheduler().runTaskAsynchronously(main,
							new SQLRunnable("INSERT INTO " + table + "(UUID, " + value + ") VALUES('" + uuid + "','" + newvalue + "') ON DUPLICATE KEY UPDATE UUID = '" + uuid + "';", "StatManager"));
				}
			}
		};
		runCallback("SELECT UUID FROM " + table + " WHERE UUID = '" + uuid + "';", call);
	}

	private static int selectValue(UUID puuid, String table, String value) {
		int returnedvalue = 0;
		String uuid = getSQLUUID(puuid);
		try {
			if (sql.existanceQuery("SELECT UUID FROM " + table + " WHERE UUID = '" + uuid + "';")) {
				ResultSet set = sql.sqlQuery("SELECT " + value + " FROM " + table + " WHERE UUID = '" + uuid + "';");
				if ((set.next()) && (set != null)) {
					returnedvalue = set.getInt(1);
				}
				set.close();
			}
		}
		catch (SQLException e) {
			main.getUtilLogger().logLevel(LogLevel.WARNING, "Stats> Failed to select value, Table: " + table + " Value: " + value);
			e.printStackTrace();
		}
		return returnedvalue;
	}

	private static void removeRecord(Player p, String table) {
		String uuid = getSQLUUID(p.getUniqueId());
		main.getServer().getScheduler().runTaskAsynchronously(main, new SQLRunnable("DELETE FROM " + table + " WHERE UUID = '" + uuid + "'", "StatManager"));
	}

	private static void removeRecord(String player, String table) {
		String uuid = getSQLUUID(UUIDFetcher.getUUID(player));
		main.getServer().getScheduler().runTaskAsynchronously(main, new SQLRunnable("DELETE FROM " + table + " WHERE UUID = '" + uuid + "'", "StatManager"));
	}

	private static void runCallback(final String query, final SQLCallback<Boolean> callback) {
		new BukkitRunnable() {

			@Override
			public void run() {
				boolean ret = false;
				try {
					ret = sql.existanceQuery(query);
				}
				catch (SQLException e) {
					main.getUtilLogger().logLevel(LogLevel.WARNING, "SQL> Sql callback failed!");
					e.printStackTrace();
				}
				final boolean callbackboolean = ret;
				new BukkitRunnable() {

					@Override
					public void run() {
						callback.execute(callbackboolean);
					}
				}.runTask(main);
			}
		}.runTaskAsynchronously(main);
	}

	public static void addToCache(final Player p, final StatCallback callback) {
		SQLCallback<int[]> stats = new SQLCallback<int[]>() {
			@Override
			public void execute(int[] response) {
				if (response.length < 7) {
					main.getUtilLogger().logLevel(LogLevel.WARNING, "Stats> Recieved invalid stat set response!");
					return;
				}
				kills.put(p.getUniqueId(), Gamemodes.BORDERLINE, response[0]);
				kills.put(p.getUniqueId(), Gamemodes.KITPVE, response[1]);
				deaths.put(p.getUniqueId(), Gamemodes.KITPVE, response[2]);
				games.put(p.getUniqueId(), Gamemodes.BORDERLINE, response[3]);
				wins.put(p.getUniqueId(), Gamemodes.BORDERLINE, response[4]);
				logins.put(p.getUniqueId(), response[5]);
				votes.put(p.getUniqueId(), response[6]);
				callback.execute();
			}
		};

		runCacheCallback(getSQLUUID(p.getUniqueId()), stats);
	}

	private static void runCacheCallback(final String uuid, final SQLCallback<int[]> call) {
		new BukkitRunnable() {

			@Override
			public void run() {
				int bdkills = 0;
				int pvekills = 0;
				int pvedeaths = 0;
				int bdgames = 0;
				int bdwins = 0;
				int logins = 0;
				int votes = 0;
				try {
					// KILLS
					ResultSet set = sql.sqlQuery(constructStatement(column_Kills, table_Borderline, uuid));
					if ((set != null) && (set.next())) {
						bdkills = set.getInt(1);
					}
					set.close();
					set = sql.sqlQuery(constructStatement(column_Kills, table_KitPVE, uuid));
					if ((set != null) && (set.next())) {
						pvekills = set.getInt(1);
					}
					set.close();

					// DEATHS
					set = sql.sqlQuery(constructStatement(column_Deaths, table_KitPVE, uuid));
					if ((set != null) && (set.next())) {
						pvedeaths = set.getInt(1);
					}
					set.close();

					// GAMES
					set = sql.sqlQuery(constructStatement(column_Games, table_Borderline, uuid));
					if ((set != null) && (set.next())) {
						bdgames = set.getInt(1);
					}
					set.close();

					// WINS
					set = sql.sqlQuery(constructStatement(column_Wins, table_Borderline, uuid));
					if ((set != null) && (set.next())) {
						bdwins = set.getInt(1);
					}
					set.close();

					// OTHER STATS
					set = sql.sqlQuery(constructStatement(column_Logins, table_Logins, uuid));
					if ((set != null) && (set.next())) {
						logins = set.getInt(1);
					}
					set.close();
					set = sql.sqlQuery(constructStatement(column_VoteCount, table_Votes, uuid));
					if ((set != null) && (set.next())) {
						votes = set.getInt(1);
					}
					set.close();
				}
				catch (SQLException e) {
					main.getUtilLogger().logLevel(LogLevel.WARNING, "Stats> SQL cache callback failed!");
					e.printStackTrace();
				}
				final int[] statarray = new int[] { bdkills, pvekills, pvedeaths, bdgames, bdwins, logins, votes };
				new BukkitRunnable() {

					@Override
					public void run() {
						call.execute(statarray);
					}
				}.runTask(main);
			}
		}.runTaskAsynchronously(main);
	}

	private static String constructStatement(String column, String table, String uuid) {
		return "SELECT " + column + " FROM " + table + " WHERE UUID ='" + uuid + "';";
	}

	public static boolean isInCache(Player p) {
		return kills.containsRow(p.getUniqueId()) && deaths.containsRow(p.getUniqueId()) && games.containsRow(p.getUniqueId()) && wins.containsRow(p.getUniqueId())
				&& votes.containsKey(p.getUniqueId()) && logins.containsKey(p.getUniqueId());
	}

	public static void removeFromCache(Player p) {

		if (kills.contains(p.getUniqueId(), Gamemodes.BORDERLINE)) {
			kills.remove(p.getUniqueId(), Gamemodes.BORDERLINE);
		}
		if (kills.contains(p.getUniqueId(), Gamemodes.KITPVE)) {
			kills.remove(p.getUniqueId(), Gamemodes.KITPVE);
		}
		if (deaths.contains(p.getUniqueId(), Gamemodes.BORDERLINE)) {
			deaths.remove(p.getUniqueId(), Gamemodes.BORDERLINE);
		}
		if (deaths.contains(p.getUniqueId(), Gamemodes.KITPVE)) {
			deaths.remove(p.getUniqueId(), Gamemodes.KITPVE);
		}
		if (games.contains(p.getUniqueId(), Gamemodes.BORDERLINE)) {
			games.remove(p.getUniqueId(), Gamemodes.BORDERLINE);
		}
		if (games.contains(p.getUniqueId(), Gamemodes.KITPVE)) {
			games.remove(p.getUniqueId(), Gamemodes.KITPVE);
		}
		if (wins.contains(p.getUniqueId(), Gamemodes.BORDERLINE)) {
			wins.remove(p.getUniqueId(), Gamemodes.BORDERLINE);
		}
		if (wins.contains(p.getUniqueId(), Gamemodes.KITPVE)) {
			wins.remove(p.getUniqueId(), Gamemodes.KITPVE);
		}
		return;
	}

	private static String getSQLUUID(UUID uuid) {
		if (uuid != null) {
			return uuid.toString().replace("-", "");
		}
		return null;
	}

}
