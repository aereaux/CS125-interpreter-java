package com.cs196.asm.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


/*
 * The Editor frame (window) is being implemented here as a Singleton.
 * In the context of our editor, it makes sense for there to be
 * a single, global instance of the frame. It's also easy to work with.
 */

// TODO : add functionality to integrate EdtiorFrame into Interpreter

public class EditorFrame extends JFrame
{
	private static EditorFrame instance;
	
	//declared "syncrhonized" as a safeguard against multithreading
	public synchronized static EditorFrame getInstance()
	{
		if(instance == null)
			instance = new EditorFrame();
		return instance;
	}
	
	//frame constants
	private static final String title = "CS196 Assembly Interpreter";
	private static final int iWidth = 480;
	private static final int iHeight = 600;
	
	
	//text editing region
	private JTextArea textArea;
	private JScrollPane textScrollPane;
	
	//debugger/run panel
	private JPanel lowerPanel;
	private JTextArea console;
	private JScrollPane consoleScrollPane;
	private JButton runButton;

	// TODO : enable file loading and saving functionality
	//top menu
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem load;
	
	private EditorFrame()
	{
		super(title);
		setLayout(new BorderLayout());
		setNimbusTheme();
		
		//initializing frame elements
		textArea = new JTextArea();
		textScrollPane = new JScrollPane(textArea);
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout());
		console = new JTextArea("Debugger / Error Console");
		console.setEditable(false);
		consoleScrollPane = new JScrollPane(console);
		runButton = new JButton("Run");
		menuBar = new JMenuBar();
		file = new JMenu("File");
		load = new JMenuItem("Load Program");
		save = new JMenuItem("Save");
		saveAs = new JMenuItem("Save As");
		
		
		//adding frame elements
		this.add(textScrollPane, BorderLayout.CENTER);
		lowerPanel.add(consoleScrollPane, BorderLayout.CENTER);
		lowerPanel.add(runButton, BorderLayout.EAST);
		this.add(lowerPanel, BorderLayout.SOUTH);
		file.add(load);
		file.addSeparator();
		file.add(save);
		file.add(saveAs);
		menuBar.add(file);
		this.add(menuBar, BorderLayout.NORTH);
		
		
		setSize(new Dimension(iWidth, iHeight));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//This method will switch the theme to Nimbus if it's installed.
	private void setNimbusTheme()
	{
		try 
		{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		    {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
	}
}
