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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayDeque;
import java.util.Deque;

import com.seven20.inaworld.enums.CommandEnum;
import com.seven20.inaworld.exceptions.ExitNotFoundException;
import com.seven20.inaworld.models.Exit;
import com.seven20.inaworld.models.Inventory;
import com.seven20.inaworld.models.LocationObject;

//
//
// GameDemo
//
// Demonstrates a simple gaming system, loaded from
// a persistent object store.
//
// Last modification date : December 20, 1997
//
class GameDemo
{
	private static final String	filename		= "InAWorld.dat";

	// InAWorld
	private InAWorld			game;
	private Inventory			playerInventory	= new Inventory();

	// Game demo constructor
	public GameDemo() throws Exception
	{

		// Create a file input stream
		FileInputStream fin = new FileInputStream( filename );

		// Create an object input stream
		ObjectInputStream objectIn = new ObjectInputStream( fin );

		// Read an object in from object store, and cast it to a InAWorld
		game = ( InAWorld ) objectIn.readObject();

		// Set the object stream to standard output
		game.setOutputStream( System.out, 40 );
	}

	public static void main( String args[] ) throws Exception
	{
		new GameDemo().play();
	}

	public void play()
	{
		String command = null;
		boolean validCommand = false;

		BufferedReader din = new BufferedReader( new InputStreamReader( System.in ) );

		for ( ;; )
		{
			// Show location
			game.showLocation();

			// Get user input
			try
			{
				command = din.readLine();

				// Print a new line
				System.out.println();
			} catch( IOException e )
			{
				e.printStackTrace();
			}

			// By default, we haven't found a valid command

			// Convert to uppercase for comparison
			parseCommand( command.toUpperCase() );

			if ( commandStack.size() > 1 )
			{
				validCommand = executeCommand(  );

			} else if ( commandStack.size() == 1 )
			{
				validCommand = executeExit( commandStack.pop(), validCommand );
			} else
			{
				System.out.println( "Huh? Invalid command!" );
			}

			// TODO // Check to see if user wants to quit
			// if ( command.compareTo( "QUIT" ) == 0 )
			// {
			// System.out.println( "Okay. Bye!" );
			// System.exit( 0 );
			// }

			// If no valid commands, warn the user is invalid
			if ( !validCommand )
			{
				System.out.println( "Huh? Invalid direction!" );
				System.out.println();
			}
		}
	}

	Deque<String>	commandStack	= new ArrayDeque<String>();

	private void parseCommand( String _command )
	{
		// _command.concat( " " );
		commandStack.clear();

		while ( _command.contains( " " ) )
		{
			commandStack.addLast( _command.substring( 0, _command.indexOf( " " ) ) );
			_command = _command.replace( commandStack.peek(), "" ).trim();
		}
		commandStack.addLast( _command );
	}

	private boolean executeCommand( )
	{
		LocationObject lo;
		CommandEnum cmd = CommandEnum.lookup( commandStack.peek() );
		switch(cmd.getDirNumber()){
		case 9002:
			lo = game.getCurrentLocation().performAction( commandStack );
			playerInventory.add(lo);
			System.out.println("The " + lo.getTitle() + ", is now in your inventory.");
			break;
		case 9003:
			lo = playerInventory.remove( commandStack.pollLast());
			game.getCurrentLocation().addObject( lo );
			System.out.println("You dropped the " + lo.getTitle() );
			break;
		case 9005:
			playerInventory.examine(commandStack.pollLast());
		}
		
		return Boolean.TRUE;
	}

	Exit		currentExit;
	CommandEnum	currentCmd;

	private boolean executeExit( String _command, boolean validCommand )
	{
		currentCmd = CommandEnum.lookup( _command );
		try
		{
			currentExit = game.getCurrentLocation().getExit( currentCmd );

			// Set location to the location pointed to by exit
			game.setCurrentLocation( currentExit.getLeadsTo() );
			validCommand = true;
		} catch( ExitNotFoundException e )
		{
			e.printStackTrace();
		}

		return validCommand;
	}
}
