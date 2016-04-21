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
import bcel.cc.lvb.visa.service.VisaReportBuilder;
import bcel.cc.lvb.visa.service.VisaRetention;

public class DataExportAction implements ActionListener {
	private Date date;
	private JProgressBar progressBar;
	private File outGoingFile;
	private File outGoingReport;
	public DataExportAction(Date date, File outGoingFile, File outGoingReport, JProgressBar progressBar){
		this.date = date;
		this.progressBar = progressBar;
		this.outGoingFile = outGoingFile;
		this.outGoingReport = outGoingReport;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		DataRetentWork work = new DataRetentWork(date, outGoingFile, outGoingReport, progressBar);
		work.execute();
	}
	
	private class DataRetentWork extends SwingWorker<Void, Void>{
		private Date date;
		private File outGoingFile;
		private File outGoingReport;
		private JProgressBar progressBar; 
		public DataRetentWork(Date date, File outGoingFile,File outGoingReport, JProgressBar progressBar){
			this.date = date;
			this.progressBar = progressBar;
			this.outGoingFile = outGoingFile;
			this.outGoingReport = outGoingReport;
		}
		@Override
		protected Void doInBackground() throws Exception {
			// Get required Beans
			VisaRetention visaRetention = (VisaRetention) MainApplicationContext.getBean("visaRetentionSub");
			VisaReportBuilder reportBuilder = (VisaReportBuilder) MainApplicationContext.getBean("visaReportBuilder");
			User user = ApplicationCache.getCurrentUser();
			String memId = user.getMember().getMemId();
			// Retention of data
			visaRetention.retend(date, memId, outGoingFile);
			// Generate reports
			reportBuilder.generate(date, memId, outGoingReport);
			return null;
		}
		
	}

}
