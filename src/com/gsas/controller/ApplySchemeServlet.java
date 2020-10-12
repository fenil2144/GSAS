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
import com.gsas.exception.SchemeNotFoundException;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.LoginVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeEligibilityVO;
import com.gsas.model.SchemeVO;
import com.gsas.service.CitizenService;
import com.gsas.service.SchemeService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;



/**
 * Servlet implementation class ApplySchemeServlet
 */
@WebServlet("/ApplySchemeServlet")
public class ApplySchemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ApplySchemeServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SchemeService schemeService = (SchemeService) ObjectFactory.getInstance(LayerType.SCHEME_SERVICE);
		CitizenService citizenService = (CitizenService) ObjectFactory.getInstance(LayerType.CITIZEN_SERVICE);
		HttpSession session = request.getSession();
		RequestDispatcher requestDispatcher = null;
		LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
		try {

			if(loginVO != null) {
				if(loginVO.isEmployee() == false) {	

					SchemeApplicantVO schemeApplicant = new SchemeApplicantVO();
					SchemeVO schemeVO = schemeService.getSchemeDetails(Long.parseLong(request.getParameter("schemeId"))); // get scheme from schemeID
					SchemeEligibilityVO schemeEligibilityVO = schemeVO.getSchemeEligibilityVO(); //get Eligibility of that scheme
					CitizenDetailsVO citizenDetails = citizenService.getCitizenDetails(loginVO.getLoginId()); //get citizenDetails from citizenId	
	
					
					schemeVO.setBankList(schemeService.getSchemeBankList(Long.parseLong(request.getParameter("schemeId"))));
					schemeVO.setDocumentList(schemeService.getSchemeDocumentsList(Long.parseLong(request.getParameter("schemeId"))));
					
					//schemeApplicant Object
					schemeApplicant.setSchemeVO(schemeVO);
					schemeApplicant.setLoginVO(loginVO);
					
					
					 schemeApplicant.setBankVO(null);
					 schemeApplicant.setAccountNumber(0);
					 schemeApplicant.setTypeOfAccount("savings");
					 schemeApplicant.setIfsc("HSBC1234567");
					 schemeApplicant.setBranch("ALwar");
					 schemeApplicant.setApplicantDocumentsList(null);
					 
					
					//checking if the citizen satisfies basic eligiblity criteria of the scheme
				    schemeApplicant.setReason(schemeService.validate(schemeEligibilityVO, citizenDetails));					
					//on successful validation
				    System.out.println("Outside!");
					if(!schemeApplicant.getReason().equalsIgnoreCase("All criteria validated successfuly")) {
						System.out.println("WE are here in true! "+schemeApplicant.getReason());
						schemeApplicant.setApprovedStatus(true);	
						request.setAttribute("schemeVO",schemeVO);
						System.out.println(schemeVO.getDocumentList().size());
						requestDispatcher = request.getRequestDispatcher("applyScheme.jsp");
						requestDispatcher.forward(request, response);
						
					}
					//on failed validation
					else {
						schemeApplicant.setApprovedStatus(false);
						System.out.println("WE are here in false! "+schemeApplicant.getReason());
						schemeService.addRejectedSchemeApplicant(schemeApplicant);
						request.setAttribute("err",schemeApplicant.getReason());
						requestDispatcher = request.getRequestDispatcher("viewSchemesCitizenServlet");
						requestDispatcher.forward(request, response);
					}
					System.out.println("Bank: "+schemeVO.getBankList().size());
					
					
					
				}else {													//If employee is already logged in
					requestDispatcher = request.getRequestDispatcher("viewSchemesEmployeeServlet");
					requestDispatcher.forward(request, response);
				}
			}
			
			else {
				requestDispatcher = request.getRequestDispatcher("citizenLogin.jsp");
				request.setAttribute("err","Sorry, You are not Authorized to view this Page");
				
				requestDispatcher.forward(request, response);
			
			}
		} catch (DatabaseException | SchemeNotFoundException | NumberFormatException | CitizenNotFoundException | DataNotFoundException e) {
			e.printStackTrace();
			requestDispatcher = request.getRequestDispatcher("viewSchemesCitizenServlet");
			request.setAttribute("err", e.getMessage());
			requestDispatcher.forward(request, response);
		} 

	}



}
