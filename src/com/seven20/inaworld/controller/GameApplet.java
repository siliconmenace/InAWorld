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

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.Enumeration;

import com.seven20.inaworld.enums.CommandEnum;
import com.seven20.inaworld.models.Exit;
import com.seven20.inaworld.models.Location;

//
//
// LocationDemo - Demonstrates the use of location/exit objects
//
// Last modification date : October 08, 1997
//
public class GameApplet extends Applet
{
	Location currentLocation;
	String command;

	TextField commandInput;
	TextArea  displayOutput;
	Button    buttonInput;

	// Constructor
	public GameApplet()
	{
		super();
	}

	// Initialisation method
	public void init()
	{
		super.init();

		// Define colours
		setBackground(Color.white);
		setForeground(Color.black);

		Panel appletPanel = new Panel();

		// Use a border layout
		BorderLayout b = new BorderLayout();
		appletPanel.setLayout (b);
		add(appletPanel);
		
		// Define UI items
		commandInput  = new TextField(20);
		displayOutput = new TextArea( 10, 60); // 10 rows x 60 chars
		buttonInput   = new Button("Go");
		Panel inputPanel = new Panel();

		// Add components to our layout / panels
		inputPanel.add(commandInput);
		inputPanel.add(buttonInput);
		appletPanel.add("North", displayOutput);
		appletPanel.add("South", inputPanel);

		// Create two locations
		Location l1 = new Location ("Entrance to hall", "You stand at the entrance of a long hallway. The hallway gets darker\nand darker, and you cannot see what lies beyond. To the east\nis an old oaken door, unlocked and beckonning");
		Location l2 = new Location ("End of hall", "You have reached the end of a long dark hallway. You can\nsee nowhere to go but back");
		Location l3 = new Location ("Small study", "This is a small and cluttered study, containing a desk covered with\npapers. Though they no doubt are of some importance,\nyou cannot read their writing");

		// Create an exit for l1
		l1.addExit (new Exit(CommandEnum.NORTH, l2));
		l1.addExit (new Exit(CommandEnum.EAST, l3));

		// Create an exit for l2
		l2.addExit (new Exit(CommandEnum.SOUTH, l1));

		// Create an exit for l3
		l3.addExit (new Exit(CommandEnum.WEST, l1));

		// Set up room locations
		currentLocation = l1;

		// Show first location
		showLocation();
		repaint();
	}

	private void showLocation()
	{
		// Show room title
		displayOutput.append( "\n" + currentLocation.getTitle() + "\n" );
		displayOutput.append( "\n" );
		
		// Show room description 
		displayOutput.append( currentLocation.getDescription() + "\n" );

		// Show available exits
		displayOutput.append( "\nAvailable exits : \n" );
		for (Enumeration e = currentLocation.getExits().elements(); e.hasMoreElements();)
		{
			Exit an_exit = (Exit) e.nextElement();
			displayOutput.append (an_exit + "\n");
		}		
		
	}

	public boolean action (Event evt, Object focus)
	{
		String command;

		// Was a button pressed ? 
		if (evt.target instanceof Button)
		{
			// Obtain string
			command = commandInput.getText();

			// Don't parse blank commands
			if (command.length() == 0)
				return true;

			// Convert to uppercase for comparison
			command = command.toUpperCase();

			// Search for an exit match
			for (Enumeration e = currentLocation.getExits().elements(); e.hasMoreElements();)
			{
				Exit an_exit = (Exit) e.nextElement();

				if ( (an_exit.getDirectionName().compareTo(command) == 0) ||
					 (an_exit.getShortDirectionName().compareTo(command) == 0 )
				   )
				{
					// Set location to the location pointed to by exit
					currentLocation = an_exit.getLeadsTo();

					// Show new location
					showLocation();

					// Clear text area
					commandInput.setText (new String());

					// Event handled
					return true;
				}				
			}

			// If code reaches here, direction is invalid
			displayOutput.append ("\nHuh? Invalid direction!\n");

			// Clear text area
			commandInput.setText (new String());

			// Event handled
			return true;
		}
		// Event not handled
		else return false;
	}

}

