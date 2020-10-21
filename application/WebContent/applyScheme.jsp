<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
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

.col2 {
	width: 400px;
	height: auto;
	/*border: 1px solid red;*/
	margin: auto;
	/*border-radius: 5px;
                background-color: brown;*/
	padding: 20px;
	text-align: center;
	align-self: center;
	opacity: 0.8;
	margin-left: 10%;
}
</style>
</head>
<body>
	<script src="JS\navigation.js"></script>
	<div class="header">
		<h1>Apply Scheme</h1>

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

			<div class="col2">
				<div class="card">

					<h2>${schemeVO.schemeName}</h2>
					<img src="${schemeVO.imagePath}" class="cardImage" alt="Scheme Image">
					<p>${schemeVO.summary}</p>
					<p>${schemeVO.description}</p>
					<p>${schemeVO.startDate}</p>
				</div>



			</div>

			<form method="POST" action="ApplySchemeDocumentServlet"
				enctype="multipart/form-data">		
				<div class="col1">
					<input type="hidden" name="schemeId" value="${schemeVO.schemeId}">
					<label for="bank">Choose a bank:</label> <select name="bank">
						<option value="">Select</option>
						<c:forEach items="${schemeVO.bankList}" var="bank">
							${bank.bankId}
							${bank.bankName}
								<option value="${bank.bankId}">${bank.bankName}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col1">
					<input type="number" name="accountNumber" id="accountNumber"
						placeholder="Enter Account Number" required pattern="^d{10}$"><br>
				</div>



				<div class="col1">
					<input type="text" name="typeOfAccount" id="typeOfAccount"
						placeholder="Enter Type of Account" required><br>
				</div>

				<div class="col1">
					<input type="text" name="branch" id="branch"
						placeholder="Enter Branch" required><br>
				</div>

				<div class="col1">
					<input type="text" name="ifsc" id="ifsc"
						placeholder="Enter IFSC Code" required pattern="^[A-Z]{4}0[A-Z0-9]{6}$"><br>
				</div>
				<div class="col1">
					<label for="documents">Upload documents:</label><br>
					<c:forEach items="${schemeVO.documentList}" var="documents">
						<label for="${documents.documentId}">${documents.documentName}</label>
						<input type="file" name="${documents.documentId}"
							id="${documents.documentId}">
					</c:forEach>

				</div>

				<div class="col1">
					<button type="submit" id="submit">Save</button>

				</div>
				<div class="col1">

					<button type="button" id="button" onclick="cancelScheme()">
						Cancel</button>
				</div>

				<div class="error">
					<p id="val">
					</p>
				</div>




			</form>
		</div>
	</div>

	<div class="footer">
		<h2>Coordinated by: Team Agastya</h2>
	</div>
</body>
</html>