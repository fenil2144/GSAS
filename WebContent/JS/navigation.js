/**
 * 
 */

function navigateBack() {
	location.replace("schemeManagement.jsp")
}

function displayScheme() {
	location.replace("displaySchemes.jsp")
}

function applyScheme() {
    location.replace("applyScheme.jsp")
    }

function switchPage() {
    location.replace("schemeManagement.jsp")
    }

function navigateLogin() {
    location.replace("citizenLogin.jsp")
    }

function addScheme() {
    location.replace("addScheme.jsp")
    }

function applyScheme() {
    location.replace("applyScheme.jsp")
    }
function redirectServlet(){
	
}

function cancelScheme(){
	location.replace("viewAllScehemes.jsp")
}

function validate(event){
    
    var password = document.getElementById("citizenPassword").value;
    var retype=document.getElementById("citizenPassword1").value;
    var aadhar =document.getElementById("aadhar").value;
    var aadharCheck=/^\d{12}$/;
    var phone =document.getElementById("phone").value;
    var phoneCheck=/^\d{10}$/;
    var pan=document.getElementById("pan").value;
    var panCheck = /^([A-Z]){5}([0-9]){4}([A-Z]){1}?$/;
    
    f1=false
    f2=false
    f3=false
    f4=false
    f5=false
    status=true
    if(password.length<=6){
        alert( "Password should be more than 6 characters!" );
        status =false;
        f1=false
        }else{
        f1=true
    }
    if(password.localeCompare(retype)!=0){
        alert("Password and confirm password should be same");
        status=false;
        f2=false
    }else{
        f2=true
    }
    if(aadharCheck.test(aadhar)){
        f3=true
    }else{
        alert("Adhaar Number should be of 12 digits");
        status=false;
        f3=false;
    }

    if(phoneCheck.test(phone)){
        f4=true
    }else{
        alert("Phone Number should be of 10 digits");
        status=false;
        f4=false
    }

    if(panCheck.test(pan)){
        f5=true
    }else{
        alert("Invalid Pan Number");
        status=false;
        f5=false
    }
    
    if(status=="false"){
    event.preventDefault();
    }
    
    //return status;

}

function validateAccount(event1){
    var number = document.getElementById("accountNumber").value;
    var accountCheck=/^\d{10}$/;
    var ifsc = document.getElementById("ifsc").value;
    var ifscCheck= /^\[A-Z]{4}0[A-Z0-9]{6}$/;
    f4=false
    f3=false
    status=false
    if(accountCheck.test(number)){
        f4=true
    }else{
        alert("Enter a valid account number.");
        status=false;
        f4=false
    }
    if(ifsc.test(ifscCheck)){
        f3=true
    }else{
        alert("Enter a valid ifsc code.");
        status=false;
        f3=false
    }

    
    if(status=="false"){
        event.preventDefault();
        }

    
}






