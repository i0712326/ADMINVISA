package bcel.cc.lvb.visa.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.dao.IssueTxnDao;
import bcel.cc.lvb.visa.dao.ProcCodeDao;
import bcel.cc.lvb.visa.dao.VisaTranxDao;
import bcel.cc.lvb.visa.entity.IssueTxn;
import bcel.cc.lvb.visa.entity.ProcCode;
import bcel.cc.lvb.visa.entity.VisaTranx;

public class VisaRetentionImpSub extends VisaRetentionImp {
	private Logger logger = Logger.getLogger(VisaRetentionImpSub.class);
	private VisaTranxDao visaTranxDao1;
	private VisaTranxDao visaTranxDao2;
	private IssueTxnDao issueTxnDao;
	private ProcCodeDao procCodeDao;
	private VisaTranxReportService visaReportService;
	private long num, amt;
	public void setVisaTranxDao1(VisaTranxDao visaTranxDao1) {
		this.visaTranxDao1 = visaTranxDao1;
	}
	public void setVisaTranxDao2(VisaTranxDao visaTranxDao2) {
		this.visaTranxDao2 = visaTranxDao2;
	}
	public void setIssueTxnDao(IssueTxnDao issueTxnDao) {
		this.issueTxnDao = issueTxnDao;
	}
	public void setProcCodeDao(ProcCodeDao procCodeDao) {
		this.procCodeDao = procCodeDao;
	}
	public void setVisaReportService(VisaTranxReportService visaReportService) {
		this.visaReportService = visaReportService;
	}
	@Override
	public void replucate(Date date) {
		try{
			online = visaTranxDao1.getVisaTranx(date);
			int size = online.size();
			for(int i=0;i<size;i++){
				String proc = online.get(i).getProc();
				ProcCode procCode = procCodeDao.getProcCode(proc);
				online.get(i).setProcCode(procCode);
			}
			visaTranxDao2.saveBulk(online);
		}
		catch(SQLException | HibernateException e){
			online = new ArrayList<VisaTranx>();
			logger.debug("Exception occured while try to fetch online transactions", e);
		}
		
	}

	@Override
	public void fetchOnline(Date date, String memId) {
		try{
			online = visaTranxDao2.getVisaTranx(date,memId);
			num+=online.size();
		}
		catch(SQLException | HibernateException e){
			online = new ArrayList<VisaTranx>();
			logger.debug("Exception occured while try to fetch data from Online table",e);
		}
	}

	@Override
	public void fetchDispute(Date date, String memId) {
		try {
			acqOutgoing = issueTxnDao.getAcqOutgoing(date, memId);
			issOutgoing = issueTxnDao.getIssOutgoing(date, memId);
			num+=acqOutgoing.size()+issOutgoing.size();
		} catch (HibernateException | SQLException e) {
			dispute = new ArrayList<IssueTxn>();
			logger.debug("Exception occured while try to fetch data from dispute table",e);
		}
	}
	
	@Override
	public void writeHeader(Date date, String memId, File file) {
		try {
			visaReportService.writeHeader(date, memId, file);
		} catch (IOException e) {
			logger.debug("Exception occured while try to write header", e);
		}
	}
	@Override
	public void writeTrailer(File file) {
		try {
			visaReportService.writeTailer(num, amt, file);
		} catch (IOException e) {
			logger.debug("Exception occured while try to write trailer", e);
		}
	}
	@Override
	public void writeOnline(File file) {
		try {
			visaReportService.writeOnline(online, file);
		} catch (IOException e) {
			logger.debug("Exception occured while try to write online data to file", e);
		}
	}

	@Override
	public void writeDispute(File file) {
		try {
			visaReportService.writeDispute(acqOutgoing, file);
			visaReportService.writeDispute(issOutgoing, file);
		} catch (IOException e) {
			logger.debug("Exception occured while try to write dispute data to file", e);
		}

	}

}
