package com.gsas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gsas.exception.DatabaseException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.BankVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.IncomeGroupVO;
import com.gsas.model.MinistryVO;
import com.gsas.model.ProfessionVO;
import com.gsas.model.SchemeEligibilityVO;
import com.gsas.model.SchemeVO;
import com.gsas.model.SectorVO;
import com.gsas.utility.DBUtility;

public class SchemeDaoImpl implements SchemeDao{
	private Connection connection;

	@Override
	public void addScheme(SchemeVO scheme) throws DatabaseException {
		try {
			connection = DBUtility.getConnection();
			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for scheme_seq)");
			ResultSet rs = sequenceStatement.executeQuery();
			long seq = 0;
			if(rs.next()) {
				seq = rs.getLong(1);
			} 
			if(seq == 0) {
				//Need to throw an error
				System.out.println("Error in sequence number");
			}

//			
			//Add into Scheme eligibility table
			PreparedStatement selectStatement = connection.prepareStatement("insert into scheme_eligibility values(?,?,?,?,?,?)");
			selectStatement.setLong(1, seq);//scheme eligibility ID
			selectStatement.setInt(2, scheme.getSchemeEligibilityVO().getMaxAge());
			selectStatement.setInt(3, scheme.getSchemeEligibilityVO().getMinAge());
			selectStatement.setLong(4, scheme.getSchemeEligibilityVO().getIncomeGroupVO().getIncomeGroupId());//income_group_ref
			selectStatement.setString(5, scheme.getSchemeEligibilityVO().getGender());
			selectStatement.setLong(6, scheme.getSchemeEligibilityVO().getProfessionVO().getProfessionId());//profession_ref
			selectStatement.executeUpdate();
			

			
			//Add into Scheme Table
			selectStatement = connection.prepareStatement("insert into scheme_master values(?,?,?,?,?,?,?,?,?,?)");
			selectStatement.setLong(1, seq);//scheme id
			selectStatement.setString(2, scheme.getSchemeName());
			selectStatement.setString(3, scheme.getSummary());
			selectStatement.setString(4, scheme.getDescription());
			selectStatement.setString(5, scheme.getImagePath());
			selectStatement.setLong(6, scheme.getMinistryVO().getMinistryId());//ministry ref
			selectStatement.setLong(7, scheme.getSectorVO().getSectorId());//sector ref
			selectStatement.setDate(8, java.sql.Date.valueOf(scheme.getStartDate()));
			selectStatement.setLong(9, scheme.getSchemeEligibilityVO().getSchemeEligibilityId());//scheme Eligibility ref
			selectStatement.setBoolean(10, scheme.isStatus());
			selectStatement.executeUpdate();
			
			
			
			List<DocumentVO> docList=scheme.getDocumentList();
			for(DocumentVO doc : docList ) {
				selectStatement = connection.prepareStatement("insert into scheme_documents values(?,?,?)");
				selectStatement.setLong(1, seq);
				selectStatement.setLong(2, scheme.getSchemeId());
				selectStatement.setLong(3, doc.getDocumentId());
				selectStatement.executeUpdate();
			}
			List<BankVO> bankList=scheme.getBankList();
			for(BankVO bank : bankList) {
				selectStatement = connection.prepareStatement("insert into scheme_banks values(?,?,?)");
				selectStatement.setLong(1, seq);
				selectStatement.setLong(2, scheme.getSchemeId());
				selectStatement.setLong(3, bank.getBankId());
				selectStatement.executeUpdate();
			}
			
			
			selectStatement.close();
			connection.close();

		}catch (ClassNotFoundException | SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}

