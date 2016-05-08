package com.tevonetwork.tevoapi.Commands;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UUIDFetcher;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;

public class NameCheckCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if ((sender.isOp()) || (sender instanceof ConsoleCommandSender))
		{
			if (args.length > 0)
			{
				String opname = args[0];
				UUID opuuid = UUIDFetcher.getUUID(opname);
				if (opuuid != null)
				{
					JSONArray previousnames = getPreviousNames(opuuid);

					
					CategoryMSG.senderHeader(sender, Category.UTILS, "Name Check History for " + CC.tnPlayer + opname);
					
					
					@SuppressWarnings("unchecked")
					Iterator<JSONObject> iterator = previousnames.iterator();
				
					while (iterator.hasNext())
					{
						JSONObject object = iterator.next();
						String DateString = "";
						if (object.get("changedToAt") != null)
						{
							long dateL = ((Long)object.get("changedToAt")).longValue();
							DateString = displayDate(Long.valueOf(dateL));
							sender.sendMessage("");
							sender.sendMessage("§eSince: " + DateString + ":");
							sender.sendMessage(ChatColor.GRAY + (String)object.get("name"));
							sender.sendMessage("");
						}
						else
						{
							sender.sendMessage("");
							sender.sendMessage("§eOriginal name:");
							sender.sendMessage(ChatColor.GRAY + (String)object.get("name"));
						}
					}
					CategoryMSG.senderFooter(sender);
				}
				else
				{
					CategoryMSG.senderMessage(sender, Category.UTILS, CC.tnError + "Invalid Player!");
				}
			}
			else
			{
				CategoryMSG.senderArgsErr(sender, Category.UTILS, "/namecheck <player>");
			}
			
		}
		else
		{
			PermMSG.noPerm(sender, Rank.MODERATOR);
		}
			
		
		return false;
	}

	private static JSONArray getPreviousNames(UUID uniqueId) {
		
		JSONParser parser = new JSONParser();
		JSONArray array = null;
		try
		{
			HttpURLConnection connection = (HttpURLConnection)new URL("https://api.mojang.com/user/profiles/" + uniqueId.toString().replace("-", "") + "/names").openConnection();
			array = (JSONArray)parser.parse(new InputStreamReader(connection.getInputStream()));
		}
		catch(Exception exception)
		{
			
		}
		return array;
		
	}
	
	public String displayDate(Long date)
	{
		Date timestamp = new Date(date.longValue());
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(timestamp);
	    int year = cal.get(1);
	    int month = cal.get(2);
	    int day = cal.get(5);
	    String month1 = "error";
	    if (month == 0) {
	      month1 = "Jan";
	    } else if (month == 1) {
	      month1 = "Feb";
	    } else if (month == 2) {
	      month1 = "Mar";
	    } else if (month == 3) {
	      month1 = "Apr";
	    } else if (month == 4) {
	      month1 = "May";
	    } else if (month == 5) {
	      month1 = "Jun";
	    } else if (month == 6) {
	      month1 = "Jul";
	    } else if (month == 7) {
	      month1 = "Aug";
	    } else if (month == 8) {
	      month1 = "Sep";
	    } else if (month == 9) {
	      month1 = "Oct";
	    } else if (month == 10) {
	      month1 = "Nov";
	    } else if (month == 11) {
	      month1 = "Dec";
	    }
	    
	    return year + "-" + month1 + "-" + day;
	}
	
	

	
	
}
