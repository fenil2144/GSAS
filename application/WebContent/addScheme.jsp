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
<script type="text/javascript">
fun(app){
	alert(app);
}
</script>
<style>
.main1 {
	width: 600px;
	height: auto;
	border: 1px solid red;
	padding: 20px;
	text-align: center;
	align-self: center;
}


</style>
</head>
<body>
	<script src="JS\navigation.js"></script>
	<div class="header">
		<h1>Add Scheme</h1>

	</div>
	<div class="sidenav">
		<div class="image">
			<img src="logo\logo.jpeg" alt="LOGO">
		</div>

		<div class="heading">
			<h2>Menu</h2>
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
			<form method="post" action="InsertSchemeServlet" enctype="multipart/form-data">
				<div class="col1">
					<label class="required-field">Enter scheme name</label> 
						<input type="text" name="schemeName" id="schemeName" placeholder="Enter scheme name" required>
				</div>
				<div class="col1">
					<label class="required-field"> Summary of the scheme </label>
					<textarea rows="4" cols="30" id="summary" name="summary" required>Enter your summary..</textarea>
					
				</div>
				<div class="col1">
					<label class="required-field">Description of the scheme </label>
						<textarea rows="4" cols="50" id="description" name="description" required>Enter your description..</textarea>
				</div>
				<div class="col1">
					<label class="required-field">Upload Image </label>
						<input type="file" id="imagePath" name="imagePath" required>

				</div>
				<div class="col1">
					<label for="ministry" class="required-field">Choose a ministry:</label> 
					<select name="ministry" id="ministry" required>
						<option value="" >Select</option>
						<c:forEach items="${ministryList}" var="ministryObj">
							<option value="${ministryObj.ministryId}">${ministryObj.ministryName}</option>		<!-- ministry.ministryName -->

						</c:forEach>
					</select>
				</div>
				<div class="col1">
					<label for="sector" class="required-field">Choose a sector:</label> 
					<select name="sector" id="sector" required >
						<option value="" id="select">Select</option>
						<c:forEach items="${sectorList}" var="sectorObj">
							<option value="${sectorObj.sectorId}">${sectorObj.sectorName}</option>	<!-- sector.sectorName -->
						</c:forEach>
					</select>
				</div>
				<div class="col1">
					<label for="startDate" class="required-field">Enter start date</label> 
					<input type="text" name="startDate" id="startDate" placeholder="Enter date in format(YYYY-MM-DD)" required><br>

				</div>
				<div class="col1">Select eligibility criteria</div>
				<div class="section">
					<div class="eligibilityCol">
						<label class="required-field">Enter minimum age</label> 
						<input type="number" name="minAge" id="minAge" placeholder="Enter min age" required min="0" max="130"><br>
					</div>
					<div class="eligibilityCol">
						<label class="required-field">Enter maximum age</label> 
							<input type="number" name="maxAge" id="maxAge" placeholder="Enter max age" required max="130" min="0"><br>
					</div>

					<div class="col1">
						<label for="profession" class="required-field">Choose a profession:</label> 
						<select	name="profession" required>
							<option value="" id="select">Select</option>
							<c:forEach items="${professionList}" var="profession">
								<option value="${profession.professionId}">${profession.professionName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="gender">
						<label class="required-field">Select gender</label>

					</div>
					<div class="genderCol">

						<input type="radio" id="male" name="gender" value="male" required>
						<label for="male"> Male</label><br> 
							<input type="radio"	id="female" name="gender" value="female"> 
						<label for="female"> Female</label><br> 
							<input type="radio"	id="other" name="gender" value="others"> 
						<label for="other"> Other</label><br>


					</div>
					<div class="col1">
						<label for="incomeGroup" class="required-field">Choose a income group:</label> 
						<select	name="incomeGroup" required>
							<option value="select" id="select">Select</option>
							<c:forEach items="${incomeGroupList}" var="incomeGroup">
								<option value="${incomeGroup.incomeGroupId}">${incomeGroup.incomeGroupName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col1">
					<label class="required-field">Choose Documents</label>

				</div>
				<div class="documentsCol" id="documentsCol">
					<c:forEach items="${documentList}" var="document">
						<input type="checkbox" id="document" name="document" value="${document.documentId}" >
						<label for="document">${document.documentName}</label><br> 
						
					</c:forEach>
<!-- 						<input type="checkbox" id="panCard" name="document" value="panCard">
					<label for="panCard"> Pan Card</label><br> 
						<input type="checkbox" id="passport" name="document" value="passport">
					<label for="passport"> Passport</label><br> 
						<input type="checkbox" id="aadharCard" name="document" value="aadharCard">
					<label for="aadharCard"> Aadhar Card</label><br> -->


				</div>
				<div class="col1">
					<label class="required-field">Choose Bank</label>

				</div>
				<div class="bankCol" id="bankCol">
					<c:forEach items="${bankList}" var="bank">
						<input type="checkbox" id="bank" name="bank" value="${bank.bankId}" >
						<label for="bank">${bank.bankName}</label><br> 
						
					</c:forEach>
<!-- 					<input type="checkbox" id="HSBC" name="bank" value="HSBC">
					<label for="HSBC"> HSBC</label><br> 
					<input type="checkbox" id="ICICI" name="bank" value="ICICI"> 
					<label for="pvt"> ICICI</label><br> 
						<input type="checkbox" id="HDFC" name="bank" value="HDFC"> 
					<label for="HDFC"> HDFC</label><br>
 -->

				</div>
				<div class="col1">
					<button type="submit" id="submit">Submit</button>
				</div>
				

			</form>

		</div>
	</div>



	<div class="footer">
		<h2>Coordinated by: Team Agastya</h2>
	</div>


</body>
</html>