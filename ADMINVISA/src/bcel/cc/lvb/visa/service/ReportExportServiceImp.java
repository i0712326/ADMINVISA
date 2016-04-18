package bcel.cc.lvb.visa.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.dao.VisaTranxDao;
import bcel.cc.lvb.visa.entity.VisaTranx;

public class ReportExportServiceImp implements ReportExportService {
	private Logger logger = Logger.getLogger(getClass());
	private VisaTranxDao visaDao;
	private VisaTranxReportService service;
	
	public void setVisaDao(VisaTranxDao visaDao) {
		this.visaDao = visaDao;
	}
	public void setService(VisaTranxReportService service) {
		this.service = service;
	}
	@Override
	public void export(Date date, File file) {
		List<VisaTranx> list;
		try {
			list = visaDao.getVisaTranx(date);
			service.writeOnline(list, file);
		} catch (HibernateException | SQLException | IOException e) {
			logger.debug("Exception occured while try to export data to file", e);
		}
		
	}

}
