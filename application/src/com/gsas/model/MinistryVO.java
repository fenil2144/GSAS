package com.gsas.model;

public class MinistryVO {
	
	private long ministryId;
	private String ministryName;
	public MinistryVO() {
		super();
	}
	public MinistryVO(long ministryId) {
		super();
		this.ministryId = ministryId;
	}
	public MinistryVO(long ministryId, String ministryName) {
		super();
		this.ministryId = ministryId;
		this.ministryName = ministryName;
	}
	public long getMinistryId() {
		return ministryId;
	}
	public void setMinistryId(long ministryId) {
		this.ministryId = ministryId;
	}
	public String getMinistryName() {
		return ministryName;
	}
	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}
	@Override
	public String toString() {
		return "MinistryVO [ministryId=" + ministryId + ", ministryName=" + ministryName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ministryId ^ (ministryId >>> 32));
		result = prime * result + ((ministryName == null) ? 0 : ministryName.hashCode());
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
		MinistryVO other = (MinistryVO) obj;
		if (ministryId != other.ministryId)
			return false;
		if (ministryName == null) {
			if (other.ministryName != null)
				return false;
		} else if (!ministryName.equals(other.ministryName))
			return false;
		return true;
	}
	
	
}
