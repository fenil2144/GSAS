package com.gsas.dao;

import java.util.List;

import com.gsas.exception.DatabaseException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.BankVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.SchemeApplicantDocumentsVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeVO;

public interface SchemeDao {
	public void addScheme(SchemeVO scheme) throws DatabaseException;
	public void editScheme(SchemeVO scheme) throws DatabaseException;
	public SchemeVO getSchemeDetails(Long schemeId) throws SchemeNotFoundException, DatabaseException;
	public List<SchemeVO> getAllScheme() throws DatabaseException, SchemeNotFoundException;
	public SchemeApplicantVO addSchemeApplicant(SchemeApplicantVO schemeApplicant, List<SchemeApplicantDocumentsVO> doc ) throws DatabaseException;
	public SchemeApplicantVO addSchemeApplicant(SchemeApplicantVO schemeApplicant) throws DatabaseException;
	public List<DocumentVO> getDocumentsList(Long scheme_id) throws DatabaseException;
	public List<BankVO> getBankList(Long scheme_id) throws DatabaseException;
}
