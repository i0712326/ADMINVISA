package bcel.cc.lvb.visa.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.dao.IssueTxnDao;
import bcel.cc.lvb.visa.dao.VisaTranxDao;
import bcel.cc.lvb.visa.entity.IssueTxn;
import bcel.cc.lvb.visa.entity.VisaTranx;
import bcel.cc.lvb.visa.util.UtilPackage;

public class VisaReportBuilderImp implements VisaReportBuilder {
	private Logger logger = Logger.getLogger(getClass());
	private VisaTranxDao visaTranxDao;
	private IssueTxnDao issueTxnDao;
	// online transaction
	private ReportEntry issTxnEntry;
	private ReportEntry acqTxnEntry;
	private ReportEntry revTxnEntry;
	private ReportEntry errTxnEntry;
	// dispute transaction
	private ReportEntry oIssRrEntry;
	private ReportEntry oAcqFfEntry;
	private ReportEntry oIssCbEntry;
	private ReportEntry oAcqRpEntry;
	private ReportEntry oAcqAjEntry;
	private ReportEntry iAcqRrEntry;
	private ReportEntry iIssFfEntry;
	private ReportEntry iAcqCbEntry;
	private ReportEntry iIssRpEntry;
	private ReportEntry iIssAjEntry;
	
	public void setVisaTranxDao(VisaTranxDao visaTranxDao){
		this.visaTranxDao = visaTranxDao;
	}
	public void setIssueTxnDao(IssueTxnDao issueTxnDao){
		this.issueTxnDao = issueTxnDao;
	}
	
