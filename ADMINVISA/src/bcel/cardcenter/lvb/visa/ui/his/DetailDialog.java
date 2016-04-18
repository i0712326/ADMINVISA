package bcel.cardcenter.lvb.visa.ui.his;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import bcel.cc.lvb.visa.context.MainApplicationContext;
import bcel.cc.lvb.visa.dao.ProcCodeDao;
import bcel.cc.lvb.visa.dao.ReasonCodeDao;
import bcel.cc.lvb.visa.entity.ProcCode;
import bcel.cc.lvb.visa.entity.ReasonCode;
import bcel.cc.lvb.visa.entity.VisaTranx;
import bcel.cc.lvb.visa.util.UtilPackage;

public class DetailDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(getClass());
	private final JPanel contentPanel = new JPanel();
	private JTextField mtiField;
	private JTextField dateField;
	private JTextField timeField;
	private JTextField cardField;
	private JTextField processingField;
	private JTextField referField;
	private JTextField traceField;
	private JTextField resField;
	private JTextField amountField;
	private JTextField surchargeField;
	private JTextField terminalField;
	private JTextField noteField;
	private VisaTranx v;
	public DetailDialog(JFrame parent, String title, VisaTranx visaTranx) {
		super(parent, title, true);
		ReasonCodeDao reasonCodeDao = (ReasonCodeDao) MainApplicationContext.getBean("reasonCodeDao");
		ProcCodeDao procCodeDao = (ProcCodeDao) MainApplicationContext.getBean("procCodeDao");
		this.v = visaTranx;
		setBounds(100, 100, 630, 401);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JLabel mtiLabel = new JLabel("MTI");
		mtiLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		mtiField = new JTextField(v.getMti());
		mtiField.setEditable(false);
		mtiField.setColumns(10);
		
		JLabel dateLabel = new JLabel("DATE");
		dateLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		dateField = new JTextField(UtilPackage.date2Str(v.getDate(),"MMdd"));
		dateField.setEditable(false);
		dateField.setColumns(10);
		
		JLabel timeLabel = new JLabel("TIME");
		timeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		timeField = new JTextField(v.getTime());
		timeField.setEditable(false);
		timeField.setColumns(10);
		
		JLabel cardLabel = new JLabel("CARD NUMBER");
		cardLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		cardField = new JTextField(v.getCard());
		cardField.setEditable(false);
		cardField.setColumns(10);
		
		JLabel processingLabel = new JLabel("PROCESSIN CODE");
		processingLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		processingField = new JTextField(v.getProcCode().getCode());
		processingField.setEditable(false);
		processingField.setColumns(10);
		
		JLabel referLabel = new JLabel("REFFERENCE");
		referLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		referField = new JTextField(v.getRefer());
		referField.setEditable(false);
		referField.setColumns(10);
		
		JLabel traceLabel = new JLabel("TRACE");
		traceLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		traceField = new JTextField(v.getTrace());
		traceField.setEditable(false);
		traceField.setColumns(10);
		
		JLabel processingCodeLabel = new JLabel("RESPONSE CODE");
		processingCodeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		resField = new JTextField(v.getRes());
		resField.setEditable(false);
		resField.setColumns(10);
		
		JLabel amountLabel = new JLabel("AMOUNT");
		amountLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		amountField = new JTextField(UtilPackage.numFormat(v.getAmount()));
		amountField.setEditable(true);
		amountField.setColumns(10);
		
		JLabel surchargeLabel = new JLabel("SURCHARGE");
		surchargeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		surchargeField = new JTextField(UtilPackage.numFormat(v.getFee()));
		surchargeField.setEditable(true);
		surchargeField.setColumns(10);
		
		JLabel terminalLabel = new JLabel("TERMINAL ID");
		terminalLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		terminalField = new JTextField(v.getTermId());
		terminalField.setEditable(false);
		terminalField.setColumns(10);
		
		JLabel decisionLabel = new JLabel("DECISION");
		decisionLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		List<ProcCode> procCodes = null;
		try {
			procCodes = procCodeDao.getDispCodes();
		} catch (HibernateException | SQLException e) {
			logger.debug("Exception occured while try to fetch processiong codes",e);
		}
		JComboBox<ProcCode> decisionComboBox = new JComboBox<ProcCode>(new Vector<ProcCode>(procCodes));
		
		JLabel reasonLabel = new JLabel("REASON CODE");
		reasonLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		List<ReasonCode> reasonCodes = null;
		
		try {
			ProcCode procCode = (ProcCode) decisionComboBox.getSelectedItem();
			reasonCodes = reasonCodeDao.getReasonCodes(procCode.getCode());
		} catch (HibernateException | SQLException e) {
			logger.debug("Exception occured while try to fetch reason codes",e);
		}
		JComboBox<ReasonCode> reasonComboBox = new JComboBox<ReasonCode>(new Vector<ReasonCode>(reasonCodes));
		JLabel partialLabel = new JLabel("PARTIAL");
		partialLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JCheckBox partialBox = new JCheckBox("");
		
		JLabel noteLabel = new JLabel("NOTE");
		noteLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		noteField = new JTextField();
		noteField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(processingLabel)
								.addComponent(cardLabel)
								.addComponent(referLabel)
								.addComponent(traceLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(cardField, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(referField, Alignment.LEADING)
											.addComponent(processingField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))
									.addGap(18)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(terminalLabel)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(decisionLabel)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(partialLabel)
												.addComponent(reasonLabel)))))
								.addComponent(traceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(partialBox)
								.addComponent(reasonComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(decisionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(terminalField, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(8)
									.addComponent(mtiLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(mtiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(dateLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(dateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(2)
									.addComponent(timeLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(timeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(137)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(processingCodeLabel)
								.addComponent(amountLabel)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(6)
									.addComponent(surchargeLabel)))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(surchargeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(amountField, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
								.addComponent(resField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(noteLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(noteField, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(mtiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(mtiLabel)
						.addComponent(processingCodeLabel)
						.addComponent(resField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(dateLabel)
						.addComponent(dateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(amountLabel)
						.addComponent(amountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(timeLabel)
						.addComponent(timeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(surchargeLabel)
						.addComponent(surchargeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cardLabel)
						.addComponent(cardField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(terminalLabel)
						.addComponent(terminalField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(processingLabel)
						.addComponent(processingField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(decisionLabel)
						.addComponent(decisionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(referField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(reasonLabel)
							.addComponent(reasonComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(referLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(traceLabel)
						.addComponent(traceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(partialLabel)
						.addComponent(partialBox))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(noteLabel)
						.addComponent(noteField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new SaveDisputeAction(v,decisionComboBox,reasonComboBox,noteField, referField, traceField, amountField,surchargeField, partialBox, this));
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						DetailDialog.this.dispose();
					}
					
				});
			}
		}
		decisionComboBox.addActionListener(new ReasonCodeBoxAction(decisionComboBox,reasonComboBox));
	}
	
}
