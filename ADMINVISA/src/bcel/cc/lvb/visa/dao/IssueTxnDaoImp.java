package bcel.cc.lvb.visa.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import bcel.cc.lvb.visa.entity.IssueTxn;

public class IssueTxnDaoImp implements IssueTxnDao {
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Transactional("lvbVisa")
	@Override
	public void save(IssueTxn issueTxn) throws SQLException, HibernateException {
		hibernateTemplate.save(issueTxn);
	}
	@Transactional("lvbVisa")
	@Override
	public List<IssueTxn> getIssuesByDate(Date date) throws SQLException,
			HibernateException {
		String hql = "from IssueTxn d where d.date = :date";
		return toList(hibernateTemplate.findByNamedParam(hql, "date", date));
	}
	
	@Transactional("lvbVisa")
	@Override
	public List<IssueTxn> getIssues(Date date, String card, String ref,
			String trace) throws SQLException, HibernateException {
		String hql = "select d from IssueTxn as d inner join d.visaTranx as v where d.date = :date and v.card like :card and v.refer like :refer and v.trace like :trace";
		String[] paramNames = {"date","card","refer","trace"};
		Object[] values = {date, card, ref, trace};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	@Transactional("lvbVisa")
	@Override
	public List<IssueTxn> getIssOutgoing(Date date, String memId)
			throws SQLException, HibernateException {
		String hql = "select s from IssueTxn as s inner join on s.visaTranx as v where v.issId = :memId and s.date = :date and s.proc in ('500001','600001')";
		String[] paramNames = {"memId","date"};
		Object[] values = {memId,date};
		return toList(hibernateTemplate.findByNamedParam(hql,paramNames,values));
	}
	@Transactional("lvbVisa")
	@Override
	public List<IssueTxn> getAcqOutgoing(Date date, String memId)
			throws SQLException, HibernateException {
		String hql = "select s from IssueTxn as s inner join on s.visaTranx as v where s.date = :date and v.issId = :memId and s.proc in ('500002','700001','800001')";
		String[] paramNames = {"date","memId"};
		Object[] values = {date, memId};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	
	@Transactional("lvbVisa")
	@Override
	public List<IssueTxn> getIssIncoming(Date date, String memId)
			throws SQLException, HibernateException {
		String hql= "select s from IssueTxn as s inner join on s.visaTranx as vwhere s.date = :date and v.acqId= :memId and s.proc in ('500002','700001','800001')";
		String[] paramNames = {"date","memId"};
		Object[] values = {date, memId};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	@Transactional("lvbVisa")
	@Override
	public List<IssueTxn> getAcqIncoming(Date date, String memId)
			throws SQLException, HibernateException {
		String hql = "select s from IssueTxn as s inner join on s.visaTranx as v where s.date = :date and v.acqId = :memId and s.proc in ('500001','600001')";
		String[] paramNames = {"date","memId"};
		Object[] values = {date, memId};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	
	@Transactional("lvbVisa")
	@Override
	public List<IssueTxn> getIssueTxnIssByProc(Date date, String memId, String proc)
			throws SQLException, HibernateException {
		String hql = "select s from IssueTxn as s innner join on s.visaTranx as v where s.date = :date and v.issId = :memId and s.proc = :proc";
		String[] paramNames = {"date","memId","proc"};
		Object[] values = {date, memId, proc};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	@Transactional("lvbVisa")
	@Override
	public List<IssueTxn> getIssueTxnAcqByProc(Date date, String memId, String proc)
			throws SQLException, HibernateException {
		String hql = "select s from IssueTxn as s innner join on s.visaTranx as v where s.date = :date and v.acqId = :memId and s.proc = :proc";
		String[] paramNames = {"date","memId","proc"};
		Object[] values = {date, memId, proc};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	@Transactional("lvbVisa")
	@Override
	public IssueTxn getIssueTxn(IssueTxn is) throws SQLException,
			HibernateException {
		String hql = "select s from IssueTxn as s inner join s.visaTranx as v "
				+ "	inner join s.procCode as p "
				+ "where p.code = :code and v.card = :card and v.refer = :refer and v.trace = :trace";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		query.setString("code", is.getProcCode().getCode());
		query.setString("card", is.getVisaTranx().getCard());
		query.setString("refer", is.getVisaTranx().getRefer());
		query.setString("trace", is.getVisaTranx().getTrace());
		return (IssueTxn) query.uniqueResult();
	}
	@Transactional("lvbVisa")
	@Override
	public IssueTxn getIssueTxnByProc(IssueTxn is, String code)
			throws SQLException, HibernateException {
		String hql = "select s from IssueTxn as s inner join s.visaTranx as v "
				+ "	inner join s.procCode as p "
				+ "where p.code = :code and v.card = :card and v.refer = :refer and v.trace = :trace";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		query.setString("code", code);
		query.setString("card", is.getVisaTranx().getCard());
		query.setString("refer", is.getVisaTranx().getRefer());
		query.setString("trace", is.getVisaTranx().getTrace());
		return (IssueTxn) query.uniqueResult();
	}
	private List<IssueTxn> toList(List<?> beans){
		if(beans==null) return new ArrayList<IssueTxn>();
		if(beans.isEmpty()) return new ArrayList<IssueTxn>();
		int size = beans.size();
		IssueTxn[] list =new IssueTxn[size];
		list = beans.toArray(list);
		return Arrays.asList(list);
	}
}
