package com.gsas.model;

public class SectorVO {
	
	private long sectorId;
	private String sectorName;
	public SectorVO() {
		super();
	}
	public SectorVO(long sectorId) {
		super();
		this.sectorId = sectorId;
	}
	public SectorVO(long sectorId, String sectorName) {
		super();
		this.sectorId = sectorId;
		this.sectorName = sectorName;
	}
	public long getSectorId() {
		return sectorId;
	}
	public void setSectorId(long sectorId) {
		this.sectorId = sectorId;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	@Override
	public String toString() {
		return "SectorVO [sectorId=" + sectorId + ", sectorName=" + sectorName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (sectorId ^ (sectorId >>> 32));
		result = prime * result + ((sectorName == null) ? 0 : sectorName.hashCode());
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
		SectorVO other = (SectorVO) obj;
		if (sectorId != other.sectorId)
			return false;
		if (sectorName == null) {
			if (other.sectorName != null)
				return false;
		} else if (!sectorName.equals(other.sectorName))
			return false;
		return true;
	}
	
	
}
