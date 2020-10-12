package com.gsas.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gsas.exception.CitizenNotFoundException;
import com.gsas.exception.DataNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.LoginVO;
import com.gsas.service.CitizenService;
import com.gsas.service.CommonService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class EditCitizenDetailsServlet
 */
@WebServlet("/EditCitizenDetailsServlet")
public class EditCitizenDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditCitizenDetailsServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CitizenService citizenService = (CitizenService) ObjectFactory.getInstance(LayerType.CITIZEN_SERVICE);
		CommonService commonService = (CommonService) ObjectFactory.getInstance(LayerType.COMMON_SERVICE);

		RequestDispatcher requestDispatcher = null;
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");

			try {
				if(loginVO != null) {		//Citizen must be Logged In in order to perform Edit Operation
					if(loginVO.isEmployee() == false) {	
						CitizenDetailsVO citizenDetailsVO = citizenService.getCitizenDetails(loginVO.getLoginId());
						request.setAttribute("citizenDetailsVO", citizenDetailsVO);
						request.setAttribute("incomeGroupList",commonService.getAllIncomeGroups());
						request.setAttribute("professionList",commonService.getAllProfessions());
						
						requestDispatcher = request.getRequestDispatcher("editCitizenDetails.jsp");
						requestDispatcher.forward(request, response);
					}
					else {													//If employee is logged in
						requestDispatcher = request.getRequestDispatcher("viewSchemesEmployeeServlet");
						requestDispatcher.forward(request, response);
					}
				}
				else {
					request.setAttribute("err","Please Login First");
					requestDispatcher = request.getRequestDispatcher("citizenLogin.jsp");
					requestDispatcher.forward(request, response);
				}
				
			} catch (CitizenNotFoundException | DataNotFoundException | DatabaseException e) {
				requestDispatcher = request.getRequestDispatcher("citizenLogin.jsp");
				request.setAttribute("err", e.getMessage());
				requestDispatcher.forward(request, response);
			} 
		
	}

}
