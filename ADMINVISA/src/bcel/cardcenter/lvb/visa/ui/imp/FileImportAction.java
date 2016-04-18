package bcel.cardcenter.lvb.visa.ui.imp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import org.apache.log4j.Logger;

public class FileImportAction implements ActionListener {
	private JTable table;
	private JTextField filePath;
	private JDatePickerImpl datePicker;
	private JButton clearButton;
	private JButton confirmButton;
	private JButton processButton;
	public FileImportAction(JTable table, JTextField filePath,
			JDatePickerImpl datePicker, JButton clearButton, JButton confirmButton, JButton processButton) {
		this.table 			= table;
		this.filePath 		= filePath;
		this.datePicker		= datePicker;
		this.clearButton 	=clearButton;
		this.confirmButton 	= confirmButton;
		this.processButton 	= processButton;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(filePath.getText().isEmpty()){
			JOptionPane.showMessageDialog(null,
				    "Please specify file.",
				    "Processing Error.",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		ImportDataWork worker = new ImportDataWork(table, filePath, datePicker, clearButton, confirmButton, processButton);
		
		worker.execute();
		try {
			boolean ret = worker.get();
			if(ret){
				JOptionPane.showMessageDialog(null, "All read data success full.");
			}
			else{
				JOptionPane.showMessageDialog(null,
					    "Erro occured while process data.",
					    "Processing Error.",
					    JOptionPane.ERROR_MESSAGE);
			}
		} catch (InterruptedException | ExecutionException e1) {
			JOptionPane.showMessageDialog(null,
				    "Erro occured while process data.",
				    "Processing Error.",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	private class ImportDataWork extends SwingWorker<Boolean, Object>{
		private Logger logger = Logger.getLogger(getClass());
		private JTable table;
		private JTextField filePath;
		private JDatePickerImpl datePicker;
		private JButton clearButton;
		private JButton confirmButton;
		private JButton processButton;
		
		public ImportDataWork(JTable table, JTextField filePath,
				JDatePickerImpl datePicker, JButton clearButton,
				JButton confirmButton, JButton processButton) {
			this.table 			= table;
			this.filePath 		= filePath;
			this.datePicker 	= datePicker;
			this.clearButton 	= clearButton;
			this.confirmButton 	= confirmButton;
			this.processButton 	= processButton;
		}

		@Override
		protected Boolean doInBackground() throws Exception {
			/*try {
				ApplicationCache.getImportCache().clear();
				String date = datePicker.getJFormattedTextField().getText();
				String path = filePath.getText();
				File file = new File(path);
				FileImportService service = new FileImportServiceImp();
				List<VisaTranx> list = service.toEntity(file, Utility.str2date(date,"MMM DD, yyyy"));
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				for(VisaTranx item : list){
					Vector<VisaTranx> vector = new Vector<VisaTranx>();
					vector.add(item);
					tableModel.addRow(vector);
				}
				clearButton.setVisible(true);
				confirmButton.setVisible(true);
				processButton.setVisible(false);
				ApplicationCache.getImportCache().addAll(list);
				return true;
			} catch (ParseException e1) {
				logger.debug("Exception occured while try to process data",e1);
				return false;
			}*/
			return null;
		}
		
	}
}
