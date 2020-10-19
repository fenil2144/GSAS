
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
                <img src="logo\logo.jpeg" alt="lOGO">
            </div>
            
            <div class="heading">
                <h2> Menu </h2>
            </div>
            <div class="content" id="first">
                <a href="EditCitizenDetailsServlet">Edit Details</a>
            </div>
            <div class="content">
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
            <h1>Schemes</h1>
            <div class="row">
            
                
                <c:forEach items="${notAppliedSchemeList}" var="schemeVO">
                <input type="hidden" name="schemeId" value="${schemeVO.schemeId }">
                    <div class="column">
                    <div class="col1">
                        <div class="card">
                            
                            <h2>${schemeVO.schemeName } </h2>
                            <img src="${schemeVO.imagePath }" class="cardImage" alt="Scheme Image">
                            <p>${schemeVO.summary }</p>
                            <p>
                                <c:url value="GovernmentSchemesApplicationSystem/ApplySchemeServlet" var="editURL">
                                    <c:param name="schemeId" value="${schemeVO.schemeId}" />
                                    
                                </c:url>
                                
                                <a href="/<c:out value="${editURL}"/>">
                                    <button type="submit" id="${schemeVO.schemeId}">Apply Scheme</button>
                                </a>
                                
                            </p>
                        </div>
                        </div>
                        </div>
                            </c:forEach>
                            
                    </div>
                    </div>
                <div class="main1">
                    <h1> Applied Schemes</h1>
                    <div class="row">
                
                <c:forEach items="${acceptedSchemeList}" var="schemeApplicantVO">
                <input type="hidden" name="schemeId" value="${schemeApplicantVO.schemeVO.schemeId}">
                    <div class="column">
                        <div class="card">
                            
                            <h2>${schemeApplicantVO.schemeVO.schemeName} </h2>
                            <img src="${schemeApplicantVO.schemeVO.imagePath}" class="cardImage" alt="Scheme Image">
                            <p>${schemeApplicantVO.schemeVO.summary}</p>

                        </div>
                        </div>
                            </c:forEach>
                            
                    </div>
                </div>
                 <div class="main1">
                    <h1> Rejected Schemes</h1>
                    <div class="row">
                
                <c:forEach items="${rejectedSchemeList}" var="schemeApplicantVO">
                <input type="hidden" name="schemeId" value="${schemeApplicantVO.schemeVO.schemeId}">
                    <div class="column">
                        <div class="card">
                            
                            <h2>${schemeApplicantVO.schemeVO.schemeName} </h2>
                            <img src="${schemeApplicantVO.schemeVO.imagePath}" class="cardImage" alt="Scheme Image">
                            <p>${schemeApplicantVO.schemeVO.summary}</p>
                            <p style="color:red;">
                                ${schemeApplicantVO.reason}
                            </p>

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