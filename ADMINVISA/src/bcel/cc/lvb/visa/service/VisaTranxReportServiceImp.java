package bcel.cc.lvb.visa.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import bcel.cc.lvb.visa.context.ISOMsgPackager;
import bcel.cc.lvb.visa.entity.IssueTxn;
import bcel.cc.lvb.visa.entity.VisaTranx;
import bcel.cc.lvb.visa.util.UtilPackage;

public class VisaTranxReportServiceImp implements VisaTranxReportService {
	private Logger logger = Logger.getLogger(VisaTranxReportServiceImp.class);
	private int count =1;
	@Override
	public void writeHeader(Date date, String issId, String acqId, File file) throws IOException{
		FileOutputStream fos = new FileOutputStream(file);
		byte[] data = getHeader(date, issId, acqId);
		fos.write(data);
		fos.close();
	}
	@Override
	public void writeOnline(List<VisaTranx> list, File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file, true);
		Iterator<VisaTranx> iter = list.iterator();
		while(iter.hasNext()){
			byte[] data = getVisaTranxBytes(iter.next());
			fos.write(data);
			count++;
		}
		fos.close();
		return;
	}
	@Override
	public void writeDispute(List<IssueTxn> list, File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file, true);
		Iterator<IssueTxn> iter = list.iterator();
		while(iter.hasNext()){
			byte[] data = getVisaTranxBytes(iter.next());
			fos.write(data);
			count++;
		}
		fos.close();
		return;
	}
	@Override
	public void writeTailer(long num, long amt, File file) throws IOException{
		FileOutputStream fos = new FileOutputStream(file, true);
		byte[] data = getTrailer(num,amt);
		fos.write(data);
		fos.close();
	}
	/**
	 * Header consist of MTI as 0510 and fields consist (15, 99, 100)
	 * @param date
	 * @param issId
	 * @param acqId
	 * @return header data in byte array
	 */
	// write online data to file
	private byte[] getHeader(Date date, String issId, String acqId){
		ISOMsg isoMsg = new ISOMsg();
		String mti = "0510";
		try{
			isoMsg.setMTI(mti);
			isoMsg.set( 15, UtilPackage.date2Str(date, "yyMMDD"));
			isoMsg.set( 99, issId);
			isoMsg.set(100, acqId);
			isoMsg.setPackager(ISOMsgPackager.getPackager());
			return isoMsg.pack();
		}
		catch(ISOException e){
			logger.debug("Exception occured while try to write header", e);
		}
		return null;
	}
	/**
	 * online transaction field (2,3,4,7,11,12,32,33,37,38,39,41,46,81)
	 * @param v
	 * @return byte array of visa transaction ( success, error, reversal )
	 */
	private byte[] getVisaTranxBytes(VisaTranx v){
		ISOMsg isoMsg = new ISOMsg();
		try {
			String date = UtilPackage.date2Str(v.getDate());
			String time	= v.getTime();
			String amount = String.format("%012d", (int)v.getAmount()*100);
			String fee = String.format("%012d", (int)v.getFee()*100);
			String msgNumber = String.format("%08d", count);
			
			isoMsg.setMTI(v.getMti());
			isoMsg.set( 2, v.getCard());
			isoMsg.set( 3, v.getProc());
			isoMsg.set( 4, amount);
			isoMsg.set( 7, date);
			isoMsg.set(11, v.getTrace());
			isoMsg.set(12, date+time);
			isoMsg.set(32, v.getAcqId());
			isoMsg.set(33, v.getIssId());
			isoMsg.set(37, v.getRefer());
			isoMsg.set(38, v.getAppCode());
			isoMsg.set(39, v.getRes());
			isoMsg.set(41, v.getTermId());
			isoMsg.set(46, fee);
			isoMsg.set(81, msgNumber);
			isoMsg.setPackager(ISOMsgPackager.getPackager());
			
			return isoMsg.pack();
		} catch (ISOException e) {
			logger.debug("Exception occured while try to export iso message to byte array", e);
		}
		
		return null;
	}
	/**
	 * MTI 0200 field (2,3,4,7,11,12,
	 * @param is
	 * @return
	 */
	private byte[] getVisaTranxBytes(IssueTxn is){
		ISOMsg isoMsg = new ISOMsg();
		try {
			VisaTranx v = is.getVisaTranx();
			String date = UtilPackage.date2Str(is.getDate());
			String time	= is.getTime();
			String amount = String.format("%012d", (int)v.getAmount()*100);
			String fee = String.format("%012d", (int)is.getProcCode().getAcqFee()*100);
			String msgNumber = String.format("%08d", count);
			
			isoMsg.setMTI(v.getMti());
			isoMsg.set( 2, v.getCard());
			isoMsg.set( 3, is.getProcCode().getCode());
			isoMsg.set( 4, amount);
			isoMsg.set( 7, date);
			isoMsg.set(11, v.getTrace());
			isoMsg.set(12, date+time);
			isoMsg.set(25, is.getReasonCode().getCode());
			isoMsg.set(32, v.getAcqId());
			isoMsg.set(33, v.getIssId());
			isoMsg.set(37, v.getRefer());
			isoMsg.set(38, v.getAppCode());
			isoMsg.set(41, v.getTermId());
			isoMsg.set(46, fee);
			isoMsg.set(81, msgNumber);
			isoMsg.set(101, is.getSupport());
			isoMsg.set(104, is.getNote());
			isoMsg.setPackager(ISOMsgPackager.getPackager());
			return isoMsg.pack();
		} catch (ISOException e) {
			logger.debug("Exception occured while try to export iso message to byte array", e);
		}
		
		return null;
	}
	/**
	 * 
	 * MTI is 0520, file trailer consist of (83,97)
	 * @param number
	 * @param net
	 * @return
	 */
	private byte[] getTrailer(long number, long net){
		ISOMsg isoMsg = new ISOMsg();
		String mti = "0520";
		try{
			isoMsg.setMTI(mti);
			isoMsg.set(83, String.format("%010d", number));
			isoMsg.set(97, String.format("%017d", net));
			isoMsg.setPackager(ISOMsgPackager.getPackager());
			return isoMsg.pack();
		}
		catch(ISOException e){
			logger.debug("Exception occured while try to write tailer",e);
		}
		return null;
	}
}
