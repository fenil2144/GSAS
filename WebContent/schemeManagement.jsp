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
		<h1>Scheme Management Page</h1>
	</div>
	<div class="sidenav">
		<div class="image">
			<img src="logo\logo.jpeg">
		</div>

		<div class="heading">
			<h2>Menu</h2>
		</div>
		<div class="content" id="first">
			<a href="index.jsp">Logout</a>
		</div>

	</div>

	<div class="main">
		<div class="main1">
			<div class="row">
				<form method="post" action="EditSchemeServlet">
					<c:forEach items="${schemeList}" var="scheme">
						<input type="hidden" name="schemeId" value="${scheme.schemeId }" id="schemeId">
						<p>${scheme.schemeId }</p>
						<div class="column">
							<div class="card">

								<h2>${scheme.schemeName }</h2>
								<img src="${scheme.imagePath }" class=".cardImage">
								<p>${scheme.summary }</p>
								<p>
									<button type="submit" id="${scheme.schemeId }">Edit
										Scheme</button>
								</p>


							</div>
						</div>
					</c:forEach>
				</form>

			</div>
		</div>

		<!-- <form method="post" action="EditSchemeServlet">
            
            	<c:forEach items="${schemeList}" var="scheme">
            	<input type="hidden" name="schemeId" value="${scheme.schemeId }">
					<div class="column">
						<div class="card">
							
							<h2>${scheme.schemeName } </h2>
							<img src="${scheme.imagePath }" class=".cardImage">
							<p>${scheme.summary }</p>
							<p>
								<button type="submit" id="${scheme.schemeId }">Edit Scheme</button>
							</p>

						</div>
						</div>
							</c:forEach>
                </form>
                
            </div>-->
		<div class="add">

			<form method="post" action="AddSchemeServlet">
				<button type="submit" name="add" id="add">Add Scheme</button>
			</form>
		</div>


		<div class="add">
			Upload scheme in JSON/XML format <input type="file" id="files"
				name="files" multiple>


		</div>
	</div>



	<div class="footer">
		<h2>Footer</h2>
	</div>

</body>
</html>