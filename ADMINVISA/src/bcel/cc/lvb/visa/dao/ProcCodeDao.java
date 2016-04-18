package bcel.cc.lvb.visa.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.entity.ProcCode;

public interface ProcCodeDao {
	public ProcCode getProcCode(String code) throws SQLException, HibernateException;
	public List<ProcCode> getDispCodes() throws SQLException, HibernateException;
}
