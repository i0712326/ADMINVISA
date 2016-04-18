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
@Table(name="USRTBL")
public class User {
	@Id
	@Column(name="USERID")
	private String usrId;
	@Column(name="PASSWD")
	private String passwd;
	@Column(name="TEL")
	private String tel;
	@Column(name="NAME")
	private String name;
	@ManyToOne
	@JoinColumn(name="MEMTBL_MEMID")
	private Member member;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private List<IssueTxn> issueTxns;
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public List<IssueTxn> getIssueTxns() {
		return issueTxns;
	}
	public void setIssueTxns(List<IssueTxn> issueTxns) {
		this.issueTxns = issueTxns;
	}
}
