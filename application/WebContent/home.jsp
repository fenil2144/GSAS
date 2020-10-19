<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css\common.css" rel="stylesheet">
<link href="css\form.css" rel="stylesheet">
</head>
<style>
.main1 {
	width: 600px;
	height: auto;
	border: 1px solid red;
	padding: 20px;
	text-align: center;
	align-self: center;
	margin-left:5%;
	margin-right:30%;
}
</style>
<body>
	<div class="header">
		<h1>GSAS</h1>
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
	<div class="rightcolumn">
		<div class="innertext">
			<h2>About GSAS</h2>
			<p>GSAS is a government scheme application software. Where you
				can visit all the schemes and apply for them. It helps the citizen
				to visit all the schemes and see the eligibility criteria and apply
				for them.
				</p>
		</div>
	</div>
		<div class="main1">
			<div class="row">

				<c:forEach items="${schemeList}" var="scheme">
					<input type="hidden" name="schemeId" value="${scheme.schemeId }"
						id="schemeId">

					<div class="column">
						<div class="card">

							<h2>${scheme.schemeName }</h2>
							<img src="${scheme.imagePath }" class=".cardImage" alt="Scheme Image">
							<p>${scheme.summary }</p>

						</div>
					</div>
				</c:forEach>


			</div>
		</div>
	</div>
	

	<div class="footer">
		<h2>Coordinated by: Team Agastya</h2>
	</div>


</body>
</html>