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
import com.gsas.model.LoginVO;
import com.gsas.service.CitizenService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class CitizenLoginServlet
 */
@WebServlet("/CitizenLoginServlet")
public class CitizenLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CitizenLoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CitizenService citizenService = (CitizenService) ObjectFactory.getInstance(LayerType.CITIZEN_SERVICE);
		RequestDispatcher rd = null;
		try {
			LoginVO loginVO = citizenService.Authenticate(request.getParameter("username"), request.getParameter("password"));
			HttpSession session = request.getSession();
			session.setAttribute("loginVO", loginVO);
			
			
			rd = request.getRequestDispatcher("viewAllSchemes.jsp");
			rd.forward(request, response);
			
		} catch (AuthenticationException e) {
			rd = request.getRequestDispatcher("loginFailure.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}

}
