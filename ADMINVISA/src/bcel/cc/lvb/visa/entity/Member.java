package bcel.cc.lvb.visa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MEMTBL")
public class Member {
	@Id
	@Column(name="MEMID")
	private String memId;
	@Column(name="MEMNAME")
	private String name;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="member")
	private List<Bin> bins;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="member")
	private List<User> users;
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Bin> getBins() {
		return bins;
	}
	public void setBins(List<Bin> bins) {
		this.bins = bins;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
