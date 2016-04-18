package bcel.cc.lvb.visa.context;

import java.util.ArrayList;
import java.util.List;

import bcel.cc.lvb.visa.entity.Member;
import bcel.cc.lvb.visa.entity.User;
import bcel.cc.lvb.visa.entity.VisaTranx;

public class ApplicationCache {
	private static List<VisaTranx> importCache = new ArrayList<VisaTranx>();
	private static User currentUser = new User();
	public static List<VisaTranx> getImportCache(){
		return importCache;
	}
	public static User getCurrentUser() {
		Member member = new Member();
		member.setMemId("621354");
		currentUser.setUsrId("i0712326@gmail.com");
		currentUser.setMember(member);
		return currentUser;
	}
}
