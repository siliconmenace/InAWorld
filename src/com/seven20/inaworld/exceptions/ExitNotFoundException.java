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
package com.seven20.inaworld.exceptions;

import com.seven20.inaworld.enums.CommandEnum;

public class ExitNotFoundException extends Exception
{
	private static String msg = "Could not find and exit by this name : %s";

	public ExitNotFoundException( CommandEnum _exit )
	{
		super(String.format( msg, _exit.toString() ));
	}

	public ExitNotFoundException( CommandEnum _exit, Exception e )
	{
		super(String.format( msg, _exit.toString() ), e);
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

}

