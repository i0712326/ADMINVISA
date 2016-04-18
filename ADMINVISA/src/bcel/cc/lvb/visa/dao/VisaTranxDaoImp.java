package bcel.cc.lvb.visa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import bcel.cc.lvb.visa.entity.VisaTranx;

public class VisaTranxDaoImp implements VisaTranxDao {
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Transactional("lvbVisa")
	@Override
	public void saveBulk(final List<VisaTranx> list) throws SQLException,
			HibernateException {
		hibernateTemplate.execute(new HibernateCallback<Void>(){
			@Override
			public Void doInHibernate(Session session)
					throws HibernateException, SQLException {
				int count = 1;
				for(VisaTranx v : list){
					session.save(v);
					if(count%50==0){
						session.flush();
						session.clear();
					}
					count++;
				}
				return null;
			}
			
		});
	}
	@Transactional("lvbVisa")
	@Override
	public List<VisaTranx> getVisaTranx(Date date, String acqId)
			throws SQLException, HibernateException {
		String hql = "from VisaTranx v where v.date = :date and v.acqId = :acqId";
		String[] paramNames = {"date","acqId"};
		Object[] values = {date, acqId};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	
	@Transactional("bcelPool")
	@Override
	public List<VisaTranx> getVisaTranx(final Date date) throws SQLException,	HibernateException {
		return toList(hibernateTemplate.execute(new HibernateCallback<List<VisaTranx>>(){
			@Override
			public List<VisaTranx> doInHibernate(Session session)
					throws HibernateException, SQLException {
				VisaWork visaWk = new VisaWork(date);
				session.doWork(visaWk);
				return visaWk.getVisa();
			}
			
		}));
	}
	@Transactional("lvbVisa")
	@Override
	public List<VisaTranx> getIssVisaTranx(Date date, String memId)
			throws SQLException, HibernateException {
		String hql = "from VisaTranx v where v.date = :date and v.issId = :memId order by v.time";
		String[] paramNames = {"date", "memId"};
		Object[] values = {date, memId};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	@Transactional("lvbVisa")
	@Override
	public List<VisaTranx> getAcqVisaTranx(Date date, String memId)
			throws SQLException, HibernateException {
		String hql = "from VisaTranx v where v.date = :date and v.acqId = :memId order by v.time";
		String[] paramNames = {"date", "memId"};
		Object[] values = {date, memId};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	
	@Override
	public List<VisaTranx> getErrVisaTranx(Date date, String memId)
			throws SQLException, HibernateException {
		String hql = "from VisaTranx v where v.date = :date and v.res != '00' and (v.acqId = :memId or v.issId = :memId)";
		String[] paramNames = {"date","memId"};
		Object[] values= {date, memId};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	@Transactional("lvbVisa")
	@Override
	public List<VisaTranx> getRevVisaTranx(Date date, String memId)
			throws SQLException, HibernateException {
		String hql = "from VisaTranx v where v.date = :date and v.mti = '0420' and (v.acqId = :memId or v.issId = :memId)";
		String[] paramNames = {"date","memId"};
		Object[] values= {date, memId};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	
	@Transactional("lvbVisa")
	@Override
	public VisaTranx getUnVisaTrax(String mti, String card, String trace,
			String refer) throws SQLException, HibernateException {
		String hql = "from VisaTranx as v where v.mti = :mti and v.trace = :trace and v.refer = :refer and v.card = :card";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		query.setString("mti", mti);
		query.setString("trace", trace);
		query.setString("refer", refer);
		query.setString("card", card);
		return (VisaTranx) query.uniqueResult();
	}
	@Transactional("lvbVisa")
	@Override
	public List<VisaTranx> getVisaTranx(Date date, String card, String trace,
			String refer) throws SQLException, HibernateException {
		String hql = "from VisaTranx as v where v.date = :date and v.trace like :trace and v.refer like :refer and v.card like :card order by v.time";
		String[] paramNames = {"date","trace","refer","card"};
		Object[] values = {date, trace, refer, card};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values));
	}
	private List<VisaTranx> toList(List<?> beans){
		if(beans==null) return new ArrayList<VisaTranx>();
		if(beans.isEmpty()) return new ArrayList<VisaTranx>();
		int size = beans.size();
		VisaTranx[] list = new VisaTranx[size];
		list = beans.toArray(list);
		return Arrays.asList(list);
	}
	// process inner class
	private class VisaWork implements Work{
		private List<VisaTranx> visa = new ArrayList<VisaTranx>();
		private Date date;
		public VisaWork(Date date){
			this.date = date;
		}
		@Override
		public void execute(Connection conn) throws SQLException {
			String sql = "SELECT MTI, CARD, CODE, TXNDATE, RRNO, AMOUNT, TERMID, APP, RES, TRACENO, TXNTIME, ACQ, TXNTYPE, CHARGE FROM "
					+"(select distinct(tcl.sys_trace_no) as uniq,  decode(cat.type_of_details,'R','0420','0200') as MTI, cat.card_no as CARD, tcl.proccode as CODE, to_date(to_char(cat.maker_dttm,'YYYY-MM-DD') ,'YYYY-MM-DD') as TXNDATE, cat.rrno as RRNO, trunc(cat.txn_amt,2) as AMOUNT, tcl.termid as TERMID, tcl.charge as FEE, tcl.adv_authid as APP, tcl.response_code as RES, tcl.sys_trace_no as TRACENO, to_char(cat.maker_dttm,'HH24:MI:SS') as TXNTIME, cat.acq_id as ACQ, tcl.proccode as PROCCODE, cat.type_of_details as TXNTYPE, decode(substr(tcl.proccode,0,1),'3',0,'0',tcl.charge - 5000) as CHARGE from authctl tcl, txn_details td, card_acct_txn cat  where tcl.uniqueid = td.txn_ref_no  and td.txn_ref_no = cat.txn_ref_no  and tcl.card_no like '4%'  and cat.acq_id = '220699' and tcl.response_code = '00' and tcl.msgtype = '0200' and cat.rrno not in  ( select c.rrno from card_acct_txn c where c.type_of_details = 'R' )  and to_date(to_char(cat.maker_dttm,'YYYY-MM-DD'),'YYYY-MM-DD') = ?  "
					+ "union  select distinct(tcl.sys_trace_no) as uniq,  decode(cat.type_of_details,'R','0420','0200') as MTI, cat.card_no as CARD, tcl.proccode as CODE, to_date(to_char(cat.maker_dttm,'YYYY-MM-DD') ,'YYYY-MM-DD') as TXNDATE, cat.rrno as RRNO, trunc(cat.txn_amt,2) as AMOUNT, tcl.termid as TERMID, tcl.charge as FEE, tcl.adv_authid as APP, tcl.response_code as RES, tcl.sys_trace_no as TRACENO, to_char(cat.maker_dttm,'HH24:MI:SS') as TXNTIME, cat.acq_id as ACQ, tcl.proccode as PROCCODE, cat.type_of_details as TXNTYPE, decode(substr(tcl.proccode,0,1),'3',0,'0',tcl.charge - 5000) as CHARGE  from authctl tcl, txn_details td, card_acct_txn cat  where tcl.uniqueid = td.txn_ref_no  and td.txn_ref_no = cat.txn_ref_no  and tcl.card_no like '4%'  and cat.acq_id = '220699'  and tcl.response_code != '00'  and tcl.msgtype = '0200'  and to_date(to_char(cat.maker_dttm,'YYYY-MM-DD'),'YYYY-MM-DD') = ? "
					+ "union  select distinct(tcl.sys_trace_no) as uniq,  decode(cat.type_of_details,'R','0420','0200') as MTI, cat.card_no as CARD, tcl.proccode as CODE, to_date(to_char(cat.maker_dttm,'YYYY-MM-DD') ,'YYYY-MM-DD') as TXNDATE, cat.rrno as RRNO, trunc(cat.txn_amt,2) as AMOUNT, tcl.termid as TERMID, tcl.charge as FEE, tcl.adv_authid as APP, tcl.response_code as RES, tcl.sys_trace_no as TRACENO, to_char(cat.maker_dttm,'HH24:MI:SS') as TXNTIME, cat.acq_id as ACQ, tcl.proccode as PROCCODE, cat.type_of_details as TXNTYPE, decode(substr(tcl.proccode,0,1),'3',0,'0',tcl.charge - 5000) as CHARGE  from authctl tcl, txn_details td, card_acct_txn cat  where tcl.uniqueid = td.txn_ref_no and td.txn_ref_no = cat.txn_ref_no  and tcl.card_no like '4%'  and cat.acq_id = '220699'  and tcl.response_code = '00'  and tcl.msgtype = '0200'  and cat.type_of_details ='R'  and to_date(to_char(cat.maker_dttm,'YYYY-MM-DD'),'YYYY-MM-DD') = ? ) TBL";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setDate(1, date);
			stat.setDate(2, date);
			stat.setDate(3, date);
			ResultSet rs = stat.executeQuery();
			while(rs.next()){
				VisaTranx v = new VisaTranx();
				v.setMti(rs.getString("MTI"));
				v.setCard(rs.getString("CARD"));
				v.setDate(rs.getDate("TXNDATE"));
				v.setTime(rs.getString("TXNTIME"));
				v.setAmount(rs.getDouble("AMOUNT"));
				v.setProc(rs.getString("CODE"));
				v.setRefer(rs.getString("RRNO"));
				v.setRes(rs.getString("RES"));
				v.setFee(rs.getDouble("CHARGE"));
				v.setTermId(rs.getString("TERMID"));
				v.setAcqId(rs.getString("ACQ"));
				v.setType(rs.getString("TXNTYPE"));
				v.setAppCode(rs.getString("APP"));
				v.setTrace(rs.getString("TRACENO"));
				visa.add(v);
			}
		}
		
		public List<VisaTranx> getVisa(){
			return visa;
		}
	}
	
}