	@Override
	public void editScheme(SchemeVO scheme) throws DatabaseException {
		try {
			Connection connection = DBUtility.getConnection();
			PreparedStatement selectStatement = connection.prepareStatement("update scheme_eligibility set min_age=?, max_age=?, income_group_ref=?, gender=?, profession_ref=? where scheme_eligibility_id=?");
			selectStatement.setInt(1, scheme.getSchemeEligibilityVO().getMinAge());
			selectStatement.setInt(2, scheme.getSchemeEligibilityVO().getMaxAge());
			selectStatement.setLong(3, scheme.getSchemeEligibilityVO().getIncomeGroupVO().getIncomeGroupId());
			selectStatement.setString(4, scheme.getSchemeEligibilityVO().getGender());
			selectStatement.setLong(5, scheme.getSchemeEligibilityVO().getProfessionVO().getProfessionId());
			selectStatement.setLong(6, scheme.getSchemeId());
			
			selectStatement = connection.prepareStatement("update scheme_master set name=?, summary=?, description=?, image_path=?, ministry_ref=?, sector_ref=?, start_date=?, scheme_eligibility_ref=?, status=? where scheme_id=?");
			selectStatement.setString(1, scheme.getSchemeName());
			selectStatement.setString(2, scheme.getSummary());
			selectStatement.setString(3, scheme.getDescription());
			selectStatement.setString(4, scheme.getImagePath());
			selectStatement.setLong(5, scheme.getMinistryVO().getMinistryId());
			selectStatement.setLong(6, scheme.getSectorVO().getSectorId());
			selectStatement.setDate(7, java.sql.Date.valueOf(scheme.getStartDate()));
			selectStatement.setLong(8, scheme.getSchemeEligibilityVO().getSchemeEligibilityId());
			selectStatement.setBoolean(9, scheme.isStatus());
			selectStatement.setLong(10, scheme.getSchemeId());
			selectStatement.executeUpdate();
			
			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for scheme_seq)");
			ResultSet rs = sequenceStatement.executeQuery();
			long seq = 0;
			if(rs.next()) {
				seq = rs.getLong(1);
			} 
			if(seq == 0) {
				//Need to throw an error
				System.out.println("Error in sequence number");
			}
			
			selectStatement = connection.prepareStatement("delete FROM scheme_documents_id where scheme_ref=?");
			selectStatement.setLong(1, scheme.getSchemeId());
			selectStatement.executeUpdate();
			
			List<DocumentVO> docList=scheme.getDocumentList();
			for(DocumentVO doc : docList ) {
				selectStatement = connection.prepareStatement("insert into scheme_documents values(?,?,?)");
				selectStatement.setLong(1, seq);
				selectStatement.setLong(2, scheme.getSchemeId());
				selectStatement.setLong(3, doc.getDocumentId());
				selectStatement.executeUpdate();
			}
			
			selectStatement = connection.prepareStatement("delete FROM scheme_bank where scheme_ref=?");
			selectStatement.setLong(1, scheme.getSchemeId());
			selectStatement.executeUpdate();
			
			List<BankVO> bankList=scheme.getBankList();
			for(BankVO bank : bankList) {
				selectStatement = connection.prepareStatement("insert into scheme_banks values(?,?,?)");
				selectStatement.setLong(1, seq);
				selectStatement.setLong(2, scheme.getSchemeId());
				selectStatement.setLong(3, bank.getBankId());
				selectStatement.executeUpdate();
			}
			
			
			selectStatement.close();
			connection.close();
			
			
		}catch (ClassNotFoundException | SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}

