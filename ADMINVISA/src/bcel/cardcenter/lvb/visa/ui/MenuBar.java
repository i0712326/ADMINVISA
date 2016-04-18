package bcel.cardcenter.lvb.visa.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private JMenu fileMenu = new JMenu("File");
	private JMenu actionMenu = new JMenu("Action");
	private JMenu editMenu = new JMenu("Edit");
	private JMenu queryMenu = new JMenu("Query");
	private JMenu blockMenu = new JMenu("Block");
	private JMenu recordMenu = new JMenu("Record");
	private JMenu fieldMenu = new JMenu("Field");
	private JMenu helpMenu = new JMenu("Help");
	private JMenu windowMenu = new JMenu("Window");
	
	private JMenuItem openMenuItem = new JMenuItem("open");
	private JMenuItem saveMenuItem = new JMenuItem("save");
	private JMenuItem saveAsMenuItem = new JMenuItem("save as");
	private JMenuItem expportMenuItem = new JMenuItem("export");
	private JMenuItem importMenuItem = new JMenuItem("import");
	private JMenuItem exitMenuItem = new JMenuItem("exit");
	
	public MenuBar(){
		add(fileMenu);
		add(actionMenu);
		add(editMenu);
		add(queryMenu);
		add(blockMenu);
		add(recordMenu);
		add(fieldMenu);
		add(helpMenu);
		add(windowMenu);
		
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(importMenuItem);
		fileMenu.add(expportMenuItem);
		fileMenu.add(exitMenuItem);
	}
}
