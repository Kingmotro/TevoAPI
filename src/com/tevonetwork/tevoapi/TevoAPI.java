package com.tevonetwork.tevoapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;

import com.tevonetwork.tevoapi.API.BossBar.BossBarAnimation;
import com.tevonetwork.tevoapi.API.Configs.ConfigManager;
import com.tevonetwork.tevoapi.API.MySQL.SQLManager;
import com.tevonetwork.tevoapi.API.Portals.PortalManager;
import com.tevonetwork.tevoapi.API.Util.UtilLogger;
import com.tevonetwork.tevoapi.Commands.BackCMD;
import com.tevonetwork.tevoapi.Commands.BroadcastCMD;
import com.tevonetwork.tevoapi.Commands.CICMD;
import com.tevonetwork.tevoapi.Commands.ChannelCMD;
import com.tevonetwork.tevoapi.Commands.ClearChatCMD;
import com.tevonetwork.tevoapi.Commands.DEOPCMD;
import com.tevonetwork.tevoapi.Commands.DayCMD;
import com.tevonetwork.tevoapi.Commands.DelWarpCMD;
import com.tevonetwork.tevoapi.Commands.FeedCMD;
import com.tevonetwork.tevoapi.Commands.FlyCMD;
import com.tevonetwork.tevoapi.Commands.GamemodeCMD;
import com.tevonetwork.tevoapi.Commands.HelpCMD;
import com.tevonetwork.tevoapi.Commands.ICMD;
import com.tevonetwork.tevoapi.Commands.InfoCMD;
import com.tevonetwork.tevoapi.Commands.KickCMD;
import com.tevonetwork.tevoapi.Commands.KillAllCMD;
import com.tevonetwork.tevoapi.Commands.MSGCMD;
import com.tevonetwork.tevoapi.Commands.NameCheckCMD;
import com.tevonetwork.tevoapi.Commands.NightCMD;
import com.tevonetwork.tevoapi.Commands.OPCMD;
import com.tevonetwork.tevoapi.Commands.PingCMD;
import com.tevonetwork.tevoapi.Commands.PortalCMD;
import com.tevonetwork.tevoapi.Commands.PunishCMD;
import com.tevonetwork.tevoapi.Commands.RCMD;
import com.tevonetwork.tevoapi.Commands.RamCMD;
import com.tevonetwork.tevoapi.Commands.SetWarpCMD;
import com.tevonetwork.tevoapi.Commands.SlapCMD;
import com.tevonetwork.tevoapi.Commands.SpeedCMD;
import com.tevonetwork.tevoapi.Commands.SudoCMD;
import com.tevonetwork.tevoapi.Commands.TASCMD;
import com.tevonetwork.tevoapi.Commands.TPCMD;
import com.tevonetwork.tevoapi.Commands.TPHereCMD;
import com.tevonetwork.tevoapi.Commands.TPPOSCMD;
import com.tevonetwork.tevoapi.Commands.TevoAPICMD;
import com.tevonetwork.tevoapi.Commands.TimeCMD;
import com.tevonetwork.tevoapi.Commands.TokensCMD;
import com.tevonetwork.tevoapi.Commands.VoteCMD;
import com.tevonetwork.tevoapi.Commands.WarpCMD;
import com.tevonetwork.tevoapi.Commands.WorldCMD;
import com.tevonetwork.tevoapi.Core.LogLevel;
import com.tevonetwork.tevoapi.Core.Chat.Announcer;
import com.tevonetwork.tevoapi.Core.Networking.ServerPlayerCounter;
import com.tevonetwork.tevoapi.Core.Networking.WorldPlayerCounter;
import com.tevonetwork.tevoapi.Core.Travel.WarpManager;
import com.tevonetwork.tevoapi.Core.World.ChunkUnloader;
import com.tevonetwork.tevoapi.Core.World.WorldManager;
import com.tevonetwork.tevoapi.Listeners.BlockBreakListener;
import com.tevonetwork.tevoapi.Listeners.BlockFadeListener;
import com.tevonetwork.tevoapi.Listeners.BlockPhysicsListener;
import com.tevonetwork.tevoapi.Listeners.BlockPlaceListener;
import com.tevonetwork.tevoapi.Listeners.ChatListener;
import com.tevonetwork.tevoapi.Listeners.CreatureSpawnListener;
import com.tevonetwork.tevoapi.Listeners.DeathListener;
import com.tevonetwork.tevoapi.Listeners.EntityBreakDoorListener;
import com.tevonetwork.tevoapi.Listeners.EntityChangeBlockListener;
import com.tevonetwork.tevoapi.Listeners.EntityCreatePortalListener;
import com.tevonetwork.tevoapi.Listeners.EntityDamageByBlockListener;
import com.tevonetwork.tevoapi.Listeners.EntityDamageByEntityListener;
import com.tevonetwork.tevoapi.Listeners.EntityDamageListener;
import com.tevonetwork.tevoapi.Listeners.EntityExplodeListener;
import com.tevonetwork.tevoapi.Listeners.EntityInteractListener;
import com.tevonetwork.tevoapi.Listeners.FoodLevelChangeListener;
import com.tevonetwork.tevoapi.Listeners.IncomingBungeeChannelListener;
import com.tevonetwork.tevoapi.Listeners.IncomingTevoNetworkChannelListener;
import com.tevonetwork.tevoapi.Listeners.InventoryClickListener;
import com.tevonetwork.tevoapi.Listeners.InventoryOpenListener;
import com.tevonetwork.tevoapi.Listeners.JoinListener;
import com.tevonetwork.tevoapi.Listeners.LeavesDecayListener;
import com.tevonetwork.tevoapi.Listeners.LoginListener;
import com.tevonetwork.tevoapi.Listeners.MoveListener;
import com.tevonetwork.tevoapi.Listeners.PlayerChangedWorldListener;
import com.tevonetwork.tevoapi.Listeners.PlayerCommandPreProcessListener;
import com.tevonetwork.tevoapi.Listeners.PlayerInteractListener;
import com.tevonetwork.tevoapi.Listeners.PlayerRespawnListener;
import com.tevonetwork.tevoapi.Listeners.QuitListener;
import com.tevonetwork.tevoapi.Listeners.SignChangeListener;
import com.tevonetwork.tevoapi.Listeners.TeleportListener;
import com.tevonetwork.tevoapi.Listeners.WeatherChangeListener;

