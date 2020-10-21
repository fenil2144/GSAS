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
	height: auto;
	border: 1px solid red;
	padding: 20px;
	text-align: center;
	align-self: center;
}
</style>
</head>
<body>
	<script src="JS\navigation.js">
		
	</script>
	<div class="header">
		<h1>Scheme Management Page</h1>
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
			<div class="row">
				<!--  <form method="post" action="EditSchemeServlet">-->
				<c:forEach items="${schemeList}" var="scheme">
					<input type="hidden" name="schemeId" value="${scheme.schemeId}"
						id="schemeId">
					<div class="column">
						<div class="card">

							<h2>${scheme.schemeName }</h2>

							<img src="${scheme.imagePath }" class="cardImage" alt="Scheme Image">

							<p>${scheme.summary }</p>
							<p>
								<%-- <c:url value="GSAS/EditSchemeServlet" var="editURL"> --%>
								<c:url value="GovernmentSchemesApplicationSystem/EditSchemeServlet" var="editURL">
									<c:param name="schemeId" value="${scheme.schemeId}" />
									
								</c:url>
								
								<a href="/<c:out value="${editURL}"/>">
									<button type="submit" id="${scheme.schemeId}">Edit
										Scheme</button>
								</a>
								
							</p>



						</div>
					</div>
				</c:forEach>
				<!--  </form>-->

			</div>
		</div>


		<div class="add">

			<form method="post" action="AddSchemeServlet">
				<button type="submit" name="add" id="add">Add Scheme</button>
			</form>
		</div>

		<form method="post" action="InsertSchemeJsonServlet" enctype="multipart/form-data">
			<div class="add">
				Upload scheme in JSON format <input type="file" id="uploadSchemeJson" name="uploadSchemeJson">
			<button type="submit" name="Upload" id="Upload"> Upload Scheme </button>
			</div>
		</form>

	</div>



	<div class="footer">
		<h2>Coordinated by: Team Agastya</h2>
	</div>

</body>
</html>