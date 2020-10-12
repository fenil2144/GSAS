package com.gsas.service;

import java.util.List;

import com.gsas.dao.CitizenDao;
import com.gsas.exception.AuthenticationException;
import com.gsas.exception.CitizenNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.exception.DuplicateUserException;
import com.gsas.exception.InvalidSequenceException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.LoginVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeVO;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

public class CitizenServiceImpl implements CitizenService {
	private CitizenDao citizenDao = null;
	
	
	public CitizenServiceImpl() {
		citizenDao = (CitizenDao) ObjectFactory.getInstance(LayerType.CITIZEN_DAO);
	}

	@Override
	public void registerCitizen(CitizenDetailsVO citizenDetailsVO) throws DatabaseException, InvalidSequenceException {
		citizenDao.registerCitizen(citizenDetailsVO);
	}
	
	@Override
	public LoginVO Authenticate(String userName, String password) throws AuthenticationException {
		LoginVO loginVO = citizenDao.Authenticate(userName, password);
		if(loginVO == null) {
			throw new AuthenticationException("Sorry something went wrong");
		}
		return loginVO;
	}

	@Override
	public CitizenDetailsVO getCitizenDetails(long citizenId) throws CitizenNotFoundException {
		CitizenDetailsVO citizenDetailsVO = citizenDao.getCitizenDetails(citizenId);
		if(citizenDetailsVO == null) {
			throw new CitizenNotFoundException("Sorry something went wrong");
		}
		return citizenDetailsVO;
	}

	@Override
	public void updateCitizenDetails(CitizenDetailsVO citizenDetailsVO) {
		citizenDao.updateCitizenDetails(citizenDetailsVO);
	}

	@Override
	public List<SchemeVO> getNotAppliedSchemeList(long citizenId) throws SchemeNotFoundException, DatabaseException {
		return citizenDao.getNotAppliedSchemeList(citizenId);
	}

	@Override
	public List<SchemeApplicantVO> getAppliedSchemeList(long citizenId, boolean approvedStatus) throws SchemeNotFoundException, DatabaseException {
		return citizenDao.getAppliedSchemeList(citizenId, approvedStatus);
	}


	@Override
	public boolean isCitizenUnique(CitizenDetailsVO citizenDetailsVO) throws DatabaseException, DuplicateUserException {
		boolean isUnique = citizenDao.isCitizenUnique(citizenDetailsVO);
		if(isUnique == false) {
			throw new DuplicateUserException("Username, Aadhar Or Pan already registered.");
		}
		return isUnique;
	}

}