	@Override
	public void generate(Date date, String memId, File file) {
		// get online for issuing and acquiring

		try {
			// issuing transaction
			List<VisaTranx> issTxns = visaTranxDao.getIssVisaTranx(date, memId);
			issTxnEntry = new ReportEntry(issTxns.size(), calAmt(issTxns), calFee(issTxns));
			
			// acquiring transaction
			List<VisaTranx> acqTxns = visaTranxDao.getAcqVisaTranx(date, memId);
			acqTxnEntry = new ReportEntry(acqTxns.size(), calAmt(acqTxns), calFee(acqTxns));
			
			// error transaction
			List<VisaTranx> errTxns = visaTranxDao.getErrVisaTranx(date, memId);
			errTxnEntry = new ReportEntry(errTxns.size(), calAmt(errTxns), calFee(errTxns));
			
			// reversal transaction
			List<VisaTranx> revTxns = visaTranxDao.getRevVisaTranx(date, memId);
			revTxnEntry = new ReportEntry(revTxns.size(), calAmt(revTxns), calFee(revTxns));
			
			// get outgoing
			List<IssueTxn> oIssRr = issueTxnDao.getIssueTxnIssByProc(date, memId, "500001");
			oIssRrEntry = new ReportEntry(oIssRr.size(), calIssueAmt(oIssRr),calIssueFee(oIssRr));
			
			List<IssueTxn> oAcqFf = issueTxnDao.getIssueTxnAcqByProc(date, memId, "500002");
			oAcqFfEntry = new ReportEntry(oAcqFf.size(), calIssueAmt(oAcqFf), calIssueFee(oAcqFf));
			
			List<IssueTxn> oIssCb = issueTxnDao.getIssueTxnIssByProc(date, memId, "600001");
			oIssCbEntry = new ReportEntry(oIssCb.size(), calIssueAmt(oIssCb), calIssueFee(oIssCb));
			
			List<IssueTxn> oAcqRp = issueTxnDao.getIssueTxnAcqByProc(date, memId, "800001");
			oAcqRpEntry = new ReportEntry(oAcqRp.size(), calIssueAmt(oAcqRp), calIssueFee(oAcqRp));
			
			List<IssueTxn> oAcqAj = issueTxnDao.getIssueTxnAcqByProc(date, memId, "700001");
			oAcqAjEntry = new ReportEntry(oAcqAj.size(), calIssueAmt(oAcqAj), calIssueFee(oAcqAj));
			// get incoming
			List<IssueTxn> iAcqRr = issueTxnDao.getIssueTxnAcqByProc(date, memId, "500001");
			iAcqRrEntry = new ReportEntry(iAcqRr.size(), calIssueAmt(iAcqRr), calIssueFee(iAcqRr));
			
			List<IssueTxn> iIssFf = issueTxnDao.getIssueTxnIssByProc(date, memId, "500002");
			iIssFfEntry = new ReportEntry(iIssFf.size(), calIssueAmt(iIssFf), calIssueFee(iIssFf));
			
			List<IssueTxn> iAcqCb = issueTxnDao.getIssueTxnAcqByProc(date, memId, "600001");
			iAcqCbEntry = new ReportEntry(iAcqCb.size(), calIssueAmt(iAcqCb), calIssueFee(iAcqCb));
			
			List<IssueTxn> iIssRp = issueTxnDao.getIssueTxnIssByProc(date, memId, "800001");
			iIssRpEntry = new ReportEntry(iIssRp.size(), calIssueAmt(iIssRp), calIssueFee(iIssRp));
			
			List<IssueTxn> iIssAj = issueTxnDao.getIssueTxnIssByProc(date, memId, "700001");
			iIssAjEntry = new ReportEntry(iIssAj.size(), calIssueAmt(iIssAj), calIssueFee(iIssAj));
			
			/* write out report */
			writeHeader(memId, date, file);
			
			// financial reports (issuing, acquiring, charge back (i/o), re presentment(i/o), adjustment(i/o) 
			writeReport("ISSUING TRANSACTION", issTxns, file);
			writeReport("ACQUIRNG TRANSACTION", acqTxns, file);
			// incoming
			writeDispute("INCOMGING CHARGEBACK", memId, date, iAcqCb, file);
			writeDispute("INCOMING REPRESENTMENT", memId, date, iIssRp, file);
			writeDispute("INCOMING ADJUSTMENT", memId, date, iIssAj, file);
			// outgoing
			writeDispute("OUTGOING CHARGEBACK", memId, date, oIssCb, file);
			writeDispute("OUTGOING REPRESENTMENT", memId, date, oAcqRp, file);
			writeDispute("OUTGOING ADJUSTMENT", memId, date, oAcqAj, file);
			
			// non-financial reports (error, reversal, retrieval request (i/o), fulfillment(i/o)
			writeReport("ERROR TRANSACTION", errTxns, file);
			writeReport("REVERSAL TRANSACTION", revTxns, file);
			
			writeDispute("INCOMING RETRIEVAL REQUEST", memId, date, iAcqRr, file);
			writeDispute("INCOMING FULFILLMENT", memId, date, iIssFf, file);
			
			writeDispute("OUTGOING RETRIEVAL REQUEST", memId, date, oIssRr, file);
			writeDispute("OUTGOING FULFILLMENT", memId, date, oAcqFf, file);
			
			// summary report
			writeSummary(file);
			
		} catch (HibernateException | SQLException e) {
			logger.debug("Exception occoured while try to export reports", e);
		}
	}
	public void writeHeader(String memId, Date date, File file){

		try {
			PrintWriter pw = new PrintWriter(file);
			pw.printf("MEMBER : %s\r\n", memId);
			pw.printf("REPORT DATE : %s\r\n", UtilPackage.date2Str(date));
			pw.close();
		} catch (FileNotFoundException e) {
			logger.debug("Exception occured while try to write report to file", e);
		}
	}
	public void writeReport(String title, List<VisaTranx> list, File file){
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.printf("REPORT TITLE : %s\r\n", title);
			pw.printf("+--------+------------+------------+----------------------+------------+----------------+-----------+-------+--------------+-----------+-----------+-------+\r\n");
			pw.printf("|   NO   |    DATE    |   TIME     |       CARD           |   PROCC    |      REFER     |   TRACE   |  RES  |     AMOUNT   |    FEE    |  TERMINAL |   MTI |\r\n");
			pw.printf("+--------+------------+------------+----------------------+------------+----------------+-----------+-------+--------------+-----------+-----------+-------+\r\n");
			int id = 1;
			for (VisaTranx item : list) {
				pw.printf(
						"%5d %12s %12s %25s %10s %18s %10s %6s %16s %10s %12s %6s\r\n",
						id, UtilPackage.date2Str(item.getDate(), "MMDD"),
						item.getTime(), item.getCard(), item.getProcCode()
								.getCode(), item.getRefer(), item.getTrace(),
						item.getRes(), UtilPackage.numFormat(item.getAmount()),
						UtilPackage.numFormat(item.getFee()), item.getTermId(),
						item.getMti());
				id++;
			}
			pw.printf("+--------+------------+------------+----------------------+------------+----------------+-----------+-------+--------------+-----------+-----------+-------+\r\n");
			pw.close();
		} catch (FileNotFoundException e) {
			logger.debug("Exception occured while try to print out report", e);
		}
		
	}
	public void writeDispute(String title, String memId, Date date, List<IssueTxn> list, File file){
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.printf("REPORT TITLE : %s\r\n", title);
			pw.printf("+--------+------------+------------+----------------------+------------+----------------+-----------+-------+--------------+-----------+-----------+-------+\r\n");
			pw.printf("|   NO   |    DATE    |   TIME     |       CARD           |   PROCC    |      REFER     |   TRACE   |  RES  |     AMOUNT   |    FEE    |  TERMINAL |   MTI |\r\n");
			pw.printf("+--------+------------+------------+----------------------+------------+----------------+-----------+-------+--------------+-----------+-----------+-------+\r\n");
			int id = 1;
			for (IssueTxn item : list) {
				VisaTranx v = item.getVisaTranx();
				pw.printf(
						"%5d %12s %12s %25s %10s %18s %10s %6s %16s %10s %12s %6s\r\n",
						id, UtilPackage.date2Str(item.getDate(), "MMDD"),
						item.getTime(), v.getCard(), item.getProcCode()
								.getCode(), v.getRefer(), v.getTrace(), item
								.getReasonCode(), UtilPackage.numFormat(v
								.getAmount()), UtilPackage.numFormat(v.getFee()),
						v.getTermId(), v.getMti());
				id++;
			}
			pw.printf("+--------+------------+------------+----------------------+------------+----------------+-----------+-------+--------------+-----------+-----------+-------+\r\n");
			pw.close();
		} catch (FileNotFoundException e) {
			logger.debug("Exception occured while try to print out report", e);
		}
	}
	
	public void writeSummary(File file){
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.printf("DAILY SUMMARY\r\n");
			pw.printf("--------------------------------------------\r\n");
			pw.printf("NON FINANCIAL SUMMARY\r\n");
			pw.printf("+--------------------------+-----------------+---------------+----------------+-----------------+\r\n");
			pw.printf("|      CATEGORY            |      NUMBER     |      AMOUNT   |      FEE       |     TOTAL       |\r\n");
			pw.printf("+--------------------------+-----------------+---------------+----------------+-----------------+\r\n");
			pw.printf("|  ERROR                   |    %5s          |      %10s     |      %10s      |      %10s       |\r\n", UtilPackage.intFormat(errTxnEntry.getNumber()), UtilPackage.numFormat(errTxnEntry.getAmount()), UtilPackage.numFormat(errTxnEntry.getFee()), UtilPackage.numFormat(errTxnEntry.getTotal()));
			pw.printf("|  REVERSAL                |    %5s          |      %10s     |      %10s      |      %10s       |\r\n", UtilPackage.intFormat(revTxnEntry.getNumber()), UtilPackage.numFormat(revTxnEntry.getAmount()), UtilPackage.numFormat(revTxnEntry.getFee()), UtilPackage.numFormat(revTxnEntry.getTotal()));
			pw.printf("|  OUTGOING RETRIEVAL      |    %5s          |      %10s     |      %10s      |      %10s       |\r\n", UtilPackage.intFormat(oIssRrEntry.getNumber()), UtilPackage.numFormat(oIssRrEntry.getAmount()), UtilPackage.numFormat(oIssRrEntry.getFee()), UtilPackage.numFormat(oIssRrEntry.getTotal()));
			pw.printf("|  OUTGOING FULFILLMENT    |    %5s          |      %10s     |      %10s      |      %10s       |\r\n", UtilPackage.intFormat(oAcqFfEntry.getNumber()), UtilPackage.numFormat(oAcqFfEntry.getAmount()), UtilPackage.numFormat(oAcqFfEntry.getFee()), UtilPackage.numFormat(oAcqFfEntry.getTotal()));
			pw.printf("|  INCOMING RETRIEVAL      |    %5s          |      %10s     |      %10s      |      %10s       |\r\n", UtilPackage.intFormat(iAcqRrEntry.getNumber()), UtilPackage.numFormat(iAcqRrEntry.getAmount()), UtilPackage.numFormat(iAcqRrEntry.getFee()), UtilPackage.numFormat(iAcqRrEntry.getTotal()));
			pw.printf("|  INCOMING FULFILLMENT    |    %5s          |      %10s     |      %10s      |      %10s       |\r\n", UtilPackage.intFormat(iIssFfEntry.getNumber()), UtilPackage.numFormat(iIssFfEntry.getAmount()), UtilPackage.numFormat(iIssFfEntry.getFee()), UtilPackage.numFormat(iIssFfEntry.getTotal()));
			pw.printf("+--------------------------+-----------------+---------------+----------------+-----------------+\r\n");
			pw.printf("FINANCIAL SUMMARY\r\n");
			pw.printf("+--------------------------+-----------------+---------------+----------------+-----------------+\r\n");
			pw.printf("|      CATEGORY            |      NUMBER     |      AMOUNT   |       FEE      |      TOTAL      |\r\n");
			pw.printf("+--------------------------+-----------------+---------------+----------------+-----------------+\r\n");
			pw.printf("|  ISSUING                 |    %5s          |      %10s     |      %10s      |       %10s      |\r\n", UtilPackage.intFormat(issTxnEntry.getNumber()), UtilPackage.numFormat(issTxnEntry.getAmount()), UtilPackage.numFormat(issTxnEntry.getFee()), UtilPackage.numFormat(issTxnEntry.getTotal()));
			pw.printf("|  ACQUING                 |    %5s          |      %10s     |      %10s      |       %10s      |\r\n", UtilPackage.intFormat(acqTxnEntry.getNumber()), UtilPackage.numFormat(acqTxnEntry.getAmount()), UtilPackage.numFormat(acqTxnEntry.getFee()), UtilPackage.numFormat(acqTxnEntry.getTotal()));
			pw.printf("|  OUTGOING CHARGEBACK     |    %5s          |      %10s     |      %10s      |       %10s      |\r\n", UtilPackage.intFormat(oIssCbEntry.getNumber()), UtilPackage.numFormat(oIssCbEntry.getAmount()), UtilPackage.numFormat(oIssCbEntry.getFee()), UtilPackage.numFormat(oIssCbEntry.getTotal()));
			pw.printf("|  OUTGOING REPRESENTMENT  |    %5s          |      %10s     |      %10s      |       %10s      |\r\n", UtilPackage.intFormat(oAcqRpEntry.getNumber()), UtilPackage.numFormat(oAcqRpEntry.getAmount()), UtilPackage.numFormat(oAcqRpEntry.getFee()), UtilPackage.numFormat(oAcqRpEntry.getTotal()));
			pw.printf("|  OUTGOING ADJUSTMENT     |    %5s          |      %10s     |      %10s      |       %10s      |\r\n", UtilPackage.intFormat(oAcqAjEntry.getNumber()), UtilPackage.numFormat(oAcqAjEntry.getAmount()), UtilPackage.numFormat(oAcqAjEntry.getFee()), UtilPackage.numFormat(oAcqAjEntry.getTotal()));
			pw.printf("|  INCOMING CHARGEBACK     |    %5s          |      %10s     |      %10s      |       %10s      |\r\n", UtilPackage.intFormat(iAcqCbEntry.getNumber()), UtilPackage.numFormat(iAcqCbEntry.getAmount()), UtilPackage.numFormat(iAcqCbEntry.getFee()), UtilPackage.numFormat(iAcqCbEntry.getTotal()));
			pw.printf("|  INCOMING REPRESENTMENT  |    %5s          |      %10s     |      %10s      |       %10s      |\r\n", UtilPackage.intFormat(iIssRpEntry.getNumber()), UtilPackage.numFormat(iIssRpEntry.getAmount()), UtilPackage.numFormat(iIssRpEntry.getFee()), UtilPackage.numFormat(iIssRpEntry.getTotal()));
			pw.printf("|  INCOMING ADJUSTMENT     |    %5s          |      %10s     |      %10s      |       %10s      |\r\n", UtilPackage.intFormat(iIssAjEntry.getNumber()), UtilPackage.numFormat(iIssAjEntry.getAmount()), UtilPackage.numFormat(iIssAjEntry.getFee()), UtilPackage.numFormat(iIssAjEntry.getTotal()));
			pw.printf("+--------------------------+-----------------+---------------+----------------+-----------------+\r\n");
			double net = getNet();
			pw.printf("Net Settlement : %10s\r\n", UtilPackage.numFormat(net));
			pw.close();
		} catch (FileNotFoundException e) {
			logger.debug("Exception occured while try to export report", e);
		}
		
	}
	
	private double calAmt(List<VisaTranx> list){
		double sum = 0;
		
		for(VisaTranx item:list)
			sum+=item.getAmount();
		
		return sum;
	}
	private double calFee(List<VisaTranx> list){
		double sum = 0;
		for(VisaTranx item:list)
			sum+=item.getFee();
		
		return sum;
	}
	
	private double calIssueAmt(List<IssueTxn> list){
		double sum = 0;
		
		for(IssueTxn item:list)
			sum+=item.getVisaTranx().getAmount();
		return sum;
	}
	
	private double calIssueFee(List<IssueTxn> list){
		double sum = 0;
		for(IssueTxn item:list)
			sum+=item.getVisaTranx().getFee();
		return sum;
	}
	private double getNet(){
		return acqTxnEntry.getTotal()-issTxnEntry.getTotal()+oIssCbEntry.getTotal()-iAcqCbEntry.getTotal()+oAcqRpEntry.getTotal()-iIssRpEntry.getTotal()+oAcqAjEntry.getTotal()-iIssAjEntry.getTotal();
	}
	private class ReportEntry{
		private int number;
		private double amount;
		private double fee;
		private double total;
		public ReportEntry(int number, double amount, double fee){
			this.number = number;
			this.amount = amount;
			this.fee = fee;
			this.total = amount+fee;
		}
		public int getNumber() {
			return number;
		}
		public double getAmount() {
			return amount;
		}
		public double getFee() {
			return fee;
		}
		public double getTotal(){
			return total;
		}
	}
	
}
