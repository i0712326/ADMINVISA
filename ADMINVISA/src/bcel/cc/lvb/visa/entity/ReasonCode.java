package bcel.cc.lvb.visa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="RCODETBL")
public class ReasonCode {
	@Id
	@Column(name="RCODE")
	private String code;
	@Column(name="NOTE")
	private String note;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="reasonCode")
	private List<IssueTxn> issueTxns;
	@ManyToOne
	@JoinColumn(name="PROCTBL_PCODE",referencedColumnName="PCODE")
	private ProcCode procCode;
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
	@Override
	public String toString() {
		return code;
	}
	
}
