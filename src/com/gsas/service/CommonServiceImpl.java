package com.gsas.service;

import java.util.List;

import com.gsas.dao.CitizenDao;
import com.gsas.dao.CommonDao;
import com.gsas.exception.DataNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.model.BankVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.IncomeGroupVO;
import com.gsas.model.MinistryVO;
import com.gsas.model.ProfessionVO;
import com.gsas.model.SectorVO;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

public class CommonServiceImpl implements CommonService {
	private CommonDao commonDao = null;
	
	

	public CommonServiceImpl() {
		commonDao = (CommonDao) ObjectFactory.getInstance(LayerType.CITIZEN_DAO);
	}

	@Override
	public List<MinistryVO> getAllMinistry() throws DataNotFoundException, DatabaseException {
		return commonDao.getAllMinistry();
	}

	@Override
	public List<SectorVO> getAllSectors() throws DataNotFoundException, DatabaseException {
		return commonDao.getAllSectors();
	}

	@Override
	public List<ProfessionVO> getAllProfessions() throws DataNotFoundException, DatabaseException {
		return commonDao.getAllProfessions();
	}

	@Override
	public List<IncomeGroupVO> getAllIncomeGroups() throws DataNotFoundException, DatabaseException {
		return commonDao.getAllIncomeGroups();
	}

	@Override
	public List<DocumentVO> getAllDocuments() throws DataNotFoundException, DatabaseException {
		return commonDao.getAllDocuments();
	}

	@Override
	public List<BankVO> getAllBanks() throws DataNotFoundException, DatabaseException {
		return commonDao.getAllBanks();
	}

}
