package com.gsas.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.gsas.exception.DatabaseException;
import com.gsas.exception.InvalidSequenceException;
import com.gsas.model.BankVO;
import com.gsas.model.DocumentVO;
import com.gsas.model.IncomeGroupVO;
import com.gsas.model.LoginVO;
import com.gsas.model.MinistryVO;
import com.gsas.model.ProfessionVO;
import com.gsas.model.SchemeEligibilityVO;
import com.gsas.model.SchemeVO;
import com.gsas.model.SectorVO;
import com.gsas.service.SchemeService;
import com.gsas.utility.FileName;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class InsertSchemeServlet
 */
@WebServlet("/InsertSchemeServlet")
@MultipartConfig
public class InsertSchemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InsertSchemeServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SchemeService schemeService = (SchemeService) ObjectFactory.getInstance(LayerType.SCHEME_SERVICE);
		RequestDispatcher rd = null;
		try {
			HttpSession session = request.getSession();
			LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
			if(loginVO != null) {
				if(loginVO.isEmployee() == true) {						//If employee is already logged in
					SchemeVO schemeVO = new SchemeVO();
					schemeVO.setSchemeName(request.getParameter("schemeName"));
					schemeVO.setSummary(request.getParameter("summary"));
					schemeVO.setDescription(request.getParameter("description"));
					String fileName = null;
					
		            Part part = request.getPart("imagePath");
		            InputStream is = part.getInputStream();

		            // get filename to use on the server
		            fileName = new File(FileName.extractFileName(part)).getName();

		            FileOutputStream os = new FileOutputStream ("F:\\images\\"+fileName);
		            
		            // write bytes taken from uploaded file to target file
		            int ch = is.read();
		            while (ch != -1) {
		                 os.write(ch);
		                 ch = is.read();
		            }
		            os.close();

		            schemeVO.setImagePath("F:\\images\\"+fileName);


					MinistryVO ministryVO = new MinistryVO(Long.parseLong(request.getParameter("ministry")));
					schemeVO.setMinistryVO(ministryVO);
					
					SectorVO sectorVO = new SectorVO(Long.parseLong(request.getParameter("sector")));
					schemeVO.setSectorVO(sectorVO);
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					formatter = formatter.withLocale( Locale.ENGLISH );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
					LocalDate date = LocalDate.parse(request.getParameter("startDate"), formatter);
					schemeVO.setStartDate(date);
					
					SchemeEligibilityVO schemeEligibilityVO = new SchemeEligibilityVO();
					schemeEligibilityVO.setMinAge(Integer.parseInt(request.getParameter("minAge")));
					schemeEligibilityVO.setMaxAge(Integer.parseInt(request.getParameter("maxAge")));
					schemeEligibilityVO.setGender(request.getParameter("gender"));
					schemeEligibilityVO.setIncomeGroupVO(new IncomeGroupVO(Long.parseLong(request.getParameter("incomeGroup"))));
					schemeEligibilityVO.setProfessionVO(new ProfessionVO(Long.parseLong(request.getParameter("profession"))));
					schemeVO.setSchemeEligibilityVO(schemeEligibilityVO);
					schemeVO.setStatus(true);
					
					String[] documentIdList = request.getParameterValues("document");
					List<DocumentVO> documentList = new ArrayList<DocumentVO>();
					for(String documentId : documentIdList) {
						documentList.add(new DocumentVO(Long.parseLong(documentId)));
					}
					schemeVO.setDocumentList(documentList);
					
					String[] bankIdList = request.getParameterValues("bank");
					List<BankVO> bankList = new ArrayList<BankVO>();
					for(String bankId : bankIdList) {
						bankList.add(new BankVO(Long.parseLong(bankId)));
					}
					schemeVO.setBankList(bankList);
					
					schemeService.addScheme(schemeVO);
					request.setAttribute("message","Scheme Added Successfully!");
					rd = request.getRequestDispatcher("AddSchemeServlet");
					rd.forward(request, response);
				}
				else {													//If user is already logged in
					rd = request.getRequestDispatcher("viewSchemesCitizenServlet");
					rd.forward(request, response);
				}
			}
			else {
				request.setAttribute("err","Please Login First");
				rd = request.getRequestDispatcher("employeeLogin.jsp");
				rd.forward(request, response);
			}
			
		} catch (DatabaseException | InvalidSequenceException  e) {
			rd = request.getRequestDispatcher("AddSchemeServlet");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			rd = request.getRequestDispatcher("AddSchemeServlet");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}

}