package bcel.cardcenter.lvb.visa.ui.his;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import bcel.cc.lvb.visa.entity.VisaTranx;
import bcel.cc.lvb.visa.util.UtilPackage;

public class HisDefaultTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] colNames = {"MTI", "DATE", "TIME", "CARD", "PROCC", "REFER", "TRACE", "RES","AMONT","FEE","TERMINAL"};
	private static Vector<String> columnNames = new Vector<String>(11);
	static{
		columnNames.add(colNames[0]);
		columnNames.add(colNames[1]);
		columnNames.add(colNames[2]);
		columnNames.add(colNames[3]);
	    columnNames.add(colNames[4]);
		columnNames.add(colNames[5]);
		columnNames.add(colNames[6]);
		columnNames.add(colNames[7]);
		columnNames.add(colNames[8]);
		columnNames.add(colNames[9]);
		columnNames.add(colNames[10]);
	}
	@SuppressWarnings("rawtypes")
	private static Class[] colTypes = { 
										 String.class, 
										 String.class, 
										 String.class, 
										 String.class, 
										 String.class,
										 String.class,
										 String.class,
										 String.class,
										 String.class,
										 String.class,
										 String.class
									};
	public HisDefaultTableModel(Vector<VisaTranx> dataVector) {
		super(dataVector,columnNames);
		super.dataVector = dataVector;
	}

	@Override
	public int getRowCount() {
		return dataVector.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int columnIndex) {
	      return colTypes[columnIndex];
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void addRow(Vector rowData) {
		super.addRow(rowData);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vector<VisaTranx> rowData = (Vector<VisaTranx>) dataVector.elementAt(rowIndex);
		VisaTranx data = rowData.elementAt(0);
		switch (columnIndex) {
		case 0:
			return data.getMti();
		case 1:
			return UtilPackage.date2Str(data.getDate(),"MMdd");
		case 2:
			return data.getTime();
		case 3:
			return data.getCard();
		case 4:
			return data.getProcCode().getCode();
		case 5:
			return data.getRefer();
		case 6:
			return data.getTrace();
		case 7:
			return data.getRes();
		case 8:
			return UtilPackage.numFormat(data.getAmount());
		case 9:
			return UtilPackage.numFormat(data.getFee());
		case 10:
			return data.getTermId();
		}
		return new String();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
