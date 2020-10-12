package com.gsas.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
					
				
					//Creating a JSONParser object
				      JSONParser jsonParser = new JSONParser();
				      
	      
				         //Parsing the contents of the JSON file
				         JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("D:/generated.json"));
				         //Retrieving the array
				         JSONArray jsonArray = (JSONArray) jsonObject.get("players_data");
			
				         
				         for(Object object : jsonArray) {
				            JSONObject record = (JSONObject) object;
				            
							SchemeVO schemeVO = new SchemeVO();
							schemeVO.setSchemeName((String) record.get("schemeName"));
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
				            
				            
				            
				            int scheme_eligibility_id = Integer.parseInt((String) record.get("SEID"));//fetches scheme eligibility id from json file
				            int minage = Integer.parseInt((String) record.get("MINAGE"));//fetches minage  from json file
				            int maxage = Integer.parseInt((String) record.get("MAXAGE"));//fetches max age  id from json file
				            int incid = Integer.parseInt((String) record.get("INCID"));//fetches scheme income group id from json file
				            String gen = (String) record.get("GEN");//fetches  gender from json file
				            int pid = Integer.parseInt((String) record.get("PID"));//fetches profession from json file
				            int scid=Integer.parseInt((String) record.get("SCID"));//fetches scheme eligibility id from json file
				            String sch_name=(String) record.get("SCH_NAME");//fetches scheme name from json file
				            String sch_sum=(String) record.get("SCH_SUM");//fetches summary from json file
				            String sch_des=(String) record.get("SCH_DES");//fetches scheme description from json file
				            String img_path=(String) record.get("IMG_PATH");//fetch image path from json file
				            int mid = Integer.parseInt((String) record.get("MID"));//fetches ministry id from json file
				            int sect_id = Integer.parseInt((String) record.get("SECT_ID"));//fetches sector  id from json file
				            String d=(String) record.get("DOS");//fetches date of start from json file(1st step)
				            long dos = Date.valueOf(d).getTime();//fetches date of start from json file(two step process)
				            boolean status=  (Boolean)record.get("STATUS");//fetches status of scheme file(in json we store boolean as 1 or 0)
				            int sch_docid=Integer.parseInt((String) record.get("SCH_DOCID"));//fetches scheme document id from json file
				            int docu_id=Integer.parseInt((String) record.get("DOCU_ID"));//fetches scheme eligibility id from json file
				            int scheme_bank_id=Integer.parseInt((String) record.get("SCHEME_BANK_ID"));//fetches scheme bank id from json file
				            int bank_id=Integer.parseInt((String) record.get("BANK_ID"));//fetches scheme bankid from json file
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
