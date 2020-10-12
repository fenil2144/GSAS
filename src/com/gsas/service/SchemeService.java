package com.gsas.service;

import java.util.List;

import com.gsas.exception.CitizenNotEligibleException;
import com.gsas.exception.DataNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.exception.InvalidSequenceException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.BankVO;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.SchemeApplicantDocumentsVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeEligibilityVO;
import com.gsas.model.SchemeVO;

public interface SchemeService {
	
	public void addScheme(SchemeVO scheme) throws DatabaseException, InvalidSequenceException;
	public void updateScheme(SchemeVO scheme) throws DatabaseException, InvalidSequenceException;
	public SchemeVO getSchemeDetails(Long schemeId) throws SchemeNotFoundException, DatabaseException;
	public List<SchemeVO> getAllScheme() throws SchemeNotFoundException, DatabaseException;
	//public SchemeApplicantVO validate(SchemeVO scheme, BankVO bank, List<SchemeApplicantDocumentsVO> docList, SchemeApplicantVO schemeApplicant);
	public String validate(SchemeEligibilityVO schemeEligibility, CitizenDetailsVO citizenDetails) throws DataNotFoundException, DatabaseException;
	public void addSchemeApplicant(SchemeApplicantVO schemeApplicant) throws DatabaseException;
	public void addRejectedSchemeApplicant(SchemeApplicantVO schemeApplicant) throws DatabaseException;
	public List<DocumentVO> getSchemeDocumentsList(Long scheme_id) throws DatabaseException;
	public List<BankVO> getSchemeBankList(Long scheme_id) throws DatabaseException;
}
