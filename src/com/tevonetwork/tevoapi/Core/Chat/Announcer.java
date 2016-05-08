package com.tevonetwork.tevoapi.Core.Chat;

import java.util.ArrayList;
import java.util.List;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.Messages.AnnounceMSG;

public class Announcer implements Runnable {

	private static List<String> announcements = new ArrayList<String>();
	private static TevoAPI main = TevoAPI.getInstance();
	private static int counter = 0;

	public Announcer() {
		announcements = main.getConfigManager().getConfig().getStringList("announcer");
	}

	@Override
	public void run() {
		if (announcements.size() <= 0) {
			return;
		}
		AnnounceMSG.toServer(announcements.get(counter));
		counter++;
		if (counter >= announcements.size()) {
			counter = 0;
		}
	}

	public static void reload() {
		announcements = main.getConfigManager().getConfig().getStringList("announcer");
		main.getUtilLogger().logNormal("Announcer> Reloaded!");
	}
}
