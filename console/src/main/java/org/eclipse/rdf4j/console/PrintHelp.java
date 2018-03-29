/*******************************************************************************
 * Copyright (c) 2015 Eclipse RDF4J contributors, Aduna, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/
package org.eclipse.rdf4j.console;

import java.util.Locale;
import java.util.Map;

/**
 * Prints available command and options to the console.
 * 
 * @author Dale Visser
 */
public class PrintHelp extends ConsoleCommand {
	public static final String USAGE = "Usage:\n";
	
	private final Map<String,? extends Help> commands;
	
	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getHelpShort() {
		return "Displays this help message";
	}
	
	@Override
	public String getHelpLong() {
		return "No additional help available";
	}
	
	/**
	 * Constructor
	 * 
	 * @param consoleIO 
	 */
	PrintHelp(ConsoleIO consoleIO, ConsoleState state, Map<String,? extends Help> commands) {
		super(consoleIO, state);
		this.commands = commands;
	}

	@Override
	public void execute(String... parameters) {
		if (parameters.length < 2) {
			printCommandOverview();
			return;
		}
		
		final String target = parameters[1].toLowerCase(Locale.ENGLISH);
		Help cmd = commands.get(target);
		if (cmd != null) {
			consoleIO.writeln(cmd.getHelpLong());
		} else {
			consoleIO.writeln("No additional info available for command " + target);
		}
	}

	/**
	 * Print list of commands
	 */
	private void printCommandOverview() {
		consoleIO.writeln("For more information on a specific command, try 'help <command>'.");
		consoleIO.writeln("List of all commands:");
		
		commands.forEach((k,v) -> {
			consoleIO.writeln(String.format("%-12s %s", k, v.getHelpShort()));
		});
		
		consoleIO.writeln("exit, quit  Exit the console");
	}
}
