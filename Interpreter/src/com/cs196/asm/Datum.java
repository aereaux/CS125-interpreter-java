package com.cs196.asm;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Datum extends JPanel
{
	private int address;
	private int value;
	private String identifier;
	
	private JLabel aLabel;
	private JLabel vLabel;
	private JLabel iLabel;
	
	public Datum(int value, int address, String identifier)
	{
		this.address = address;
		this.value = value;
		this.identifier = identifier;
		
		aLabel = new JLabel(Integer.toString(address));
		vLabel = new JLabel(Integer.toString(value));
		iLabel = new JLabel(identifier);
		
		this.setLayout(new BorderLayout());
		add(aLabel, BorderLayout.WEST);
		add(iLabel, BorderLayout.EAST);
		add(vLabel, BorderLayout.CENTER);
	}
	
	public int getAddress()
	{
		return address;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void updateValue(int value)
	{
		this.value = value;
		vLabel.setText(Integer.toString(value));
	}
	
	public void changeIdentifier(String identifier)
	{
		this.identifier = identifier;
	}
}
