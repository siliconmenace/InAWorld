/*
 * COPYRIGHT:
 *
 *   The computer systems, procedures, data bases and programs
 *   created and maintained by DST Systems, Inc., are proprietary
 *   in nature and as such are confidential.  Any unauthorized
 *   use or disclosure of such information may result in civil
 *   liabilities.
 *
 *   Copyright 2015 by DST Systems, Inc.
 *   All Rights Reserved.
 */
package com.seven20.inaworld.models;

import java.io.Serializable;

public class LocationObject implements Interactive, Serializable
{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2352183034698460995L;
	private Title title;
	private Description description;
	private Weight weight;
	private ObjectId objectId;

	
	public LocationObject(String _title, String _desc, int _weight){
		title = new Title(_title);
		description = new Description( _desc );
		weight = new Weight(_weight );
		objectId = new ObjectId();
	}

	public Title getTitle()
	{
		return title;
	}

	public Description getDescription()
	{
		return description;
	}

	public Weight getWeight()
	{
		return weight;
	}
	
	public Double getObjectId(){
		return objectId.getId();
	}
	
	public String toString(){
		return title.toString();
	}

	@Override
	public LocationObject getInteractiveObject( )
	{
		return this;
	}
}

