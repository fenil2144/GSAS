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
	
		
}
