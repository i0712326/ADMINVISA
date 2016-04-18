package bcel.cardcenter.lvb.visa.ui.his;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.context.MainApplicationContext;
import bcel.cc.lvb.visa.dao.ReasonCodeDao;
import bcel.cc.lvb.visa.entity.ProcCode;
import bcel.cc.lvb.visa.entity.ReasonCode;

public class ReasonCodeBoxAction implements ActionListener {
	private Logger logger = Logger.getLogger(getClass());
	private JComboBox<ProcCode> procCodeBox;
	private JComboBox<ReasonCode> reasonCodeBox;
	
	public ReasonCodeBoxAction(JComboBox<ProcCode> procCodeBox,
			JComboBox<ReasonCode> reasonCodeBox) {
		this.procCodeBox = procCodeBox;
		this.reasonCodeBox = reasonCodeBox;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ReasonCodeDao reasonCodeDao = (ReasonCodeDao) MainApplicationContext.getBean("reasonCodeDao");
			ProcCode procCodeItem = (ProcCode) procCodeBox.getSelectedItem();
			String proc =procCodeItem.getCode().trim();
			List<ReasonCode> list = reasonCodeDao.getReasonCodes(proc);
			reasonCodeBox.removeAllItems();
			for(ReasonCode item : list)
				reasonCodeBox.addItem(item);
		} catch (HibernateException | SQLException e1) {
			logger.debug("Exception occured while try to fetch reason code list", e1);
		}
	}

}
