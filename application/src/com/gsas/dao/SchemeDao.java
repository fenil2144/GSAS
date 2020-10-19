package com.gsas.dao;

import java.util.List;

import com.gsas.exception.DatabaseException;
import com.gsas.exception.InvalidSequenceException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.BankVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeVO;

public interface SchemeDao {
	public void addScheme(SchemeVO scheme) throws DatabaseException, InvalidSequenceException;
	public void editScheme(SchemeVO scheme) throws DatabaseException, InvalidSequenceException;
	public SchemeVO getSchemeDetails(Long schemeId) throws SchemeNotFoundException, DatabaseException;
	public List<SchemeVO> getAllScheme() throws DatabaseException, SchemeNotFoundException;
	public void addSchemeApplicant(SchemeApplicantVO schemeApplicant) throws DatabaseException;
	public void addRejectedSchemeApplicant(SchemeApplicantVO schemeApplicantVO) throws DatabaseException;
	public List<DocumentVO> getSchemeDocumentsList(Long scheme_id) throws DatabaseException;
	public List<BankVO> getSchemeBankList(Long scheme_id) throws DatabaseException;
}
