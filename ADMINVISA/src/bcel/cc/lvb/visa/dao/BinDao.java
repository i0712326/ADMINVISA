package bcel.cc.lvb.visa.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.entity.Bin;

public interface BinDao {
	public Bin getBin(String bin) throws SQLException, HibernateException;
}
