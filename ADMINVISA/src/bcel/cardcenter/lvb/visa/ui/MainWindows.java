package bcel.cardcenter.lvb.visa.ui;

import javax.swing.JFrame;

public class MainWindows extends JFrame {
	private static final long serialVersionUID = 1L;
	public MainWindows(int x, int y, int width, int height){
		super("File Exchange Manager");
		setBounds(x, y, width, height);
		setJMenuBar(new MenuBar());
		setContentPane(new MainPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
