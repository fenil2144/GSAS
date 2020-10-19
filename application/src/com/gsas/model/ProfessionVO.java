package com.gsas.model;

public class ProfessionVO {
	
	private long professionId;
	private String professionName;
	public ProfessionVO() {
		super();
	}
	public ProfessionVO(long professionId) {
		super();
		this.professionId = professionId;
	}
	public ProfessionVO(long professionId, String professionName) {
		super();
		this.professionId = professionId;
		this.professionName = professionName;
	}
	public long getProfessionId() {
		return professionId;
	}
	public void setProfessionId(long professionId) {
		this.professionId = professionId;
	}
	public String getProfessionName() {
		return professionName;
	}
	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}
	@Override
	public String toString() {
		return "ProfessionVO [professionId=" + professionId + ", professionName=" + professionName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (professionId ^ (professionId >>> 32));
		result = prime * result + ((professionName == null) ? 0 : professionName.hashCode());
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
		ProfessionVO other = (ProfessionVO) obj;
		if (professionId != other.professionId)
			return false;
		if (professionName == null) {
			if (other.professionName != null)
				return false;
		} else if (!professionName.equals(other.professionName))
			return false;
		return true;
	}
	
	
}
