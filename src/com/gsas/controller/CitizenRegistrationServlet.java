package com.gsas.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsas.exception.DatabaseException;
import com.gsas.exception.DuplicateUserException;
import com.gsas.exception.InvalidSequenceException;
import com.gsas.model.AddressVO;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.IncomeGroupVO;
import com.gsas.model.LoginVO;
import com.gsas.model.ProfessionVO;
import com.gsas.service.CitizenService;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/CitizenRegistrationServlet")
public class CitizenRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public CitizenRegistrationServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CitizenService citizenService = (CitizenService) ObjectFactory.getInstance(LayerType.CITIZEN_SERVICE);
		RequestDispatcher requestDispatcher = null;
		try {
			LoginVO loginVO = new LoginVO();
			loginVO.setUserName(request.getParameter("username"));
			loginVO.setPassword(request.getParameter("password"));
			
			AddressVO addressVO = new AddressVO();
			addressVO.setStreet(request.getParameter("street"));
			addressVO.setCity(request.getParameter("city"));
			addressVO.setState(request.getParameter("state"));
			addressVO.setPincode(Integer.parseInt(request.getParameter("pincode")));
			
			CitizenDetailsVO citizenDetailsVO = new CitizenDetailsVO();
			citizenDetailsVO.setFirstName(request.getParameter("firstName"));
			citizenDetailsVO.setMiddleName(request.getParameter("middleName"));
			citizenDetailsVO.setLastName(request.getParameter("lastName"));
		
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			formatter = formatter.withLocale( Locale.ENGLISH );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
			LocalDate date = LocalDate.parse(request.getParameter("dateOfBirth"), formatter);
			citizenDetailsVO.setDateOfBirth(date);
			
			citizenDetailsVO.setGender(request.getParameter("gender"));
			citizenDetailsVO.setEmail(request.getParameter("email"));
			citizenDetailsVO.setPhone(Long.parseLong(request.getParameter("phone")));
			citizenDetailsVO.setAddressVO(addressVO);
			citizenDetailsVO.setIncomeGroup(new IncomeGroupVO(Long.parseLong(request.getParameter("incomeGroup"))));
			citizenDetailsVO.setProfession(new ProfessionVO(Long.parseLong(request.getParameter("profession"))));
			citizenDetailsVO.setAdharNumber(Long.parseLong( request.getParameter("adharNumber") ));
			citizenDetailsVO.setPancardNumber(request.getParameter("pancardNumber"));
			citizenDetailsVO.setLoginVO(loginVO);
			
			if(citizenService.isCitizenUnique(citizenDetailsVO) == true) {
			citizenService.registerCitizen(citizenDetailsVO);
			request.setAttribute("message", "Citizen Registered Successfully with User Name: "+loginVO.getUserName());
			requestDispatcher = request.getRequestDispatcher("citizenLogin.jsp");
			}
			/*else {
				request.setAttribute("err", "Citizen cannot be registered with User Name: "+loginVO.getUserName());
			}*/
				
		} catch (DatabaseException | InvalidSequenceException | DuplicateUserException e) {
			request.setAttribute("err", e.getMessage());
			requestDispatcher = request.getRequestDispatcher("index.jsp");
			e.printStackTrace();
		}
		

		requestDispatcher.forward(request, response);
	}

}
