package com.gsas.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
 * Servlet implementation class InsertSchemeJsonServlet
 */
@WebServlet("/InsertSchemeJsonServlet")
public class InsertSchemeJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertSchemeJsonServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SchemeService schemeService = (SchemeService) ObjectFactory.getInstance(LayerType.SCHEME_SERVICE);
		RequestDispatcher rd = null;
		try {
			HttpSession session = request.getSession();
			LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
			if(loginVO != null) {
				if(loginVO.isEmployee() == true) {
					
					String fileName = null;
					
		            Part part = request.getPart("imagePath");
		            InputStream is = part.getInputStream();

		            // get filename to use on the server
		            fileName = new File(FileName.extractFileName(part)).getName();
		            FileOutputStream os = new FileOutputStream ("F:\\sts-Workspace\\GovernmentSchemesApplicationSystem\\WebContent\\json\\"+fileName);
		            
		            // write bytes taken from uploaded file to target file
		            int ch = is.read();
		            while (ch != -1) {
		                 os.write(ch);
		                 ch = is.read();
		            }
		            os.close();				
					//Creating a JSONParser object
				      JSONParser jsonParser = new JSONParser();
				      
	      
				         //Parsing the contents of the JSON file
				         JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("F:\\sts-Workspace\\GovernmentSchemesApplicationSystem\\WebContent\\json\\"+fileName));
				         //Retrieving the array
				         JSONArray jsonArray = (JSONArray) jsonObject.get("scheme_data");
			
				         
				         for(Object object : jsonArray) {
				            JSONObject record = (JSONObject) object;
				            
							SchemeVO schemeVO = new SchemeVO();
							schemeVO.setSchemeName((String) record.get("schemeName"));
							schemeVO.setSummary((String) record.get("summary"));
							schemeVO.setDescription((String) record.get("description"));
							
							schemeVO.setImagePath((String) record.get("F:\\sts-Workspace\\GovernmentSchemesApplicationSystem\\WebContent\\json\\"+fileName));
							
							MinistryVO ministryVO = new MinistryVO(Long.parseLong((String) record.get("ministry")));
							schemeVO.setMinistryVO(ministryVO);
							
							SectorVO sectorVO = new SectorVO(Long.parseLong((String) record.get("sector")));
							schemeVO.setSectorVO(sectorVO);
							
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							formatter = formatter.withLocale( Locale.ENGLISH );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
							LocalDate date = LocalDate.parse((String) record.get("startDate"), formatter);
							schemeVO.setStartDate(date);
							
							SchemeEligibilityVO schemeEligibilityVO = new SchemeEligibilityVO();
							schemeEligibilityVO.setMinAge(Integer.parseInt((String) record.get("minAge")));
							schemeEligibilityVO.setMaxAge(Integer.parseInt((String) record.get("maxAge")));
							schemeEligibilityVO.setGender(request.getParameter((String) record.get("gender")));
							schemeEligibilityVO.setIncomeGroupVO(new IncomeGroupVO(Long.parseLong((String) record.get("incomeGroup"))));
							schemeEligibilityVO.setProfessionVO(new ProfessionVO(Long.parseLong((String) record.get("profession"))));
							schemeVO.setSchemeEligibilityVO(schemeEligibilityVO);
							schemeVO.setStatus(Boolean.parseBoolean((String) record.get("status")));

							String[] documentIdList = (String[]) record.get("document");
							List<DocumentVO> documentList = new ArrayList<DocumentVO>();
							for(String documentId : documentIdList) {
								documentList.add(new DocumentVO(Long.parseLong(documentId)));
							}
							schemeVO.setDocumentList(documentList);
							
							String[] bankIdList = (String[]) record.get("bank");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
