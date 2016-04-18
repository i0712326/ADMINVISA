package bcel.cardcenter.lvb.visa.ui.his;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.context.MainApplicationContext;
import bcel.cc.lvb.visa.dao.VisaTranxDao;
import bcel.cc.lvb.visa.entity.VisaTranx;

public class TableActionListener implements MouseListener {
	private JTable table;
	public TableActionListener(JTable table){
		this.table = table;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
		VisaTranxDao vDao = (VisaTranxDao) MainApplicationContext.getBean("visaTranxDao2");
		int rowId = table.getSelectedRow();
		String mti 		= (String) table.getValueAt(rowId, 0);
		String card 	= (String) table.getValueAt(rowId, 3);
		String refer 	= (String) table.getValueAt(rowId, 5);
		String trace 	= (String) table.getValueAt(rowId, 6);
		
		VisaTranx v = vDao.getUnVisaTrax(mti, card, trace, refer);
			
		System.out.println(v.toString());
		
		DetailDialog dialog = new DetailDialog(new JFrame(),"DISPUTE FORM",v);
		dialog.setVisible(true);
		} catch (HibernateException | SQLException e1) {
			
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
       
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
