package bcel.cc.lvb.visa.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ISSUETBL")
public class IssueTxn {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DISPID")
	private int id;
	@Column(name="DISPDATE")
	private Date date;
	@Column(name="DISPTIME")
	private String time;
	@Column(name="NOTE")
	private String note;
	@Column(name="AMOUNT")
	private double amount;
	@Column(name="FEE")
	private double fee;
	@Column(name="PARTIAL")
	private String partial = "N";
	@Column(name="SUPPORT")
	private String support;
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ONLINETBL_CARDNUMBER",referencedColumnName="CARDNUMBER"),
		@JoinColumn(name="ONLINETBL_RRNNO", referencedColumnName="RRNNO"),
		@JoinColumn(name="ONLINETBL_MTI", referencedColumnName="MTI"),
		@JoinColumn(name="ONLINETBL_TRACENO", referencedColumnName="TRACENO")
	})
	private VisaTranx visaTranx;
	@ManyToOne
	@JoinColumn(name="PROCTBL_PCODE", referencedColumnName="PCODE")
	private ProcCode procCode;
	@ManyToOne
	@JoinColumn(name="RCODETBL_RCODE", referencedColumnName="RCODE" )
	private ReasonCode reasonCode;
	@ManyToOne
	@JoinColumn(name="USRTBL_USERID", referencedColumnName="USERID")
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSupport() {
		return support;
	}
	public void setSupport(String support) {
		this.support = support;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getPartial() {
		return partial;
	}
	public void setPartial(String partial) {
		this.partial = partial;
	}
	public VisaTranx getVisaTranx() {
		return visaTranx;
	}
	public void setVisaTranx(VisaTranx visaTranx) {
		this.visaTranx = visaTranx;
	}
	public ProcCode getProcCode() {
		return procCode;
	}
	public void setProcCode(ProcCode procCode) {
		this.procCode = procCode;
	}
	public ReasonCode getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(ReasonCode reasonCode) {
		this.reasonCode = reasonCode;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
