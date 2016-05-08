package com.tevonetwork.tevoapi.API.Portals;

import com.tevonetwork.tevoapi.API.Regions.Region;

public class Portal {

	private String name;
	private Region portalregion;
	private String portaldestination;
	private boolean enabled;
	
	public Portal(String name, String destination, Region region)
	{
		this.name = name;
		this.portaldestination = destination;
		this.portalregion = region;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public void setDestination(String destination)
	{
		this.portaldestination = destination;
	}
	
	public boolean isEnabled()
	{
		return this.enabled;
	}
	
	public Region getRegion()
	{
		return this.portalregion;
	}
	
	public String getDestination()
	{
		return this.portaldestination;
	}
	
	public String getName()
	{
		return this.name;
	}
}
