package bcel.cc.lvb.visa.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="SETLTBL")
public class SettleEntry {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SETLID")
	private long id;
	@Column(name="SETLDATE")
	private Date date;
	@Column(name="SETLNUM")
	private long num;
	@Column(name="SETLNET")
	private double net;
	
	@Column(name="SETLISSNUM")
	private long issNum;
	@Column(name="SETLISSAMT")
	private double issAmt;
	@Column(name="SETLISSFEE")
	private double issFee;
	
	@Column(name="SETLACQNUM")
	private long acqNum;
	@Column(name="SETLACQAMT")
	private double acqAmt;
	@Column(name="SETLACQFEE")
	private double acqFee;
	
	@Column(name="SETLERRNUM")
	private long errNum;
	@Column(name="SETLERRAMT")
	private double errAmt;
	@Column(name="SETLERRFEE")
	private double errFee;
	
	@Column(name="SETLREVNUM")
	private long revNum;
	@Column(name="SETLREVAMT")
	private double revAmt;
	@Column(name="SETLREVFEE")
	private double revFee;
	
	@Column(name="INCRRNUM")
	private long inRrNum;
	@Column(name="INCRRAMT")
	private double inRrAmt;
	@Column(name="INCRRFEE")
	private double inRrFee;
	
	@Column(name="OUTRRNUM")
	private long ouRrNum;
	@Column(name="OUTRRAMT")
	private double ouRrAmt;
	@Column(name="OUTRRFEE")
	private double ouRrFee;
	
	@Column(name="INCFFNUM")
	private long inFfNum;
	@Column(name="INCFFAMT")
	private double inFfAmt;
	@Column(name="INCFFFEE")
	private double inFfFee;
	
	@Column(name="OUTFFNUM")
	private long ouFfNum;
	@Column(name="OUTFFAMT")
	private double ouFfAmt;
	@Column(name="OUTFFFEE")
	private double ouFfFee;
	
	@Column(name="INCCBNUM")
	private long inCbNum;
	@Column(name="INCCBAMT")
	private double inCbAmt;
	@Column(name="INCCBFEE")
	private double inCbFee;
	
	@Column(name="OUTCBNUM")
	private long ouCbNum;
	@Column(name="OUTCBAMT")
	private double ouCbAmt;
	@Column(name="OUTCBFEE")
	private double ouCbFee;
	
	@Column(name="INCRPNUM")
	private long inRpNum;
	@Column(name="INCRPAMT")
	private double inRpAmt;
	@Column(name="INCRPFEE")
	private double inRpFee;
	
	@Column(name="OUTRPNUM")
	private long ouRpNum;
	@Column(name="OUTRPAMT")
	private double ouRpAmt;
	@Column(name="OUTRPFEE")
	private double ouRpFee;
	
	@Column(name="INCADJNUM")
	private long inAjNum;
	@Column(name="INCADJAMT")
	private double inAjAmt;
	@Column(name="INCADJFEE")
	private double inAjFee;
	
	@Column(name="OUTADJNUM")
	private long ouAjNum;
	@Column(name="OUTADJAMT")
	private double ouAjAmt;
	@Column(name="OUTADJFEE")
	private double ouAjFee;
	
	@ManyToOne
	@JoinColumn(name="MEMTBL_MEMID",table="MEMTBL",referencedColumnName="MEMID")
	private Member member;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public double getNet() {
		return net;
	}
	public void setNet(double net) {
		this.net = net;
	}
	public long getIssNum() {
		return issNum;
	}
	public void setIssNum(long issNum) {
		this.issNum = issNum;
	}
	public double getIssAmt() {
		return issAmt;
	}
	public void setIssAmt(double issAmt) {
		this.issAmt = issAmt;
	}
	public double getIssFee() {
		return issFee;
	}
	public void setIssFee(double issFee) {
		this.issFee = issFee;
	}
	public long getAcqNum() {
		return acqNum;
	}
	public void setAcqNum(long acqNum) {
		this.acqNum = acqNum;
	}
	public double getAcqAmt() {
		return acqAmt;
	}
	public void setAcqAmt(double acqAmt) {
		this.acqAmt = acqAmt;
	}
	public double getAcqFee() {
		return acqFee;
	}
	public void setAcqFee(double acqFee) {
		this.acqFee = acqFee;
	}
	public long getErrNum() {
		return errNum;
	}
	public void setErrNum(long errNum) {
		this.errNum = errNum;
	}
	public double getErrAmt() {
		return errAmt;
	}
	public void setErrAmt(double errAmt) {
		this.errAmt = errAmt;
	}
	public double getErrFee() {
		return errFee;
	}
	public void setErrFee(double errFee) {
		this.errFee = errFee;
	}
	public long getRevNum() {
		return revNum;
	}
	public void setRevNum(long revNum) {
		this.revNum = revNum;
	}
	public double getRevAmt() {
		return revAmt;
	}
	public void setRevAmt(double revAmt) {
		this.revAmt = revAmt;
	}
	public double getRevFee() {
		return revFee;
	}
	public void setRevFee(double revFee) {
		this.revFee = revFee;
	}
	public long getInRrNum() {
		return inRrNum;
	}
	public void setInRrNum(long inRrNum) {
		this.inRrNum = inRrNum;
	}
	public double getInRrAmt() {
		return inRrAmt;
	}
	public void setInRrAmt(double inRrAmt) {
		this.inRrAmt = inRrAmt;
	}
	public double getInRrFee() {
		return inRrFee;
	}
	public void setInRrFee(double inRrFee) {
		this.inRrFee = inRrFee;
	}
	public long getOuRrNum() {
		return ouRrNum;
	}
	public void setOuRrNum(long ouRrNum) {
		this.ouRrNum = ouRrNum;
	}
	public double getOuRrAmt() {
		return ouRrAmt;
	}
	public void setOuRrAmt(double ouRrAmt) {
		this.ouRrAmt = ouRrAmt;
	}
	public double getOuRrFee() {
		return ouRrFee;
	}
	public void setOuRrFee(double ouRrFee) {
		this.ouRrFee = ouRrFee;
	}
	
