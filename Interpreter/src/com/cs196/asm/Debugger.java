package com.cs196.asm;

import javax.swing.JTextArea;
import java.util.regex.*;

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
	private String[] args;
	
	public Debugger(String[] args, JTextArea console, int sleepTimeMS)
	{
		this.console = console;
		this.sleepTimeMS = sleepTimeMS;
		this.args = args;
	}
	
	//returns the program execution delay time
	public int getSleepTimeMS()
	{
		return sleepTimeMS;
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
	
	
	/*
	 * LOADING METHODS
	 */
	
	//loads the program - returns false if there are errors
	public boolean load(Program program)
	{
		loadData(program);
		return false;
	}
	
	//loads data
	private void loadData(Program program)
	{
		Pattern pattern = Pattern.compile("#data\\s*[0-9]*\\s*a[0-9]*\\s*[a-zA-Z0-9_]*");
		Pattern address = Pattern.compile("a[0-9]*");
		Matcher matcher;
		for(String s : args)
		{
			matcher = pattern.matcher(s);
			if(matcher.find())
			{
				System.out.println("here!");
				matcher = address.matcher(s);
				
			}
		}
	}
}
