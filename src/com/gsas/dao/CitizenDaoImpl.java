package com.gsas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gsas.exception.AuthenticationException;
import com.gsas.exception.CitizenNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.AddressVO;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.LoginVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeVO;
import com.gsas.utility.DBUtility;

public class CitizenDaoImpl implements CitizenDao {
	private Connection connection;

@Override
	public void registerCitizen(CitizenDetailsVO citizenDetailsVO) {
		try {
			connection = DBUtility.getConnection();
			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for citizen_seq)");
			ResultSet rs = sequenceStatement.executeQuery();
			long seq = 0;
			if(rs.next()) {
				seq = rs.getLong(1);
			} 
			if(seq == 0) {
				//Need to throw an error
				System.out.println("Error in sequence number");
			}
			//citizen_credential
			PreparedStatement preparedStatement = connection.prepareStatement("insert into login_credential values(?,?,?,?)");
			preparedStatement.setLong(1, seq);
			preparedStatement.setString(2, citizenDetailsVO.getCitizenVO().getUserName());
			preparedStatement.setString(3, citizenDetailsVO.getCitizenVO().getPassword());
			preparedStatement.setBoolean(4, false);
			preparedStatement.executeUpdate();
			
			//citizen_address
			preparedStatement = connection.prepareStatement("insert into citizen_address values(?,?,?,?,?)");
			preparedStatement.setLong(1, seq);
			preparedStatement.setString(2, citizenDetailsVO.getAddressVO().getStreet());
			preparedStatement.setString(3, citizenDetailsVO.getAddressVO().getCity());
			preparedStatement.setString(4, citizenDetailsVO.getAddressVO().getState());
			preparedStatement.setInt(5, citizenDetailsVO.getAddressVO().getPincode());
			preparedStatement.executeUpdate();
			
			//citizen_details
			preparedStatement = 
					connection.prepareStatement("insert into citizen_master values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setLong(1, seq);
			preparedStatement.setString(2, citizenDetailsVO.getFirstName());
			preparedStatement.setString(3, citizenDetailsVO.getMiddleName());
			preparedStatement.setString(4, citizenDetailsVO.getLastName());
			preparedStatement.setDate(5, java.sql.Date.valueOf(citizenDetailsVO.getDateOfBirth()));
			preparedStatement.setString(6, citizenDetailsVO.getGender());
			preparedStatement.setString(7, citizenDetailsVO.getEmail());
			preparedStatement.setLong(8, citizenDetailsVO.getPhone());
			preparedStatement.setLong(9, seq); //address_ref FK (citizen_address)
			preparedStatement.setString(10, citizenDetailsVO.getIncomeGroup());
			preparedStatement.setString(11, citizenDetailsVO.getProfession());
			preparedStatement.setLong(12, citizenDetailsVO.getAdharNumber());
			preparedStatement.setString(13, citizenDetailsVO.getPancardNumber());
			preparedStatement.setLong(14, seq); //citizen_ref FK (citizen_credential)
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
}
	@Override
	public LoginVO Authenticate(String userName, String password) throws AuthenticationException {
		LoginVO loginVO = null;
		try {
			Connection connection = DBUtility.getConnection();
			PreparedStatement fetchStatement = connection.prepareStatement("select * from login_credential where user_name = ? and password = ? and is_employee= false");
			fetchStatement.setString(1, userName);
			fetchStatement.setString(2, password);
			
			ResultSet resultSet = fetchStatement.executeQuery();
			if(resultSet.next()) {
				loginVO = new LoginVO();
				loginVO.setLoginId(resultSet.getLong("login_id"));
				loginVO.setUserName(resultSet.getString("user_name"));
				loginVO.setPassword(resultSet.getString("password"));
				return loginVO;
			}
			resultSet.close();
			fetchStatement.close();
			connection.close();
			if(loginVO == null) {
				throw new AuthenticationException("Sorry username or Password is Incorrect");
			} 
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return loginVO;
	}

	@Override
	public CitizenDetailsVO getCitizenDetails(long citizenId) throws CitizenNotFoundException {
		CitizenDetailsVO citizenDetailsVO = null;
		try {
						
			connection = DBUtility.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from citizen_master d inner join login_credential c "
					+ "on d.citizen_ref = c.citizen_id inner join citizen_address a"
					+ " on a.address_id = d.address_ref where citizen_ref=?");

			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				LoginVO loginVO = new LoginVO();
				loginVO.setLoginId(resultSet.getLong("login_id"));
				loginVO.setUserName(resultSet.getString("user_name"));
				loginVO.setPassword(resultSet.getString("password"));
				loginVO.setEmployee(resultSet.getBoolean("is_employee"));
				
				AddressVO addressVO = new AddressVO();
				addressVO.setStreet(resultSet.getString("street"));
				addressVO.setCity(resultSet.getString("city"));
				addressVO.setState(resultSet.getString("state"));
				addressVO.setPincode(resultSet.getInt("pincode"));
				
				citizenDetailsVO = new CitizenDetailsVO();
				citizenDetailsVO.setCitizenDetailsId(resultSet.getLong("citizen_details_id"));
				citizenDetailsVO.setFirstName(resultSet.getString("first_name"));
				citizenDetailsVO.setMiddleName(resultSet.getString("middle_name"));
				citizenDetailsVO.setLastName(resultSet.getString("last_name"));
				Date date = new Date(resultSet.getDate("date_of_birth").getTime());
				citizenDetailsVO.setDateOfBirth( date.toLocalDate());
				citizenDetailsVO.setGender(resultSet.getString("gender"));
				citizenDetailsVO.setEmail(resultSet.getString("email"));
				citizenDetailsVO.setPhone(resultSet.getLong("phone"));
				citizenDetailsVO.setAddressVO(addressVO);
				citizenDetailsVO.setIncomeGroup(resultSet.getString("income_group"));
				citizenDetailsVO.setProfession(resultSet.getString("profession"));
				citizenDetailsVO.setAdharNumber(resultSet.getLong("adhar_number"));
				citizenDetailsVO.setPancardNumber(resultSet.getString("pancard_number"));
				citizenDetailsVO.setCitizenVO(loginVO);
				
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
			if(citizenDetailsVO == null) {
				throw new CitizenNotFoundException("Sorry username or Password is Incorrect");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return citizenDetailsVO;
	}

	@Override
	public void updateCitizenDetails(CitizenDetailsVO citizenDetailsVO) {
		try {
			connection = DBUtility.getConnection();
			
			//Update citizen_credential
			PreparedStatement updateStatement = connection.prepareStatement("update login_credential set user_name=?,password=? where citizen_id=?");
			updateStatement.setString(1, citizenDetailsVO.getCitizenVO().getUserName());
			updateStatement.setString(2, citizenDetailsVO.getCitizenVO().getPassword());
			updateStatement.setLong(3, citizenDetailsVO.getCitizenVO().getLoginId());
			updateStatement.executeUpdate();
			
			//Update citizen_address
			updateStatement = connection.prepareStatement("update citizen_address set street=?,city=?,state=?,pincode=? where address_id=?");
			updateStatement.setString(1, citizenDetailsVO.getAddressVO().getStreet());
			updateStatement.setString(2, citizenDetailsVO.getAddressVO().getCity());
			updateStatement.setString(3, citizenDetailsVO.getAddressVO().getState());
			updateStatement.setInt(4, citizenDetailsVO.getAddressVO().getPincode());
			updateStatement.setLong(5, citizenDetailsVO.getAddressVO().getAddressId());
			updateStatement.executeUpdate();
			
			//Update citizen_details
			updateStatement = connection.prepareStatement("update citizen_details set first_name=?,middle_name=?,last_name=?,date_of_birth=?,gender=?,email=?,phone=?,address_ref=?,income_group=?,profession=?,adhar_number=?,pancard_number=?,citizen_ref=? where citizen_details_id=?");
			updateStatement.setString(1, citizenDetailsVO.getFirstName());
			updateStatement.setString(2, citizenDetailsVO.getMiddleName());
			updateStatement.setString(3, citizenDetailsVO.getLastName());
			updateStatement.setDate(4, java.sql.Date.valueOf(citizenDetailsVO.getDateOfBirth()));
			updateStatement.setString(5, citizenDetailsVO.getGender());
			updateStatement.setString(6, citizenDetailsVO.getEmail());
			updateStatement.setLong(7, citizenDetailsVO.getPhone());
			updateStatement.setLong(8, citizenDetailsVO.getAddressVO().getAddressId() ); //address_ref FK (citizen_address)
			updateStatement.setString(9, citizenDetailsVO.getIncomeGroup());
			updateStatement.setString(10, citizenDetailsVO.getProfession());
			updateStatement.setLong(11, citizenDetailsVO.getAdharNumber());
			updateStatement.setString(12, citizenDetailsVO.getPancardNumber());
			updateStatement.setLong(13, citizenDetailsVO.getCitizenVO().getLoginId()); //citizen_ref FK (citizen_credential)
			updateStatement.setLong(14, citizenDetailsVO.getCitizenDetailsId());
			updateStatement.executeUpdate();

			updateStatement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<SchemeVO> getNotAppliedSchemeList(long citizenId) throws SchemeNotFoundException, DatabaseException {
		
		List<SchemeVO> notAppliedSchemeList = new ArrayList<SchemeVO>();
		
		try {
			Connection connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("SELECT scheme_id,scheme_name,summary,description,image_path,STATUS FROM scheme_master WHERE scheme_id NOT IN (SELECT scheme_ref FROM scheme_applicant WHERE citizen_ref = ? and status = ?");
			selectStatement.setLong(1, citizenId);
			selectStatement.setBoolean(2, true);
			
			ResultSet resultSet = selectStatement.executeQuery();
			while(resultSet.next()) {
				SchemeVO schemeVO = new SchemeVO();
				schemeVO.setSchemeId(resultSet.getLong("scheme_id"));
				schemeVO.setSchemeName(resultSet.getString("scheme_name"));
				schemeVO.setSummary(resultSet.getString("summary"));
				schemeVO.setDescription(resultSet.getString("description"));
				schemeVO.setImagePath(resultSet.getString("image_path"));
				
				notAppliedSchemeList.add(schemeVO);
			}
			
			resultSet.close();
			selectStatement.close();	
			connection.close();
			if(notAppliedSchemeList.isEmpty()) {
				throw new SchemeNotFoundException("Scheme Not Found");
			}
		} catch(SQLException | ClassNotFoundException e) {

			throw new DatabaseException(e.getMessage());
		}
		return notAppliedSchemeList;

	}
	
	@Override
	public List<SchemeVO> getAppliedSchemeList(long citizenId,boolean approvedStatus) throws SchemeNotFoundException, DatabaseException {
		List<SchemeVO> appliedSchemeList = new ArrayList<SchemeVO>();
		
		try {
			Connection connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("SELECT scheme_id,scheme_name,summary,description,image_path,approved_status,reason FROM scheme_master s INNER JOIN scheme_applicant a ON s.scheme_id = a.scheme_ref WHERE citizen_ref = ? AND approved_status = ? and status = ?");
			selectStatement.setLong(1, citizenId);
			selectStatement.setBoolean(2, approvedStatus);
			selectStatement.setBoolean(3, true);
			
			ResultSet resultSet = selectStatement.executeQuery();
			while(resultSet.next()) {
				SchemeVO schemeVO = new SchemeVO();
				schemeVO.setSchemeId(resultSet.getLong("scheme_id"));
				schemeVO.setSchemeName(resultSet.getString("scheme_name"));
				schemeVO.setSummary(resultSet.getString("summary"));
				schemeVO.setDescription(resultSet.getString("description"));
				schemeVO.setImagePath(resultSet.getString("image_path"));
				schemeVO.setStatus(resultSet.getBoolean("approved_status"));
				
				SchemeApplicantVO schemeApplicantVO = new SchemeApplicantVO();
				schemeApplicantVO.setReason(resultSet.getString("reason"));
				
				schemeApplicantVO.setSchemeVO(schemeVO);
				
				appliedSchemeList.add(schemeVO);
			}
			
			resultSet.close();
			selectStatement.close();
			connection.close();
			if(appliedSchemeList.isEmpty()) {
				throw new SchemeNotFoundException("Scheme Not Found");
			}
		} catch(SQLException | ClassNotFoundException e) {

			throw new DatabaseException(e.getMessage());
		}
		return appliedSchemeList;
	}


}