	public long getInFfNum() {
		return inFfNum;
	}
	public void setInFfNum(long inFfNum) {
		this.inFfNum = inFfNum;
	}
	public double getInFfAmt() {
		return inFfAmt;
	}
	public void setInFfAmt(double inFfAmt) {
		this.inFfAmt = inFfAmt;
	}
	public double getInFfFee() {
		return inFfFee;
	}
	public void setInFfFee(double inFfFee) {
		this.inFfFee = inFfFee;
	}
	public long getOuFfNum() {
		return ouFfNum;
	}
	public void setOuFfNum(long ouFfNum) {
		this.ouFfNum = ouFfNum;
	}
	public double getOuFfAmt() {
		return ouFfAmt;
	}
	public void setOuFfAmt(double ouFfAmt) {
		this.ouFfAmt = ouFfAmt;
	}
	public double getOuFfFee() {
		return ouFfFee;
	}
	public void setOuFfFee(double ouFfFee) {
		this.ouFfFee = ouFfFee;
	}
	public long getInCbNum() {
		return inCbNum;
	}
	public void setInCbNum(long inCbNum) {
		this.inCbNum = inCbNum;
	}
	public double getInCbAmt() {
		return inCbAmt;
	}
	public void setInCbAmt(double inCbAmt) {
		this.inCbAmt = inCbAmt;
	}
	public double getInCbFee() {
		return inCbFee;
	}
	public void setInCbFee(double inCbFee) {
		this.inCbFee = inCbFee;
	}
	public long getOuCbNum() {
		return ouCbNum;
	}
	public void setOuCbNum(long ouCbNum) {
		this.ouCbNum = ouCbNum;
	}
	public double getOuCbAmt() {
		return ouCbAmt;
	}
	public void setOuCbAmt(double ouCbAmt) {
		this.ouCbAmt = ouCbAmt;
	}
	public double getOuCbFee() {
		return ouCbFee;
	}
	public void setOuCbFee(double ouCbFee) {
		this.ouCbFee = ouCbFee;
	}
	public long getInRpNum() {
		return inRpNum;
	}
	public void setInRpNum(long inRpNum) {
		this.inRpNum = inRpNum;
	}
	public double getInRpAmt() {
		return inRpAmt;
	}
	public void setInRpAmt(double inRpAmt) {
		this.inRpAmt = inRpAmt;
	}
	public double getInRpFee() {
		return inRpFee;
	}
	public void setInRpFee(double inRpFee) {
		this.inRpFee = inRpFee;
	}
	public long getOuRpNum() {
		return ouRpNum;
	}
	public void setOuRpNum(long ouRpNum) {
		this.ouRpNum = ouRpNum;
	}
	public double getOuRpAmt() {
		return ouRpAmt;
	}
	public void setOuRpAmt(double ouRpAmt) {
		this.ouRpAmt = ouRpAmt;
	}
	public double getOuRpFee() {
		return ouRpFee;
	}
	public void setOuRpFee(double ouRpFee) {
		this.ouRpFee = ouRpFee;
	}
	public long getInAjNum() {
		return inAjNum;
	}
	public void setInAjNum(long inAjNum) {
		this.inAjNum = inAjNum;
	}
	public double getInAjAmt() {
		return inAjAmt;
	}
	public void setInAjAmt(double inAjAmt) {
		this.inAjAmt = inAjAmt;
	}
	public double getInAjFee() {
		return inAjFee;
	}
	public void setInAjFee(double inAjFee) {
		this.inAjFee = inAjFee;
	}
	public long getOuAjNum() {
		return ouAjNum;
	}
	public void setOuAjNum(long ouAjNum) {
		this.ouAjNum = ouAjNum;
	}
	public double getOuAjAmt() {
		return ouAjAmt;
	}
	public void setOuAjAmt(double ouAjAmt) {
		this.ouAjAmt = ouAjAmt;
	}
	public double getOuAjFee() {
		return ouAjFee;
	}
	public void setOuAjFee(double ouAjFee) {
		this.ouAjFee = ouAjFee;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}
