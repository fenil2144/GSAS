package com.gsas.model;

import java.time.LocalDate;
import java.util.List;

public class SchemeVO {
	
	private long schemeId;
	private String schemeName;
	private String summary;
	private String description;
	private String imagePath;
	private MinistryVO ministryVO;
	private SectorVO sectorVO;
	private LocalDate startDate;
	private SchemeEligibilityVO schemeEligibilityVO;
	private boolean status;
	private List<DocumentVO> documentList;
	private List<BankVO> bankList;
	public SchemeVO() {
		super();
	}
	public SchemeVO(long schemeId, String schemeName, String summary, String description, String imagePath,
			MinistryVO ministryVO, SectorVO sectorVO, LocalDate startDate, SchemeEligibilityVO schemeEligibilityVO,
			boolean status, List<DocumentVO> documentList, List<BankVO> bankList) {
		super();
		this.schemeId = schemeId;
		this.schemeName = schemeName;
		this.summary = summary;
		this.description = description;
		this.imagePath = imagePath;
		this.ministryVO = ministryVO;
		this.sectorVO = sectorVO;
		this.startDate = startDate;
		this.schemeEligibilityVO = schemeEligibilityVO;
		this.status = status;
		this.documentList = documentList;
		this.bankList = bankList;
	}
	
	public SchemeVO(long schemeId) {
		super();
		this.schemeId = schemeId;
	}
	public long getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(long schemeId) {
		this.schemeId = schemeId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public MinistryVO getMinistryVO() {
		return ministryVO;
	}
	public void setMinistryVO(MinistryVO ministryVO) {
		this.ministryVO = ministryVO;
	}
	public SectorVO getSectorVO() {
		return sectorVO;
	}
	public void setSectorVO(SectorVO sectorVO) {
		this.sectorVO = sectorVO;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public SchemeEligibilityVO getSchemeEligibilityVO() {
		return schemeEligibilityVO;
	}
	public void setSchemeEligibilityVO(SchemeEligibilityVO schemeEligibilityVO) {
		this.schemeEligibilityVO = schemeEligibilityVO;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<DocumentVO> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<DocumentVO> documentList) {
		this.documentList = documentList;
	}
	public List<BankVO> getBankList() {
		return bankList;
	}
	public void setBankList(List<BankVO> bankList) {
		this.bankList = bankList;
	}
	@Override
	public String toString() {
		return "SchemeVO [schemeId=" + schemeId + ", schemeName=" + schemeName + ", summary=" + summary
				+ ", description=" + description + ", imagePath=" + imagePath + ", ministryVO=" + ministryVO
				+ ", sectorVO=" + sectorVO + ", startDate=" + startDate + ", schemeEligibilityVO=" + schemeEligibilityVO
				+ ", status=" + status + ", documentList=" + documentList + ", bankList=" + bankList + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankList == null) ? 0 : bankList.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((documentList == null) ? 0 : documentList.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((ministryVO == null) ? 0 : ministryVO.hashCode());
		result = prime * result + ((schemeEligibilityVO == null) ? 0 : schemeEligibilityVO.hashCode());
		result = prime * result + (int) (schemeId ^ (schemeId >>> 32));
		result = prime * result + ((schemeName == null) ? 0 : schemeName.hashCode());
		result = prime * result + ((sectorVO == null) ? 0 : sectorVO.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
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
		SchemeVO other = (SchemeVO) obj;
		if (bankList == null) {
			if (other.bankList != null)
				return false;
		} else if (!bankList.equals(other.bankList))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (documentList == null) {
			if (other.documentList != null)
				return false;
		} else if (!documentList.equals(other.documentList))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (ministryVO == null) {
			if (other.ministryVO != null)
				return false;
		} else if (!ministryVO.equals(other.ministryVO))
			return false;
		if (schemeEligibilityVO == null) {
			if (other.schemeEligibilityVO != null)
				return false;
		} else if (!schemeEligibilityVO.equals(other.schemeEligibilityVO))
			return false;
		if (schemeId != other.schemeId)
			return false;
		if (schemeName == null) {
			if (other.schemeName != null)
				return false;
		} else if (!schemeName.equals(other.schemeName))
			return false;
		if (sectorVO == null) {
			if (other.sectorVO != null)
				return false;
		} else if (!sectorVO.equals(other.sectorVO))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		return true;
	}
	
		
}