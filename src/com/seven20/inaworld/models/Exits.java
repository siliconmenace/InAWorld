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

import com.seven20.inaworld.enums.CommandEnum;
import com.seven20.inaworld.exceptions.ExitNotFoundException;

class Exits implements Serializable
{

	/**
     * 
     */
	private static final long	serialVersionUID	= -5933136442132120728L;
	private Vector<Exit>		exits				= new Vector<Exit>();

	public Exits()
	{
	}

	public void add( Exit _exit )
	{
		exits.addElement( _exit );
	}

	public void remove( Exit _exit )
	{
		if ( exits.contains( _exit ) )
		{
			exits.removeElement( _exit );
		}
	}

	@SuppressWarnings("unchecked")
	public Vector<Exit> list()
	{
		return ( Vector<Exit> ) exits.clone();
	}

	
}
