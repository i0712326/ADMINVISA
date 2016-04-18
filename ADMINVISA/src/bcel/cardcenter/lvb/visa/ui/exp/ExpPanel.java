package bcel.cardcenter.lvb.visa.ui.exp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import bcel.cardcenter.lvb.visa.ui.DateModelFormater;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ExpPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private JDatePickerImpl datePicker = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()), new DateModelFormater());
	public ExpPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel menuPanel = new JPanel();
		add(menuPanel, BorderLayout.NORTH);
		JTextField outGoingTextField = new JTextField();
		
		JLabel settleDateLabel = new JLabel("SETTLE DATE");
		settleDateLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		datePicker.getJFormattedTextField().setEditable(true);
		datePicker.getJFormattedTextField().setColumns(10);
		
		JButton exportBtn = new JButton("Export");
		
		JLabel fileChooserLabel = new JLabel("OUTGOING FILE");
		fileChooserLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton outGoingBBtn = new JButton("Browse");
		
		JLabel reportsLabel = new JLabel("REPORTS FILE");
		reportsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JTextField reportsTextField = new JTextField();
		
		JButton reportBBtn = new JButton("Browse");
		
		GroupLayout gl_menuPanel = new GroupLayout(menuPanel);
		gl_menuPanel.setHorizontalGroup(
			gl_menuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addGroup(gl_menuPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_menuPanel.createSequentialGroup()
							.addGap(24)
							.addComponent(settleDateLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(exportBtn))
						.addGroup(gl_menuPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_menuPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(reportsLabel)
								.addComponent(fileChooserLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_menuPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(reportsTextField)
								.addComponent(outGoingTextField, 292, 292, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_menuPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(reportBBtn)
								.addComponent(outGoingBBtn))))
					.addContainerGap(143, Short.MAX_VALUE))
		);
		gl_menuPanel.setVerticalGroup(
			gl_menuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_menuPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_menuPanel.createSequentialGroup()
							.addGroup(gl_menuPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(exportBtn)
								.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(14))
						.addGroup(gl_menuPanel.createSequentialGroup()
							.addComponent(settleDateLabel)
							.addGap(18)))
					.addGroup(gl_menuPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(fileChooserLabel)
						.addComponent(outGoingTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(outGoingBBtn))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_menuPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(reportsLabel)
						.addComponent(reportsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(reportBBtn))
					.addGap(20))
		);
		menuPanel.setLayout(gl_menuPanel);
		
		JPanel containPanel = new JPanel();
		add(containPanel, BorderLayout.SOUTH);
		containPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(550,30));
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setToolTipText("");
		containPanel.add(progressBar);
		
		JPanel statusPanel = new JPanel();
		add(statusPanel, BorderLayout.CENTER);
		GroupLayout gl_statusPanel = new GroupLayout(statusPanel);
		gl_statusPanel.setHorizontalGroup(
			gl_statusPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 645, Short.MAX_VALUE)
		);
		gl_statusPanel.setVerticalGroup(
			gl_statusPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 261, Short.MAX_VALUE)
		);
		statusPanel.setLayout(gl_statusPanel);
		
		outGoingBBtn.addActionListener(new FileChooserAction(outGoingTextField));
		reportBBtn.addActionListener(new FileChooserAction(reportsTextField));
	}
	
	private class FileChooserAction implements ActionListener{
		private JTextField filePath;
		public FileChooserAction(JTextField filePath){
			this.filePath = filePath;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setCurrentDirectory(new File("C:\\Users\\phoud\\Documents"));
			
			int result = jFileChooser.showOpenDialog(new JFrame());
			
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = jFileChooser.getSelectedFile();
			    filePath.setText(selectedFile.getAbsolutePath());
			}
		}
		
	}
	
}
