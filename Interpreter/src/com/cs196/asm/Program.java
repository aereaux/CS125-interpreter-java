package com.cs196.asm;

/*
 * The Program will hold the commands given to it by the debugger.
 * It will execute commands sequentially upon the step method
 * being called.
 */

public class Program 
{
	public Program()
	{
	}
	
	//will return false when the program is finished
	public boolean step()
	{
		return false;
	}
}
