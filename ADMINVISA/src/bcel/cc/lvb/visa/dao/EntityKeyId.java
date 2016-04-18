package bcel.cc.lvb.visa.dao;

import java.io.Serializable;

public class EntityKeyId implements Serializable{
	private static final long serialVersionUID = 1L;
	private String mti;
	private String card;
	private String refer;
	private String trace;
	public String getMti() {
		return mti;
	}
	public void setMti(String mti) {
		this.mti = mti;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((mti == null) ? 0 : mti.hashCode());
		result = prime * result + ((refer == null) ? 0 : refer.hashCode());
		result = prime * result + ((trace == null) ? 0 : trace.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityKeyId other = (EntityKeyId) obj;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (mti == null) {
			if (other.mti != null)
				return false;
		} else if (!mti.equals(other.mti))
			return false;
		if (refer == null) {
			if (other.refer != null)
				return false;
		} else if (!refer.equals(other.refer))
			return false;
		if (trace == null) {
			if (other.trace != null)
				return false;
		} else if (!trace.equals(other.trace))
			return false;
		return true;
	}
	
}
