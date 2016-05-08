package com.tevonetwork.tevoapi.Core.common;

import java.util.ArrayList;
import java.util.List;

import com.tevonetwork.tevoapi.API.Util.CC;

public class JavaCommon {

	public static List<String> ConvertArraytoList(String [] stringarray)
	{
		List<String> stringlist = new ArrayList<String>();
		for (int index = 0; index < stringarray.length; index++)
		{
			String string = stringarray[index];
			stringlist.add(string);
		}
		return stringlist;
	}
	
	public static String booleantoEnabledDisabled(boolean value)
	{
		if (value)
		{
			return CC.tnEnable + "Enabled";
		}
		else
		{
			return CC.tnDisable + "Disabled";
		}
	}
	
	public static String convertEnum(String enumstring)
	{
		StringBuilder builder = new StringBuilder();
		if (enumstring.toUpperCase().contains("_"))
		{
			String[] array = enumstring.toUpperCase().split("_");
			for (int index = 0; index < array.length; index++)
			{
				if (index == 0)
				{
					builder.append(array[index].substring(0, 1) + array[index].substring(1, array[index].length()).toLowerCase());
				}
				else
				{
					builder.append(" " + array[index].substring(0, 1) + array[index].substring(1, array[index].length()).toLowerCase());
				}
			}
		}
		else
		{
			builder.append(enumstring.toUpperCase().substring(0, 1) + enumstring.toLowerCase().substring(1, enumstring.length()));
		}
		return builder.toString();
	}
	
}
