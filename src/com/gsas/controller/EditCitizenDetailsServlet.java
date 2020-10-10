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
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.LoginVO;
import com.gsas.service.CitizenService;
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
		RequestDispatcher requestDispatcher = null;
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");

			try {
				if(loginVO != null) {		//Citizen must be Logged In in order to perform Edit Operation
					CitizenDetailsVO citizenDetailsVO = citizenService.getCitizenDetails(loginVO.getLoginId());
					request.setAttribute("citizenDetailsVO", citizenDetailsVO);
					
					requestDispatcher = request.getRequestDispatcher("editCitizenDetails.jsp");
					requestDispatcher.forward(request, response);
				}
				
			} catch (CitizenNotFoundException e) {
				requestDispatcher = request.getRequestDispatcher("citizenLogin.jsp");
				request.setAttribute("err", e.getMessage());
				requestDispatcher.forward(request, response);
			}
		
	}

}
