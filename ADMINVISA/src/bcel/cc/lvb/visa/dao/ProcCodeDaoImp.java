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

import bcel.cc.lvb.visa.entity.ProcCode;

public class ProcCodeDaoImp implements ProcCodeDao {
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Transactional("lvbVisa")
	@Override
	public ProcCode getProcCode(String code) throws SQLException,
			HibernateException {
		String hql = "from ProcCode c where c.code = :code";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		query.setString("code", code);
		return (ProcCode) query.uniqueResult();
	}
	@Transactional("lvbVisa")
	@Override
	public List<ProcCode> getDispCodes() throws SQLException,
			HibernateException {
		String hql = "from ProcCode c where c.cat = 'DP'";
		return toList(hibernateTemplate.find(hql));
	}
	private List<ProcCode> toList(final List<?> beans){
		if(beans==null) return new ArrayList<ProcCode>();
		if(beans.isEmpty()) return new ArrayList<ProcCode>();
		int size = beans.size();
		ProcCode[] list = new ProcCode[size];
		list = beans.toArray(list);
		return Arrays.asList(list);
	}
	
}
