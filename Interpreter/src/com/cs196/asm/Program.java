package com.cs196.asm;

import java.util.HashMap;

import com.cs196.asm.gui.DataWindow;

/*
 * The Program will hold the commands given to it by the debugger.
 * It will execute commands sequentially upon the step method
 * being called.
 */

public class Program 
{
	//user pre-specified data
	private HashMap<Integer, Datum> data;
	
	private DataWindow dataWindow = null;
	
	public Program()
	{
		data = new HashMap<Integer, Datum>();
		dataWindow = new DataWindow();
	}
	
	//will return false when the program is finished
	public boolean step()
	{
		return false;
	}
	
	//enters or overwrites data entry of a given address - returns true if a new entry
	public boolean dataEntry(int value, int address, String identifier)
	{
		if(data.containsKey(address))
		{
			System.out.println("Data updated {" + value + ", "
					+ address + ", " + identifier + "}");
			Datum d = data.get(address);
			d.updateValue(value);
			d.changeIdentifier(identifier);
			return false;
		}
		Datum datum = new Datum(value, address, identifier);
		data.put(address, datum);
		dataWindow.addDataElement(datum);
		System.out.println("Data entered {" + value + ", "
				+ address + ", " + identifier + "}");
		return true;
	}
	
	//opens and populates windows with corresponding data
	public void populate()
	{
		dataWindow.populate();
	}
	
	//closes currently existing Windows
	public void dispose()
	{
		if(dataWindow != null)
		{
			dataWindow.setVisible(false);
			dataWindow.dispose();
		}
	}
}
