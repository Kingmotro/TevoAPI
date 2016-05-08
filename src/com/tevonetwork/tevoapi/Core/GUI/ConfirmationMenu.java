package com.tevonetwork.tevoapi.Core.GUI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.ItemStackFactory;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer.playerSounds;
import com.tevonetwork.tevoapi.Core.Items;

public class ConfirmationMenu {

	private Inventory inv;
	private ItemStackFactory isf;
	private String description;
	private Player p;

	public ConfirmationMenu() {
		this.isf = new ItemStackFactory();
		this.inv = Bukkit.createInventory(null, 9, "Confirmation");
		this.inv.setItem(0, this.isf.createItemStack(Items.EMERALDBLOCK, CC.cGreen + "CONFIRM"));
		this.inv.setItem(8, this.isf.createItemStack(Items.REDSTONEBLOCK, CC.cRED + "CANCEL"));
	}

	public void setDescription(String description) {
		this.description = CC.tnInfo + description;
		this.inv.setItem(4, this.isf.createItemStack(Items.PAPER, this.description));
	}

	public void open(Player player) {
		this.p = player;
		this.p.openInventory(this.inv);
		UtilPlayer.sound(this.p, playerSounds.TPAREQUEST);
	}

}
