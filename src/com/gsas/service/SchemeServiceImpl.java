package com.gsas.service;

import java.time.LocalDate;
import java.util.List;

import com.gsas.dao.CommonDao;
import com.gsas.dao.SchemeDao;
import com.gsas.exception.DataNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.exception.InvalidSequenceException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.BankVO;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.IncomeGroupVO;
import com.gsas.model.ProfessionVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeEligibilityVO;
import com.gsas.model.SchemeVO;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

public class SchemeServiceImpl implements SchemeService {
	private SchemeDao schemeDao = null;
	private CommonDao commonDao = null;
	
	public SchemeServiceImpl() {
		schemeDao = (SchemeDao) ObjectFactory.getInstance(LayerType.SCHEME_DAO);
		commonDao = (CommonDao) ObjectFactory.getInstance(LayerType.COMMON_DAO);
		
	}

	@Override
	public void addScheme(SchemeVO scheme) throws DatabaseException, InvalidSequenceException {
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
			e.printStackTrace();
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
	public String validate(SchemeEligibilityVO schemeEligibilityVO, CitizenDetailsVO citizenDetailsVO) throws DataNotFoundException, DatabaseException{

	//Age Validation
	//Getting the age of citizen
	LocalDate today = LocalDate.now();
	int age = today.getYear() - citizenDetailsVO.getDateOfBirth().getYear();
	int month = today.getMonthValue() - citizenDetailsVO.getDateOfBirth().getMonthValue();
	if(month < 0 || (month == 0 && today.getDayOfMonth() < citizenDetailsVO.getDateOfBirth().getDayOfMonth())) {
	age--;
	}
	if(age < schemeEligibilityVO.getMinAge() || age > schemeEligibilityVO.getMaxAge()) {
		return "Age criteria is not satisfied";
	}
	//Income Group Validation
	List<IncomeGroupVO> incomeGroupList = commonDao.getAllIncomeGroups();
	String incomeGroupName  = null;
	for(IncomeGroupVO incomeGroupVO : incomeGroupList) {
		if(incomeGroupVO.getIncomeGroupId() == schemeEligibilityVO.getIncomeGroupVO().getIncomeGroupId()) {
			incomeGroupName = incomeGroupVO.getIncomeGroupName();
		}
	}
	if(incomeGroupName.equalsIgnoreCase("Any")) {
		
	}
	else {
		
		if(schemeEligibilityVO.getIncomeGroupVO().getIncomeGroupId() != citizenDetailsVO.getIncomeGroup().getIncomeGroupId()) {
			return "Income Group not matched";
		}
	}
	//Gender Validation
	if(!schemeEligibilityVO.getGender().equalsIgnoreCase(citizenDetailsVO.getGender())) {
		return "Scheme eligible only for "+schemeEligibilityVO.getGender()+" , you can't apply";
	}
	//Profession Validation
	List<ProfessionVO> professionList = commonDao.getAllProfessions();
	String professionName  = null;
	for(ProfessionVO professionVO : professionList) {
		if(professionVO.getProfessionId() == schemeEligibilityVO.getProfessionVO().getProfessionId()) {
			professionName = professionVO.getProfessionName();
		}
	}
	if(professionName.equalsIgnoreCase("Any")) {
		
	}
	else {
		if(schemeEligibilityVO.getProfessionVO().getProfessionId() != citizenDetailsVO.getProfession().getProfessionId()) {
			return "Profession not matched";
		}
	}

	return "All criteria validated successfully";
	}

	

//	@Override
//	public SchemeApplicantVO validate(SchemeVO scheme, BankVO bank, List<SchemeApplicantDocumentsVO> docList,
//			SchemeApplicantVO schemeApplicant) {
//		// TODO Auto-generated method stub
//		
//		Boolean isBankSupported = false, isDocSupported = true;
//		//checking citizen bank with the banks supported by scheme
//		for(BankVO i : scheme.getBankList()) 
//			if(bank.equals(i)) {
//				isBankSupported = true;
//				break;
//			}	
//		//checking documents
//		for(DocumentVO j : scheme.getDocumentList()) 
//			for(SchemeApplicantDocumentsVO k : docList) 
//				if(j.compareTo(k.getDocumentVO()) != 0) {
//					isDocSupported = false;
//					break;
//				}			
//		
//		
//		//if bank and documents are supported by the scheme
//		if(isBankSupported == true && isDocSupported == true) {
//			schemeApplicant.setApprovedStatus(true);
//			schemeApplicant.setReason("Your Application has been accepted for "+scheme.getSchemeName());
//			
//		}
//		//if the scheme does not support bank
//		else if(isBankSupported == false && isDocSupported == true) {
//			schemeApplicant.setApprovedStatus(false);
//			schemeApplicant.setReason("Sorry! "+scheme.getSchemeName()+" does not support your bank");
//			
//		}//if the scheme does not support the documents
//		else if(isBankSupported ==true && isDocSupported == false) {
//			schemeApplicant.setApprovedStatus(false);
//			schemeApplicant.setReason("Kindly upload all the necessary documents for "+scheme.getSchemeName());
//		}
//		//if both bank and documents are not supported by the scheme
//		else {
//			schemeApplicant.setApprovedStatus(false);
//			schemeApplicant.setReason("Sorry! your bank details and documents don't match");			
//		}
//		
//		return schemeApplicant;
//	}

	@Override
	public void addSchemeApplicant(SchemeApplicantVO schemeApplicant) throws DatabaseException {
		schemeDao.addSchemeApplicant(schemeApplicant);
	}

	@Override
	public void addRejectedSchemeApplicant(SchemeApplicantVO schemeApplicantVO) throws DatabaseException {
		schemeDao.addRejectedSchemeApplicant(schemeApplicantVO);
	}

	@Override
	public List<DocumentVO> getSchemeDocumentsList(Long scheme_id) throws DatabaseException {
		return schemeDao.getSchemeDocumentsList(scheme_id);
	}

	@Override
	public List<BankVO> getSchemeBankList(Long scheme_id) throws DatabaseException {
		return schemeDao.getSchemeBankList(scheme_id);
	}

}
