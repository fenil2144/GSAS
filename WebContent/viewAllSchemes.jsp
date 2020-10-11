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
                height: auto;
                padding: 50px;
                text-align: center;
                align-self: center;
                opacity: 0.8;
                margin:8px;
                margin-left:10%;
                margin-right:20%;
       
            }

        </style>
        
    </head>
    <body>
    <script src="JS\navigation.js"></script>
        <div class="header">
            <h1>Schemes</h1>
        </div>
        <div class="sidenav">
        <div class="image">
                <img src="logo\logo.jpeg">
            </div>
            
            <div class="heading">
                <h2> Menu </h2>
            </div>
            <div class="content" id="first">
                <a href="EditCitizenDetailsServlet">Edit Details</a>
            </div>
            <div class="content">
             <a href="index.jsp">Logout</a>
            </div>
            
            
     </div>
     
        <div class="main">
            <div class="main1">
			<h1>Schemes</h1>
			<div class="row">
			<form action="ApplySchemeServlet">
				
				<c:forEach items="${notAppliedSchemeList}" var="notAppliedSchemeList">
				<input type="hidden" name="schemeId" value="${notAppliedSchemeList.schemeId }">
					<div class="column">
						<div class="card">
							
							<h2>${notAppliedSchemeList.schemeName } </h2>
							<img src="${notAppliedSchemeList.imagePath }" class=".cardImage">
							<p>${notAppliedSchemeList.summary }</p>
							<p>
								<button type="submit">Apply</button>
							</p>

						</div>
						</div>
							</c:forEach>
							</form>
					</div>
					</div>
                <div class="main1">
                    <h1> Applied Schemes</h1>
                    <div class="row">
				
				<c:forEach items="${acceptedSchemeList}" var="acceptedSchemeList">
				<input type="hidden" name="schemeId" value="${acceptedSchemeList.schemeId }">
					<div class="column">
						<div class="card">
							
							<h2>${acceptedSchemeList.schemeName } </h2>
							<img src="${acceptedSchemeList.imagePath }" class=".cardImage">
							<p>${acceptedSchemeList.summary }</p>

						</div>
						</div>
							</c:forEach>
							
					</div>
                </div>
                <div class="main1">
                    <h1> Rejected Schemes</h1>
                    <div class="row">
				
				<c:forEach items="${rejectedSchemeList}" var="schemeApplicantVO">
				<input type="hidden" name="schemeId" value="${schemeApplicantVO.schemeVO.schemeId }">
					<div class="column">
						<div class="card">
							
							<h2>${schemeApplicantVO.schemeVO.schemeName } </h2>
							<img src="${schemeApplicantVO.schemeVO.imagePath }" class=".cardImage">
							<p>${schemeApplicantVO.schemeVO.summary }</p>
							<p>
								${schemeApplicantVO.reason }
							</p>

						</div>
						</div>
							</c:forEach>
							
					</div>
                </div>

            
        </div>
        
 

        <div class="footer">
            <h2>Footer</h2>
        </div>
        
    
    </body>
</html>