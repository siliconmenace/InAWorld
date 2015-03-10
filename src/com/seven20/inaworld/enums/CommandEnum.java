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
package com.seven20.inaworld.enums;

import java.util.HashMap;
import java.util.Map;

public enum CommandEnum
{

	UNDEFINED(0x0000, "UNDEFINED", "UNDEF"), NORTH(0x0001, "NORTH", "N"), SOUTH(0x0002, "SOUTH", "S"), EAST(0x0003, "EAST", "E"), WEST(0x0004, "WEST", "W"), UP(0x0005, "UP", "U"), DOWN(
			0x0006, "DOWN", "D"), NORTHEAST(0x0007, "NORTHEAST", "NE"), NORTHWEST(0x0008, "NORTHWEST", "NW"), SOUTHEAST(0x0009, "SOUTHEAST", "SE"), SOUTHWEST(0x0010, "SOUTHWEST",
			"SW"), IN(0x0011, "IN", "I"), OUT(0x0012, "OUT", "O"), INVENTORY(0x9001, "INV", "V"), GET(0x232A, "GET", "G"), DROP(0x232B, "DROP", "DP"), USE(0X232C, "USE", "USE" ), EXAMINE(0X232D, "EXAM", "X");

	private int									commandIndex;
	private String								commandName;
	private String								shortName;

	private static Map<Integer, CommandEnum>	lookupCmdNumber	= new HashMap<Integer, CommandEnum>();
	private static Map<String, CommandEnum>		lookupShortName	= new HashMap<String, CommandEnum>();
	private static Map<String, CommandEnum>		lookupCmdName	= new HashMap<String, CommandEnum>();

	static
	{
		for ( CommandEnum value : CommandEnum.values() )
		{
			lookupCmdNumber.put( value.commandIndex, value );
			lookupShortName.put( value.shortName, value );
			lookupCmdName.put( value.getDirName(), value );
		}
	}

	CommandEnum( int i, String name, String shortName )
	{
		this.commandIndex = i;
		this.commandName = name;
		this.shortName = shortName;
	}

	public int getDirNumber()
	{
		return commandIndex;
	}

	public String getDirName()
	{
		return commandName;
	}

	public String getShortName()
	{
		return shortName;
	}

	public static CommandEnum lookup( int direction )
	{
		return lookupCmdNumber.get( direction );
	}

	public static CommandEnum lookup( String key )
	{
		return lookupCmdName.containsKey( key ) ? lookupCmdName.get( key ) : lookupShortName.get( key );
	}

}
