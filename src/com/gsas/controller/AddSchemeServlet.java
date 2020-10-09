package com.gsas.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gsas.exception.DataNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.BankVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.IncomeGroupVO;
import com.gsas.model.LoginVO;
import com.gsas.model.MinistryVO;
import com.gsas.model.ProfessionVO;
import com.gsas.model.SectorVO;
import com.gsas.service.CommonService;
import com.gsas.service.SchemeService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class AddSchemeServlet
 */
@WebServlet("/AddSchemeServlet")
public class AddSchemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddSchemeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommonService commonService = (CommonService) ObjectFactory.getInstance(LayerType.COMMON_SERVICE);
		RequestDispatcher rd = null;
		List<MinistryVO> ministryList = null;
		List<SectorVO> sectorList = null;
		List<ProfessionVO> professionList = null;
		List<IncomeGroupVO> incomeGroupList = null;
		List<DocumentVO> documentList = null;
		List<BankVO> bankList = null;
		
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
		
		try {
			if(loginVO != null) {
				if(loginVO.isEmployee() == true) {						//If employee is already logged in
					
					ministryList = commonService.getAllMinistry();
					request.setAttribute("ministryList", ministryList);
					
					sectorList = commonService.getAllSectors();
					request.setAttribute("sectorList", sectorList);
					
					professionList = commonService.getAllProfessions();
					request.setAttribute("professionList", professionList);
					
					incomeGroupList = commonService.getAllIncomeGroups();
					request.setAttribute("incomeGroupList", incomeGroupList);
					
					documentList = commonService.getAllDocuments();
					request.setAttribute("documentList", documentList);
					
					bankList = commonService.getAllBanks();
					request.setAttribute("bankList", bankList);
					
					
					rd = request.getRequestDispatcher("AddScheme.jsp");
					rd.forward(request, response);
				}
				else {													//If user is already logged in
					rd = request.getRequestDispatcher("viewSchemesCitizenServlet");
					rd.forward(request, response);
				}
			}
			else {
				request.setAttribute("err", "Please Login First");
				rd = request.getRequestDispatcher("employeeLogin.jsp");
				rd.forward(request, response);
			}
		}catch(DatabaseException | DataNotFoundException e) {
			rd = request.getRequestDispatcher("viewAllSchemes.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		} 
	}



}
