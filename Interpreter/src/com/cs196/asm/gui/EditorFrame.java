package com.cs196.asm.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.cs196.asm.Debugger;
import com.cs196.asm.Interpreter;


/*
 * The Editor frame (window) is being implemented here as a Singleton.
 * In the context of our editor, it makes sense for there to be
 * a single, global instance of the frame. It's also easy to work with.
 */

// TODO : add functionality to integrate EdtiorFrame into Interpreter
// TODO : add images to enhance appearance of GUI (e.g., a green triangle on the "run" button, floppy disk for save)

public class EditorFrame extends JFrame
{
	private static EditorFrame instance;
	
	private Interpreter interpreter = null;
	
	//declared "syncrhonized" as a safeguard against multithreading
	public synchronized static EditorFrame getInstance()
	{
		if(instance == null)
			instance = new EditorFrame();
		return instance;
	}
	
	//frame constants
	private static final String title = "CS196 Assembly Interpreter";
	private static final String initConsoleText = "Debugger / Error Console";
	private static final int iWidth = 480;
	private static final int iHeight = 600;
	
	
	//text editing region
	private JTextArea textArea;
	private JScrollPane textScrollPane;
	
	//debugger/run panels
	private JPanel lowerPanel;
	private JTextArea console;
	private JScrollPane consoleScrollPane;
	private JPanel buttonPanel;
	private JButton runButton;
	private JButton clearButton;

	// TODO : enable file loading and saving functionality
	//top menu
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem load;
	private JMenu edit;
	private JMenu edit_style;
	private JMenuItem system;
	private JMenuItem nimbus;
	private JMenuItem metal;
	
	private EditorFrame()
	{
		super(title);
		setLayout(new BorderLayout());
		setTheme("System");
		
		//initializing frame elements
		textArea = new JTextArea();
		textScrollPane = new JScrollPane(textArea);
		
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout());
		lowerPanel.setPreferredSize(new Dimension(this.getWidth(), 80));
		console = new JTextArea(initConsoleText);
		console.setEditable(false);
		consoleScrollPane = new JScrollPane(console);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		runButton = new JButton("Run");
		clearButton = new JButton("Clear");
		
		menuBar = new JMenuBar();
		file = new JMenu("File");
		load = new JMenuItem("Load Program");
		save = new JMenuItem("Save");
		saveAs = new JMenuItem("Save As");
		edit = new JMenu("Edit");
		edit_style = new JMenu("Editor Style");
		system = new JMenuItem("System");
		nimbus = new JMenuItem("Nimbus");
		metal = new JMenuItem("Metal");
		
		
		//adding frame elements
		this.add(textScrollPane, BorderLayout.CENTER);
		lowerPanel.add(consoleScrollPane, BorderLayout.CENTER);
		buttonPanel.add(runButton, BorderLayout.CENTER);
		buttonPanel.add(clearButton, BorderLayout.SOUTH);
		lowerPanel.add(buttonPanel, BorderLayout.EAST);
		this.add(lowerPanel, BorderLayout.SOUTH);
		
		file.add(load);
		file.addSeparator();
		file.add(save);
		file.add(saveAs);
		menuBar.add(file);
		this.add(menuBar, BorderLayout.NORTH);
		edit_style.add(system);
		edit_style.add(nimbus);
		edit_style.add(metal);
		edit.add(edit_style);
		menuBar.add(edit);
		
		//setting simple menu functionality
		system.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0){
						setTheme("System");
		}});
		nimbus.addActionListener( new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setTheme("Nimbus");
					}});
		metal.addActionListener( new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						setTheme("Metal");
					}});
		
		//adding execution functionality to run button
		runButton.addActionListener(new Execute());
		
		//clear button functionality (shouldn't cause concurrency issues, but keep an eye open)
		clearButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						console.setText("");
					}});
		
		setSize(new Dimension(iWidth, iHeight));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//This method will switch the theme to Nimbus if it's installed.
	private void setTheme(String theme)
	{
		try 
		{
			if(theme.equals("System"))
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			else
			{
			    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
			    {
			        if (theme.equals(info.getName())) {
			            UIManager.setLookAndFeel(info.getClassName());
			            break;
			        }
			    }
			}
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) 
		{ 
			JOptionPane.showMessageDialog(this, "An error has occurred in changing your theme.", 
					"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	/*
	 * The Execute class starts the program when the run button is pressed.
	 */
	private class Execute implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			String text = textArea.getText();
			String[] lines = text.split("\n");
			if(interpreter != null)
				interpreter.close();
			
			interpreter = new Interpreter(
					new Debugger(lines, console, 50));
		}

	}
}
