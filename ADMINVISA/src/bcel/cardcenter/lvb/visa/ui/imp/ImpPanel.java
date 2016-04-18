package bcel.cardcenter.lvb.visa.ui.imp;

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

public class ImpPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JDatePickerImpl datePicker = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()), new DateModelFormater());
	/**
	 * Create the panel.
	 */
	public ImpPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel queryPanel = new JPanel();
		add(queryPanel, BorderLayout.NORTH);
		
		JLabel dateLabel = new JLabel("DATE");
		dateLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton importButton = new JButton("Import");
		importButton.setToolTipText("Execute");
		
		JLabel fileLabel = new JLabel("INCOMING FILE");
		fileLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		JTextField filePathBox = new JTextField();
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new FileChooserAction(filePathBox));
		
		GroupLayout glQueryPanel = new GroupLayout(queryPanel);
		glQueryPanel.setHorizontalGroup(
			glQueryPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glQueryPanel.createSequentialGroup()
					.addGroup(glQueryPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glQueryPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(fileLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(filePathBox, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(browseButton))
						.addGroup(glQueryPanel.createSequentialGroup()
							.addGap(63)
							.addComponent(dateLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(importButton)))
					.addContainerGap(491, Short.MAX_VALUE))
		);
		glQueryPanel.setVerticalGroup(
			glQueryPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glQueryPanel.createSequentialGroup()
					.addGap(18)
					.addGroup(glQueryPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateLabel)
						.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(importButton))
					.addGap(18)
					.addGroup(glQueryPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(filePathBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fileLabel)
						.addComponent(browseButton))
					.addContainerGap())
		);
		datePicker.getJFormattedTextField().setEditable(true);
		queryPanel.setLayout(glQueryPanel);
		
		JPanel saveButtonPanel = new JPanel();
		add(saveButtonPanel, BorderLayout.SOUTH);
		saveButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		progressBar.setPreferredSize(new Dimension(550,30));
		saveButtonPanel.add(progressBar);
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
