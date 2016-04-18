package bcel.cardcenter.lvb.visa.ui.imp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bcel.cc.lvb.visa.context.ApplicationCache;

public class ClearDataAction implements ActionListener {
	private JTable dataTable;
	private JButton processButton;
	private JButton clearButton;
	private JButton confirmButton;
	public ClearDataAction(JTable dataTable, JButton processButton, JButton clearButton, JButton confirmButton){
		this.dataTable = dataTable;
		this.processButton = processButton;
		this.clearButton = clearButton;
		this.confirmButton = confirmButton;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
		 if (model.getRowCount() > 0) {
             for (int i = model.getRowCount() - 1; i > -1; i--) {
                 model.removeRow(i);
             }
         }
		 ApplicationCache.getImportCache().clear();
		 processButton.setVisible(true);
		 clearButton.setVisible(false);
		 confirmButton.setVisible(false);
	}

}
