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
package com.seven20.inaworld.controller;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Vector;

import com.seven20.inaworld.io.WidthLimitedOutputStream;
import com.seven20.inaworld.models.Exit;
import com.seven20.inaworld.models.Inventory;
import com.seven20.inaworld.models.Location;
import com.seven20.inaworld.models.LocationObject;
//
//
// InAWorld
//
// Last modification date : November 13, 1997
//

public class InAWorld implements Serializable
{
	// List of Location objects
	private Vector								locations;

	// List of Exit objects
	private Vector								exits;

	// The current location of the player
	private Location							currentLocation;

	private Inventory							inventory;

	// Character width for descriptions
	private int									charWidth;

	// Output stream for gaming system
	transient private WidthLimitedOutputStream	output;

	// InAWorld constructor
	public InAWorld()
	{
		// Instantiate vector lists for location/exits
		locations = new Vector();
		exits = new Vector();

		// The default location of a player isn't known
		currentLocation = null;

		// By default, use standard output
		setOutputStream( System.out, 80 );
	}

	// InAWorld constructor
	public InAWorld( int characterWidth )
	{
		// Call default constructor
		this();

		charWidth = characterWidth;
	}

	/** Returns the current location of the player */
	public Location getCurrentLocation()
	{
		return currentLocation;
	}

	/** Assigns a new location to the current location of the player */
	public void setCurrentLocation( Location newLocation )
	{
		currentLocation = newLocation;
	}

	/** Adds a new exit to the gaming system */
	public void addExit( Exit exit )
	{
		// Check if exit vector already contains exit
		if ( !exits.contains( exit ) )
			// Exit doesn't exist, and must be added
			exits.addElement( exit );
	}

	/** Adds a new location to the gaming system */
	public void addLocation( Location location )
	{
		// Check if location vector already contains location
		if ( !locations.contains( location ) )
			// Location doesn't exist, and must be added
			locations.addElement( location );
	}

	/** Sets the output stream for the gaming systewm */
	public void setOutputStream( OutputStream out, int width )
	{
		output = new WidthLimitedOutputStream( out, width );
	}

	/** Shows the current game location */
	public void showLocation()
	{
		// Show title
		output.println( currentLocation.getTitle() );

		// Show description
		output.println( currentLocation.getDescription() );

		output.println();
		if(currentLocation.getLocationObjects().size() > 0)
		{
			output.println( "You can see " + currentLocation.getLocationObjects().size() + " items :" );
		}
		
		for ( LocationObject e : currentLocation.getLocationObjects() )
		{
			output.println( e.toString() );

		}

		output.println();

		// Show available exits
		output.println( "Available exits :" );

		// Traverse elements of vector
		for ( Exit e : currentLocation.getExits() )
		{
			// Print exit to our output stream
			output.println( e.toString() );
		}
	}

}
