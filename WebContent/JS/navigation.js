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





function validatePassword() {
    var password = document.getElementById("citizenPassword").value;
    var retype=document.getElementById("citizenPassword1").value;
    if (password.length <= 6) {
        alert( "Password should be more that 6 characters");
    }
    
    if(password!=retype){
        alert("pass not same");
    }
    return false;
}
function validateAdhaar(){
    var aadhar =document.getElementById("aadhar").value;
    var aob=/^\d{12}$/;
    if(aob.test(aadhar)){
        return "";
    }else{
        return "Adhaar Number should be of 12 digits";
    }
    return "";
}
function validatePhone(){
    var phone =document.getElementById("phone").value;
    var check=/^\d{10}$/;
    if(check.test(phone)){
        return "";
    }else{
        return "Phone Number should be of 10 digits";
    }
    return "";
}
function validatePan(){
    var pan=document.getElementById("pan").value;
    var regpan = /^([A-Z]){5}([0-9]){4}([A-Z]){1}?$/;

    if(!regpan.test(pan)){
        return "Invalid Pan Number";
    }
    // if(regpan.test(pan)){
    //     return "";
    // }else{
    //     return "Invalid Pan Number";
    // }
    //return "";
}
// function validate(){
//     var result ="";
//     result+=validatePassword();
//     result+=validateAdhaar();
//     //alert("asd");
//     if(result ==""){
//         return true;
//     }
//     alert(result);
// }
function validate(){
    //var result="";
    //val.innerText="";
    var f1=false;
    var f2=false;
    var f3=false;
    var f4=false;
    document.getElementById("val").innerHTML="";
    if(validatePassword().localeCompare("")!=0){
        let li=document.createElement('li');
        li.innerText=validatePassword();
        //alert(validatePassword());
        f1=true;
        document.getElementById("val").appendChild(li);
        //val.appendChild(li);
        
    }

//    if(validateAdhaar().localeCompare("")!=0){
//        let li=document.createElement('li');
//        li.innerText=validateAdhaar();
//        f2=true;
//        document.getElementById("val").appendChild(li);
//        //val.appendChild(li);
//        
//    }
//    if(validatePhone().localeCompare("")!=0){
//        let li=document.createElement('li');
//        li.innerText=validatePhone();
//        f3=true;
//        document.getElementById("val").appendChild(li);
//        //val.appendChild(li);
//        
//    }
//    if(validatePan().localeCompare(""!=0)){
//        let li=document.createElement('li');
//        li.innerText=validatePan();
//        f4=true;
//        document.getElementById("val").appendChild(li);
//        //val.appendChild(li);
//    }


}