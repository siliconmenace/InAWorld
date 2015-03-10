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
import java.util.Deque;
import java.util.Vector;

import static com.seven20.inaworld.enums.CommandEnum.*;

import com.seven20.inaworld.enums.CommandEnum;
import com.seven20.inaworld.exceptions.ExitNotFoundException;

public class Location implements Serializable
{
	/**
     * 
     */
	private static final long	serialVersionUID	= -2083704260875987644L;
	private Title				title;
	private Description			description;
	private LocationObjects		objects				= new LocationObjects();
	private Exits				exits				= new Exits();

	public Location( String _title, String _description )
	{
		title = new Title( _title );
		description = new Description( _description );
	}

	public String getTitle()
	{
		return title.getTitle();
	}

	public String getDescription()
	{
		return description.getDescription();
	}

	public void addExit( Exit _exit )
	{
		exits.add( _exit );
	}

	public void removeExit( Exit _exit )
	{
		exits.remove( _exit );
	}

	public Vector<Exit> getExits()
	{
		return exits.list();
	}

	public Exit getExit( CommandEnum _exit ) throws ExitNotFoundException
	{
		Exit exit = null;
		for ( Exit e : exits.list() )
		{

			if ( e.getDirectionName().equals( _exit.getDirName() ) )
			{
				exit = e;
			}
		}
		if ( exit != null )
		{
			return exit;
		} else
		{
			throw new ExitNotFoundException( _exit );
		}
	}

	public Vector<LocationObject> getLocationObjects()
	{
		return objects.list();
	}

	public void addObject( LocationObject locationObject )
	{
		objects.add( locationObject );
	}

	public LocationObject performAction( Deque<String> commandStack )
	{
		CommandEnum cmd = CommandEnum.lookup( commandStack.pop() );
		switch(cmd.getDirNumber())
		{
		case 9002:
			return objects.get(commandStack.pop());
		}
		return null;
		
	}
}
