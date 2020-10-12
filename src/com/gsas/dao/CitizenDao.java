package com.gsas.dao;

import java.util.List;

import com.gsas.exception.AuthenticationException;
import com.gsas.exception.CitizenNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.exception.InvalidSequenceException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.LoginVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeVO;

public interface CitizenDao {
	
	public void registerCitizen(CitizenDetailsVO citizenDetailsVO) throws DatabaseException, InvalidSequenceException;
	public LoginVO Authenticate(String userName,String password) throws AuthenticationException;
	public CitizenDetailsVO getCitizenDetails(long citizenId) throws CitizenNotFoundException;
	public void updateCitizenDetails(CitizenDetailsVO citizenDetailsVO);
	public List<SchemeVO> getNotAppliedSchemeList(long citizenId) throws SchemeNotFoundException, DatabaseException;
	public List<SchemeApplicantVO> getAppliedSchemeList(long citizenId, boolean approvedStatus) throws SchemeNotFoundException, DatabaseException;
	public boolean isCitizenUnique(CitizenDetailsVO citizenDetailsVO) throws DatabaseException;

}
