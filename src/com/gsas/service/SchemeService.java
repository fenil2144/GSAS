package com.gsas.service;

import java.util.List;

import com.gsas.exception.CitizenNotEligibleException;
import com.gsas.exception.DatabaseException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.BankVO;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.SchemeApplicantDocumentsVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeEligibilityVO;
import com.gsas.model.SchemeVO;

public interface SchemeService {
	
	public void addScheme(SchemeVO scheme) throws DatabaseException;
	public void updateScheme(SchemeVO scheme) throws DatabaseException;
	public SchemeVO getSchemeDetails(Long schemeId) throws SchemeNotFoundException, DatabaseException;
	public List<SchemeVO> getAllScheme() throws SchemeNotFoundException, DatabaseException;
	public SchemeApplicantVO validate(SchemeVO scheme, BankVO bank, List<SchemeApplicantDocumentsVO> docList, SchemeApplicantVO schemeApplicant);
	public String validate(SchemeEligibilityVO schemeEligibility, CitizenDetailsVO citizenDetails);
	public SchemeApplicantVO addSchemeApplicant(SchemeApplicantVO schemeApplicant, List<SchemeApplicantDocumentsVO> document) throws DatabaseException;
	public  SchemeApplicantVO addSchemeApplicant(SchemeApplicantVO schemeApplicant);
}
