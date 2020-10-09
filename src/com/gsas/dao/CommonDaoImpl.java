package com.gsas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gsas.exception.DataNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.model.BankVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.IncomeGroupVO;
import com.gsas.model.MinistryVO;
import com.gsas.model.ProfessionVO;
import com.gsas.model.SectorVO;
import com.gsas.utility.DBUtility;

public class CommonDaoImpl implements CommonDao {
	private Connection connection;

	@Override
	public List<MinistryVO> getAllMinistry() throws DataNotFoundException, DatabaseException {
		
		List<MinistryVO> ministryList = new ArrayList<MinistryVO>();
		
		try {
			connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("select * from ministry");
			ResultSet resultSet = selectStatement.executeQuery();
			
			MinistryVO ministryVO = null;
			while(resultSet.next()) {
				ministryVO = new MinistryVO();
				ministryVO.setMinistryId(resultSet.getLong("ministry_id"));
				ministryVO.setMinistryName(resultSet.getString("ministry_name"));

				ministryList.add(ministryVO);
			}
			
			resultSet.close();
			selectStatement.close();
			connection.close();
			if(ministryList.isEmpty()) {
				throw new DataNotFoundException("Ministry Not Found");
			}
		} catch(SQLException | ClassNotFoundException e) {

			throw new DatabaseException(e.getMessage());
		}
		return ministryList;
		
	}

	@Override
	public List<SectorVO> getAllSectors() throws DataNotFoundException, DatabaseException{
		List<SectorVO> sectorList = new ArrayList<SectorVO>();
		
		try {
			connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("select * from sector");
			ResultSet resultSet = selectStatement.executeQuery();
			
			SectorVO sectorVO = null;
			while(resultSet.next()) {
				sectorVO = new SectorVO();
				sectorVO.setSectorId(resultSet.getLong("sector_id"));
				sectorVO.setSectorName(resultSet.getString("sector_name"));

				sectorList.add(sectorVO);
			}
			
			resultSet.close();
			selectStatement.close();
			connection.close();
			if(sectorList.isEmpty()) {
				throw new DataNotFoundException("Sectors Not Found");
			}
		} catch(SQLException | ClassNotFoundException e) {

			throw new DatabaseException(e.getMessage());
		}
		return sectorList;
		
	}

	@Override
	public List<ProfessionVO> getAllProfessions()  throws DataNotFoundException, DatabaseException{
		List<ProfessionVO> professionList = new ArrayList<ProfessionVO>();
		
		try {
			connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("select * from profession");
			ResultSet resultSet = selectStatement.executeQuery();
			
			ProfessionVO professionVO = null;
			while(resultSet.next()) {
				professionVO = new ProfessionVO();
				professionVO.setProfessionId(resultSet.getLong("profession_id"));
				professionVO.setProfessionName(resultSet.getString("profession_name"));

				professionList.add(professionVO);
			}
			
			resultSet.close();
			selectStatement.close();
			connection.close();
			if(professionList.isEmpty()) {
				throw new DataNotFoundException("Profession Not Found.");
			}
		} catch(SQLException | ClassNotFoundException e) {

			throw new DatabaseException(e.getMessage());
		}
		return professionList;
		
	}

	@Override
	public List<IncomeGroupVO> getAllIncomeGroups()  throws DataNotFoundException, DatabaseException{
		List<IncomeGroupVO> incomeGroupList = new ArrayList<IncomeGroupVO>();
		
		try {
			connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("select * from income_group");
			ResultSet resultSet = selectStatement.executeQuery();
			
			IncomeGroupVO incomeGroupVO = null;
			while(resultSet.next()) {
				incomeGroupVO = new IncomeGroupVO();
				incomeGroupVO.setIncomeGroupId(resultSet.getLong("income_group_id"));
				incomeGroupVO.setIncomeGroupName(resultSet.getString("income_group_name "));

				incomeGroupList.add(incomeGroupVO);
			}
			
			resultSet.close();
			selectStatement.close();
			connection.close();
			if(incomeGroupList.isEmpty()) {
				throw new DataNotFoundException("Income Group Not Found");
			}
		} catch(SQLException | ClassNotFoundException e) {

			throw new DatabaseException(e.getMessage());
		}
		return incomeGroupList;
		
	}

	@Override
	public List<DocumentVO> getAllDocuments()  throws DataNotFoundException, DatabaseException{
		List<DocumentVO> documentList = new ArrayList<DocumentVO>();
		
		try {
			connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("select * from document");
			ResultSet resultSet = selectStatement.executeQuery();
			
			DocumentVO documentVO = null;
			while(resultSet.next()) {
				documentVO = new DocumentVO();
				documentVO.setDocumentId(resultSet.getLong("document_id"));
				documentVO.setDocumentName(resultSet.getString("document_name"));

				documentList.add(documentVO);
			}
			
			resultSet.close();
			selectStatement.close();
			connection.close();
			if(documentList.isEmpty()) {
				throw new DataNotFoundException("Document Not Found");
			}
		} catch(SQLException | ClassNotFoundException e) {

			throw new DatabaseException(e.getMessage());
		}
		return documentList;
		
	}

	@Override
	public List<BankVO> getAllBanks()  throws DataNotFoundException, DatabaseException{
		List<BankVO> bankList = new ArrayList<BankVO>();
		
		try {
			connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("select * from bank");
			ResultSet resultSet = selectStatement.executeQuery();
			
			BankVO bankVO = null;
			while(resultSet.next()) {
				bankVO = new BankVO();
				bankVO.setBankId(resultSet.getLong("bank_id"));
				bankVO.setBankName(resultSet.getString("bank_name"));

				bankList.add(bankVO);
			}
			
			resultSet.close();
			selectStatement.close();
			connection.close();
			if(bankList.isEmpty()) {
				throw new DataNotFoundException("Bank Not Found");
			}
		} catch(SQLException | ClassNotFoundException e) {

			throw new DatabaseException(e.getMessage());
		}
		return bankList;
		
	}

}
