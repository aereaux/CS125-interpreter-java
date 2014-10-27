package com.cs196.asm;

import com.cs196.asm.gui.EditorFrame;

// TODO : write the interpreter

/*
 * Interpreter is the program's main class.
 * It has access to everything it needs to run the program
 * at a high level, though much of it is abstracted.
 */

public class Interpreter implements Runnable
{
	//MAIN METHOD
	public static void main(String args[])
	{
		EditorFrame.getInstance();
	}

	
	
	private Debugger debugger;
	private Thread interpreterThread;
	
	//the program to be executed
	private Program program = null;
	
	public Interpreter(Debugger debugger)
	{
		program = new Program();
		this.debugger = debugger;
		debugger.clear();
		if(debugger.load(program))
		{
			program.populate();
			interpreterThread = new Thread(this);
			interpreterThread.start();
		}
	}
	
	//INTERPRETER THREAD. The basic run cycle will be outlined here.
	public void run()
	{
		debugger.putLine("Starting...");
		while(program.step())
		{
			try
			{
				Thread.sleep(debugger.getSleepTimeMS());
			}catch(Exception e){ e.printStackTrace(); }
		}
		debugger.putLine("Program Complete");
	}
	
	
	//terminates current program, typically upon "Run" button being pressed
	public void close()
	{
		program.dispose();
	}
}
