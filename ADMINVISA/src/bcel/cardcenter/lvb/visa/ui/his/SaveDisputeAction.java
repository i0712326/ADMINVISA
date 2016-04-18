package bcel.cardcenter.lvb.visa.ui.his;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import bcel.cardcenter.lvb.visa.ui.OperationValidation;
import bcel.cc.lvb.visa.context.ApplicationCache;
import bcel.cc.lvb.visa.context.MainApplicationContext;
import bcel.cc.lvb.visa.dao.IssueTxnDao;
import bcel.cc.lvb.visa.entity.IssueTxn;
import bcel.cc.lvb.visa.entity.ProcCode;
import bcel.cc.lvb.visa.entity.ReasonCode;
import bcel.cc.lvb.visa.entity.User;
import bcel.cc.lvb.visa.entity.VisaTranx;
import bcel.cc.lvb.visa.util.UtilPackage;

public class SaveDisputeAction implements ActionListener {
	private Logger logger = Logger.getLogger(getClass());
	private IssueTxnDao issueTxnDao	= (IssueTxnDao) MainApplicationContext.getBean("issueTxnDao");
	private VisaTranx visaTranx;
	private JComboBox<ProcCode> decisionComboBox;
	private JComboBox<ReasonCode> reasonComboBox;
	private JTextField noteField;
	private JTextField referField;
	private JTextField traceField;
	private JTextField amountField;
	private JTextField surchargeField;
	private JCheckBox partialBox;
	private JDialog dialog;
	public SaveDisputeAction(VisaTranx v, JComboBox<ProcCode> decisionComboBox,
			JComboBox<ReasonCode> reasonComboBox, JTextField noteField,
			JTextField referField, JTextField traceField,
			JTextField amountField, JTextField surchargeField, JCheckBox partialBox,
			DetailDialog detailDialog) {
		this.visaTranx = v;
		this.decisionComboBox = decisionComboBox;
		this.reasonComboBox = reasonComboBox;
		this.noteField = noteField;
		this.referField = referField;
		this.amountField = amountField;
		this.traceField = traceField;
		this.surchargeField = surchargeField;
		this.partialBox = partialBox;
		this.dialog = detailDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OperationValidation validation = new OperationValidation();
		IssueTxn issueTxn = new IssueTxn();
		ProcCode procCode = (ProcCode) decisionComboBox.getSelectedItem();
		ReasonCode reasonCode = (ReasonCode) reasonComboBox.getSelectedItem();
		java.sql.Date txnDate = new java.sql.Date(new java.util.Date().getTime());
		issueTxn.setDate(txnDate);
		issueTxn.setTime(UtilPackage.date2Str(txnDate, "HH:MM:ss"));
		issueTxn.setNote(noteField.getText().trim());
		String support = referField.getText().trim()+traceField.getText().trim()+".zip";
		issueTxn.setSupport(support);
		double amount = Double.parseDouble(amountField.getText().trim().replaceAll(",", ""));
		double fee = Double.parseDouble(surchargeField.getText().trim().replaceAll(",", ""));
		issueTxn.setAmount(amount);
		boolean partialVal = partialBox.isSelected();
		if(partialVal){
			issueTxn.setPartial("Y");
		}
		else{
			issueTxn.setFee(fee);
		}
		issueTxn.setVisaTranx(visaTranx);
		issueTxn.setProcCode(procCode);
		issueTxn.setReasonCode(reasonCode);
		User user = ApplicationCache.getCurrentUser();
		issueTxn.setUser(user);
		
		try {
			if(validation.checkData(issueTxn)){
				issueTxnDao.save(issueTxn);
				JOptionPane.showMessageDialog(null, "All data has posted successfully.");
				dialog.dispose();
			}
		} catch (HibernateException | SQLException e1) {
			JOptionPane.showMessageDialog(null,
				    "Erro occured while posting data.",
				    "Processing Error.",
				    JOptionPane.ERROR_MESSAGE);
			logger.debug("Exception occured while try to save issue", e1);
		}
		
	}
}
