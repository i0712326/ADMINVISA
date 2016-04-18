package bcel.cardcenter.lvb.visa.ui.his;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import bcel.cardcenter.lvb.visa.ui.DateModelFormater;
import bcel.cc.lvb.visa.entity.VisaTranx;

public class HisPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField referField;
	private JTextField traceField;
	private JTextField cardField;
	private JTable table;
	private UtilDateModel utilDateModel = new UtilDateModel();
	private JDatePanelImpl jDatePanelImpl = new JDatePanelImpl(utilDateModel);
	private DateModelFormater dateModelFormater = new DateModelFormater();
	private JDatePickerImpl datePicker = new JDatePickerImpl(jDatePanelImpl, dateModelFormater);
	
	public HisPanel() {
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel dateLabel = new JLabel("DATE");
		dateLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		
		JLabel referLabel = new JLabel("REFER");
		referLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		referField = new JTextField();
		referField.setText("%");
		referField.setColumns(10);
		
		JLabel traceLabel = new JLabel("TRACE");
		traceLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		traceField = new JTextField();
		traceField.setText("%");
		traceField.setColumns(10);
		
		JLabel cardLabel = new JLabel("CARD");
		cardLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		cardField = new JTextField();
		cardField.setText("%");
		cardField.setColumns(10);
		
		GroupLayout glPanel = new GroupLayout(panel);
		glPanel.setHorizontalGroup(
			glPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(glPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(referLabel)
						.addComponent(dateLabel))
					.addGap(18)
					.addGroup(glPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanel.createSequentialGroup()
							.addComponent(referField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(traceLabel))
						.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(glPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanel.createSequentialGroup()
							.addComponent(cardLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cardField, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(glPanel.createSequentialGroup()
							.addComponent(traceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(searchButton)))
					.addGap(185))
		);
		glPanel.setVerticalGroup(
			glPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glPanel.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addGroup(glPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(glPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(cardLabel)
							.addComponent(cardField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(dateLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(glPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(referLabel)
						.addComponent(referField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(traceLabel)
						.addComponent(traceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addContainerGap())
		);
		glPanel.linkSize(SwingConstants.VERTICAL, new Component[] {dateLabel, searchButton});
		datePicker.getJFormattedTextField().setEditable(true);
		panel.setLayout(glPanel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new HisDefaultTableModel(new Vector<VisaTranx>()));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		DefaultTableColumnModel colModel = (DefaultTableColumnModel)table.getColumnModel();
		
	    TableColumn colMti  = colModel.getColumn(0);
	    TableColumn colDate = colModel.getColumn(1);
	    TableColumn colTime = colModel.getColumn(2);
	    TableColumn colCard = colModel.getColumn(3);
	    TableColumn colProc = colModel.getColumn(4);
	    TableColumn colRef  = colModel.getColumn(5);
	    TableColumn colTra  = colModel.getColumn(6);
	    TableColumn colRes  = colModel.getColumn(7);
	    TableColumn colAmt  = colModel.getColumn(8);
	    TableColumn colFee  = colModel.getColumn(9);
	    TableColumn colTerm = colModel.getColumn(10);
	    
	    colMti.setPreferredWidth (30 + 1 * 20);
	    colDate.setPreferredWidth(30 + 2 * 20);
	    colTime.setPreferredWidth(30 + 2 * 20);
	    colCard.setPreferredWidth(30 + 5 * 20);
	    colProc.setPreferredWidth(30 + 4 * 20);
	    colRef.setPreferredWidth (30 + 5 * 20);
	    colTra.setPreferredWidth (30 + 2 * 20);
	    colRes.setPreferredWidth (30 + 1 * 20);
	    colAmt.setPreferredWidth (30 + 5 * 20);
	    colFee.setPreferredWidth (30 + 4 * 20);
	    colTerm.setPreferredWidth(30 + 4 * 20);
	    
	    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	    rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
	    colAmt.setCellRenderer(rightRenderer);
	    colFee.setCellRenderer(rightRenderer);
	    table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
	    
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton adjustButton = new JButton("VIEW");
		panel_2.add(adjustButton);
		searchButton.addActionListener(new SearchDataAction(datePicker, cardField, referField, traceField, table));
		adjustButton.addActionListener(new PostDisputeAction(table));
	}
	
	
}
