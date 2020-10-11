package com.gsas.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gsas.exception.DatabaseException;
import com.gsas.exception.InvalidSequenceException;
import com.gsas.model.IncomeGroupVO;
import com.gsas.model.LoginVO;
import com.gsas.model.MinistryVO;
import com.gsas.model.ProfessionVO;
import com.gsas.model.SchemeEligibilityVO;
import com.gsas.model.SchemeVO;
import com.gsas.model.SectorVO;
import com.gsas.service.SchemeService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class InsertSchemeServlet
 */
@WebServlet("/InsertSchemeServlet")
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
					
					ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
					
						List<FileItem> images=upload.parseRequest(request);//To store of list  files FileItem datatype is used 
						
							String name=images.get(0).getName();//gets the name of file  
							try{name=name.substring(name.lastIndexOf("\\")+1);}catch(Exception e){}//this will give the name of file it removes stuffs like c:\downloads and gives the name

							//System.out.println(name);
							images.get(0).write(new File("F:\\sts-Workspace\\GovernmentSchemesApplicationSystem\\WebContent\\images"+name));//create folder imagescheme where image of the  will be stored
						    //images folder created in local computer and write function writes into that folder

					schemeVO.setImagePath("F:\\sts-Workspace\\GovernmentSchemesApplicationSystem\\WebContent\\images"+ name);
					
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
			
		} catch (DatabaseException | InvalidSequenceException e) {
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
