package com.gsas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gsas.exception.DatabaseException;
import com.gsas.exception.InvalidSequenceException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.BankVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.IncomeGroupVO;
import com.gsas.model.MinistryVO;
import com.gsas.model.ProfessionVO;
import com.gsas.model.SchemeApplicantDocumentsVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeEligibilityVO;
import com.gsas.model.SchemeVO;
import com.gsas.model.SectorVO;
import com.gsas.utility.DBUtility;

public class SchemeDaoImpl implements SchemeDao{
	private Connection connection;

	@Override
	public void addScheme(SchemeVO schemeVO) throws DatabaseException, InvalidSequenceException {
		try {
			connection = DBUtility.getConnection();
			connection.setAutoCommit(false);	//Implementing Transaction
			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for scheme_seq)");
			ResultSet resultSet = sequenceStatement.executeQuery();
			long seq = 0;
			if(resultSet.next()) {
				seq = resultSet.getLong(1);
			} 
			if(seq == 0) {
				
				throw new InvalidSequenceException();
			}
			connection.setAutoCommit(false);		//Imlementing Transaction
			
			//Add into Scheme eligibility table
			PreparedStatement selectStatement = connection.prepareStatement("insert into scheme_eligibility values(?,?,?,?,?,?)");
			selectStatement.setLong(1, seq);//scheme eligibility ID
			selectStatement.setInt(2, schemeVO.getSchemeEligibilityVO().getMinAge());
			selectStatement.setInt(3, schemeVO.getSchemeEligibilityVO().getMaxAge());
			selectStatement.setLong(4, schemeVO.getSchemeEligibilityVO().getIncomeGroupVO().getIncomeGroupId());//income_group_ref
			selectStatement.setString(5, schemeVO.getSchemeEligibilityVO().getGender());
			selectStatement.setLong(6, schemeVO.getSchemeEligibilityVO().getProfessionVO().getProfessionId());//profession_ref
			selectStatement.executeUpdate();
			

			
			//Add into Scheme Table
			selectStatement = connection.prepareStatement("insert into scheme_master values(?,?,?,?,?,?,?,?,?,?)");
			selectStatement.setLong(1, seq);//scheme id
			selectStatement.setString(2, schemeVO.getSchemeName());
			selectStatement.setString(3, schemeVO.getSummary());
			selectStatement.setString(4, schemeVO.getDescription());
			selectStatement.setString(5, schemeVO.getImagePath());
			selectStatement.setLong(6, schemeVO.getMinistryVO().getMinistryId());//ministry ref
			selectStatement.setLong(7, schemeVO.getSectorVO().getSectorId());//sector ref
			selectStatement.setDate(8, java.sql.Date.valueOf(schemeVO.getStartDate()));
			selectStatement.setLong(9, seq);//scheme Eligibility ref
			selectStatement.setBoolean(10, schemeVO.isStatus());
			selectStatement.executeUpdate();
			
			
			sequenceStatement = connection.prepareStatement("values(next value for document_seq)");
			long documentSeq = 0;
 
			List<DocumentVO> documentList=schemeVO.getDocumentList();
			for(DocumentVO doc : documentList ) {
				resultSet = sequenceStatement.executeQuery();
				if(resultSet.next()) {
					documentSeq = resultSet.getLong(1);
				} 
				if(documentSeq == 0) {
					throw new InvalidSequenceException();
				}
				selectStatement = connection.prepareStatement("insert into scheme_documents values(?,?,?)");
				selectStatement.setLong(1, documentSeq);
				selectStatement.setLong(2, seq);
				selectStatement.setLong(3, doc.getDocumentId());
				selectStatement.executeUpdate();
			}
			
			sequenceStatement = connection.prepareStatement("values(next value for bank_seq)");
			long banktSeq = 0;
			List<BankVO> bankList=schemeVO.getBankList();
			for(BankVO bank : bankList) {
				resultSet = sequenceStatement.executeQuery();
				if(resultSet.next()) {
					banktSeq = resultSet.getLong(1);
				} 
				if(banktSeq == 0) {
					throw new InvalidSequenceException();
				}
				selectStatement = connection.prepareStatement("insert into scheme_banks values(?,?,?)");
				selectStatement.setLong(1, banktSeq);
				selectStatement.setLong(2, seq);
				selectStatement.setLong(3, bank.getBankId());
				selectStatement.executeUpdate();
			}

			connection.commit();	//Committing the changes.

			selectStatement.close();
			connection.close();

		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
		
	}

