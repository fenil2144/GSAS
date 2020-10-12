<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css\common.css" rel="stylesheet">
        <link href="css\form.css" rel="stylesheet">
        <style>
            .main1{
                width: 600px;
                height: 100%;
                 border: 1px solid red;
                /*border-radius: 5px;
                background-color: brown;*/
                padding: 20px;
                text-align: center;
                align-self: center;
                opacity: 0.8;
            }
        </style>
            </head>
    <body>
        <div class="header">
            <h1>Edit Scheme</h1>
            
        </div>
        <div class="sidenav">
        <div class="image">
                <img src="logo\logo.jpeg">
            </div>
            
            <div class="heading">
                <h2> Menu </h2>
            </div>
            <div class="content" id="first">
                <a href="LogoutServlet">Logout</a>
            </div>
            <div class="content">
             <a href="viewSchemesEmployeeServlet">Add more Schemes</a>
            </div>
            
     </div>
     
        <div class="main">
        <div class="message">
        <c:if test="${message != null}">
            <h3>
                <c:out value="${message}" />
            </h3>
        </c:if>
        </div>
        <div class="error">
        <c:if test="${err != null}">
            <h3>
                <c:out value="${err}" />
            </h3>
        </c:if>
        </div>
		<div class="main1">
			<form method="POST" action="UpdateSchemeServlet" enctype="multipart/form-data">
			<input type="hidden" name="schemeId" id="schemeId" value="${schemeVO.schemeId}">
			<input type="hidden" name="schemeEligibilityId" id="schemeEligibilityId" value="${schemeVO.schemeEligibilityVO.schemeEligibilityId}">
				<div class="col1">
					<label class="required-field">Enter scheme name</label> <input type="text"
						name="schemeName" id="schemeName" value="${schemeVO.schemeName}" placeholder="Enter scheme name" required>
						<p>${schemeVO.schemeName}</p>
				</div>
				<div class="col1">
					<label class="required-field"> Summary of the scheme </label>
						<textarea rows="4" cols="30" +id="summary" name="summary" required>${schemeVO.summary}</textarea>

				</div>
				<div class="col1">
					<label class="required-field">Description of the scheme </label><textarea rows="4"
							cols="50" id="description" name="description"  required>${schemeVO.description}</textarea>
				</div>
				<div class="col1">
					<label class="required-field">Upload Image </label><input type="file" id="imagePath" name="imagePath" value="${schemeVO.imagePath}" required>

				</div>
				<div class="col1">
					<label for="ministry" class="required-field">Choose a ministry:</label> <select
						name="ministry" required>
						<option value="select">Select</option>
						<c:forEach items="${ministryList}" var="ministry">
							<option value="${ministry.ministryId}" <c:if test="${ministry.ministryId eq schemeVO.ministryVO.ministryId}">selected="selected"</c:if>>${ministry.ministryName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col1">
					<label for="sector" class="required-field">Choose a sector:</label> 
					<select name="sector" required>
						<option value="select">Select</option>
						<c:forEach items="${sectorList}" var="sector">
							<option value="${sector.sectorId}" <c:if test="${sector.sectorId eq schemeVO.sectorVO.sectorId}">selected="selected"</c:if>>${sector.sectorName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col1">
					<label for="startDate" class="required-field">Enter start date</label> <input type="text"
						name="startDate" id="startDate" value="${schemeVO.startDate}" placeholder="Enter date in format(YYYY-MM-DD)" required><br>

				</div>
				<div class="col1">Select eligibility criteria</div>
				<div class="section">
					<div class="eligibilityCol">
						<label>Enter minimum age</label> <input type="number"
							name="minAge" id="minAge" placeholder="Enter min age" value="${schemeVO.schemeEligibilityVO.minAge }"><br>
					</div>
					<div class="eligibilityCol">
						<label>Enter maximum age</label> <input type="number"
							name="maxAge" id="maxAge" placeholder="Enter max age" value="${schemeVO.schemeEligibilityVO.maxAge }"><br>
					</div>

					<div class="col1">
						<label for="profession">Choose a profession:</label> <select
							name="profession">
							<option value="select">Select</option>
							<c:forEach items="${professionList}" var="profession">
								<option value="${profession.professionId}" <c:if test="${profession.professionId eq schemeVO.schemeEligibilityVO.professionVO.professionId}">selected="selected"</c:if>>${profession.professionName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="gender">
						<label>Select gender</label>

					</div>
					<div class="genderCol">

						<input type="radio" id="male" name="gender" <c:if test="${schemeVO.schemeEligibilityVO.gender eq 'male'}">checked="checked"</c:if> >
						<label for="male"> Male</label><br> 
							<input type="radio"	id="female" name="gender" <c:if test="${schemeVO.schemeEligibilityVO.gender eq 'female'}">checked="checked"</c:if> > <label
							for="female"> Female</label><br> 
							<input type="radio"	id="other" name="gender"  <c:if test="${schemeVO.schemeEligibilityVO.gender eq 'both'}">checked="checked"</c:if> > 
							<label for="both"> Both</label><br>

					</div>
					<div class="col1">
						<label for="incomeGroup">Choose a income group:</label> <select
							name="incomeGroup">
							<option value="select">Select</option>
							<c:forEach items="${incomeGroupList}" var="incomeGroup">
								<option value="${incomeGroup.incomeGroupId}" <c:if test="${incomeGroup.incomeGroupId eq schemeVO.schemeEligibilityVO.incomeGroupVO.incomeGroupId}">selected="selected"</c:if>>${incomeGroup.incomeGroupName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col1">
					<label class="required-field">Choose Documents</label>

				</div>
				<div class="documentsCol" id="documentsCol">
					<c:forEach items="${documentList}" var="document">
						<input type="checkbox" id="document" name="document" value="${document.documentId}">
						<label for="document">${document.documentName}</label><br> 
						
					</c:forEach>

<!-- 					<input type="checkbox" id="panCard" name="panCard" value="panCard">
					<label for="panCard"> Pan Card</label><br> <input
						type="checkbox" id="passport" name="passport" value="passport">
					<label for="passport"> Passport</label><br> <input
						type="checkbox" id="aadharCard" name="aadharCard"
						value="aadharCard"> <label for="aadharCard">
						Aadhar Card</label><br> -->


				</div>
				<div class="col1">
					<label class="required-field">Choose Bank</label>

				</div>
				<div class="bankCol" id="bankCol">
					<c:forEach items="${bankList}" var="bank">
						<input type="checkbox" id="bank" name="bank" value="${bank.bankId}">
						<label for="bank">${bank.bankName}</label><br> 
					</c:forEach>

<!-- 					<input type="checkbox" id="HSBC" name="HSBC" value="HSBC">
					<label for="HSBC"> HSBC</label><br> <input type="checkbox"
						id="ICICI" name="ICICI" value="ICICI"> <label for="pvt">
						ICICI</label><br> <input type="checkbox" id="HDFC" name="HDFC"
						value="HDFC"> <label for="HDFC"> HDFC</label><br> -->


				</div>
				<div class="col1">
					
					<button type="submit" id="submit">Submit</button>
					
				</div>
				

			</form>

		</div>
	</div>
 

        <div class="footer">
            <p>Coordinated by: Team Agastya</p>
        </div>
    
    </body>
</html>