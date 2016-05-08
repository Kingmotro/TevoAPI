package com.tevonetwork.tevoapi.API.Titles;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {

	private String text;
	
	public ActionBar() {}
	
	public ActionBar(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void send(Player p)
	{
		PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(this.text), (byte)2);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
}
