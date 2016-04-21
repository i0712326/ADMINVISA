package bcel.cc.lvb.visa.service;

import java.io.File;
import java.sql.Date;
import java.util.List;

import bcel.cc.lvb.visa.entity.IssueTxn;
import bcel.cc.lvb.visa.entity.VisaTranx;

public abstract class VisaRetentionImp implements VisaRetention {
	protected List<VisaTranx> online;
	protected List<IssueTxn> dispute;
	protected List<IssueTxn> issOutgoing;
	protected List<IssueTxn> acqOutgoing;
	public abstract void replucate(Date date);
	public abstract void fetchOnline(Date date, String memId);
	public abstract void fetchDispute(Date date, String memId);
	public abstract void writeHeader(Date date, String memId, File file);
	public abstract void writeOnline(File file);
	public abstract void writeDispute(File file);
	public abstract void writeTrailer(File file);
	@Override
	public void retend(Date date, String memId, File file) {
		// duplicate data from 
		replucate(date);
		
		// fetch outgoing files
		fetchOnline(date, memId);
		fetchDispute(date, memId);
		
		// write outgoing files
		writeHeader(date, memId, file);
		writeOnline(file);
		writeDispute(file);
		writeTrailer(file);
	}

}
