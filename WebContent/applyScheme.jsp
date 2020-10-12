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
            .col2{
                width: 400px;
                height: auto;
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
<script src="JS\navigation.js"></script>
        <div class="header">
            <h1>Apply Scheme</h1>
            
        </div>
        <div class="sidenav">
        <div class="image">
                <img src="logo\logo.jpeg">
            </div>
            
            <div class="heading">
                <h2> Menu </h2>
            </div>
            <div class="content" id="first">
                <a href="index.jsp">Logout</a>
            </div>
            
     </div>

        <div class="main">
            <div class="main1">
                
                    <div class="row">
				
				
				<input type="hidden" name="schemeId" value="${schemeApplicantVO.schemeVO.schemeId}">
					<div class="column">
						<div class="card">
							
							<h2>${schemeApplicantVO.schemeVO.schemeName} </h2>
							<img src="${schemeApplicantVO.schemeVO.imagePath}" class=".cardImage">
							<p>${schemeApplicantVO.schemeVO.summary}</p>
							<p>
								${schemeApplicantVO.reason}
							</p>

						</div>
						</div>
							
					</div>   
					<form method="POST" action="ApplySchemeDocumentServlet">                
					 <div class="col1">
                        <label for="bank">Choose a bank:</label>

                        <select name="bank" id="bank">
                        	<option value="select" id="select">Select</option>
                            <option value="hsbc">HSBC</option>
                            <option value="icic">ICICI</option>
                            <option value="hdfc">HDFC</option>
                        </select>
                    </div>
                    
                    <div class="col1">
                        <input type="number" name="accountNumber" id="accountNumber" placeholder="Enter Account Number" required><br>
                    </div>
                    
                    <div class="col1">
                        <input type="text" name="bankName" id="bankName" placeholder="Enter Bank" required><br>
                    </div>
                    
                    <div class="col1">
                        <input type="text" name="typeOfAccount" id="typeOfAccount" placeholder="Enter Type of Account" required><br>
                    </div>
                    
                    <div class="col1">
                        <input type="text" name="branch" id="branch" placeholder="Enter Branch" required><br>
                    </div>
                    
                     <div class="col1">
                        <input type="text" name="ifsc" id="ifsc" placeholder="Enter IFSC Code" required><br>
                    </div>
                     <div class="col1"> 
                        Upload supporting documents <input type="file" id="files" name="files" multiple >
                    </div>
                    
                    <div class="col1">
                        <button type="submit" id="submit" > Save </button>
                       
                    </div>
                    <div class="col1">
                        
                        <button type="button" id="button" onclick="cancelScheme()"> Cancel </button>
                    </div>
                    
                    
                    
                   
                    </form>
            </div>
        </div>
        
        <div class="footer">
            <h2>Coordinated by: Team Agastya</h2>
        </div>
</body>
</html>




