package bcel.cardcenter.lvb.visa.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel menuPanel = new MenuPanel();
	private JPanel midPanel = new MiddlePanel();
	public MainPanel(){
		this.setLayout(new BorderLayout());
		this.add(menuPanel, BorderLayout.NORTH);
		//this.add(new JButton("BUTTON2"), BorderLayout.WEST);
		this.add(midPanel, BorderLayout.CENTER);
		//this.add(new JButton("BUTTON4"), BorderLayout.EAST);
	}
}
