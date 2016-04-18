package bcel.cc.lvb.visa.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.entity.ReasonCode;

public interface ReasonCodeDao {
	public ReasonCode getReasonCode(String code) throws SQLException, HibernateException;
	public List<ReasonCode> getReasonCodes(String proc) throws SQLException, HibernateException;
	public List<ReasonCode> getReasonCodes() throws SQLException, HibernateException;;
}
