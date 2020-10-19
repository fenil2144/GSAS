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
.main1 {
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
	<script type="text/javascript" src="JS\navigation.js"></script>
	<div class="header">
		<h1>Citizen Registration</h1>
	</div>
	<div class="sidenav">
		<div class="image">
			<img src="logo\logo.jpeg" alt="LOGO">
		</div>

		<div class="heading">
			<h2>Menu</h2>
		</div>
		<div class="content" id="first">
			<a href="employeeLogin.jsp">Employee Login</a>
		</div>
		<div class="content">
			<a href="citizenLogin.jsp">Citizen Login</a>
		</div>
		<div class="content">
			<a href="index.jsp">List Schemes</a>
		</div>
		<div class="content">
			<a href="AddCitizenServlet">Citizen Register</a>
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
			
			<form method="POST" action="CitizenRegistrationServlet" onsubmit="validate(event)">
				<div class="col1" id="name">
					<label class="required-field"> Enter your Name</label><input type="text" name="firstName"
						id="firstName" placeholder="First Name" required><input
						type="text" name="middleName" id="middleName"
						placeholder="Middle Name"><input type="text"
						name="lastName" id="lastName" placeholder="Last Name" required>
					
				</div>
				<div class="col1" id="citizenUsername">
					<label class="required-field"> Enter your username</label><input type="text"
						name="username" id="username"
						placeholder="Enter your username." required><br>
				</div>
				<div class="col1">
					<label class="required-field"> Enter your password</label><input type="password"
						name="password" id="citizenPassword"
						placeholder="Enter your password." required><br>
			
				</div>
				<div class="col1">
					<label class="required-field"> Confirm your password</label><input type="password"
						name="password1" id="citizenPassword1"
						placeholder="Enter your password." required><br>
	
				</div>
				<div class="col1" id="dob">
					<label class="required-field"> Enter your Date of Birth</label><input type="text"
						name="dateOfBirth" id="dateOfBirth"
						placeholder="Enter your Date of Birth.(YYYY-MM-DD)" required><br>
				</div>
				<div class="col1">
					<label class="required-field">Select gender</label>

				</div>
				<div class="col1" id="col2">
					<!--<button type="submit" id="submit" value="submit" onclick="add(col2)">Add eligibility criteria.</button>-->

					<input type="radio" id="male" name="gender" value="male"> <label
						for="male"> Male</label><br> <input type="radio" id="female"
						name="gender" value="female"> <label for="female">
						Female</label><br> <input type="radio" id="other" name="gender"
						value="others"> <label for="other"> Other</label><br>


				</div>
				<div class="col1" id="phoneNumber">
					<label class="required-field"> Enter your Phone Number</label><input type="text"
						name="phone" id="phone" placeholder="Enter your Phone Number."
						required><br>
					
				</div>
				<div class="col1" id="emailId">
					<label class="required-field"> Enter your Email ID</label><input type="email" name="email"
						placeholder="Enter your Email ID." required><br>

				</div>
				<div class="col1" id="address">
					<label class="required-field"> Enter your Address</label><input type="text" name="street"
						id="street" placeholder="Enter your street" required> <input
						type="text" name="city" id="city" placeholder="Enter your city"
						required> <input type="text" name="state" id="state"
						placeholder="Enter your state" required> <input
						type="text" name="pincode" id="pincode"
						placeholder="Enter your pincode" required >

			
				</div>
				<div class="col1" >
					<label for="profession" class="required-field">Choose a profession:</label> <select
							name="profession" id="profession" required >
							<option value="" id="select">Select</option>
							<c:forEach items="${professionList}" var="profession">
								<option value="${profession.professionId}">${profession.professionName}</option>
							</c:forEach>
						</select>
				</div>
				<div class="col1">
						<label for="incomeGroup" class="required-field">Choose a income group:</label> <select
							name="incomeGroup" required >
							<option value="" id="select">Select</option>
							<c:forEach items="${incomeGroupList}" var="incomeGroup">
								<option value="${incomeGroup.incomeGroupId}">${incomeGroup.incomeGroupName}</option>
							</c:forEach>
						</select>
					</div>
				<div class="col1">
					<label class="required-field"> Enter your Aadhar Number</label><input type="number"
						name="adharNumber" id="aadhar"
						placeholder="Enter your Aadhar Number." required><br>
			
				</div>
				<div class="col1">
					<label class="required-field"> Enter your PAN Number</label><input type="text"
						name="pancardNumber" id="pan"
						placeholder="Enter your PAN Number." required 
						><br>
				</div>

				<div class="col1">
					<label>
						<button type="submit">Submit</button>
					</label>
				</div>
				<div class="error">
				<p id="val"><p>
				</div>

			</form>

		</div>
	</div>



	<div class="footer">
		<h2>Coordinated by: Team Agastya</h2>
	</div>


</body>
</html>