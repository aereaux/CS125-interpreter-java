package com.cs196.asm;

import javax.swing.JTextArea;

/*
 * The debugger will serve multiple purposes.
 * Firstly, it will act as a layer of abstraction
 * between the EditorFrame and Console output.
 * Second, it will proof-read and generate programs.
 */

public class Debugger 
{
	private JTextArea console;
	private int sleepTimeMS;
	
	public Debugger(String[] args, JTextArea console, int sleepTimeMS)
	{
		this.console = console;
		this.sleepTimeMS = sleepTimeMS;
	}
	
	//returns the program execution delay time
	public int getSleepTimeMS()
	{
		return sleepTimeMS;
	}
	
	//loads the program - returns false if there are errors
	public boolean load(Program program)
	{
		return false;
	}
	
	//append a line to the console
	public void putLine(String s)
	{
		console.append(s + "\n");
	}
	
	//clears console text
	public void clear()
	{
		console.setText("");
	}
}
