package bcel.cc.lvb.visa.dao;

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

import bcel.cc.lvb.visa.entity.ReasonCode;

public class ReasonCodeDaoImp implements ReasonCodeDao {
	private SessionFactory sessionFactory;
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Transactional("lvbVisa")
	@Override
	public ReasonCode getReasonCode(String code) throws SQLException,
			HibernateException {
		String hql = "from ReasonCode rc where rc.code = :code";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		return (ReasonCode) query.uniqueResult();
	}
	@Transactional("lvbVisa")
	@Override
	public List<ReasonCode> getReasonCodes(String proc) throws SQLException,
			HibernateException {
		String hql = "select rc from ReasonCode rc inner join rc.procCode pc where pc.code = :proc";
		return toList(hibernateTemplate.findByNamedParam(hql, "proc", proc));
	}
	
	@Override
	public List<ReasonCode> getReasonCodes() throws SQLException,
			HibernateException {
		String hql = "from ReasonCode rc order by rc.code";
		return  toList(hibernateTemplate.find(hql));
	}
	private List<ReasonCode> toList(final List<?> beans){
		if(beans==null) return new ArrayList<ReasonCode>();
		if(beans.isEmpty()) return new ArrayList<ReasonCode>();
		int size = beans.size();
		ReasonCode[] list = new ReasonCode[size];
		list = beans.toArray(list);
		return Arrays.asList(list);
	}

}