/**
 * TevoAPI Manages all tevo network servers and provides some useful utils.
 * 
 * NOTE: The API will break on minecraft updates as it does make NMS calls.
 * 
 * @author Thrusmyster
 *
 */
public class TevoAPI extends JavaPlugin {

	private UtilLogger logger;
	private ConfigManager cfm;
	private WorldManager wm;
	private SQLManager sql;
	public String server_name;
	public static String incoming_TevoNetwork_Channel = "TevoNetworkIncoming";
	public static String outgoing_TevoNetwork_Channel = "TevoNetworkOutgoing";
	public static String outgoing_Bungee_Channel = "BungeeCord";

	public void onEnable() {
		main = this;
		logger = new UtilLogger(this);
		startup();
		logger.logNormal("Plugin> Registering Listeners...");
		registerListeners();
		logger.logNormal("Plugin> Registering Command...");
		registerCMDS();
		logger.logNormal("Plugin> Registering Network Channels...");
		registerChannels();
		logger.logNormal("Plugin> Starting tasks...");
		startTasks();
		logger.logEnableDisable(true);
	}

	public void onDisable() {
		logger.logEnableDisable(false);
	}

	private static TevoAPI main;

	public static TevoAPI getInstance() {
		return main;
	}

	public WorldManager getWorldManager() {
		return this.wm;
	}

	public SQLManager getSQLManager() {
		return this.sql;
	}

	public ConfigManager getConfigManager() {
		return this.cfm;
	}

	public UtilLogger getUtilLogger() {
		return this.logger;
	}

	private void startup() {
		logger.logNormal("Plugin> Loading config manager...");
		this.cfm = new ConfigManager();
		this.cfm.load();
		logger.logNormal("Plugin> Loading sql manager...");
		this.sql = new SQLManager();
		if (this.sql.initialize("tevoserv_MC")) {
			logger.logNormal("SQL> SQL Initialization was successful!");
		}
		else {
			logger.logLevel(LogLevel.SEVERE, "SQL> Something went wrong when initializing SQL!");
		}
		logger.logNormal("Plugin> Loading warp manager...");
		WarpManager.setup();
		logger.logNormal("Plugin> Loading world manager...");
		this.wm = new WorldManager();
		this.wm.initialize();
		PortalManager.load();
	}

	private void startTasks() {
		BukkitScheduler s = Bukkit.getScheduler();
		s.scheduleSyncRepeatingTask(this, new WorldPlayerCounter(), 60L, 60L);
		s.scheduleSyncRepeatingTask(this, new ServerPlayerCounter(), 60L, 80L);
		s.scheduleSyncRepeatingTask(this, new ChunkUnloader(), 600L, 600L);
		s.scheduleSyncRepeatingTask(this, new BossBarAnimation(), 100L, 2L);
		s.scheduleSyncRepeatingTask(this, new Announcer(), 100L, 3000L);
		s.scheduleSyncRepeatingTask(this, this.sql, 800L, 6000L);
		logger.logNormal("Plugin> Tasks have been started.");
	}

