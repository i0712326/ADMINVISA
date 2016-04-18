package bcel.cc.lvb.visa.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import bcel.cc.lvb.visa.dao.EntityKeyId;
@Entity
@Table(name="ONLINETBL")
@IdClass(EntityKeyId.class)
public class VisaTranx {
	@Id
	@Column(name="CARDNUMBER")
	private String card;
	@Id
	@Column(name="RRNNO")
	private String refer;
	@Id
	@Column(name="TRACENO")
	private String trace;
	@Id
	@Column(name="MTI")
	private String mti;
	@Column(name="TXNDATE")
	private Date date;
	@Column(name="TXNTIME")
	private String time;
	@Column(name="RES")
	private String res;
	@Column(name="TXNAMT")
	private double amount;
	@Column(name="TXNFEE")
	private double fee;
	@Column(name="TERMID")
	private String termId;
	@Column(name="ISSID")
	private String issId = "621354";
	@Column(name="ACQID")
	private String acqId = "220699";
	@Transient
	private String type;
	@Transient
	private String proc;
	@Column(name="APPRCODE")
	private String appCode;
	@Column(name="IMPDATE")
	private Date impDate = new Date(System.currentTimeMillis());
	@ManyToOne
	@JoinColumn(name="PROCTBL_PCODE", referencedColumnName="PCODE")
	private ProcCode procCode;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="visaTranx", orphanRemoval=true)
	private List<IssueTxn> issuesTxn;
	@ManyToOne
	@JoinColumn(name="BINTBL_BIN", referencedColumnName="BIN")
	private Bin bin;
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
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getRefer() {
		return refer;
	}
	public void setRefer(String refer) {
		this.refer = refer;
	}
	public String getTrace() {
		return trace;
	}
	public void setTrace(String trace) {
		this.trace = trace;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
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
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getIssId() {
		return issId;
	}
	public void setIssId(String issId) {
		this.issId = issId;
	}
	public String getAcqId() {
		return acqId;
	}
	public void setAcqId(String acqId) {
		this.acqId = acqId;
	}
	public String getMti() {
		return mti;
	}
	public void setMti(String mti) {
		this.mti = mti;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	public String getProc() {
		return proc;
	}
	public void setProc(String proc) {
		this.proc = proc;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public ProcCode getProcCode() {
		return procCode;
	}
	public void setProcCode(ProcCode procCode) {
		this.procCode = procCode;
	}
	public List<IssueTxn> getIssuesTxn() {
		return issuesTxn;
	}
	public void setIssuesTxn(List<IssueTxn> issuesTxn) {
		this.issuesTxn = issuesTxn;
	}
	public Bin getBin() {
		return bin;
	}
	public void setBin(Bin bin) {
		this.bin = bin;
	}
}
