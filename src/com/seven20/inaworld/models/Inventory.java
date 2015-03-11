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

public class Inventory implements Serializable
{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= -296933493056628216L;
	/**
	 * 
	 */

	private Vector<LocationObject>	objects				= new Vector<LocationObject>();

	public Inventory()
	{
	}

	public void add( LocationObject lo )
	{
		objects.addElement( lo );
	}

	public LocationObject remove( LocationObject lo )
	{
		LocationObject removedItem = null;
		if ( objects.contains( lo ) )
		{
			removedItem = objects.remove( objects.indexOf(lo));
		}
		return removedItem;
	}
	
	public LocationObject remove( String obj )
	{
		return get(obj);
	}

	@SuppressWarnings("unchecked")
	public Vector<LocationObject> list()
	{
		return ( Vector<LocationObject> ) objects.clone();
	}

	public LocationObject get( String obj )
	{
		for ( LocationObject lo : objects )
		{
			if ( lo.getTitle().getText().equalsIgnoreCase( obj ) )
			{
				//objects.remove( lo );
				return lo.getInteractiveObject();
			}
		}
		return null;
	}

	public void examine( String obj )
	{
		try
		{
			System.out.println( get( obj ).getDescription() );
		} catch( Exception e )
		{
			System.out.println( "You don't have the " + obj );
		}
	}

	public LocationObject drop(String _object) {
	   
	    LocationObject lo = get(_object); 
	    if ( lo == null) {
		System.out.println("You don't have the " + _object);
		return null;
	    } else {
		System.out.println("You dropped the " + lo.getTitle());
		return remove(lo);
		
	    }
	}

	

}