	private void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new BlockBreakListener(), this);
		pm.registerEvents(new BlockPlaceListener(), this);
		pm.registerEvents(new BlockFadeListener(), this);
		pm.registerEvents(new BlockPhysicsListener(), this);
		pm.registerEvents(new ChatListener(), this);
		pm.registerEvents(new CreatureSpawnListener(), this);
		pm.registerEvents(new DeathListener(), this);
		pm.registerEvents(new EntityBreakDoorListener(), this);
		pm.registerEvents(new EntityChangeBlockListener(), this);
		pm.registerEvents(new EntityCreatePortalListener(), this);
		pm.registerEvents(new EntityDamageByBlockListener(), this);
		pm.registerEvents(new EntityDamageByEntityListener(), this);
		pm.registerEvents(new EntityDamageListener(), this);
		pm.registerEvents(new EntityExplodeListener(), this);
		pm.registerEvents(new EntityInteractListener(), this);
		pm.registerEvents(new FoodLevelChangeListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		pm.registerEvents(new InventoryOpenListener(), this);
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new LeavesDecayListener(), this);
		pm.registerEvents(new LoginListener(), this);
		pm.registerEvents(new MoveListener(), this);
		pm.registerEvents(new PlayerChangedWorldListener(), this);
		pm.registerEvents(new PlayerInteractListener(), this);
		pm.registerEvents(new QuitListener(), this);
		pm.registerEvents(new SignChangeListener(), this);
		pm.registerEvents(new TeleportListener(), this);
		pm.registerEvents(new WeatherChangeListener(), this);
		pm.registerEvents(new PlayerCommandPreProcessListener(), this);
		pm.registerEvents(new PlayerRespawnListener(), this);
		logger.logNormal("Plugin> Listeners have been registered.");
	}

	private void registerChannels() {
		Messenger m = Bukkit.getServer().getMessenger();
		m.registerOutgoingPluginChannel(this, outgoing_Bungee_Channel);
		m.registerIncomingPluginChannel(this, outgoing_Bungee_Channel, new IncomingBungeeChannelListener());
		m.registerIncomingPluginChannel(this, incoming_TevoNetwork_Channel, new IncomingTevoNetworkChannelListener());
		m.registerOutgoingPluginChannel(this, outgoing_TevoNetwork_Channel);
		logger.logNormal("Plugin> Network channels have been registered.");
	}

	private void registerCMDS() {
		this.getCommand("back").setExecutor(new BackCMD());
		this.getCommand("broadcast").setExecutor(new BroadcastCMD());
		this.getCommand("channel").setExecutor(new ChannelCMD());
		this.getCommand("clearchat").setExecutor(new ClearChatCMD());
		this.getCommand("delwarp").setExecutor(new DelWarpCMD());
		this.getCommand("gamemode").setExecutor(new GamemodeCMD());
		this.getCommand("feed").setExecutor(new FeedCMD());
		this.getCommand("fly").setExecutor(new FlyCMD());
		this.getCommand("help").setExecutor(new HelpCMD());
		this.getCommand("namecheck").setExecutor(new NameCheckCMD());
		this.getCommand("ping").setExecutor(new PingCMD());
		this.getCommand("portal").setExecutor(new PortalCMD());
		this.getCommand("slap").setExecutor(new SlapCMD());
		this.getCommand("tas").setExecutor(new TASCMD());
		this.getCommand("tevoapi").setExecutor(new TevoAPICMD());
		this.getCommand("world").setExecutor(new WorldCMD());
		this.getCommand("i").setExecutor(new ICMD());
		this.getCommand("info").setExecutor(new InfoCMD());
		this.getCommand("msg").setExecutor(new MSGCMD());
		this.getCommand("reply").setExecutor(new RCMD());
		this.getCommand("setwarp").setExecutor(new SetWarpCMD());
		this.getCommand("tp").setExecutor(new TPCMD());
		this.getCommand("vote").setExecutor(new VoteCMD());
		this.getCommand("warp").setExecutor(new WarpCMD());
		this.getCommand("killall").setExecutor(new KillAllCMD());
		this.getCommand("tphere").setExecutor(new TPHereCMD());
		this.getCommand("op").setExecutor(new OPCMD());
		this.getCommand("deop").setExecutor(new DEOPCMD());
		this.getCommand("sudo").setExecutor(new SudoCMD());
		this.getCommand("tokens").setExecutor(new TokensCMD());
		this.getCommand("tppos").setExecutor(new TPPOSCMD());
		this.getCommand("speed").setExecutor(new SpeedCMD());
		this.getCommand("punish").setExecutor(new PunishCMD());
		this.getCommand("kick").setExecutor(new KickCMD());
		this.getCommand("ram").setExecutor(new RamCMD());
		this.getCommand("night").setExecutor(new NightCMD());
		this.getCommand("day").setExecutor(new DayCMD());
		this.getCommand("time").setExecutor(new TimeCMD());
		this.getCommand("ci").setExecutor(new CICMD());
		logger.logNormal("Plugin> Commands have been registered.");
	}

}
