package com.tevonetwork.tevoapi.API.Util;

import org.bukkit.GameMode;

import com.tevonetwork.tevoapi.Core.WorldProperty;

public class UtilWorld {

	private boolean enable_leafDecay;
	private boolean enable_iceSnowMelt;
	private boolean enable_cropTrampling;
	private boolean enable_waterDamage;
	private boolean enable_physics;
	private boolean enable_TNTblockDamage;
	private boolean enable_blockRegeneration;
	private boolean enable_fallDamage;
	private boolean enable_lavaDamage;
	private boolean enable_fireDamage;
	private boolean enable_lightningDamage;
	private boolean enable_drownDamage;
	private boolean enable_suffocationDamage;
	private boolean enable_contactDamage;
	private boolean enable_voidDamage; //Will teleport player to world spawn on entry to void.
	private boolean enable_explodeDamage;
	private boolean enable_mobDamage;
	private boolean enable_PVP;
	private boolean enable_minecraftDeathMsgs;
	private boolean enable_ropeLadders;
	private boolean enable_creeperBlockDamage;
	private boolean enable_dragonBlockDamage;
	private boolean enable_fireballBlockDamage;
	private boolean enable_zombieDoorDestruction;
	private boolean enable_endermen_Griefing;
	private boolean enable_weatherChange;
	private boolean enable_chunksinMem;
	private boolean enable_naturalMobSpawn;
	private boolean enable_build;
	private boolean enable_staffBuild;
	private boolean enable_Hunger;
	private String name;
	private GameMode defaultgamemode;
	
	public UtilWorld(String name)
	{
		this.name = name;
		this.enable_blockRegeneration = false;
		this.enable_build = false;
		this.enable_staffBuild = true;
		this.enable_chunksinMem = false;
		this.enable_contactDamage = false;
		this.enable_creeperBlockDamage = false;
		this.enable_cropTrampling = false;
		this.enable_dragonBlockDamage = false;
		this.enable_drownDamage = false;
		this.enable_endermen_Griefing = false;
		this.enable_explodeDamage = false;
		this.enable_fallDamage = false;
		this.enable_fireballBlockDamage = false;
		this.enable_fireDamage = false;
		this.enable_Hunger = false;
		this.enable_iceSnowMelt = false;
		this.enable_lavaDamage = false;
		this.enable_leafDecay = false;
		this.enable_lightningDamage = false;
		this.enable_minecraftDeathMsgs = false;
		this.enable_mobDamage = false;
		this.enable_naturalMobSpawn = false;
		this.enable_physics = false;
		this.enable_PVP = false;
		this.enable_ropeLadders = true;
		this.enable_suffocationDamage = false;
		this.enable_TNTblockDamage = false;
		this.enable_voidDamage = false;
		this.enable_waterDamage = false;
		this.enable_weatherChange = false;
		this.enable_zombieDoorDestruction = false;
		this.defaultgamemode = GameMode.SURVIVAL;
	}

	public String getWorldName() 
	{
		return this.name;
	}
	
	public boolean getWorldProperty(WorldProperty property)
	{
		if (property.equals(WorldProperty.BLOCKREGEN))
		{
			return this.enable_blockRegeneration;
		}
		else if (property.equals(WorldProperty.BUILD))
		{
			return this.enable_build;
		}
		else if (property.equals(WorldProperty.CHUNKSINRAM))
		{
			return this.enable_chunksinMem;
		}
		else if (property.equals(WorldProperty.CONTACTDAMAGE))
		{
			return this.enable_contactDamage;
		}
		else if (property.equals(WorldProperty.CREEPERBLOCKDAMAGE))
		{
			return this.enable_creeperBlockDamage;
		}
		else if (property.equals(WorldProperty.CROPTRAMPLING))
		{
			return this.enable_cropTrampling;
		}
		else if (property.equals(WorldProperty.DRAGONBLOCKDAMAGE))
		{
			return this.enable_dragonBlockDamage;
		}
		else if (property.equals(WorldProperty.DROWNDAMAGE))
		{
			return this.enable_drownDamage;
		}
		else if (property.equals(WorldProperty.ENDERMENGRIEFING))
		{
			return this.enable_endermen_Griefing;
		}
		else if (property.equals(WorldProperty.EXPLODEDAMAGE))
		{
			return this.enable_explodeDamage;
		}
		else if (property.equals(WorldProperty.FALLDAMAGE))
		{
			return this.enable_fallDamage;
		}
		else if (property.equals(WorldProperty.FIREBALLBLOCKDAMAGE))
		{
			return this.enable_fireballBlockDamage;
		}
		else if (property.equals(WorldProperty.FIREDAMAGE))
		{
			return this.enable_fireDamage;
		}
		else if (property.equals(WorldProperty.HUNGER))
		{
			return this.enable_Hunger;
		}
		else if (property.equals(WorldProperty.ICESNOWMELT))
		{
			return this.enable_iceSnowMelt;
		}
		else if (property.equals(WorldProperty.LEAFDECAY))
		{
			return this.enable_leafDecay;
		}
		else if (property.equals(WorldProperty.LAVADAMAGE))
		{
			return this.enable_lavaDamage;
		}
		else if (property.equals(WorldProperty.LIGHTNINGDAMAGE))
		{
			return this.enable_lightningDamage;
		}
		else if (property.equals(WorldProperty.NATURALMOBSPAWN))
		{
			return this.enable_naturalMobSpawn;
		}
		else if (property.equals(WorldProperty.MINECRAFTDEATHMSGS))
		{
			return this.enable_minecraftDeathMsgs;
		}
		else if (property.equals(WorldProperty.MOBDAMAGE))
		{
			return this.enable_mobDamage;
		}
		else if (property.equals(WorldProperty.PHYSICS))
		{
			return this.enable_physics;
		}
		else if (property.equals(WorldProperty.PVP))
		{
			return this.enable_PVP;
		}
		else if (property.equals(WorldProperty.ROPELADDERS))
		{
			return this.enable_ropeLadders;
		}
		else if (property.equals(WorldProperty.STAFFBUILD))
		{
			return this.enable_staffBuild;
		}
		else if (property.equals(WorldProperty.SUFFOCATIONDAMAGE))
		{
			return this.enable_suffocationDamage;
		}
		else if (property.equals(WorldProperty.TNTBLOCKDAMAGE))
		{
			return this.enable_TNTblockDamage;
		}
		else if (property.equals(WorldProperty.VOIDDAMAGE))
		{
			return this.enable_voidDamage;
		}
		else if (property.equals(WorldProperty.WATERDAMAGE))
		{
			return this.enable_waterDamage;
		}
		else if (property.equals(WorldProperty.WEATHERCHANGE))
		{
			return this.enable_weatherChange;
		}
		else if (property.equals(WorldProperty.ZOMBIEDOORDAMAGE))
		{
			return this.enable_zombieDoorDestruction;
		}
		else
		{
			return false;
		}
	}
	