	@Override
	public SchemeVO getSchemeDetails(Long schemeId) throws SchemeNotFoundException, DatabaseException {
		// TODO Auto-generated method stub
		SchemeVO schemeVO = null;
		
		try {
			Connection connection = DBUtility.getConnection();
			PreparedStatement selectStatement = connection.prepareStatement("select * from scheme_master s inner join ministry m on s.ministry_ref = m.ministry_id inner join sector sec on s.sector_ref = sec.sector_id  where scheme_id = ?");
			selectStatement.setLong(1, schemeId);
			ResultSet resultSet = selectStatement.executeQuery();
			while(resultSet.next()){
				schemeVO = new SchemeVO();
				schemeVO.setSchemeId(resultSet.getLong("scheme_id"));
				schemeVO.setSchemeName(resultSet.getString("name"));
				schemeVO.setSummary(resultSet.getString("summary"));
				schemeVO.setDescription(resultSet.getString("description"));
				schemeVO.setImagePath(resultSet.getString("image_path"));
				
				//Fetching ministry
				MinistryVO minstryVO = new MinistryVO(resultSet.getLong("ministry_id"),resultSet.getString("ministry_name") );
				schemeVO.setMinistryVO(minstryVO);
				
				//Fetching sector
				SectorVO sectorVO = new SectorVO(resultSet.getLong("sector_id"),resultSet.getString("sector_name"));
				schemeVO.setSectorVO(sectorVO);
				
				schemeVO.setStartDate(resultSet.getDate("start_date").toLocalDate());
				
				//Fetching Scheme Eligibility
				long schemeEligibilityId = resultSet.getLong("scheme_eligibility_ref");
				PreparedStatement getSchemeDetailsStatement = connection.prepareStatement("select * from scheme_elegibility s inner join income_group i on s.income_group_ref = i.income_group_id inner join profession p on s.profession_ref = p.profession_id  where scheme_elegibility_id = ?");
				getSchemeDetailsStatement.setLong(1, schemeEligibilityId);
				ResultSet getSchemeResultset = getSchemeDetailsStatement.executeQuery();
				
				while(getSchemeResultset.next()) {
					SchemeEligibilityVO schemeEligibilityVO = new SchemeEligibilityVO();
					schemeEligibilityVO.setMinAge(getSchemeResultset.getInt("min_age"));
					schemeEligibilityVO.setMaxAge(getSchemeResultset.getInt("max_age"));
					schemeEligibilityVO.setGender(getSchemeResultset.getString("gender"));
					
					IncomeGroupVO incomeGroupVO = new IncomeGroupVO(getSchemeResultset.getLong("income_group_id"), getSchemeResultset.getString("income_group_name"));
					schemeEligibilityVO.setIncomeGroupVO(incomeGroupVO);
					
					ProfessionVO professionVO = new ProfessionVO(getSchemeResultset.getLong("profession_id"), getSchemeResultset.getString("profession_name"));
					schemeEligibilityVO.setProfessionVO(professionVO);
					schemeVO.setSchemeEligibilityVO(schemeEligibilityVO);
					
				}
				schemeVO.setStatus(resultSet.getBoolean("status"));
				
				getSchemeDetailsStatement = connection.prepareStatement("select * from scheme_banks inner join bank b on s.bank_ref = b.bank_id where scheme_ref = ?");
				getSchemeDetailsStatement.setLong(1, schemeEligibilityId);
				getSchemeResultset = getSchemeDetailsStatement.executeQuery();
				
				BankVO bankVO = null;
				List<BankVO> bankList = null;
				while(getSchemeResultset.next()) {
					bankVO = new BankVO();
					bankVO.setBankId(getSchemeResultset.getLong("bank_id"));
					bankVO.setBankName(getSchemeResultset.getString("bank_name"));
					bankList.add(bankVO);
				}
				schemeVO.setBankList(bankList);
				
				getSchemeDetailsStatement = connection.prepareStatement("select * from scheme_documents s inner join documents d on s.document_ref = d.document_id where scheme_ref = ?");
				getSchemeDetailsStatement.setLong(1, schemeEligibilityId);
				getSchemeResultset = getSchemeDetailsStatement.executeQuery();
				
				DocumentVO documentVO = null;
				List<DocumentVO> documentList = null;
				
				while(getSchemeResultset.next()) {
					documentVO = new DocumentVO();
					documentVO.setDocumentId(getSchemeResultset.getLong("document_id"));
					documentVO.setDocumentName(getSchemeResultset.getString("document_name"));
					documentList.add(documentVO);
				}
				schemeVO.setDocumentList(documentList);
				
				getSchemeResultset.close();
				getSchemeDetailsStatement.close();
				
			}
			
			resultSet.close();
			selectStatement.close();
			connection.close();
			if(schemeVO == null) {
				throw new SchemeNotFoundException("Sorry Scheme doesn't exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new DatabaseException("Something Went Wrong");
		}

	return schemeVO;
	}

	@Override
	public List<SchemeVO> getAllScheme() throws DatabaseException, SchemeNotFoundException {
		
		List<SchemeVO> schemeList = new ArrayList<SchemeVO>();
		
		try {
			Connection connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("select scheme_id,name,summary,description,image_path,status from scheme_master");
			
			ResultSet resultSet = selectStatement.executeQuery();
			while(resultSet.next()) {
				SchemeVO schemeVO = new SchemeVO();
				schemeVO.setSchemeId(resultSet.getLong("scheme_id"));
				schemeVO.setSchemeName(resultSet.getString("name"));
				schemeVO.setSummary(resultSet.getString("summary"));
				schemeVO.setDescription(resultSet.getString("description"));
				schemeVO.setImagePath(resultSet.getString("image_path"));
				
//				//Fetching data from Ministry
//				long ministryId = resultSet.getLong("ministry_ref");
//				PreparedStatement ministryStatement = connection.prepareStatement("select * from ministry where ministry_id = ?");
//				ministryStatement.setLong(1, ministryId);
//				ResultSet ministryResultSet = ministryStatement.executeQuery();
//				String ministryName = ministryResultSet.getString("ministry_name");
//				MinistryVO ministryVO = new MinistryVO(ministryId, ministryName);
//				schemeVO.setMinistryVO(ministryVO);
//				
//				//Fetching data from Sector
//				long sectorId = resultSet.getLong("sector_ref");
//				PreparedStatement sectorStatement = connection.prepareStatement("select * from sector where sector_id = ?");
//				sectorStatement.setLong(1, sectorId);
//				ResultSet sectorResultSet = sectorStatement.executeQuery();
//				String sectorName = sectorResultSet.getString("sector_name");
//				SectorVO sectorVO = new SectorVO(sectorId, sectorName);
//				scheme.setSectorVO(sectorVO);
//				
//				scheme.setStartDate(resultSet.getDate("start_date").toLocalDate());
//				
//				//Fetch data from Scheme Eligibility
//				long schemeEligibilityId = resultSet.getLong("scheme_eligibility_ref");
//				PreparedStatement getSchemeDetailsStatement = connection.prepareStatement("select * from scheme_elegibility s inner join income_group i on s.income_group_ref = i.income_group_id inner join profession p on s.profession_ref = p.profession_id  where scheme_elegibility_id = ?");
//				getSchemeDetailsStatement.setLong(1, schemeEligibilityId);
//				ResultSet getSchemeResultset = getSchemeDetailsStatement.executeQuery();
//				int minAge = getSchemeResultset.getInt("min_age");
//				int maxAge = getSchemeResultset.getInt("max_age");
//				//Fetch data from Income Group
//				IncomeGroupVO incomeGroupVO = new IncomeGroupVO(getSchemeResultset.getLong("income_group_id"), getSchemeResultset.getString("income_group_name"));
//				//Fetch data from Profession
//				ProfessionVO professionVO = new ProfessionVO(getSchemeResultset.getLong("profession_id"), getSchemeResultset.getString("profession_name"));
//				String gender = getSchemeResultset.getString("gender");
//				SchemeEligibilityVO schemeEligibilityVO = new SchemeEligibilityVO(schemeEligibilityId, minAge, maxAge, incomeGroupVO, gender, professionVO);
//				scheme.setSchemeEligibilityVO(schemeEligibilityVO);
				
				schemeVO.setStatus(resultSet.getBoolean("status"));
				
				schemeList.add(schemeVO);
			}
			
			resultSet.close();
			selectStatement.close();
			connection.close();
			if(schemeList.isEmpty()) {
				throw new SchemeNotFoundException("Please Add Schemes First");
			}
		} catch(SQLException | ClassNotFoundException e) {

			throw new DatabaseException(e.getMessage());
		}
		return schemeList;

	}
	
	
}
