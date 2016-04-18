package bcel.cc.lvb.visa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PROCTBL")
public class ProcCode {
	@Id
	@Column(name="PCODE")
	private String code;
	@Column(name="NOTE")
	private String note;
	@Column(name="CATE")
	private String cat;
	@Column(name="ISSFEE")
	private float issFee;
	@Column(name="ACQFEE")
	private float acqFee;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="procCode")
	private List<IssueTxn> issues;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="procCode")
	private List<VisaTranx> visaTranxs;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="procCode")
	private List<ReasonCode> reasonCodes;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public float getIssFee() {
		return issFee;
	}
	public void setIssFee(float issFee) {
		this.issFee = issFee;
	}
	public float getAcqFee() {
		return acqFee;
	}
	public void setAcqFee(float acqFee) {
		this.acqFee = acqFee;
	}
	public List<ReasonCode> getReasonCodes() {
		return reasonCodes;
	}
	public void setReasonCodes(List<ReasonCode> reasonCodes) {
		this.reasonCodes = reasonCodes;
	}
	public List<IssueTxn> getIssues() {
		return issues;
	}
	public void setIssues(List<IssueTxn> issues) {
		this.issues = issues;
	}
	public List<VisaTranx> getVisaTranxs() {
		return visaTranxs;
	}
	public void setVisaTranxs(List<VisaTranx> visaTranxs) {
		this.visaTranxs = visaTranxs;
	}
	@Override
	public String toString() {
		return code;
	}
	
}
