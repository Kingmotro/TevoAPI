package com.tevonetwork.tevoapi.Core.Vote;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer.playerSounds;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Economy.EconomyManager;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class VoteMessenger {

	public static void sendVoteMSG(String playername, int type)
	{
		Player p = Bukkit.getPlayerExact(playername);
		if ((p == null) || (!p.isOnline()))
		{
			return;
		}
		if (type == 0)
		{
			TextComponent prefix = new TextComponent(TextComponent.fromLegacyText(CategoryMSG.getMSGPrefix(Category.VOTE)));
			TextComponent msg = new TextComponent(TextComponent.fromLegacyText(CC.tnInfo + CC.fBold + "Hey there " + CC.tnPlayer + CC.fBold + p.getName() + CC.tnInfo + CC.fBold + "! There are " + CC.tnValue + CC.fBold + "200 Tevo Tokens " + CC.tnInfo + CC.fBold + "waiting for you! "));
			TextComponent link = new TextComponent(TextComponent.fromLegacyText(CC.tnValue + CC.fBold + "Click here to vote!"));
			link.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, TextComponent.fromLegacyText(CC.tnInfo + "Click on this to head over to the voting page!")));
			link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://vote.tevonetwork.com"));
			prefix.addExtra(msg);
			prefix.addExtra(link);
			p.spigot().sendMessage(prefix);
			p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 3F, 1.2F);
		}
		if (type == 1)
		{
			UtilPlayer.message(Category.VOTE, p, CC.tnInfo + "Thanks for voting and supporting the network!");
			EconomyManager.addTokens(p, 200);
			UtilPlayer.transaction(p, 200, "voting", null);
			UtilPlayer.sound(p, playerSounds.TRANSACTIONSUCCESS);
			TevoAPI.getInstance().getUtilLogger().logNormal("VoteMessenger> Crediting " + playername + ".");
		}
		return;
	}
	
}
