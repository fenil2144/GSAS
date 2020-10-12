package com.gsas.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gsas.exception.DataNotFoundException;
import com.gsas.exception.DatabaseException;
import com.gsas.model.LoginVO;
import com.gsas.service.CommonService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class AddCitizenServlet
 */
@WebServlet("/AddCitizenServlet")
public class AddCitizenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddCitizenServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommonService commonService = (CommonService) ObjectFactory.getInstance(LayerType.COMMON_SERVICE);
		RequestDispatcher requestDispatcher = null;
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");

			try {
				if(loginVO != null) {		//Citizen must be Logged In in order to perform Edit Operation
					System.out.println(loginVO.toString());
					if(loginVO.isEmployee() == false) {	
						
						requestDispatcher = request.getRequestDispatcher("viewSchemesCitizenServlet");
						requestDispatcher.forward(request, response);
					}
					else {													//If employee is logged in
						requestDispatcher = request.getRequestDispatcher("viewSchemesEmployeeServlet");
						requestDispatcher.forward(request, response);
					}
				}
				else {
					request.setAttribute("incomeGroupList",commonService.getAllIncomeGroups());
					request.setAttribute("professionList",commonService.getAllProfessions());
					
					
					requestDispatcher = request.getRequestDispatcher("registerCitizen.jsp");
					requestDispatcher.forward(request, response);
				}
				
			} catch (DataNotFoundException | DatabaseException e) {
				e.printStackTrace();
				requestDispatcher = request.getRequestDispatcher("citizenLogin.jsp");
				request.setAttribute("err", e.getMessage());
				requestDispatcher.forward(request, response);
			} 
	}


}
