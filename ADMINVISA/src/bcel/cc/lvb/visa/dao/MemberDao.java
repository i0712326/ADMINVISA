package bcel.cc.lvb.visa.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.entity.Member;

public interface MemberDao {
	public Member getMember(String memId) throws SQLException, HibernateException;
}
