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

import com.gsas.model.AddressVO;
import com.gsas.model.CitizenDetailsVO;
import com.gsas.model.LoginVO;
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
		citizenDetailsVO.setIncomeGroup(request.getParameter("incomeGroup"));
		citizenDetailsVO.setProfession(request.getParameter("profession"));
		citizenDetailsVO.setAdharNumber(Long.parseLong( request.getParameter("adharNumber") ));
		citizenDetailsVO.setPancardNumber(request.getParameter("pancardNumber"));
		citizenDetailsVO.setCitizenVO(loginVO);
		
		citizenService.registerCitizen(citizenDetailsVO);
		
		request.setAttribute("message", "Citizen Registered Successfully with User Name: "+loginVO.getUserName());
		
		
		requestDispatcher = request.getRequestDispatcher("citizenLogin.jsp");
		requestDispatcher.forward(request, response);
	}

}
