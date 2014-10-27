package com.cs196.asm;

import java.util.ArrayList;
import java.util.List;

/*
 * The Program will hold the commands given to it by the debugger.
 * It will execute commands sequentially upon the step method
 * being called.
 */

public class Program 
{
	private int PC = 0;
	boolean N = false;
	boolean P = false;
	boolean Z = false;
	List<Integer> memory = new ArrayList<Integer>();
	int[] registers = new int[8];
	private Instruction[] code;
	
	public Program(String[] code, ArrayList<Integer> inputMemory)
	{
		this.code = new Instruction[code.length];
		for(int i = 0; i < code.length; i++) {
			this.code[i] = new Instruction(code[i], this);
		}
		memory = inputMemory;
	}
	
	//will return false when the program is finished
	public boolean step()
	{
		if(PC > code.length) {
			return false;
		}
		code[PC].execute();
		return true;
	}

	public void incrementPC(int i) {
		PC += i;
	}

	public void updateCodes(int i) {
		N = false;
		P = false;
		Z = false;
		if(i < 0) N = true;
		else if(i == 0) Z = true;
		else if(i > 0) P = true;
	}
}
