package com.cs196.asm;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private LinkedList<String> code;
	private LinkedList<Integer> codeLines;
	
	public Debugger(String[] args, JTextArea console, int sleepTimeMS)
	{
		this.console = console;
		this.sleepTimeMS = sleepTimeMS;
		code = new LinkedList<String>();
		for(String s : args)
			code.add(s);
		codeLines = new LinkedList<Integer>();
		for(int c = 0; c < code.size(); c++)
		{
			codeLines.add(c);
		}
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
	
	//puts an indented and hyphenated line
	public void putIHLine(String s)
	{
		console.append("    - " + s + "\n");
	}
	
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
		putLine("Proofreading code...");
		comment();
		trim();
		loadData(program);

		if(code.size() > 0)
		{
			putLine("Program Interpretation Failed!");
			putLine("Error Count: " + code.size());
			for(int c = 0; c < code.size(); c++)
			{
				putIHLine("Line " + codeLines.get(c) + ": '" + code.get(c) + "'");
			}
			return false;
		}
		putLine("No errors found!");
		return true;
	}
	
	//THIS MUST BE CALLED EVERY TIME A VALID LINE IS FOUND
	public void validSyntax(int c)
	{
		code.remove(c);
		codeLines.remove(c);
	}
	
	//trims whitespace from each line of code
	private void trim()
	{
		for(int c = 0; c < code.size(); c++)
		{
			code.set(c, code.get(c).trim());
			if(code.get(c).equals(""))
			{
				validSyntax(c);
				c--;
			}
		}
	}
	
	//removes commented lines
	private void comment()
	{
		for(int c = 0; c < code.size(); c++)
		{
			String s = code.get(c);
			if(s.indexOf("//") >= 0)
			{
				code.set(c, s.substring(0, s.indexOf("//")));
			}
		}
	}
	
	//loads data entries into the program
	private void loadData(Program program)
	{
		Pattern pattern = Pattern.compile("#data\\s*[0-9]+\\s*a[0-9]+\\s*[a-zA-Z0-9_]*");
		Pattern numbers = Pattern.compile("[0-9]+");
		Matcher matcher;
		LinkedList<Integer> elements = new LinkedList<Integer>();
		for(int c = 0; c < code.size(); c++)
		{
			String s = code.get(c);
			matcher = pattern.matcher(s);
			if(matcher.matches())
			{	
				matcher = numbers.matcher(s);
				matcher.find();
				String valStr = s.substring(matcher.start(), matcher.end());
				int value = Integer.parseInt(valStr);
				matcher.find();
				String aStr = s.substring(matcher.start(), matcher.end());
				int address = Integer.parseInt(aStr);
				String identifier = "";
				if(matcher.end() + 1 < s.length())
					identifier = s.substring(matcher.end() + 1, s.length()).trim();
				program.dataEntry(value, address, identifier);
				
				elements.add(codeLines.get(c));
				
				validSyntax(c);
				c--;
			}
		}
		
		if(elements.size() == 0)
			return;
		
		putLine("Found " + elements.size() + " data entries");
		String lines = "Lines: ";
		for(int c = 0; c < elements.size(); c++)
		{
			lines += elements.get(c);
			if(c < (elements.size() - 1))
				lines += ", ";
		}
		putIHLine(lines);
	}
	
	
}
