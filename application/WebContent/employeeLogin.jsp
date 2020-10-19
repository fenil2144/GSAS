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
</head>
<body>
	<script src="JS\navigation.js">
		
	</script>
	<div class="header">
		<h1>Employee Login</h1>
	</div>
	<div class="sidenav">
		<div class="image">
			<img src="logo\logo.jpeg" alt="LOGO">
		</div>

		<div class="heading">
			<h2>Menu</h2>
		</div>
		<div class="content">
			<a href="index.jsp">Home Page</a>
		</div>
		<div class="content">
			<a href="citizenLogin.jsp">Citizen Login</a>
		</div>
		<div class="content">
			<a href=AddCitizenServlet>Citizen Register</a>
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
			<form method="post" action="EmployeeLoginServlet">
				<div class="col1">
					<label class="required-field">Enter your username</label> <input
						type="text" name="username" id="username"
						placeholder="Enter your username" required><br>
				</div>
				<div class="col2">
					<label class="required-field">Enter your password</label> <input
						type="password" name="password" id="password"
						placeholder="Enter your password." required><br>

				</div>
				<div class="col3">
					<button type="submit" id="submit" class="submit">Submit</button>
				</div>

			</form>

		</div>
	</div>



	<div class="footer">
		<h2>Coordinated by: Team Agastya</h2>
	</div>

</body>
</html>