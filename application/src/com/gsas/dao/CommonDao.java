package com.gsas.dao;

import java.util.List;

import com.gsas.exception.DataNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.model.BankVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.IncomeGroupVO;
import com.gsas.model.MinistryVO;
import com.gsas.model.ProfessionVO;
import com.gsas.model.SectorVO;

public interface CommonDao {
	
	public List<MinistryVO> getAllMinistry() throws DataNotFoundException, DatabaseException;
	public List<SectorVO> getAllSectors() throws DataNotFoundException, DatabaseException;
	public List<ProfessionVO> getAllProfessions() throws DataNotFoundException, DatabaseException;
	public List<IncomeGroupVO> getAllIncomeGroups() throws DataNotFoundException, DatabaseException;
	public List<DocumentVO> getAllDocuments() throws DataNotFoundException, DatabaseException;
	public List<BankVO> getAllBanks() throws DataNotFoundException, DatabaseException;

}
