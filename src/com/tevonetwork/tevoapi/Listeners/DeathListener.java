package com.tevonetwork.tevoapi.Listeners;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Events.KillEvent;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Messages.DeathMSG;

public class DeathListener implements Listener {

	private TevoAPI main = TevoAPI.getInstance();

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		Player victim = e.getEntity();
		Entity killer = getLastEntityDamager(victim);
		if (killer != null) {
			if (killer instanceof Player) {
				Player executor = (Player) killer;
				String cause = UtilPlayer.getLastDamageCause(victim);
				if (cause != null) {
					DeathMSG.withKillerandAbility(victim, executor, cause);
				}
				else {
					DeathMSG.withKiller(victim, executor);
				}
				callEvent(executor, victim, cause);
			}
			else if (killer instanceof Projectile) {
				if (((Projectile) killer).getShooter() instanceof Player) {
					Player executor = (Player) ((Projectile) killer).getShooter();
					String cause = UtilPlayer.getLastDamageCause(victim);
					DeathMSG.withKillerandAbility(victim, executor, cause);
					callEvent(executor, victim, cause);
				}
			}
			else {
				DeathMSG.withCause(victim, killer.getName());
				callEvent(null, victim, null);
			}
		}
		else {
			if (UtilPlayer.getDamagers(victim).size() > 0) {
				Player executor = Bukkit.getPlayerExact(UtilPlayer.getDamagers(victim).get(UtilPlayer.getDamagers(victim).size() - 1));
				if (executor != null) {
					if (UtilPlayer.getLastDamageCause(victim) != null) {
						DeathMSG.withKillerandAbility(victim, executor, UtilPlayer.getLastDamageCause(victim));
						callEvent(executor, victim, UtilPlayer.getLastDamageCause(victim));
					}
					else {
						DeathMSG.withKiller(victim, executor);
						callEvent(executor, victim, "Unknown Reason");
					}
				}
				else {
					if (UtilPlayer.getLastDamageCause(victim) != null) {
						DeathMSG.withCause(victim, UtilPlayer.getLastDamageCause(victim));
						callEvent(null, victim, UtilPlayer.getLastDamageCause(victim));
					}
					else {
						DeathMSG.withCause(victim, "Unknown Reason");
						callEvent(null, victim, "Unknown Reason");
					}
				}
			}
			else {
				if (UtilPlayer.getLastDamageCause(victim) != null) {
					DeathMSG.withCause(victim, UtilPlayer.getLastDamageCause(victim));
					callEvent(null, victim, UtilPlayer.getLastDamageCause(victim));
				}
				else {
					DeathMSG.withCause(victim, "Unknown Reason");
					callEvent(null, victim, "Unknown Reason");
				}
			}
		}

	}

	private void callEvent(Player executor, Player victim, String cause) {
		KillEvent event = null;
		if (UtilPlayer.getDamagers(victim).size() > 0) {
			ArrayList<Player> assists = new ArrayList<Player>();
			for (String names : UtilPlayer.getDamagers(victim)) {
				if ((Bukkit.getPlayerExact(names) != null) && (!names.equalsIgnoreCase(executor.getName()))) {
					assists.add(Bukkit.getPlayerExact(names));
				}
			}
			if (assists.size() <= 0) {
				event = new KillEvent(executor, victim, cause);
			}
			else {
				event = new KillEvent(executor, victim, assists, cause);
			}
		}
		else {
			event = new KillEvent(executor, victim, cause);
		}
		if (event != null) {
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
		if (event.isBloodEnabled()) {
			spawnBlood(victim.getLocation());
		}
		if (event.isInstantRespawn()) {
			instantRespawn(victim);
		}
	}

	private void spawnBlood(Location loc) {
		loc.getWorld().spigot().playEffect(loc, Effect.STEP_SOUND, 152, 0, 0.5F, 1.2F, 0.5F, 1F, 8, 20);
	}

	private void instantRespawn(final Player p) {
		new BukkitRunnable() {
			@Override
			public void run() {
				p.spigot().respawn();
			}
		}.runTask(main);
	}

	private Entity getLastEntityDamager(Entity entity) {
		EntityDamageEvent event = entity.getLastDamageCause();
		if (event != null && !event.isCancelled() && (event instanceof EntityDamageByEntityEvent)) {
			Entity damager = ((EntityDamageByEntityEvent) event).getDamager();
			if (damager instanceof Projectile) {
				Projectile proj = (Projectile) damager;
				return proj;
			}
			return damager;
		}

		return null;
	}

}
