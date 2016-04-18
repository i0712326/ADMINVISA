package bcel.cardcenter.lvb.visa.ui.his;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.context.MainApplicationContext;
import bcel.cc.lvb.visa.dao.VisaTranxDao;
import bcel.cc.lvb.visa.entity.VisaTranx;

public class PostDisputeAction implements ActionListener {
	private Logger logger = Logger.getLogger(getClass());
	private JTable table;
	public PostDisputeAction(JTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int rowIndex = table.getSelectedRow();
			if (rowIndex < 0) {
				JOptionPane.showMessageDialog(null, "No Selected Data",
						"Processing Error.", JOptionPane.ERROR_MESSAGE);
				return;
			}

			VisaTranxDao vDao = (VisaTranxDao) MainApplicationContext
					.getBean("visaTranxDao2");
			int rowId = table.getSelectedRow();
			String mti = (String) table.getValueAt(rowId, 0);
			String card = (String) table.getValueAt(rowId, 3);
			String refer = (String) table.getValueAt(rowId, 5);
			String trace = (String) table.getValueAt(rowId, 6);

			VisaTranx v = vDao.getUnVisaTrax(mti, card, trace, refer);

			System.out.println(v.toString());

			DetailDialog dialog = new DetailDialog(new JFrame(),
					"DISPUTE FORM", v);
			dialog.setVisible(true);
		} catch (HibernateException | SQLException e1) {
			logger.debug("Exception occured while try to post dispute", e1);
		}

	}
}
