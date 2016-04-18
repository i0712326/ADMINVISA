package bcel.cc.lvb.visa.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import bcel.cc.lvb.visa.entity.IssueTxn;
import bcel.cc.lvb.visa.entity.VisaTranx;

public interface VisaTranxReportService {
	public void writeOnline(List<VisaTranx> list, File file) throws IOException;
	public void writeDispute(List<IssueTxn> list, File file) throws IOException;
	public void writeTailer(long num, long amt, File file) throws IOException;
	public void writeHeader(Date date, String issId, String acqId, File file) throws IOException;
	
}
