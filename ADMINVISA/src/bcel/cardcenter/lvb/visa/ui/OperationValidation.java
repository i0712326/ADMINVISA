package bcel.cardcenter.lvb.visa.ui;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.context.MainApplicationContext;
import bcel.cc.lvb.visa.dao.IssueTxnDao;
import bcel.cc.lvb.visa.entity.IssueTxn;
import bcel.cc.lvb.visa.entity.ProcCode;
import bcel.cc.lvb.visa.entity.User;
import bcel.cc.lvb.visa.entity.VisaTranx;

public class OperationValidation {
	private Logger logger = Logger.getLogger(getClass());
	private IssueTxnDao issueTxnDao = (IssueTxnDao) MainApplicationContext.getBean("issueTxnDao");
	public boolean checkData(IssueTxn is){
		boolean check = true;
		IssueTxn iss;
		try {
			// check duplication
			iss = issueTxnDao.getIssueTxn(is);
			boolean chk = (iss == null);
			check = check && chk;
			if(!chk){
				JOptionPane.showMessageDialog(null,
					    "Duplicated transaction.",
					    "Processing Error.",
					    JOptionPane.ERROR_MESSAGE);
			}
			// check whether issuer or acquirer action
			// 500001, 600001 ---> issuer
			User usr = is.getUser();
			ProcCode procCode = is.getProcCode();
			String code = procCode.getCode();
			if(code.equals("500001")||code.equals("600001")){
				VisaTranx v = is.getVisaTranx();
				String memId = usr.getMember().getMemId();
				String issId = v.getIssId();
				boolean chk1 =  (issId.equals(memId));
				check = check && chk1;
				if(!chk1){
					JOptionPane.showMessageDialog(null,
						    "Invalid issuer operation is not allowed.",
						    "Processing Error.",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			// 500002, 700001, 800001 ---> acquire
			if(code.equals("500002")||code.equals("700001")||code.equals("800001")){
				VisaTranx v = is.getVisaTranx();
				String memId = usr.getMember().getMemId();
				String acqId = v.getAcqId();
				boolean chk2 = (acqId.equals(memId));
				check = check && chk2;
				if(!chk2){
					JOptionPane.showMessageDialog(null,
						    "Invalid acquirer operation is not allowed.",
						    "Processing Error.",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			
			// check for 500001 transaction must blank
			boolean chk3 = false;
			if(code.equals("500001")){
				chk3 = issueTxnDao.getIssueTxnByProc(is, "600001")==null;
				chk3 = chk3 && issueTxnDao.getIssueTxnByProc(is, "500002")==null;
				chk3 = chk3 && issueTxnDao.getIssueTxnByProc(is, "600001")==null;
				chk3 = chk3 && issueTxnDao.getIssueTxnByProc(is, "700001")==null;
				chk3 = chk3 && issueTxnDao.getIssueTxnByProc(is, "800001")==null;
				check = check && chk3;
				if(!chk3){
					JOptionPane.showMessageDialog(null,
						    "Invalid retrieval request is not allowed.",
						    "Processing Error.",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			
			// check for 500002 must has 50001 first
			boolean chk4 = false;
			if(code.equals("500002")){
				chk4 = issueTxnDao.getIssueTxnByProc(is, "500001")!=null;
				check = check & chk4;
				if(!chk4){
					JOptionPane.showMessageDialog(null,
						    "Invalid fulfillment is not allowed.",
						    "Processing Error.",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			
			// check for 600001 must not has 700001, 800001
			boolean chk5 = false;
			if(code.equals("600001")){
				chk5 = issueTxnDao.getIssueTxnByProc(is, "700001")==null && issueTxnDao.getIssueTxnByProc(is,"800001")==null;
				check = check && chk5;
				if(!chk5){
					JOptionPane.showMessageDialog(null,
						    "Invalid charge back is not allowed.",
						    "Processing Error.",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			// check for 700001 must not has 600001
			boolean chk6 = false;
			if(code.equals("700001")){
				chk6 = issueTxnDao.getIssueTxnByProc(is, "600001")==null;
				if(!chk6){
					JOptionPane.showMessageDialog(null,
						    "Invalid adjustment is not allowed.",
						    "Processing Error.",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			// check for 800001 must has 600001
			boolean chk7 = false;
			if(code.equals("800001")){
				chk7 = issueTxnDao.getIssueTxnByProc(is, "600001")!=null;
				if(!chk7){
					JOptionPane.showMessageDialog(null,
						    "Invalid re-presentment is not allowed.",
						    "Processing Error.",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} catch (HibernateException | SQLException e) {
			logger.debug("Exception occured while try to try to fetch issue transacion", e);
		}
		return check;
	}
}
