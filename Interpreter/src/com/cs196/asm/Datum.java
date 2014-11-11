package com.cs196.asm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

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
	
	private static Font vFont;
	
	public Datum(int value, int address, String identifier)
	{
		if(vFont == null)
			vFont = new Font("Sans Serif", Font.PLAIN, 24);
		
		this.address = address;
		this.value = value;
		this.identifier = identifier;
	
		setBackground(Color.WHITE);
		
		aLabel = new JLabel("Address: a" + Integer.toString(address));
		vLabel = new JLabel(Integer.toString(value));
		iLabel = new JLabel("\"" + identifier + "\"");
		
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		vLabel.setHorizontalAlignment(JLabel.CENTER);
		iLabel.setHorizontalAlignment(JLabel.CENTER);
		
		vLabel.setFont(vFont);
		
		
		this.setLayout(new BorderLayout());
		add(aLabel, BorderLayout.NORTH);
		add(iLabel, BorderLayout.SOUTH);
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
