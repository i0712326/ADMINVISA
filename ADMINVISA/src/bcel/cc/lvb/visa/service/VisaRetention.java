package bcel.cc.lvb.visa.service;

import java.io.File;
import java.sql.Date;

public interface VisaRetention {
	public void retend(Date date, String memId, File file);
}
