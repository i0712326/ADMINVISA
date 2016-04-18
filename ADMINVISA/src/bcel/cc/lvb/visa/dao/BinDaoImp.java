package bcel.cc.lvb.visa.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import bcel.cc.lvb.visa.entity.Bin;

public class BinDaoImp implements BinDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Transactional("libVisa")
	@Override
	public Bin getBin(String bin) throws SQLException, HibernateException {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Bin b where b.bin = :bin");
		query.setString("bin", bin);
		return (Bin) query.uniqueResult();
	}

}
