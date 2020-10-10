package com.gsas.service;

import java.time.LocalDate;
import java.util.List;


import com.gsas.dao.SchemeDao;
import com.gsas.exception.DatabaseException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.BankVO;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.SchemeApplicantDocumentsVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeEligibilityVO;
import com.gsas.model.SchemeVO;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

public class SchemeServiceImpl implements SchemeService {
	private SchemeDao schemeDao = null;
	
	public SchemeServiceImpl() {
		schemeDao = (SchemeDao) ObjectFactory.getInstance(LayerType.SCHEME_DAO);
		
	}

	@Override
	public void addScheme(SchemeVO scheme) throws DatabaseException {
		schemeDao.addScheme(scheme);

	}

	@Override
	public void updateScheme(SchemeVO scheme) throws DatabaseException {
		schemeDao.editScheme(scheme);

	}

	@Override
	public SchemeVO getSchemeDetails(Long schemeId) throws SchemeNotFoundException, DatabaseException {
		// TODO Auto-generated method stub
		SchemeVO schemeVO;
		try {
			schemeVO = schemeDao.getSchemeDetails(schemeId);
		} catch (SchemeNotFoundException e) {
			throw new SchemeNotFoundException(e.getMessage());
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage());
		}
		if(schemeVO == null)
			throw new SchemeNotFoundException("Sorry Something went wrong");
		return schemeVO;
	}

	@Override
	public List<SchemeVO> getAllScheme() throws SchemeNotFoundException, DatabaseException {
		// TODO Auto-generated method stub
		List<SchemeVO> schemeList;
		try {
			schemeList = schemeDao.getAllScheme();
		} catch (SchemeNotFoundException e) {
			throw new SchemeNotFoundException(e.getMessage());
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage());
		}
		return schemeList;
	}
	
	@Override
	public String validate(SchemeEligibilityVO schemeEligibility, CitizenDetailsVO citizenDetails){

	//Age Validation
	//Getting the age of citizen
	LocalDate today = LocalDate.now();
	int age = today.getYear() - citizenDetails.getDateOfBirth().getYear();
	int month = today.getMonthValue() - citizenDetails.getDateOfBirth().getMonthValue();
	if(month < 0 || (month == 0 && today.getDayOfMonth() < citizenDetails.getDateOfBirth().getDayOfMonth())) {
	age--;
	}
	if(age < schemeEligibility.getMinAge() || age > schemeEligibility.getMaxAge()) {
	return "Age criteria is not satisfied";
	}
	//Income Group Validation
	if(schemeEligibility.getIncomeGroupVO().getIncomeGroupName() != citizenDetails.getIncomeGroup()) {
	return "Income Group not matched";
	}
	//Gender Validation
	if(schemeEligibility.getGender() != citizenDetails.getGender()) {
	return "Scheme eligible only for "+schemeEligibility.getGender()+" , you can't apply";
	}
	//Profession Validation
	if(schemeEligibility.getProfessionVO().getProfessionName() != citizenDetails.getProfession()) {

	return "Scheme eligible only for "+schemeEligibility.getProfessionVO().getProfessionName()+" , you can't apply";
	}
	return "All criteria validated successfully";
	}

	

	@Override
	public SchemeApplicantVO validate(SchemeVO scheme, BankVO bank, List<SchemeApplicantDocumentsVO> docList,
			SchemeApplicantVO schemeApplicant) {
		// TODO Auto-generated method stub
		
		Boolean isBankSupported = false, isDocSupported = true;
		//checking citizen bank with the banks supported by scheme
		for(BankVO i : scheme.getBankList()) 
			if(bank.equals(i)) {
				isBankSupported = true;
				break;
			}	
		//checking documents
		for(DocumentVO j : scheme.getDocumentList()) 
			for(SchemeApplicantDocumentsVO k : docList) 
				if(j.compareTo(k.getDocumentVO()) != 0) {
					isDocSupported = false;
					break;
				}			
		
		
		//if bank and documents are supported by the scheme
		if(isBankSupported == true && isDocSupported == true) {
			schemeApplicant.setApprovedStatus(true);
			schemeApplicant.setReason("Your Application has been accepted for "+scheme.getSchemeName());
			
		}
		//if the scheme does not support bank
		else if(isBankSupported == false && isDocSupported == true) {
			schemeApplicant.setApprovedStatus(false);
			schemeApplicant.setReason("Sorry! "+scheme.getSchemeName()+" does not support your bank");
			
		}//if the scheme does not support the documents
		else if(isBankSupported ==true && isDocSupported == false) {
			schemeApplicant.setApprovedStatus(false);
			schemeApplicant.setReason("Kindly upload all the necessary documents for "+scheme.getSchemeName());
		}
		//if both bank and documents are not supported by the scheme
		else {
			schemeApplicant.setApprovedStatus(false);
			schemeApplicant.setReason("Sorry! your bank details and documents don't match");			
		}
		
		return schemeApplicant;
	}

	@Override
	public SchemeApplicantVO addSchemeApplicant(SchemeApplicantVO schemeApplicant,
			List<SchemeApplicantDocumentsVO> document) throws DatabaseException {
		// TODO Auto-generated method stub
		
		return schemeDao.addSchemeApplicant(schemeApplicant, document);
	}

	@Override
	public SchemeApplicantVO addSchemeApplicant(SchemeApplicantVO schemeApplicant) {
		// TODO Auto-generated method stub
		return schemeDao.addSchemeApplicant(schemeApplicant);
	}}
