package bcel.cardcenter.lvb.visa.ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public MenuPanel(){
		setLayout(new FlowLayout());
		add(new JButton("Execute"));
		add(new JButton("Delete"));
		add(new JButton("Cancel"));
		add(new JButton("Export"));
		add(new JButton("Import"));
		add(new JButton("Exit"));
	}

}
