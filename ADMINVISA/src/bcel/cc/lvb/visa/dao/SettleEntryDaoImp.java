package bcel.cc.lvb.visa.dao;

import java.sql.Date;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cc.lvb.visa.entity.SettleEntry;

public class SettleEntryDaoImp implements SettleEntryDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public void save(SettleEntry settleEntry) throws SQLException,
			HibernateException {
		hibernateTemplate.save(settleEntry);
	}

	@Override
	public SettleEntry getSettleEntry(final Date date, final String memId)
			throws SQLException, HibernateException {
		return hibernateTemplate.execute(new HibernateCallback<SettleEntry>(){
			@Override
			public SettleEntry doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "select s from SettleEntry as s inner join s.member as m where m.id = :memId and s.date = :date";
				Query query = session.createQuery(hql);
				query.setString("memId", memId);
				query.setDate("date", date);
				return (SettleEntry) query.uniqueResult();
			}
			
		});
	}

}
