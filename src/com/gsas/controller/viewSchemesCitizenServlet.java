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
import com.gsas.service.CitizenService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class viewSchemesCitizenServlet
 */
@WebServlet("/viewSchemesCitizenServlet")
public class viewSchemesCitizenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public viewSchemesCitizenServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CitizenService citizenService = (CitizenService) ObjectFactory.getInstance(LayerType.CITIZEN_SERVICE);
		RequestDispatcher rd = null;
		List<SchemeVO> notAppliedSchemeList = null;
		List<SchemeVO> acceptedSchemeList = null;
		List<SchemeVO> rejectedSchemeList = null;

		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
		
		try {
			if(loginVO != null) {
				if(loginVO.isEmployee() == false) {						//If user is already logged in
					
					notAppliedSchemeList = citizenService.getNotAppliedSchemeList(loginVO.getLoginId());			//return list of all schemes
					request.setAttribute("notAppliedSchemeList", notAppliedSchemeList);
					
					acceptedSchemeList = citizenService.getAppliedSchemeList(loginVO.getLoginId(), true);
					request.setAttribute("acceptedSchemeList", acceptedSchemeList);
					
					rejectedSchemeList = citizenService.getAppliedSchemeList(loginVO.getLoginId(), false);
					request.setAttribute("rejectedSchemeList", rejectedSchemeList);
					
					rd = request.getRequestDispatcher("viewAllSchemes.jsp");
					rd.forward(request, response);
				}
				else {													//If employee is already logged in
					rd = request.getRequestDispatcher("viewSchemesCitizenServlet");
					rd.forward(request, response);
				}
			}
			else {
				
				rd = request.getRequestDispatcher("citizenlogin.jsp");
				rd.forward(request, response);
			}
		}catch(SchemeNotFoundException | DatabaseException e) {
			rd = request.getRequestDispatcher("viewAllSchemes.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}



}
