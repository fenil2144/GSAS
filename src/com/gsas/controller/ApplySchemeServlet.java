package com.gsas.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gsas.exception.DatabaseException;
import com.gsas.model.BankVO;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.LoginVO;
import com.gsas.model.SchemeApplicantDocumentsVO;
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

    /**
     * Default constructor. 
     */
    public ApplySchemeServlet() {
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
				RequestDispatcher requestDispatcher = null;
				try {
					SchemeService schemeService = (SchemeService) ObjectFactory.getInstance(LayerType.SCHEME_SERVICE);
					CitizenService citizenService = (CitizenService) ObjectFactory.getInstance(LayerType.CITIZEN_SERVICE);
					HttpSession session = request.getSession();
					
					LoginVO loginVO = (LoginVO) session.getAttribute("citizenVO");
					if(loginVO != null) {
					

						SchemeApplicantVO schemeApplicant = new SchemeApplicantVO();
						SchemeEligibilityVO citizenEligibilityDetails = new SchemeEligibilityVO();
						SchemeVO schemeVO = schemeService.getSchemeDetails(Long.parseLong(request.getParameter("schemeId"))); // get scheme from schemeID
						SchemeEligibilityVO schemeEligibilityVO = schemeVO.getSchemeEligibilityVO(); //get Eligibility of that scheme
						CitizenDetailsVO citizenDetails = citizenService.getCitizenDetails(loginVO.getLoginId()); //get citizenDetails from citizenId						
	
						//schemeApplicant Object
						schemeApplicant.setSchemeVO(schemeVO);
						schemeApplicant.setLoginVO(loginVO);
						schemeApplicant.setBankVO(null);
						schemeApplicant.setAccountNumber(0);
						schemeApplicant.setTypeOfAccount(null);
						schemeApplicant.setIfsc(null);
						schemeApplicant.setBranch(null);
						schemeApplicant.setApplicantDocumentsList(null);
						
						//checking if the citizen satisfies basic eligiblity criteria of the scheme
					    schemeApplicant.setReason(schemeService.validate(schemeEligibilityVO, citizenDetails));					
						//on successful validation
						if(schemeApplicant.getReason().equals("All criteria validated successfuly")) {
							schemeApplicant.setApprovedStatus(true);							
							requestDispatcher = request.getRequestDispatcher("applySchemes.jsp");
						}
						//on failed validation
						else {
							schemeApplicant.setApprovedStatus(false);
							schemeService.addSchemeApplicant(schemeApplicant);
							requestDispatcher = request.getRequestDispatcher("displaySchemes.jsp");
						}
						request.setAttribute("err",schemeApplicant.getReason());
						requestDispatcher.forward(request, response);			
					}
					
					else {
						requestDispatcher = request.getRequestDispatcher("citizenLogin.jsp");
						request.setAttribute("err","Sorry, You are not Authorized to view this Page");
						requestDispatcher.forward(request, response);
					
					}
				} catch (DatabaseException e) {
					requestDispatcher = request.getRequestDispatcher("ApplyScheme.jsp");
					request.setAttribute("err", e.getMessage());
					requestDispatcher.forward(request, response);
				}
				

	}

}
