package com.tevonetwork.tevoapi.Core.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RemoteServerInfo {

	private String host;
	private int port;
	
	/**
	 * Connect to Server with specified address and port.
	 * 
	 * @param host
	 * @param port
	 */
	public RemoteServerInfo(String host, int port)
	{
		this.host = host;
		this.port = port;
	}
	
	/**
	 * Connect to Server with specified address using default port (25565).
	 * 
	 * @param host
	 */
	public RemoteServerInfo(String host)
	{
		this.host = host;
		this.port = 25565;
	}
	
	public RemoteServerInfo()
	{
		this.host = "127.0.0.1";
		this.port = 25565;
	}
	
	
	public String parseData(Connection connection)
	{
		try{
			@SuppressWarnings("resource")
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(host, port), 2500);
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			out.write(0xFE);
			
			StringBuilder str = new StringBuilder();
			
			int b;
			
			while ((b = in.read()) != -1){
				if (b != 0 && b > 16 && b != 255 && b !=23 && b != 24){
					str.append((char) b);
				}
			}
			
			String[] data = str.toString().split("§");
			
			
			if (connection == Connection.ONLINE_COUNT)
			{
				return data[1];
			}
			else if (connection == Connection.MOTD)
			{
				return data[0];
			}else
			{
				System.out.println("Value not handled by TevoAPI! Request for it to be added?");
				return "Invalid Value, Contact Developer!";
			}
			
		}catch (Exception e)
		{
			return "Connection Failed, Contact Developer!";
		}
		
	}
	
	public enum Connection
	{
		ONLINE_COUNT, MOTD;
	}
	
}
