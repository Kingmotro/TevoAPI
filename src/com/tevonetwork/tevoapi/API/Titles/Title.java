package com.tevonetwork.tevoapi.API.Titles;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title {

	private String title;
	private String subtitle;
	private int fadeIn = -1;
	private int stay = -1;
	private int fadeOut = -1;
	
	public Title(){}
	
	public Title(String title)
	{
		this.title = title;
	}
	
	public Title(String title, int fadeIn, int stay, int fadeOut)
	{
		this.title = title;
		this.fadeIn  = fadeIn;
		this.stay = stay;
		this.fadeOut = fadeOut;
	}
	
	public Title(String title, String subtitle)
	{
		this.title = title;
		this.subtitle = subtitle;
	}
	
	public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut)
	{
		this.title = title;
		this.subtitle = subtitle;
		this.fadeIn  = fadeIn;
		this.stay = stay;
		this.fadeOut = fadeOut;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setSubtitle(String subtitle)
	{
		this.subtitle = subtitle;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getSubtitle()
	{
		return this.subtitle;
	}
	
	public int getStay()
	{
		return this.stay;
	}
	
	public int getfadeOut()
	{
		return this.fadeOut;
	}
	
	public int getfadeIn()
	{
		return this.fadeIn;
	}
	
	public void setfadeOut(int ticks)
	{
		this.fadeOut = ticks;
	}
	
	public void setfadeIn(int ticks)
	{
		this.fadeIn = ticks;
	}
	
	public void setStay(int ticks)
	{
		this.stay = ticks;
	}
	
	public void send(Player p)
	{
		PacketPlayOutTitle timespacket = new PacketPlayOutTitle(this.fadeIn, this.stay, this.fadeOut);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(timespacket);
		if (this.title != null)
		{
			ChatComponentText component = new ChatComponentText(this.title);
			PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, component);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(titlepacket);
		}
		if (this.subtitle != null)
		{
			ChatComponentText component = new ChatComponentText(this.subtitle);
			PacketPlayOutTitle subtitlepacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, component);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(subtitlepacket);
		}
		
	}
	
}
