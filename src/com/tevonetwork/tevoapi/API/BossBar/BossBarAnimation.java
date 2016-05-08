package com.tevonetwork.tevoapi.API.BossBar;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BossBarAnimation implements Runnable{
	
	private int frame = 0;
	
	@Override
	public void run()
	{
		for (Player players : Bukkit.getOnlinePlayers())
		{
			BossBarAPI.setMessage(players, frames.get(frame));
		}
		frame++;
		if (frame >= frames.size())
		{
			frame = 0;
		}
	}
	
	
	private List<String> frames = Arrays.asList(
			"�e�lMC.TEVONETWORK.COM",//YELLOW
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",//START
			"�6�lM�e�lC.TEVONETWORK.COM",
			"�6�lMC�e�l.TEVONETWORK.COM",
			"�6�lMC.�e�lTEVONETWORK.COM",
			"�6�lMC.T�e�lEVONETWORK.COM",
			"�6�lMC.TE�e�lVONETWORK.COM",
			"�6�lMC.TEV�e�lONETWORK.COM",
			"�6�lMC.TEVO�e�lNETWORK.COM",
			"�6�lMC.TEVON�e�lETWORK.COM",
			"�6�lMC.TEVONE�e�lTWORK.COM",
			"�6�lMC.TEVONET�e�lWORK.COM",
			"�6�lMC.TEVONETW�e�lORK.COM",
			"�6�lMC.TEVONETWO�e�lRK.COM",
			"�6�lMC.TEVONETWOR�e�lK.COM",
			"�6�lMC.TEVONETWORK�e�l.COM",
			"�6�lMC.TEVONETWORK.�e�lCOM",
			"�6�lMC.TEVONETWORK.C�e�lOM",
			"�6�lMC.TEVONETWORK.CO�e�lM",
			"�6�lMC.TEVONETWORK.COM",//GOLD
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",
			"�6�lMC.TEVONETWORK.COM",//START
			"�e�lM�6�lC.TEVONETWORK.COM",
			"�e�lMC�6�l.TEVONETWORK.COM",
			"�e�lMC.�6�lTEVONETWORK.COM",
			"�e�lMC.T�6�lEVONETWORK.COM",
			"�e�lMC.TE�6�lVONETWORK.COM",
			"�e�lMC.TEV�6�lONETWORK.COM",
			"�e�lMC.TEVO�6�lNETWORK.COM",
			"�e�lMC.TEVON�6�lETWORK.COM",
			"�e�lMC.TEVONE�6�lTWORK.COM",
			"�e�lMC.TEVONET�6�lWORK.COM",
			"�e�lMC.TEVONETW�6�lORK.COM",
			"�e�lMC.TEVONETWO�6�lRK.COM",
			"�e�lMC.TEVONETWOR�6�lK.COM",
			"�e�lMC.TEVONETWORK�6�l.COM",
			"�e�lMC.TEVONETWORK.�6�lCOM",
			"�e�lMC.TEVONETWORK.C�6�lOM",
			"�e�lMC.TEVONETWORK.CO�6�lM",
			"�e�lMC.TEVONETWORK.COM",//YELLOW
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",
			"�e�lMC.TEVONETWORK.COM",//START
			"�f�lM�e�lC.TEVONETWORK.COM",
			"�f�lMC�e�l.TEVONETWORK.COM",
			"�f�lMC.�e�lTEVONETWORK.COM",
			"�f�lMC.T�e�lEVONETWORK.COM",
			"�f�lMC.TE�e�lVONETWORK.COM",
			"�f�lMC.TEV�e�lONETWORK.COM",
			"�f�lMC.TEVO�e�lNETWORK.COM",
			"�f�lMC.TEVON�e�lETWORK.COM",
			"�f�lMC.TEVONE�e�lTWORK.COM",
			"�f�lMC.TEVONET�e�lWORK.COM",
			"�f�lMC.TEVONETW�e�lORK.COM",
			"�f�lMC.TEVONETWO�e�lRK.COM",
			"�f�lMC.TEVONETWOR�e�lK.COM",
			"�f�lMC.TEVONETWORK�e�l.COM",
			"�f�lMC.TEVONETWORK.�e�lCOM",
			"�f�lMC.TEVONETWORK.C�e�lOM",
			"�f�lMC.TEVONETWORK.CO�e�lM",
			"�f�lMC.TEVONETWORK.COM",//WHITE
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",
			"�f�lMC.TEVONETWORK.COM",//START
			"�e�lM�f�lC.TEVONETWORK.COM",
			"�e�lMC�f�l.TEVONETWORK.COM",
			"�e�lMC.�f�lTEVONETWORK.COM",
			"�e�lMC.T�f�lEVONETWORK.COM",
			"�e�lMC.TE�f�lVONETWORK.COM",
			"�e�lMC.TEV�f�lONETWORK.COM",
			"�e�lMC.TEVO�f�lNETWORK.COM",
			"�e�lMC.TEVON�f�lETWORK.COM",
			"�e�lMC.TEVONE�f�lTWORK.COM",
			"�e�lMC.TEVONET�f�lWORK.COM",
			"�e�lMC.TEVONETW�f�lORK.COM",
			"�e�lMC.TEVONETWO�f�lRK.COM",
			"�e�lMC.TEVONETWOR�f�lK.COM",
			"�e�lMC.TEVONETWORK�f�l.COM",
			"�e�lMC.TEVONETWORK.�f�lCOM",
			"�e�lMC.TEVONETWORK.C�f�lOM",
			"�e�lMC.TEVONETWORK.CO�f�lM");
	
}
