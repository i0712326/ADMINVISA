package bcel.cc.lvb.visa.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cc.lvb.visa.entity.Member;

public class MemberDaoImp implements MemberDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public Member getMember(final String memId) throws SQLException,
			HibernateException {
		return hibernateTemplate.execute(new HibernateCallback<Member>(){

			@Override
			public Member doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from Member as m where m.memId = :memId";
				Query query = session.createQuery(hql);
				query.setString("memId", memId);
				return (Member) query.uniqueResult();
			}
			
		});
	}

}
