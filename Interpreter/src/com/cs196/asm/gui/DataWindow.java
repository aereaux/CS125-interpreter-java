package com.cs196.asm.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.cs196.asm.Datum;


/*
 * Displays all data elements during program runtime.
 */
public class DataWindow extends JFrame
{
	//list of Data
	private LinkedList<Datum> data;
	
	private JPanel topPanel;
	private JPanel dataPanel;
	private JScrollPane panelScrollPane;
	private static final int MARGIN = 10;
	
	public DataWindow()
	{
		super("#data");
		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(400, 400));
		dataPanel = new JPanel();
		//dataPanel.setBackground(Color.WHITE);
		topPanel.setBackground(Color.WHITE);
		
		data = new LinkedList<Datum>();
	}
	
	//adds a data element to the window
	public void addDataElement(Datum d)
	{
		data.add(d);
	}
	
	//once all elements have been entered, the Window layout is created
	public void populate()
	{
		if(data.size() == 0)
			return;
		System.out.println(data.size());
		topPanel.setLayout(new GridLayout(data.size(), 1, MARGIN, MARGIN));
		for(Datum d : data)
			topPanel.add(d);
		//panelScrollPane = new JScrollPane(dataPanel);
		//topPanel.add(panelScrollPane);
		this.add(topPanel);
		setVisible(true);
		this.pack();
		setLocationRelativeTo(EditorFrame.getInstance());
	}
	
	//updates the visualization
	public void update()
	{
		dataPanel.repaint();
	}
}
