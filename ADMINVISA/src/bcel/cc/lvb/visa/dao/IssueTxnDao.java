package bcel.cc.lvb.visa.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.entity.IssueTxn;

public interface IssueTxnDao {
	public void save(IssueTxn issueTxn) throws SQLException, HibernateException;
	public List<IssueTxn> getIssuesByDate(Date date) throws SQLException, HibernateException;
	public List<IssueTxn> getIssues(Date date, String card, String ref, String trace) throws SQLException, HibernateException;
	// get outgoing and incoming
	public List<IssueTxn> getIssOutgoing(Date date, String memId) throws SQLException, HibernateException;
	public List<IssueTxn> getAcqOutgoing(Date date, String memId) throws SQLException, HibernateException;
	public List<IssueTxn> getIssIncoming(Date date, String memId) throws SQLException, HibernateException;
	public List<IssueTxn> getAcqIncoming(Date date, String memId) throws SQLException, HibernateException;
	// get dispute transaction by processing code
	public List<IssueTxn> getIssueTxnIssByProc(Date date, String memId, String proc) throws SQLException, HibernateException;
	public List<IssueTxn> getIssueTxnAcqByProc(Date date, String memId, String proc) throws SQLException, HibernateException;
	public IssueTxn getIssueTxn(IssueTxn is) throws SQLException, HibernateException;
	public Object getIssueTxnByProc(IssueTxn is, String string) throws SQLException, HibernateException;
}
