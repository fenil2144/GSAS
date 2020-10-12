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

function validate(){
    
    var password = document.getElementById("citizenPassword").value;
    var retype=document.getElementById("citizenPassword1").value;
    var aadhar =document.getElementById("aadhar").value;
    var aadharCheck=/^\d{12}$/;
    var phone =document.getElementById("phone").value;
    var phoneCheck=/^\d{10}$/;
    var pan=document.getElementById("pan").value;
    var panCheck = /^([A-Z]){5}([0-9]){4}([A-Z]){1}?$/;
    
    status=false;
    if(password.length<=6){
        alert( "Password should be more than 6 characters!" );
        status =false;
    }else{
        status=true;
    }
    if(password.localeCompare(retype)!=0){
        alert("Password and confirm password should be same");
        status=false;
    }else{
        status=true;
    }
    if(aadharCheck.test(aadhar)){
        status=true;
    }else{
        alert("Adhaar Number should be of 12 digits");
        status=false;
    }

    if(phoneCheck.test(phone)){
        status=true;
    }else{
        alert("Phone Number should be of 10 digits");
        status=false;
    }

    if(panCheck.test(pan)){
        status=true;
    }else{
        alert("Invalid Pan Number");
        status=false;
    }
    return status;

}






