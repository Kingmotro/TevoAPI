package com.tevonetwork.tevoapi.Core.Networking;

import java.util.HashMap;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Math.TimeUtils;

public class ServerPlayerCounter implements Runnable {

	private static HashMap<String, Integer> playercount = new HashMap<String, Integer>();
	private static HashMap<String, String> motds = new HashMap<String, String>();
	private static String[] servers;
	private static boolean listupdated = false;
	private static long last_list_update;
	private TevoAPI main = TevoAPI.getInstance();

	@Override
	public void run() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		if (servers != null) {
			for (String server : servers) {
				out.writeUTF("PlayerCount");
				out.writeUTF(server);

				new BungeeMessageTask(out.toByteArray()).runTaskAsynchronously(main);
			}
		}

	}

	public static int getPlayerCount(String servername) {
		if (playercount.containsKey(servername)) {
			return playercount.get(servername);
		}
		return 0;
	}

	public static void updateServerCount(String server, int players) {
		playercount.put(server, Integer.valueOf(players));
	}

	public static void updateServerMOTD(String server, String motd) {
		motds.put(server, motd);
	}

	public static void requestServerList() {
		if ((!listupdated) || (TimeUtils.hasElapsed(last_list_update, 600000L))) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("GetServers");

			new BungeeMessageTask(out.toByteArray()).runTaskAsynchronously(TevoAPI.getInstance());
		}
	}

	public static void updateServerList(String[] serverlist) {
		servers = serverlist;
		listupdated = true;
		TevoAPI.getInstance().getUtilLogger().logNormal("Networking> Server list updated!");
		last_list_update = System.currentTimeMillis();
	}

}
