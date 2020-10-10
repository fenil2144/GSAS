package com.gsas.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gsas.exception.AuthenticationException;
import com.gsas.exception.DatabaseException;
import com.gsas.model.LoginVO;
import com.gsas.service.EmployeeService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class EmployeeLoginServlet
 */
@WebServlet("/EmployeeLoginServlet")
public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmployeeLoginServlet() {
        super();
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeService employeeService = (EmployeeService) ObjectFactory.getInstance(LayerType.EMPLOYEE_SERVICE);
		RequestDispatcher rd = null;
		try {
			HttpSession session = request.getSession();
			LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
			if(loginVO != null) {
				if(loginVO.isEmployee() == true) {						//If employee is already logged in
					rd = request.getRequestDispatcher("viewSchemesEmployeeServlet");
					rd.forward(request, response);
				}
				else {													//If user is already logged in
					rd = request.getRequestDispatcher("viewSchemesCitizenServlet");
					rd.forward(request, response);
				}
			}
			else {
				loginVO = employeeService.Authenticate(request.getParameter("username"), request.getParameter("password"));
				session.setAttribute("loginVO", loginVO);
				rd = request.getRequestDispatcher("viewSchemesEmployeeServlet");
				rd.forward(request, response);
			}
			
		} catch (AuthenticationException | DatabaseException e) {
			rd = request.getRequestDispatcher("loginFailure.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}

}
