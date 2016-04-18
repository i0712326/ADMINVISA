package bcel.cardcenter.lvb.visa.ui.his;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import org.apache.log4j.Logger;

import bcel.cc.lvb.visa.context.MainApplicationContext;
import bcel.cc.lvb.visa.dao.VisaTranxDao;
import bcel.cc.lvb.visa.entity.VisaTranx;
import bcel.cc.lvb.visa.util.UtilPackage;

public class SearchDataAction implements ActionListener {
	private Logger logger = Logger.getLogger(getClass());
	private JDatePickerImpl datePicker;
	private JTextField cardField;
	private JTextField referField;
	private JTextField traceField;
	private JTable table;
	
	public SearchDataAction(JDatePickerImpl datePicker, JTextField cardField,
			JTextField referField, JTextField traceField, JTable table) {
		super();
		this.datePicker = datePicker;
		this.cardField = cardField;
		this.referField = referField;
		this.traceField = traceField;
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String str 			= datePicker.getJFormattedTextField().getText().trim();
			String card 		= cardField.getText().trim();
			String refer 		= referField.getText().trim();
			String trace 		= traceField.getText().trim();
			DataSearchWork work = new DataSearchWork(UtilPackage.str2date(str,"yyyy-MM-dd"), card, refer, trace, table);
			work.execute();
		} catch (ParseException e1) {
			logger.debug("Exception occured while try to fecth data from database", e1);
			JOptionPane.showMessageDialog(null,
				    "Erro occured while process data.",
				    "Processing Error.",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private class DataSearchWork extends SwingWorker<Object,Object>{
		private Date date;
		private String card;
		private String refer;
		private String trace;
		private JTable table;
		public DataSearchWork(Date date, String card, String refer,
				String trace, JTable table) {
			super();
			this.date = date;
			this.card = card;
			this.refer = refer;
			this.trace = trace;
			this.table = table;
		}

		@Override
		protected Object doInBackground() throws Exception {
			VisaTranxDao visaTranxDao =  (VisaTranxDao) MainApplicationContext.getBean("visaTranxDao2");
			List<VisaTranx> list = visaTranxDao.getVisaTranx(date, card, trace, refer);
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			// clear table previous data
			 if (tableModel.getRowCount() > 0) {
	             for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
	                 tableModel.removeRow(i);
	             }
	         }
			// add new data set
			for (VisaTranx item : list) {
				Vector<VisaTranx> vector = new Vector<VisaTranx>();
				vector.add(item);
				tableModel.addRow(vector);
			}
			
			return null;
		}
		
	}
}
