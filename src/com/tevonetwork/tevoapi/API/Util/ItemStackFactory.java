package com.tevonetwork.tevoapi.API.Util;

import java.util.List;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.tevonetwork.tevoapi.Core.Items;
import com.tevonetwork.tevoapi.Core.common.JavaCommon;

public class ItemStackFactory {
	
	public enum Armor
	{
		HELMET, CHESTPLATE, LEGGINGS, BOOTS;
	}

	public ItemStack createItemStack(Items item, String dispname)
	{
		ItemStack is = new ItemStack(Items.getItemStack(item).getType(), 1, Items.getItemStack(item).getDurability());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(dispname);
		is.setItemMeta(im);
		
		return is;
	}
	
	public ItemStack createItemStackwithLore(Items item, String dispname, String[] lore)
	{
		List<String> lorelist = JavaCommon.ConvertArraytoList(lore);
		
		ItemStack is = new ItemStack(Items.getItemStack(item).getType(), 1, Items.getItemStack(item).getDurability());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(dispname);
		im.setLore(lorelist);
		is.setItemMeta(im);
		
		return is;
	}

	public ItemStack createItemStackwithEnchantments(Items item, Map<Enchantment, Integer> enchantments)
	{
		ItemStack is = new ItemStack(Items.getItemStack(item).getType(), 1, Items.getItemStack(item).getDurability());
		ItemMeta im = is.getItemMeta();
		is.addEnchantments(enchantments);
		is.setItemMeta(im);
		
		return is;
	}
	
	public ItemStack createItemStackwithEnchantments(Items item, String dispname, Map<Enchantment, Integer> enchantments)
	{
		ItemStack is = new ItemStack(Items.getItemStack(item).getType(), 1, Items.getItemStack(item).getDurability());
		ItemMeta im = is.getItemMeta();
		is.addEnchantments(enchantments);
		im.setDisplayName(dispname);
		is.setItemMeta(im);
		
		return is;
	}
	
	public ItemStack createItemStackwithEnchantments(String dispname, Items item, String[] lore, Map<Enchantment, Integer> enchantments)
	{
		List<String> lorelist = JavaCommon.ConvertArraytoList(lore);
		
		ItemStack is = new ItemStack(Items.getItemStack(item).getType(), 1, Items.getItemStack(item).getDurability());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(dispname);
		im.setLore(lorelist);
		is.addEnchantments(enchantments);
		is.setItemMeta(im);
		
		return is;
	}
	
	public ItemStack createItemStackwithGlow(String dispname, Items item)
	{
		ItemStack is = new ItemStack(Items.getItemStack(item).getType(), 1, Items.getItemStack(item).getDurability());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(dispname);
		im.addEnchant(Enchantment.FIRE_ASPECT, 25, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		is.setItemMeta(im);
		
		return is;
	}
	
	public ItemStack createItemStackwithGlowandLore(String dispname, Items item, String[] lore)
	{
		List<String> lorelist = JavaCommon.ConvertArraytoList(lore);
		
		ItemStack is = new ItemStack(Items.getItemStack(item).getType(), 1, Items.getItemStack(item).getDurability());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(dispname);
		im.setLore(lorelist);
		im.addEnchant(Enchantment.FIRE_ASPECT, 25, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		is.setItemMeta(im);
		
		return is;
	}
	
	public ItemStack createItemStackPlayerHead(String dispname, String owner)
	{
		ItemStack is = new ItemStack(Items.getItemStack(Items.MOBHEADPLAYER).getType(), 1, Items.getItemStack(Items.MOBHEADPLAYER).getDurability());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(dispname);
		((SkullMeta)im).setOwner(owner);
		is.setItemMeta(im);
		return is;
	}
	
	public ItemStack createItemStackPlayerHeadwithLore(String dispname, String owner, String[] lore)
	{
		List<String> lorelist = JavaCommon.ConvertArraytoList(lore);
		
		ItemStack is = new ItemStack(Items.getItemStack(Items.MOBHEADPLAYER).getType(), 1, Items.getItemStack(Items.MOBHEADPLAYER).getDurability());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(dispname);
		im.setLore(lorelist);
		((SkullMeta)im).setOwner(owner);
		is.setItemMeta(im);
		return is;
	}
	
	public ItemStack createLeatherArmor(Armor part, Color color)
	{
		ItemStack is = null;
		if (part == Armor.HELMET)
		{
			is = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			is.setItemMeta(meta);
		}
		if (part == Armor.CHESTPLATE)
		{
			is = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			is.setItemMeta(meta);
		}
		if (part == Armor.LEGGINGS)
		{
			is = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			is.setItemMeta(meta);
		}
		if (part == Armor.BOOTS)
		{
			is = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			is.setItemMeta(meta);
		}
		return is;
	}
	
	public ItemStack createLeatherArmor(Armor part, Color color, String dispname)
	{
		ItemStack is = null;
		if (part == Armor.HELMET)
		{
			is = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			meta.setDisplayName(dispname);
			is.setItemMeta(meta);
		}
		if (part == Armor.CHESTPLATE)
		{
			is = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			meta.setDisplayName(dispname);
			is.setItemMeta(meta);
		}
		if (part == Armor.LEGGINGS)
		{
			is = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			meta.setDisplayName(dispname);
			is.setItemMeta(meta);
		}
		if (part == Armor.BOOTS)
		{
			is = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			meta.setDisplayName(dispname);
			is.setItemMeta(meta);
		}
		return is;
	}
	
	public ItemStack createLeatherArmor(Armor part, Color color, String dispname, String[] lore)
	{
		ItemStack is = null;
		List<String> lorelist = JavaCommon.ConvertArraytoList(lore);
		if (part == Armor.HELMET)
		{
			is = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			meta.setDisplayName(dispname);
			meta.setLore(lorelist);
			is.setItemMeta(meta);
		}
		if (part == Armor.CHESTPLATE)
		{
			is = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			meta.setDisplayName(dispname);
			meta.setLore(lorelist);
			is.setItemMeta(meta);
		}
		if (part == Armor.LEGGINGS)
		{
			is = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			meta.setDisplayName(dispname);
			meta.setLore(lorelist);
			is.setItemMeta(meta);
		}
		if (part == Armor.BOOTS)
		{
			is = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta meta = (LeatherArmorMeta)is.getItemMeta();
			meta.setColor(color);
			meta.setDisplayName(dispname);
			meta.setLore(lorelist);
			is.setItemMeta(meta);
		}
		return is;
	}
	
}
