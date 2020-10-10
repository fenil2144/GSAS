package com.gsas.model;

import java.util.List;

public class SchemeApplicantVO {
	
	private long schemeApplicantId;
	private SchemeVO schemeVO;
	private LoginVO loginVO;
	private BankVO bankVO;
	private long accountNumber;
	private String typeOfAccount;
	private String ifsc;
	private String branch;
	private List<SchemeApplicantDocumentsVO> applicantDocumentsList;
	private boolean approvedStatus;
	private String reason;

	public SchemeApplicantVO() {
		super();
	}

	public SchemeApplicantVO(long schemeApplicantId, SchemeVO schemeVO, LoginVO loginVO, BankVO bankVO,
			long accountNumber, String typeOfAccount, String ifsc, String branch,
			List<SchemeApplicantDocumentsVO> applicantDocumentsList, boolean approvedStatus, String reason) {
		super();
		this.schemeApplicantId = schemeApplicantId;
		this.schemeVO = schemeVO;
		this.loginVO = loginVO;
		this.bankVO = bankVO;
		this.accountNumber = accountNumber;
		this.typeOfAccount = typeOfAccount;
		this.ifsc = ifsc;
		this.branch = branch;
		this.applicantDocumentsList = applicantDocumentsList;
		this.approvedStatus = approvedStatus;
		this.reason = reason;
	}

	public long getSchemeApplicantId() {
		return schemeApplicantId;
	}

	public void setSchemeApplicantId(long schemeApplicantId) {
		this.schemeApplicantId = schemeApplicantId;
	}

	public SchemeVO getSchemeVO() {
		return schemeVO;
	}

	public void setSchemeVO(SchemeVO schemeVO) {
		this.schemeVO = schemeVO;
	}

	public LoginVO getLoginVO() {
		return loginVO;
	}

	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}

	public BankVO getBankVO() {
		return bankVO;
	}

	public void setBankVO(BankVO bankVO) {
		this.bankVO = bankVO;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public List<SchemeApplicantDocumentsVO> getApplicantDocumentsList() {
		return applicantDocumentsList;
	}

	public void setApplicantDocumentsList(List<SchemeApplicantDocumentsVO> applicantDocumentsList) {
		this.applicantDocumentsList = applicantDocumentsList;
	}

	public boolean isApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(boolean approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "SchemeApplicantVO [schemeApplicantId=" + schemeApplicantId + ", schemeVO=" + schemeVO + ", loginVO="
				+ loginVO + ", bankVO=" + bankVO + ", accountNumber=" + accountNumber + ", typeOfAccount=" + typeOfAccount
				+ ", ifsc=" + ifsc + ", branch=" + branch + ", applicantDocumentsList=" + applicantDocumentsList
				+ ", approvedStatus=" + approvedStatus + ", reason=" + reason + "]";
	}



	
}
