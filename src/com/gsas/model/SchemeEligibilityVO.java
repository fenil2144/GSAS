package com.gsas.model;

public class SchemeEligibilityVO {
	
	private long schemeEligibilityId;
	private int minAge;
	private int maxAge;
	private IncomeGroupVO incomeGroupVO;
	private String gender;
	private ProfessionVO professionVO;
	public SchemeEligibilityVO() {
		super();
	}
	public SchemeEligibilityVO(long schemeEligibilityId, int minAge, int maxAge, IncomeGroupVO incomeGroupVO,
			String gender, ProfessionVO professionVO) {
		super();
		this.schemeEligibilityId = schemeEligibilityId;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.incomeGroupVO = incomeGroupVO;
		this.gender = gender;
		this.professionVO = professionVO;
	}
	public long getSchemeEligibilityId() {
		return schemeEligibilityId;
	}
	public void setSchemeEligibilityId(long schemeEligibilityId) {
		this.schemeEligibilityId = schemeEligibilityId;
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public IncomeGroupVO getIncomeGroupVO() {
		return incomeGroupVO;
	}
	public void setIncomeGroupVO(IncomeGroupVO incomeGroupVO) {
		this.incomeGroupVO = incomeGroupVO;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public ProfessionVO getProfessionVO() {
		return professionVO;
	}
	public void setProfessionVO(ProfessionVO professionVO) {
		this.professionVO = professionVO;
	}
	@Override
	public String toString() {
		return "SchemeEligibilityVO [schemeEligibilityId=" + schemeEligibilityId + ", minAge=" + minAge + ", maxAge="
				+ maxAge + ", incomeGroupVO=" + incomeGroupVO + ", gender=" + gender + ", professionVO=" + professionVO
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((incomeGroupVO == null) ? 0 : incomeGroupVO.hashCode());
		result = prime * result + maxAge;
		result = prime * result + minAge;
		result = prime * result + ((professionVO == null) ? 0 : professionVO.hashCode());
		result = prime * result + (int) (schemeEligibilityId ^ (schemeEligibilityId >>> 32));
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
		SchemeEligibilityVO other = (SchemeEligibilityVO) obj;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (incomeGroupVO == null) {
			if (other.incomeGroupVO != null)
				return false;
		} else if (!incomeGroupVO.equals(other.incomeGroupVO))
			return false;
		if (maxAge != other.maxAge)
			return false;
		if (minAge != other.minAge)
			return false;
		if (professionVO == null) {
			if (other.professionVO != null)
				return false;
		} else if (!professionVO.equals(other.professionVO))
			return false;
		if (schemeEligibilityId != other.schemeEligibilityId)
			return false;
		return true;
	}
	
}
