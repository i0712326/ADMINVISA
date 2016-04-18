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
	@Column(name="SETLNET")
	private long net;
	
	@Column(name="SETLISSNUM")
	private long issNum;
	@Column(name="SETLISSAMT")
	private long issAmt;
	@Column(name="SETLISSFEE")
	private long issFee;
	
	@Column(name="SETLACQNUM")
	private long acqNum;
	@Column(name="SETLACQAMT")
	private long acqAmt;
	@Column(name="SETLACQFEE")
	private long acqFee;
	
	@Column(name="SETLERRNUM")
	private long errNum;
	@Column(name="SETLERRAMT")
	private long errAmt;
	@Column(name="SETLERRFEE")
	private long errFee;
	
	@Column(name="SETLREVNUM")
	private long revNum;
	@Column(name="SETLREVAMT")
	private long revAmt;
	@Column(name="SETLREVFEE")
	private long revFee;
	
	@Column(name="INCRRNUM")
	private long inRrNum;
	@Column(name="INCRRAMT")
	private long inRrAmt;
	@Column(name="INCRRFEE")
	private long inRrFee;
	
	@Column(name="OUTRRNUM")
	private long ouRrNum;
	@Column(name="OUTRRAMT")
	private long ouRrAmt;
	@Column(name="OUTRRFEE")
	private long ouRrFee;
	
	@Column(name="INCCBNUM")
	private long inCbNum;
	@Column(name="INCCBAMT")
	private long inCbAmt;
	@Column(name="INCCBFEE")
	private long inCbFee;
	
	@Column(name="OUTCBNUM")
	private long ouCbNum;
	@Column(name="OUTCBAMT")
	private long ouCbAmt;
	@Column(name="OUTCBFEE")
	private long ouCbFee;
	
	@Column(name="INCRPNUM")
	private long inRpNum;
	@Column(name="INCRPAMT")
	private long inRpAmt;
	@Column(name="INCRPFEE")
	private long inRpFee;
	
	@Column(name="OUTRPNUM")
	private long ouRpNum;
	@Column(name="OUTRPAMT")
	private long ouRpAmt;
	@Column(name="OUTRPFEE")
	private long ouRpFee;
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
	public long getIssNum() {
		return issNum;
	}
	public void setIssNum(long issNum) {
		this.issNum = issNum;
	}
	public long getIssAmt() {
		return issAmt;
	}
	public void setIssAmt(long issAmt) {
		this.issAmt = issAmt;
	}
	public long getIssFee() {
		return issFee;
	}
	public void setIssFee(long issFee) {
		this.issFee = issFee;
	}
	public long getAcqNum() {
		return acqNum;
	}
	public void setAcqNum(long acqNum) {
		this.acqNum = acqNum;
	}
	public long getAcqAmt() {
		return acqAmt;
	}
	public void setAcqAmt(long acqAmt) {
		this.acqAmt = acqAmt;
	}
	public long getAcqFee() {
		return acqFee;
	}
	public void setAcqFee(long acqFee) {
		this.acqFee = acqFee;
	}
	public long getErrNum() {
		return errNum;
	}
	public void setErrNum(long errNum) {
		this.errNum = errNum;
	}
	public long getErrAmt() {
		return errAmt;
	}
	public void setErrAmt(long errAmt) {
		this.errAmt = errAmt;
	}
	public long getErrFee() {
		return errFee;
	}
	public void setErrFee(long errFee) {
		this.errFee = errFee;
	}
	public long getRevNum() {
		return revNum;
	}
	public void setRevNum(long revNum) {
		this.revNum = revNum;
	}
	public long getRevAmt() {
		return revAmt;
	}
	public void setRevAmt(long revAmt) {
		this.revAmt = revAmt;
	}
	public long getRevFee() {
		return revFee;
	}
	public void setRevFee(long revFee) {
		this.revFee = revFee;
	}
	public long getInRrNum() {
		return inRrNum;
	}
	public void setInRrNum(long inRrNum) {
		this.inRrNum = inRrNum;
	}
	public long getInRrAmt() {
		return inRrAmt;
	}
	public void setInRrAmt(long inRrAmt) {
		this.inRrAmt = inRrAmt;
	}
	public long getInRrFee() {
		return inRrFee;
	}
	public void setInRrFee(long inRrFee) {
		this.inRrFee = inRrFee;
	}
	public long getOuRrNum() {
		return ouRrNum;
	}
	public void setOuRrNum(long ouRrNum) {
		this.ouRrNum = ouRrNum;
	}
	public long getOuRrAmt() {
		return ouRrAmt;
	}
	public void setOuRrAmt(long ouRrAmt) {
		this.ouRrAmt = ouRrAmt;
	}
	public long getOuRrFee() {
		return ouRrFee;
	}
	public void setOuRrFee(long ouRrFee) {
		this.ouRrFee = ouRrFee;
	}
	public long getInCbNum() {
		return inCbNum;
	}
	public void setInCbNum(long inCbNum) {
		this.inCbNum = inCbNum;
	}
	public long getInCbAmt() {
		return inCbAmt;
	}
	public void setInCbAmt(long inCbAmt) {
		this.inCbAmt = inCbAmt;
	}
	public long getInCbFee() {
		return inCbFee;
	}
	public void setInCbFee(long inCbFee) {
		this.inCbFee = inCbFee;
	}
	public long getOuCbNum() {
		return ouCbNum;
	}
	public void setOuCbNum(long ouCbNum) {
		this.ouCbNum = ouCbNum;
	}
	public long getOuCbAmt() {
		return ouCbAmt;
	}
	public void setOuCbAmt(long ouCbAmt) {
		this.ouCbAmt = ouCbAmt;
	}
	public long getOuCbFee() {
		return ouCbFee;
	}
	public void setOuCbFee(long ouCbFee) {
		this.ouCbFee = ouCbFee;
	}
	public long getInRpNum() {
		return inRpNum;
	}
	public void setInRpNum(long inRpNum) {
		this.inRpNum = inRpNum;
	}
	public long getInRpAmt() {
		return inRpAmt;
	}
	public void setInRpAmt(long inRpAmt) {
		this.inRpAmt = inRpAmt;
	}
	public long getInRpFee() {
		return inRpFee;
	}
	public void setInRpFee(long inRpFee) {
		this.inRpFee = inRpFee;
	}
	public long getOuRpNum() {
		return ouRpNum;
	}
	public void setOuRpNum(long ouRpNum) {
		this.ouRpNum = ouRpNum;
	}
	public long getOuRpAmt() {
		return ouRpAmt;
	}
	public void setOuRpAmt(long ouRpAmt) {
		this.ouRpAmt = ouRpAmt;
	}
	public long getOuRpFee() {
		return ouRpFee;
	}
	public void setOuRpFee(long ouRpFee) {
		this.ouRpFee = ouRpFee;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}