	public void setWorldProperty(WorldProperty property, boolean enable)
	{
		if (property.equals(WorldProperty.BLOCKREGEN))
		{
			this.enable_blockRegeneration = enable;
		}
		else if (property.equals(WorldProperty.BUILD))
		{
			this.enable_build = enable;
		}
		else if (property.equals(WorldProperty.CHUNKSINRAM))
		{
			this.enable_chunksinMem = enable;
		}
		else if (property.equals(WorldProperty.CONTACTDAMAGE))
		{
			this.enable_contactDamage = enable;
		}
		else if (property.equals(WorldProperty.CREEPERBLOCKDAMAGE))
		{
			this.enable_creeperBlockDamage = enable;
		}
		else if (property.equals(WorldProperty.CROPTRAMPLING))
		{
			this.enable_cropTrampling = enable;
		}
		else if (property.equals(WorldProperty.DRAGONBLOCKDAMAGE))
		{
			this.enable_dragonBlockDamage = enable;
		}
		else if (property.equals(WorldProperty.DROWNDAMAGE))
		{
			this.enable_drownDamage = enable;
		}
		else if (property.equals(WorldProperty.ENDERMENGRIEFING))
		{
			this.enable_endermen_Griefing = enable;
		}
		else if (property.equals(WorldProperty.EXPLODEDAMAGE))
		{
			this.enable_explodeDamage = enable;
		}
		else if (property.equals(WorldProperty.FALLDAMAGE))
		{
			this.enable_fallDamage = enable;
		}
		else if (property.equals(WorldProperty.FIREBALLBLOCKDAMAGE))
		{
			this.enable_fireballBlockDamage = enable;
		}
		else if (property.equals(WorldProperty.FIREDAMAGE))
		{
			this.enable_fireDamage = enable;
		}
		else if (property.equals(WorldProperty.ICESNOWMELT))
		{
			this.enable_iceSnowMelt = enable;
		}
		else if (property.equals(WorldProperty.HUNGER))
		{
			this.enable_Hunger = enable;
		}
		else if (property.equals(WorldProperty.LEAFDECAY))
		{
			this.enable_leafDecay = enable;
		}
		else if (property.equals(WorldProperty.LAVADAMAGE))
		{
			this.enable_lavaDamage = enable;
		}
		else if (property.equals(WorldProperty.LIGHTNINGDAMAGE))
		{
			this.enable_lightningDamage = enable;
		}
		else if (property.equals(WorldProperty.NATURALMOBSPAWN))
		{
			this.enable_naturalMobSpawn = enable;
		}
		else if (property.equals(WorldProperty.MINECRAFTDEATHMSGS))
		{
			this.enable_minecraftDeathMsgs = enable;
		}
		else if (property.equals(WorldProperty.MOBDAMAGE))
		{
			this.enable_mobDamage = enable;
		}
		else if (property.equals(WorldProperty.PHYSICS))
		{
			this.enable_physics = enable;
		}
		else if (property.equals(WorldProperty.PVP))
		{
			this.enable_PVP = enable;
		}
		else if (property.equals(WorldProperty.ROPELADDERS))
		{
			this.enable_ropeLadders = enable;
		}
		else if (property.equals(WorldProperty.STAFFBUILD))
		{
			this.enable_staffBuild = enable;
		}
		else if (property.equals(WorldProperty.SUFFOCATIONDAMAGE))
		{
			this.enable_suffocationDamage = enable;
		}
		else if (property.equals(WorldProperty.TNTBLOCKDAMAGE))
		{
			this.enable_TNTblockDamage = enable;
		}
		else if (property.equals(WorldProperty.VOIDDAMAGE))
		{
			this.enable_voidDamage = enable;
		}
		else if (property.equals(WorldProperty.WATERDAMAGE))
		{
			this.enable_waterDamage = enable;
		}
		else if (property.equals(WorldProperty.WEATHERCHANGE))
		{
			this.enable_weatherChange = enable;
		}
		else if (property.equals(WorldProperty.ZOMBIEDOORDAMAGE))
		{
			this.enable_zombieDoorDestruction = enable;
		}
		else
		{
			return;
		}
	}
	
	public GameMode getDefaultGamemode()
	{
		return this.defaultgamemode;
	}
	
	public void setDefaultGamemode(GameMode gamemode)
	{
		this.defaultgamemode = gamemode;
	}

}
