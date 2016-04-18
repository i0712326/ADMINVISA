package bcel.cc.lvb.visa.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.dao.IssueTxnDao;
import bcel.cc.lvb.visa.dao.VisaTranxDao;
import bcel.cc.lvb.visa.entity.IssueTxn;
import bcel.cc.lvb.visa.entity.VisaTranx;

public class ReportExportServiceImp implements ReportExportService {
	private Logger logger = Logger.getLogger(getClass());
	private VisaTranxDao visaDao;
	private IssueTxnDao issueTxnDao;
	private VisaTranxReportService service;
	
	public void setVisaDao(VisaTranxDao visaDao) {
		this.visaDao = visaDao;
	}
	public void setIssueTxnDao(IssueTxnDao issueTxnDao){
		this.issueTxnDao = issueTxnDao;
	}
	public void setService(VisaTranxReportService service) {
		this.service = service;
	}
	private List<VisaTranx> acqTxn;
	private List<VisaTranx> issTxn;
	private List<VisaTranx> errTxn;
	private List<VisaTranx> revTxn;
	
	private List<IssueTxn> incAcqTxn;
	private List<IssueTxn> incIssTxn;
	
	private List<IssueTxn> outAcqTxn;
	private List<IssueTxn> outIssTxn;
	@Override
	public void export(Date date, File file, String memId) {
		
		try {
			acqTxn = visaDao.getAcqVisaTranx(date, memId);
			issTxn = visaDao.getIssVisaTranx(date, memId);
			errTxn = visaDao.getErrVisaTranx(date, memId);
			revTxn = visaDao.getRevVisaTranx(date, memId);
			
			incAcqTxn = issueTxnDao.getAcqIncoming(date, memId);
			incIssTxn = issueTxnDao.getIssIncoming(date, memId);
			outAcqTxn = issueTxnDao.getAcqOutgoing(date, memId);
			outIssTxn = issueTxnDao.getIssOutgoing(date, memId);
			
			service.writeHeader(date, memId, file);
			
			service.writeOnline(acqTxn, file);
			service.writeOnline(issTxn, file);
			service.writeOnline(errTxn, file);
			service.writeOnline(revTxn, file);
			
			service.writeDispute(incAcqTxn, file);
			service.writeDispute(incIssTxn, file);
			service.writeDispute(outAcqTxn, file);
			service.writeDispute(outIssTxn, file);
			
			service.writeTailer(0,0,file);
			
		} catch (HibernateException | SQLException | IOException e) {
			logger.debug("Exception occured while try to export data to file", e);
		}
		
	}

}
