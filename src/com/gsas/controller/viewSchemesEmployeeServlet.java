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

import com.gsas.exception.DatabaseException;
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.LoginVO;
import com.gsas.model.SchemeVO;
import com.gsas.service.SchemeService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class viewSchemesEmployeeServlet
 */
@WebServlet("/viewSchemesEmployeeServlet")
public class viewSchemesEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public viewSchemesEmployeeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SchemeService schemeService = (SchemeService) ObjectFactory.getInstance(LayerType.SCHEME_SERVICE);
		RequestDispatcher rd = null;
		List<SchemeVO> schemeList = null;

		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
		
		try {
			if(loginVO != null) {
				if(loginVO.isEmployee() == true) {						//If employee is already logged in
					schemeList = schemeService.getAllScheme();			//return list of all schemes
					rd = request.getRequestDispatcher("viewAllSchemes.jsp");
					rd.forward(request, response);
				}
				else {													//If user is already logged in
					rd = request.getRequestDispatcher("viewSchemesCitizenServlet");
					rd.forward(request, response);
				}
			}
			else {
				
				rd = request.getRequestDispatcher("employeeLogin.jsp");
				rd.forward(request, response);
			}
		}catch(SchemeNotFoundException | DatabaseException e) {
			rd = request.getRequestDispatcher("viewAllSchemes.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}

	}



}
