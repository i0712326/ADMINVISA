package bcel.cardcenter.lvb.visa.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MiddlePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton button1 = new JButton("JBUTTON01");
	private JButton button2 = new JButton("JBUTTON02");
	public MiddlePanel(){
		setLayout(new GridLayout(2,1));
		add(button1);
		add(button2);
	}
}
