package bcel.cardcenter.lvb.visa.ui.imp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.context.ApplicationCache;
import bcel.cc.lvb.visa.context.MainApplicationContext;
import bcel.cc.lvb.visa.dao.VisaTranxDao;
import bcel.cc.lvb.visa.entity.VisaTranx;


public class ConfirmImportAction implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		List<VisaTranx> list = ApplicationCache.getImportCache();
		new ConfirmWorker(list).execute();
	}
	
	private class ConfirmWorker extends SwingWorker<Object, Object>{
		private Logger logger = Logger.getLogger(getClass());
		private List<VisaTranx> list;
		public ConfirmWorker(List<VisaTranx> list){
			this.list = list;
		}
		@Override
		protected Object doInBackground() throws Exception {
			VisaTranxDao visaTranxDao = (VisaTranxDao) MainApplicationContext.getBean("visaTranxDao");
			try {
				visaTranxDao.saveBulk(list);
				ApplicationCache.getImportCache().clear();
				JOptionPane.showMessageDialog(null, "All data has been stored.");
			} catch (HibernateException | SQLException e) {
				JOptionPane.showMessageDialog(null,
					    "Erro occured while process data.",
					    "Processing Error.",
					    JOptionPane.ERROR_MESSAGE);
				logger.debug("Exception occured while try to save data to database", e);
			}
			return null;
		}
		
	}
}
