package bcel.cc.lvb.visa.service;

import java.io.File;
import java.sql.Date;

public interface VisaReportBuilder {
	public void generate(Date date, String memId, File file);
}
