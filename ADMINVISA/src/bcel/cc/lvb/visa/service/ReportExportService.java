package bcel.cc.lvb.visa.service;

import java.io.File;
import java.sql.Date;

public interface ReportExportService {
	public void export(Date date, File file, String memId);
}
