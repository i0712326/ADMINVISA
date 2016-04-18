package bcel.cc.lvb.visa.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.entity.VisaTranx;

public interface VisaTranxDao {
	
	// get data from pool
	public List<VisaTranx> getVisaTranx(Date date) throws SQLException, HibernateException;
	// process data for local database
	public void saveBulk(List<VisaTranx> list) throws SQLException, HibernateException;
	public List<VisaTranx> getVisaTranx(Date date, String acqId) throws SQLException, HibernateException;
	// issuing and acquiring transaction
	public List<VisaTranx> getIssVisaTranx(Date date, String memId) throws SQLException, HibernateException;
	public List<VisaTranx> getAcqVisaTranx(Date date, String memId) throws SQLException, HibernateException;
	// error transaction
	public List<VisaTranx> getErrVisaTranx(Date date, String memId) throws SQLException, HibernateException;
	// reversal transaction
	public List<VisaTranx> getRevVisaTranx(Date date, String memId) throws SQLException, HibernateException;
	// search online transaction data
	public List<VisaTranx> getVisaTranx(Date date, String card, String trace, String refer) throws SQLException, HibernateException;
	public VisaTranx getUnVisaTrax(String mti, String card, String trace, String refer) throws SQLException, HibernateException;
}
