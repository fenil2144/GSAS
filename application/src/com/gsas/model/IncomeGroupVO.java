package com.gsas.model;

public class IncomeGroupVO {
	
	private long incomeGroupId;
	private String incomeGroupName;
	public IncomeGroupVO() {
		super();
	}
	public IncomeGroupVO(long incomeGroupId) {
		super();
		this.incomeGroupId = incomeGroupId;
	}
	public IncomeGroupVO(long incomeGroupId, String incomeGroupName) {
		super();
		this.incomeGroupId = incomeGroupId;
		this.incomeGroupName = incomeGroupName;
	}
	public long getIncomeGroupId() {
		return incomeGroupId;
	}
	public void setIncomeGroupId(long incomeGroupId) {
		this.incomeGroupId = incomeGroupId;
	}
	public String getIncomeGroupName() {
		return incomeGroupName;
	}
	public void setIncomeGroupName(String incomeGroupName) {
		this.incomeGroupName = incomeGroupName;
	}
	@Override
	public String toString() {
		return "IncomeGroupVO [incomeGroupId=" + incomeGroupId + ", incomeGroupName=" + incomeGroupName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (incomeGroupId ^ (incomeGroupId >>> 32));
		result = prime * result + ((incomeGroupName == null) ? 0 : incomeGroupName.hashCode());
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
		IncomeGroupVO other = (IncomeGroupVO) obj;
		if (incomeGroupId != other.incomeGroupId)
			return false;
		if (incomeGroupName == null) {
			if (other.incomeGroupName != null)
				return false;
		} else if (!incomeGroupName.equals(other.incomeGroupName))
			return false;
		return true;
	}
	
	
}
