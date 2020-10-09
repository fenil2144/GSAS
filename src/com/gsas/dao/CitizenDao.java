package com.gsas.dao;

import java.util.List;

import com.gsas.exception.AuthenticationException;
import com.gsas.exception.CitizenNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.LoginVO;
import com.gsas.model.SchemeVO;

public interface CitizenDao {
	
	public void registerCitizen(CitizenDetailsVO citizenDetailsVO);
	public LoginVO Authenticate(String userName,String password) throws AuthenticationException;
	public CitizenDetailsVO getCitizenDetails(long citizenId) throws CitizenNotFoundException;
	public void updateCitizenDetails(CitizenDetailsVO citizenDetailsVO);
	public List<SchemeVO> getNotAppliedSchemeList(long citizenId) throws SchemeNotFoundException, DatabaseException;
	public List<SchemeVO> getAppliedSchemeList(long citizenId, boolean approvedStatus) throws SchemeNotFoundException, DatabaseException;

}
