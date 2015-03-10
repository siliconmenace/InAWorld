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
import java.util.Vector;

public class LocationObjects implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1851139752077628362L;
	/**
     * 
     */

	private Vector<LocationObject>	objects	= new Vector<LocationObject>();

	public LocationObjects()
	{
	}

	public void add( LocationObject lo )
	{
		objects.addElement( lo );
	}

	public void remove( LocationObject lo )
	{
		if ( objects.contains( lo ) )
		{
			objects.removeElement( lo );
		}
	}

	@SuppressWarnings("unchecked")
	public Vector<LocationObject> list()
	{
		return ( Vector<LocationObject> ) objects.clone();
	}


	public LocationObject get( String obj )
	{
		for(LocationObject lo : objects){
			if(lo.getTitle().getText().equalsIgnoreCase(  obj )){
				objects.remove( lo );
				return lo.getInteractiveObject();
			}
		}
		return null;
	}


}
