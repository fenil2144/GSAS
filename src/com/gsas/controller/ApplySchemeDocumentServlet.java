package com.gsas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsas.exception.AuthenticationException;
import com.gsas.exception.DatabaseException;
import com.gsas.model.BankVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.LoginVO;
import com.gsas.model.SchemeApplicantDocumentsVO;
import com.gsas.model.SchemeApplicantVO;
import com.gsas.model.SchemeVO;
import com.gsas.service.SchemeService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class ApplySchemeDocumentServlet
 */
@WebServlet("/ApplySchemeDocumentServlet")
public class ApplySchemeDocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ApplySchemeDocumentServlet() {
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
			
			HttpSession session = request.getSession();
			LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
			if(loginVO != null) {
				SchemeApplicantDocumentsVO schemeApplicantDocuments =  new SchemeApplicantDocumentsVO();
				SchemeApplicantVO schemeApplicant = (SchemeApplicantVO)request.getParameter("schemeApplicant");
				List<SchemeApplicantDocumentsVO> docList = new ArrayList<>();
				SchemeVO schemeVO = schemeService.getSchemeDetails(Long.parseLong(request.getParameter("schemeId"))); // get scheme from schemeID
				
				request.setParameter("schemeVO",schemeVO);
				request.setParameter("scheme_banks",schemeVO.getBankList());
				request.getParameter("scheme_documents",schemeVO.getDocumentList());
				
				//schemeApplicant Object
				schemeApplicant.setSchemeVO(schemeVO);
				schemeApplicant.setLoginVO(loginVO);
				schemeApplicant.setBankVO(new BankVO(request.getParameter("bank").trim()));
				schemeApplicant.setAccountNumber(request.getParameter("account_number"));
				schemeApplicant.setTypeOfAccount(request.getParameter("type_of_account"));
				schemeApplicant.setIfsc(request.getParameter("ifsc"));
				schemeApplicant.setBranch(request.getParameter("branch"));
				schemeApplicant.setApplicantDocumentsList((List<SchemeApplicantDocumentsVO>)request.getParameter("List"));
				
				//validating documents and bank
				for(SchemeApplicantDocumentsVO items : schemeApplicant.getApplicantDocumentsList()) {
				schemeApplicantDocuments.setDocumentVO(new DocumentVO(Long.parseLong(request.getParameter("docId"))));
				schemeApplicantDocuments.setDocumentPath(request.getParameter("docPath"));
				docList.add(schemeApplicantDocuments);
				}
				schemeApplicant = schemeService.validate(schemeVO, schemeApplicant.getBankVO(), docList, schemeApplicant);
				if(schemeApplicant.isApprovedStatus()){  
					// fill scheme_applicant table with status=true and store document
					schemeService.addSchemeApplicant(schemeApplicant, docList); 
					requestDispatcher = request.getRequestDispatcher("applySchemes.jsp");					
				} 
				else {
					schemeService.addSchemeApplicant(schemeApplicant);
					requestDispatcher = request.getRequestDispatcher("displaySchemes.jsp");
				}
				request.setAttribute("err",schemeApplicant.getReason());
				requestDispatcher.forward(request, response);
				
			}
			else {
				request.setAttribute("err","Please Login First");
				requestDispatcher = request.getRequestDispatcher("citizenLogin.jsp");
				requestDispatcher.forward(request, response);
			}
			
		} catch (DatabaseException e) {
			requestDispatcher = request.getRequestDispatcher("ApplyScheme.jsp");
			request.setAttribute("err", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
