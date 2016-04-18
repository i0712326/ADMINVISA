package bcel.cardcenter.lvb.visa.ui.exp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import bcel.cc.lvb.visa.context.ApplicationCache;
import bcel.cc.lvb.visa.context.MainApplicationContext;
import bcel.cc.lvb.visa.dao.IssueTxnDao;
import bcel.cc.lvb.visa.dao.VisaTranxDao;
import bcel.cc.lvb.visa.entity.IssueTxn;
import bcel.cc.lvb.visa.entity.User;
import bcel.cc.lvb.visa.entity.VisaTranx;
import bcel.cc.lvb.visa.service.VisaRetention;

public class DataExportAction implements ActionListener {
	private Date date;
	private JProgressBar progressBar;
	private File file;
	public DataExportAction(Date date, File file, JProgressBar progressBar){
		this.date = date;
		this.progressBar = progressBar;
		this.file = file;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		DataRetentWork work = new DataRetentWork(date, file, progressBar);
		work.execute();
	}
	
	private class DataRetentWork extends SwingWorker<Void, Void>{
		private static final int MIN = 0;
		private static final int MAX = 100;
		private Date date;
		private File file;
		private JProgressBar progressBar; 
		public DataRetentWork(Date date, File file, JProgressBar progressBar){
			this.date = date;
			this.progressBar = progressBar;
			this.file = file;
		}
		@Override
		protected Void doInBackground() throws Exception {
			// Get required Beans
			VisaRetention visaRetention = (VisaRetention) MainApplicationContext.getBean("visaRetentionSub");
			VisaTranxDao visaTranxDao = (VisaTranxDao) MainApplicationContext.getBean("visaTranxDao2");
			IssueTxnDao issueTxnDao = (IssueTxnDao) MainApplicationContext.getBean("issueTxnDao");
			// Retention of data
			visaRetention.retend(date, file);
			// Fetching data
			User user = ApplicationCache.getCurrentUser();
			String memId = user.getMember().getMemId();
			List<VisaTranx> issTxns = visaTranxDao.getIssVisaTranx(date, memId);
			List<VisaTranx> acqTxns = visaTranxDao.getAcqVisaTranx(date, memId);
			List<VisaTranx> errTxns	= visaTranxDao.getErrVisaTranx(date, memId);
			List<VisaTranx> revTxns = visaTranxDao.getRevVisaTranx(date, memId);
			
			List<IssueTxn> incAcq = issueTxnDao.getAcqIncoming(date, memId);
			List<IssueTxn> outAcq = issueTxnDao.getAcqOutgoing(date, memId);
			List<IssueTxn> incIss = issueTxnDao.getIssIncoming(date, memId);
			List<IssueTxn> outIss = issueTxnDao.getIssOutgoing(date, memId);
			
			// Generate Report to specify data
			
			// Generate Out going files (online transaction and dispute transaction)
			return null;
		}
		
	}

}
