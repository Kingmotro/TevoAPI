package com.tevonetwork.tevoapi.API.Scoreboards;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Stats.StatManager;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Gamemodes;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Economy.EconomyManager;

public class PlayerScoreboard {

	private TevoAPI main = TevoAPI.getInstance();
	private Player scoreboardowner;
	private Scoreboard sb;
	private Gamemodes gm;
	private int animtaskID;
	private Score rank;
	private Score tokens;
	private Score kills;
	private Score deaths;
	private Score games;
	private Score wins;
	private Score kd;
	private Team owner;
	private Team admin;
	private Team builder;
	private Team moderator;
	private Team crystal;
	private Team loyalist;
	private Team mystic;
	private Team none;
	private Objective title;
	private int animPos;
	
	public PlayerScoreboard(Player p, Gamemodes gamemode)
	{
		this.scoreboardowner = p;
		this.sb = Bukkit.getScoreboardManager().getNewScoreboard();
		this.gm = gamemode;
		setup();
	}
	
	/**
	 * Invoked on call.
	 * Setup the scoreboard for the player.
	 * This should NOT be called any more than once.
	 * 
	 */
	private void setup()
	{
		this.owner = this.sb.registerNewTeam("Owner");
		this.owner.setPrefix(Rank.getRankPrefix(Rank.OWNER) + " " + CC.cWhite);
		this.owner.setAllowFriendlyFire(true);
		this.owner.setCanSeeFriendlyInvisibles(false);
		this.admin = this.sb.registerNewTeam("Admin");
		this.admin.setPrefix(Rank.getRankPrefix(Rank.ADMIN) + " " + CC.cWhite);
		this.admin.setAllowFriendlyFire(true);
		this.admin.setCanSeeFriendlyInvisibles(false);
		this.builder = this.sb.registerNewTeam("Builder");
		this.builder.setPrefix(Rank.getRankPrefix(Rank.BUILDER) + " " + CC.cWhite);
		this.builder.setAllowFriendlyFire(true);
		this.builder.setCanSeeFriendlyInvisibles(false);
		this.moderator = this.sb.registerNewTeam("Moderator");
		this.moderator.setPrefix(Rank.getRankPrefix(Rank.MODERATOR) + " " + CC.cWhite);
		this.moderator.setAllowFriendlyFire(true);
		this.moderator.setCanSeeFriendlyInvisibles(false);
		this.crystal = this.sb.registerNewTeam("Crystal");
		this.crystal.setPrefix(Rank.getRankPrefix(Rank.CRYSTAL) + " " + CC.cWhite);
		this.crystal.setAllowFriendlyFire(true);
		this.crystal.setCanSeeFriendlyInvisibles(false);
		this.loyalist = this.sb.registerNewTeam("Loyalist");
		this.loyalist.setPrefix(Rank.getRankPrefix(Rank.LOYALIST) + " " + CC.cWhite);
		this.loyalist.setAllowFriendlyFire(true);
		this.loyalist.setCanSeeFriendlyInvisibles(false);
		this.mystic = this.sb.registerNewTeam("Mystic");
		this.mystic.setPrefix(Rank.getRankPrefix(Rank.MYSTIC) + " " + CC.cWhite);
		this.mystic.setAllowFriendlyFire(true);
		this.mystic.setCanSeeFriendlyInvisibles(false);
		this.none = this.sb.registerNewTeam("Default");
		this.none.setPrefix(Rank.getRankPrefix(Rank.DEFAULT));
		this.none.setAllowFriendlyFire(true);
		this.none.setCanSeeFriendlyInvisibles(false);
		this.title = this.sb.registerNewObjective("TevoNetwork", "dummy");
		this.title.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.animPos = 0;
		this.animtaskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable()
		{
			public void run()
			{
				title.setDisplayName(titleanimationframes.get(animPos));
				animPos++;
				if (animPos >= titleanimationframes.size())
				{
					animPos = 0;
				}
			}
		}, 20L, 2L);
		this.title.getScore(CC.cYellow + CC.fBold + "Rank:");
		this.title.getScore(CC.cYellow + CC.fBold + "Rank:").setScore(12);
		this.title.getScore(" ");
		this.title.getScore(" ").setScore(10);
		this.title.getScore(CC.cYellow + CC.fBold + "Tevo Tokens:");
		this.title.getScore(CC.cYellow + CC.fBold + "Tevo Tokens:").setScore(9);
		this.title.getScore("  ");
		this.title.getScore("  ").setScore(1);
		this.title.getScore("tevonetwork.com");
		this.title.getScore("tevonetwork.com").setScore(0);
		this.scoreboardowner.setScoreboard(this.sb);
		update();
		
	}
	
	/**
	 * Invoked on setup().
	 * Updates all stats on the scoreboard.
	 * 
	 */
	public void update()
	{
		if (this.rank != null)
		{
			this.sb.resetScores(this.rank.getEntry());
		}
		if (this.kills != null)
		{
			this.sb.resetScores(this.kills.getEntry());
		}
		if (this.deaths != null)
		{
			this.sb.resetScores(this.deaths.getEntry());
		}
		if (this.games != null)
		{
			this.sb.resetScores(this.games.getEntry());
		}
		if (this.wins != null)
		{
			this.sb.resetScores(this.wins.getEntry());
		}
		if (this.kd != null)
		{
			this.sb.resetScores(this.kd.getEntry());
		}
		if (this.title.getScore(CC.tnInfo + CC.fBold + "Stats:") != null)
		{
			this.sb.resetScores(this.title.getScore(CC.tnInfo + CC.fBold + "Stats:").getEntry());
		}
		if (this.title.getScore("   ") != null)
		{
			this.sb.resetScores(this.title.getScore("   ").getEntry());
		}
		if (this.scoreboardowner.isOp())
		{
			this.rank = this.title.getScore(Rank.getRankPrefix(Rank.OWNER));
			this.rank.setScore(11);
		}
		else
		{
			Rank rank = UtilPlayer.getRank(this.scoreboardowner);
			if (rank.equals(Rank.DEFAULT))
			{
				this.rank = this.title.getScore(CC.cGray + CC.fBold + "No Rank");
				this.rank.setScore(11);
			}
			else
			{
				this.rank = this.title.getScore(Rank.getRankPrefix(rank));
				this.rank.setScore(11);
			}
		}
		this.tokens = this.title.getScore(CC.tnValue + EconomyManager.getTokensBal(this.scoreboardowner));
		this.tokens.setScore(8);
		
		if (gm != null)
		{
			if (this.gm != Gamemodes.HUB)
			{
				this.kills = this.title.getScore(CC.tnInfo + "Kills: " + CC.tnValue + StatManager.getKills(this.scoreboardowner, this.gm));
				this.kills.setScore(5);
			}
			switch(this.gm)
			{
				case HUB:
					break;
				case BORDERLINE:
					this.title.getScore("   ");
					this.title.getScore("   ").setScore(7);
					
					this.title.getScore(CC.cYellow + CC.fBold + "Stats:");
					this.title.getScore(CC.cYellow + CC.fBold + "Stats:").setScore(6);
					
					this.games = this.title.getScore(CC.tnInfo + "Games: " + CC.tnValue + StatManager.getGames(this.scoreboardowner, Gamemodes.BORDERLINE));
					this.games.setScore(3);
					
					this.wins = this.title.getScore(CC.tnInfo + "Wins: " + CC.tnValue + StatManager.getWins(this.scoreboardowner, Gamemodes.BORDERLINE));
					this.wins.setScore(2);
					break;
				case KITPVE:
					this.title.getScore("   ");
					this.title.getScore("   ").setScore(7);
					
					this.deaths = this.title.getScore(CC.tnInfo + "Deaths: " + CC.tnValue + StatManager.getDeaths(this.scoreboardowner, this.gm));
					this.deaths.setScore(4);
					
					this.title.getScore(CC.cYellow + CC.fBold + "Stats:");
					this.title.getScore(CC.cYellow + CC.fBold + "Stats:").setScore(6);
					
					this.kd = this.title.getScore(CC.tnInfo + "K/D: " + CC.tnValue + StatManager.getKD(this.scoreboardowner));
					this.kd.setScore(3);
					break;
			}
		}
	}
	
	/**
	 * Update the scoreboard rank.
	 * 
	 */
	public void updateRank()
	{
		if (this.rank == null)
		{
			return;
		}
		this.sb.resetScores(this.rank.getEntry());
		if (this.scoreboardowner.isOp())
		{
			this.rank = this.title.getScore(Rank.getRankPrefix(Rank.OWNER));
			this.rank.setScore(11);
		}
		else
		{
			Rank rank = UtilPlayer.getRank(this.scoreboardowner);
			if (rank.equals(Rank.DEFAULT))
			{
				this.rank = this.title.getScore(CC.cGray + CC.fBold + "No Rank");
				this.rank.setScore(11);
			}
			else
			{
				this.rank = this.title.getScore(Rank.getRankPrefix(rank));
				this.rank.setScore(11);
			}
		}
	}
	
	/**
	 * Update the scoreboard tokens.
	 * 
	 */
	public void updateTokens()
	{
		if (this.tokens == null)
		{
			return;
		}
		this.sb.resetScores(this.tokens.getEntry());
		this.tokens = this.title.getScore(CC.tnValue + EconomyManager.getTokensBal(this.scoreboardowner));
		this.tokens.setScore(8);
	}
	
	/**
	 * Update the scoreboard kills.
	 * 
	 */
	public void updateKills()
	{
		if (this.kills == null)
		{
			return;
		}
		if (this.gm == Gamemodes.HUB)
		{
			return;
		}
		this.sb.resetScores(this.kills.getEntry());
		this.kills = this.title.getScore(CC.tnInfo + "Kills: " + CC.tnValue + StatManager.getKills(this.scoreboardowner, this.gm));
		this.kills.setScore(5);
	}
	
	/**
	 * Update the scoreboard deaths.
	 * 
	 */
	public void updateDeaths()
	{
		if (this.deaths == null)
		{
			return;
		}
		if ((this.gm == Gamemodes.HUB) || (this.gm == Gamemodes.BORDERLINE))
		{
			return;
		}
		this.sb.resetScores(this.deaths.getEntry());
		this.deaths = this.title.getScore(CC.tnInfo + "Deaths: " + CC.tnValue + StatManager.getDeaths(this.scoreboardowner, this.gm));
		this.deaths.setScore(4);
	}
	
	/**
	 * Update the scoreboard games.
	 * 
	 */
	public void updateGames()
	{
		if (this.games == null)
		{
			return;
		}
		if ((this.gm == Gamemodes.HUB) || (this.gm == Gamemodes.KITPVE))
		{
			return;
		}
		this.sb.resetScores(this.games.getEntry());
		this.games = this.title.getScore(CC.tnInfo + "Games: " + CC.tnValue + StatManager.getGames(this.scoreboardowner, Gamemodes.BORDERLINE));
		this.games.setScore(3);
	}
	
	/**
	 * Update the scoreboard wins.
	 * 
	 */
	public void updateWins()
	{
		if (this.wins == null)
		{
			return;
		}
		if ((this.gm == Gamemodes.HUB) || (this.gm == Gamemodes.KITPVE))
		{
			return;
		}
		this.sb.resetScores(this.games.getEntry());
		this.wins = this.title.getScore(CC.tnInfo + "Wins: " + CC.tnValue + StatManager.getWins(this.scoreboardowner, Gamemodes.BORDERLINE));
		this.wins.setScore(2);
	}
	
	/**
	 * Update the scoreboard KD ratio.
	 * 
	 */
	public void updateKD()
	{
		if (this.kd == null)
		{
			return;
		}
		if ((this.gm == Gamemodes.HUB) || (this.gm == Gamemodes.BORDERLINE))
		{
			return;
		}
		this.sb.resetScores(this.kd.getEntry());
		this.kd = this.title.getScore(CC.tnInfo + "K/D: " + CC.tnValue + StatManager.getKD(this.scoreboardowner));
		this.kd.setScore(3);
	}
	
	@SuppressWarnings("deprecation")
	public void addPlayerRank(Player p, Rank rank)
	{
		switch(rank)
		{
			case OWNER:
				this.owner.addPlayer(p);
				break;
			case DEVELOPER:
				this.owner.addPlayer(p);
				break;
			case ADMIN:
				this.admin.addPlayer(p);
				break;
			case BUILDER:
				this.builder.addPlayer(p);
				break;
			case MODERATOR:
				this.moderator.addPlayer(p);
				break;
			case CRYSTAL:
				this.crystal.addPlayer(p);
				break;
			case LOYALIST:
				this.loyalist.addPlayer(p);
				break;
			case MYSTIC:
				this.mystic.addPlayer(p);
				break;
			case DEFAULT:
				this.none.addPlayer(p);
				break;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void removePlayerRank(Player p)
	{
		Set<Team> teams = this.sb.getTeams();
		Iterator<Team> itr = teams.iterator();
		while(itr.hasNext())
		{
			Team team = itr.next();
			if (team.hasPlayer(p))
			{
				team.removePlayer(p);
				break;
			}
		}
	}
	
	public void end()
	{
		Bukkit.getServer().getScheduler().cancelTask(this.animtaskID);
	}
	
	public Player getPlayer()
	{
		return this.scoreboardowner;
	}
	
	public Gamemodes getGamemode()
	{
		return this.gm;
	}
	
	public void setPlayer(Player p)
	{
		this.scoreboardowner = p;
		update();
	}
	
	public void setGamemode(Gamemodes gamemode)
	{
		this.gm = gamemode;
		update();
	}
	
	private List<String> titleanimationframes = Arrays.asList("§2§lTevo §4§lNetwork",
																"§e§lTe§2§lvo §4§lNetwork",
																"§2§lTe§e§lvo §4§lNetwork",
																"§2§lTevo §e§lNe§4§ltwork",
																"§2§lTevo §4§lNe§e§ltw§4§lork",
																"§2§lTevo §4§lNetw§e§lor§4§lk",
																"§2§lTevo §4§lNetwor§e§lk",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork",
																"§2§lTevo §4§lNetwork"
																);
}
