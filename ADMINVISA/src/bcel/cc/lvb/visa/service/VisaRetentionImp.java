package bcel.cc.lvb.visa.service;

import java.io.File;
import java.sql.Date;
import java.util.List;

import bcel.cc.lvb.visa.entity.IssueTxn;
import bcel.cc.lvb.visa.entity.VisaTranx;

public abstract class VisaRetentionImp implements VisaRetention {
	protected List<VisaTranx> online;
	protected List<IssueTxn> dispute;
	public abstract void replucate(Date date);
	public abstract void fetchOnline(Date date);
	public abstract void fetchDispute(Date date);
	public abstract void writeOnline(File file);
	public abstract void writeDispute(File file);
	@Override
	public void retend(Date date, File file) {
		replucate(date);
		fetchOnline(date);
		fetchDispute(date);
		writeOnline(file);
		writeDispute(file);
	}

}
