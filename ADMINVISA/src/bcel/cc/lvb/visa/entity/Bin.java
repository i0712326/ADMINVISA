package bcel.cc.lvb.visa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BINTBL")
public class Bin {
	@Id
	@Column(name="BIN")
	private String bin;
	@Column(name="NOTE")
	private String note;
	@ManyToOne
	@JoinColumn(name="MEMTBL_MEMID")
	private Member member;
	public String getBin() {
		return bin;
	}
	public void setBin(String bin) {
		this.bin = bin;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}
