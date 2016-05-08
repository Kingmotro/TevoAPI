package com.tevonetwork.tevoapi.API.Util;

import java.util.UUID;

public class UtilUUID {

	public static String toString(UUID uuid)
	{
		return uuid.toString().replace("-", "");
	}
	
}
