package com.tevonetwork.tevoapi.API.BossBar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.BossBar.reflection.Reflection;

public abstract class BossBarAPI
{
  private static final Map<UUID, BossBar> barMap = new ConcurrentHashMap<UUID, BossBar>();
  
  /**
   * Sets the bossbar message for the specified player.
   * 
   * @param player
   * @param message
   */
  public static void setMessage(Player player, String message)
  {
    setMessage(player, message, 100.0F);
  }
  
  /**
   * Sets the bossbar message for the specified player and at the specified percentage. 
   * 
   * @param player
   * @param message
   * @param percentage
   */
  public static void setMessage(Player player, String message, float percentage)
  {
    setMessage(player, message, percentage, 0);
  }
  
  /**
   * Sets the bossbar message for the specified player and at the specified percentage.
   * Times out after the sepcified time.
   * 
   * @param player
   * @param message
   * @param percentage
   * @param timeout
   */
  public static void setMessage(Player player, String message, float percentage, int timeout)
  {
    setMessage(player, message, percentage, timeout, 100.0F);
  }
  
  /**
   * Sets the bossbar message for the specified player and at the specified percentage with min health.
   * Times out after the sepcified time.
   * 
   * @param player
   * @param message
   * @param percentage
   * @param timeout
   * @param minHealth
   */
  public static void setMessage(Player player, String message, float percentage, int timeout, float minHealth)
  {
    if (!barMap.containsKey(player.getUniqueId())) {
      barMap.put(player.getUniqueId(), new BossBar(player, message, percentage, timeout, minHealth));
    }
    BossBar bar = (BossBar)barMap.get(player.getUniqueId());
    if (!bar.message.equals(message)) {
      bar.setMessage(message);
    }
    float newHealth = percentage / 100.0F * bar.getMaxHealth();
    if (bar.health != newHealth) {
      bar.setHealth(newHealth);
    }
    if (!bar.isVisible()) {
      bar.setVisible(true);
    }
  }
  
  /**
   * Returns whether a player has a bossbar.
   * 
   * @param player
   * @return
   */
  public static boolean hasBar(@Nonnull Player player)
  {
    return barMap.containsKey(player.getUniqueId());
  }
  
  /**
   * Removes the bossbar for the specified player.
   * 
   * @param player
   */
  public static void removeBar(@Nonnull Player player)
  {
    BossBar bar = getBossBar(player);
    if (bar == null) {
      return;
    }
    bar.setVisible(false);
    barMap.remove(player.getUniqueId());
  }
  
  /**
   * Set the health of the bossbar for the specified player.
   * 
   * @param player
   * @param percentage
   */
  public static void setHealth(Player player, float percentage)
  {
    BossBar bar = getBossBar(player);
    if (bar == null) {
      return;
    }
    bar.setHealth(percentage);
  }
  
  /**
   * Returns the players bossbar object.
   * 
   * @param player The owner of the bossbar.
   * @return Returns null if no bar is present.
   */
  @Nullable
  public static BossBar getBossBar(@Nonnull Player player)
  {
    if (player == null) {
      return null;
    }
    return (BossBar)barMap.get(player.getUniqueId());
  }
  
  /**
   * Returns all active bossbars
   * 
   * @return Returns a collection of bossbar objects.
   */
  public static Collection<BossBar> getBossBars()
  {
    List<BossBar> list = new ArrayList<BossBar>(barMap.values());
    return list;
  }
  
  protected static void sendPacket(Player p, Object packet)
  {
    if ((p == null) || (packet == null)) {
      throw new IllegalArgumentException("player and packet cannot be null");
    }
    try
    {
      Object handle = Reflection.getHandle(p);
      Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
      Reflection.getMethod(connection.getClass(), "sendPacket", new Class[] { Reflection.getNMSClass("Packet") }).invoke(connection, new Object[] { packet });
    }
    catch (Exception localException) {}
  }
}