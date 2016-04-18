package bcel.cardcenter.lvb.visa.ui.imp;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import org.apache.log4j.Logger;

import bcel.cc.lvb.visa.context.ApplicationCache;
import bcel.cc.lvb.visa.util.UtilPackage;


public class ExportDataAction implements ActionListener {
	private Logger logger = Logger.getLogger(getClass());
	private JComboBox<String> comboBox;
	private JDatePickerImpl datePicker;
	public ExportDataAction(JComboBox<String> comboBox, JDatePickerImpl datePicker){
		this.comboBox = comboBox;
		this.datePicker = datePicker;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(ApplicationCache.getImportCache().isEmpty()){
				JOptionPane.showMessageDialog(null,
					    "Erro occured while export data.",
					    "Processing Error.",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			JFileChooser jFileChooser = new JFileChooser();
			
			int result = jFileChooser.showOpenDialog(new JFrame());
			File selectedFile = null;
			if (result == JFileChooser.APPROVE_OPTION)
			   selectedFile = jFileChooser.getSelectedFile();
			
			String option = (String) comboBox.getSelectedItem();
			String str = datePicker.getJFormattedTextField().getText().trim();
			ExportDataWorker worker = new ExportDataWorker(option, UtilPackage.str2date(str, "MMM DD, yyyy"), selectedFile);
			
			worker.execute();
			if(worker.get()){
				JOptionPane.showMessageDialog(null, "Export Data is Success.");
			}
		} catch (ParseException | HeadlessException | InterruptedException | ExecutionException e1) {
			logger.debug("Exception occured while try to export report", e1);
			JOptionPane.showMessageDialog(null,
				    "Erro occured while export data.",
				    "Processing Error.",
				    JOptionPane.ERROR_MESSAGE);
		}
		
	}
	private class ExportDataWorker extends SwingWorker<Boolean,Object>{
		private String option;
		private Date date;
		private File file;
		public ExportDataWorker(String option, Date date, File file){
			this.option = option;
			this.date = date;
			this.file = file;
		}
		@Override
		protected Boolean doInBackground() throws Exception {
			/*
			VisaTranxDao visaTranxDao = (VisaTranxDao) MainApplicationContext.getBean("visaTranxDao");
			ReportBuilder reportBuilder = (ReportBuilder) MainApplicationContext.getBean("reportBuilder");
			List<VisaTranx> list = null;
			if(option.equals("SUCCESS"))
				list = visaTranxDao.getVisaSuc(date);
			else if(option.equals("ERROR"))
				list = visaTranxDao.getVisaErr(date);
			else
				list = visaTranxDao.getVisaRev(date);
			reportBuilder.buildReport(file, list, option, date);
			return true;
			*/
			return null;
		}
		
	}
}
