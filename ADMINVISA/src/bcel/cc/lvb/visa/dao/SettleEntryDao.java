package bcel.cc.lvb.visa.dao;

import java.sql.Date;
import java.sql.SQLException;

import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.entity.SettleEntry;

public interface SettleEntryDao {
	public void save(SettleEntry settleEntry) throws SQLException, HibernateException;
	public SettleEntry getSettleEntry(Date date, String memId) throws SQLException, HibernateException;
}