	@Override
	public void editScheme(SchemeVO scheme) throws DatabaseException, InvalidSequenceException {
		try {
			Connection connection = DBUtility.getConnection();
			//connection.setAutoCommit(false);	//Implementing Transaction
			PreparedStatement selectStatement = connection.prepareStatement("update scheme_eligibility set min_age=?, max_age=?, income_group_ref=?, gender=?, profession_ref=? where scheme_eligibility_id=?");
			selectStatement.setInt(1, scheme.getSchemeEligibilityVO().getMinAge());
			selectStatement.setInt(2, scheme.getSchemeEligibilityVO().getMaxAge());
			selectStatement.setLong(3, scheme.getSchemeEligibilityVO().getIncomeGroupVO().getIncomeGroupId());
			selectStatement.setString(4, scheme.getSchemeEligibilityVO().getGender());
			selectStatement.setLong(5, scheme.getSchemeEligibilityVO().getProfessionVO().getProfessionId());
			selectStatement.setLong(6, scheme.getSchemeEligibilityVO().getSchemeEligibilityId());
			selectStatement.executeUpdate();
			
			selectStatement = connection.prepareStatement("update scheme_master set scheme_name=?, summary=?, description=?, image_path=?, ministry_ref=?, sector_ref=?, start_date=?, scheme_eligibility_ref=?, status=? where scheme_id=?");
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
			ResultSet resultSet = sequenceStatement.executeQuery();
			long seq = 0;
			if(resultSet.next()) {
				seq = resultSet.getLong(1);
			} 
			if(seq == 0) {
				System.out.println("Error in sequence number");
			}
			
			selectStatement = connection.prepareStatement("delete FROM scheme_documents where scheme_ref=?");
			selectStatement.setLong(1, scheme.getSchemeId());
			selectStatement.executeUpdate();
			
			sequenceStatement = connection.prepareStatement("values(next value for document_seq)");
			long documentSeq = 0;
 
			List<DocumentVO> documentList=scheme.getDocumentList();
			for(DocumentVO doc : documentList ) {
				resultSet = sequenceStatement.executeQuery();
				if(resultSet.next()) {
					documentSeq = resultSet.getLong(1);
				} 
				if(documentSeq == 0) {
					throw new InvalidSequenceException();
				}
				selectStatement = connection.prepareStatement("insert into scheme_documents values(?,?,?)");
				selectStatement.setLong(1, documentSeq);
				selectStatement.setLong(2, scheme.getSchemeId());
				selectStatement.setLong(3, doc.getDocumentId());
				selectStatement.executeUpdate();
			}
			
			selectStatement = connection.prepareStatement("delete FROM scheme_banks where scheme_ref=?");
			selectStatement.setLong(1, scheme.getSchemeId());
			selectStatement.executeUpdate();
			
			sequenceStatement = connection.prepareStatement("values(next value for bank_seq)");
			long bankSeq = 0;
			List<BankVO> bankList=scheme.getBankList();
			for(BankVO bank : bankList) {
				resultSet = sequenceStatement.executeQuery();
				if(resultSet.next()) {
					bankSeq = resultSet.getLong(1);
				} 
				if(bankSeq == 0) {
					throw new InvalidSequenceException();
				}
				selectStatement = connection.prepareStatement("insert into scheme_banks values(?,?,?)");
				selectStatement.setLong(1, bankSeq);
				selectStatement.setLong(2, scheme.getSchemeId());
				selectStatement.setLong(3, bank.getBankId());
				selectStatement.executeUpdate();
			}

			//connection.commit();	//Committing the changes.
			selectStatement.close();
			connection.close();
			
			
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
		
	}

	@Override
	public SchemeVO getSchemeDetails(Long schemeId) throws SchemeNotFoundException, DatabaseException {
		SchemeVO schemeVO = null;
		
		try {
			Connection connection = DBUtility.getConnection();
			PreparedStatement selectStatement = connection.prepareStatement("select * from scheme_master s inner join ministry m on s.ministry_ref = m.ministry_id inner join sector sec on s.sector_ref = sec.sector_id  where scheme_id = ?");
			selectStatement.setLong(1, schemeId);
			ResultSet resultSet = selectStatement.executeQuery();
			while(resultSet.next()){
				schemeVO = new SchemeVO();
				schemeVO.setSchemeId(resultSet.getLong("scheme_id"));
				schemeVO.setSchemeName(resultSet.getString("scheme_name"));
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
				PreparedStatement getSchemeDetailsStatement = connection.prepareStatement("select * from scheme_eligibility s inner join income_group i on s.income_group_ref = i.income_group_id inner join profession p on s.profession_ref = p.profession_id  where scheme_eligibility_id = ?");
				getSchemeDetailsStatement.setLong(1, schemeEligibilityId);
				ResultSet getSchemeResultset = getSchemeDetailsStatement.executeQuery();
				
				while(getSchemeResultset.next()) {
					SchemeEligibilityVO schemeEligibilityVO = new SchemeEligibilityVO();
					schemeEligibilityVO.setSchemeEligibilityId(getSchemeResultset.getLong("scheme_eligibility_id"));
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
				
				getSchemeDetailsStatement = connection.prepareStatement("select * from scheme_banks s inner join bank b on s.bank_ref = b.bank_id where scheme_ref = ?");
				getSchemeDetailsStatement.setLong(1, schemeEligibilityId);
				getSchemeResultset = getSchemeDetailsStatement.executeQuery();
				
				BankVO bankVO = null;
				List<BankVO> bankList = new ArrayList<BankVO>();
				while(getSchemeResultset.next()) {
					bankVO = new BankVO();
					bankVO.setBankId(getSchemeResultset.getLong("bank_id"));
					bankVO.setBankName(getSchemeResultset.getString("bank_name"));
					bankList.add(bankVO);
				}
				schemeVO.setBankList(bankList);
				
				getSchemeDetailsStatement = connection.prepareStatement("select * from scheme_documents s inner join document d on s.document_ref = d.document_id where scheme_ref = ?");
				getSchemeDetailsStatement.setLong(1, schemeEligibilityId);
				getSchemeResultset = getSchemeDetailsStatement.executeQuery();
				
				DocumentVO documentVO = null;
				List<DocumentVO> documentList = new ArrayList<DocumentVO>();;
				
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
			
			e.printStackTrace();
			throw new DatabaseException("Something Went Wrong");
		}

	return schemeVO;
	}

	@Override
	public List<SchemeVO> getAllScheme() throws DatabaseException, SchemeNotFoundException {
		
		List<SchemeVO> schemeList = new ArrayList<SchemeVO>();
		
		try {
			Connection connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("select scheme_id,scheme_name,summary,description,image_path,status from scheme_master");
			
			ResultSet resultSet = selectStatement.executeQuery();
			while(resultSet.next()) {
				SchemeVO schemeVO = new SchemeVO();
				schemeVO.setSchemeId(resultSet.getLong("scheme_id"));
				schemeVO.setSchemeName(resultSet.getString("scheme_name"));
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
				throw new SchemeNotFoundException("Schemes Not Found");
			}
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
		return schemeList;

	}
	
	//Adding SchemeApplicants  with there document list (this function in only called after validation)
	
	@Override
	public void addSchemeApplicant(SchemeApplicantVO schemeApplicant)
			throws DatabaseException {
		try {
			Connection connection = DBUtility.getConnection();
			connection.setAutoCommit(false);	//Implementing Transaction

			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for scheme_seq)");
			ResultSet resultSet = sequenceStatement.executeQuery();
			long seq = 0;
			if(resultSet.next()) {
				seq = resultSet.getLong(1);
			} 
			if(seq == 0) {
				
				System.out.println("Error in sequence number");
			}
			
			PreparedStatement insertStatement = connection.prepareStatement("insert into scheme_applicant values(?,?,?,?,?,?,?,?,?,?)");
			insertStatement.setLong(1, seq);
			insertStatement.setLong(2, schemeApplicant.getSchemeVO().getSchemeId());
			insertStatement.setLong(3, schemeApplicant.getLoginVO().getLoginId());
			insertStatement.setLong(4, schemeApplicant.getBankVO().getBankId());
			insertStatement.setLong(5, schemeApplicant.getAccountNumber());
			insertStatement.setString(6, schemeApplicant.getTypeOfAccount());
			insertStatement.setString(7, schemeApplicant.getIfsc());
			insertStatement.setString(8, schemeApplicant.getBranch());
			insertStatement.setBoolean(9, schemeApplicant.isApprovedStatus());
			insertStatement.setString(10, schemeApplicant.getReason());
			insertStatement.execute();
			
			for(SchemeApplicantDocumentsVO document: schemeApplicant.getApplicantDocumentsList()) {
				insertStatement = connection.prepareStatement("insert into scheme_applicant_documents values(?,?,?,?)");
				insertStatement.setLong(1, seq);
				insertStatement.setLong(2, seq);
				insertStatement.setLong(3, document.getDocumentVO().getDocumentId());
				insertStatement.setString(4, document.getDocumentPath());
				insertStatement.execute();
				
			}
			connection.commit();

			insertStatement.close();
			connection.close();
			

		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	
	// Adding Scheme Applicant details (this function is called when validation failed and the status and reason is updated in the 
	// Scheme applicant table)
	
	@Override
	public void addRejectedSchemeApplicant(SchemeApplicantVO schemeApplicant) throws DatabaseException {
		try {
			Connection connection = DBUtility.getConnection();
			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for scheme_seq)");
			ResultSet resultSet = sequenceStatement.executeQuery();
			long seq = 0;
			if(resultSet.next()) {
				seq = resultSet.getLong(1);
			} 
			if(seq == 0) {
				
				System.out.println("Error in sequence number");
			}
			PreparedStatement ps = connection.prepareStatement("insert into scheme_applicant values(?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, seq);
			ps.setLong(2, schemeApplicant.getSchemeVO().getSchemeId());
			ps.setLong(3, schemeApplicant.getLoginVO().getLoginId());
//			ps.setLong(4, schemeApplicant.getBankVO().getBankId());
			ps.setLong(4,1);
			ps.setLong(5, schemeApplicant.getAccountNumber());
			ps.setString(6, schemeApplicant.getTypeOfAccount());
			ps.setString(7, schemeApplicant.getIfsc());
			ps.setString(8, schemeApplicant.getBranch());
			ps.setBoolean(9, schemeApplicant.isApprovedStatus());
			ps.setString(10, schemeApplicant.getReason());
			ps.execute();
			
			ps.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public List<DocumentVO> getSchemeDocumentsList(Long scheme_id) throws DatabaseException {
		List<DocumentVO> documentList=new ArrayList<DocumentVO>();
		try {
			Connection connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("SELECT document_id, document_name FROM scheme_documents s INNER JOIN document d ON s.document_ref = d.document_id WHERE scheme_ref=?");
			selectStatement.setLong(1, scheme_id);
			ResultSet resultSet = selectStatement.executeQuery();
			
			while(resultSet.next()) {
				DocumentVO documentVO=new DocumentVO();
				documentVO.setDocumentId(resultSet.getLong("document_id"));
				documentVO.setDocumentName(resultSet.getString("document_name"));
				documentList.add(documentVO);
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
		return documentList;
	}

	@Override
	public List<BankVO> getSchemeBankList(Long scheme_id) throws DatabaseException {
		List<BankVO> bankList=new ArrayList<BankVO>();
		try {
			Connection connection = DBUtility.getConnection();
			
			PreparedStatement selectStatement = connection.prepareStatement("SELECT bank_id, bank_name FROM scheme_banks s INNER JOIN bank b ON s.bank_ref=b.bank_id WHERE scheme_ref=?");
			selectStatement.setLong(1, scheme_id);
			ResultSet resultSet = selectStatement.executeQuery();
			
			while(resultSet.next()) {
				BankVO bankVO=new BankVO();
				bankVO.setBankId(resultSet.getLong("bank_id"));
				bankVO.setBankName(resultSet.getString("bank_name"));
				bankList.add(bankVO);
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
		return bankList;
	}
	
	
	
}