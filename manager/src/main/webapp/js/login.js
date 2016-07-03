"use strict";
function login() {
	var username = $("#userName")[0].value;
	var password = $("#pwd")[0].value;
	$("#not_login").remove();

	if (username == "" || password == "") {
		alert("Username and password should not be empty.");
		return;
	}

	var data = {
		name : username,
		password : password
	}
	console.log(data);
	$.ajax({
		url : 'rest/user/login',
		type : "POST",
		data : JSON.stringify(data),
		contentType: "application/json",
		dataType: "text",
		success: function(role) {
			console.log(role);
			if (role == "Not Found") {
				$("#reset_login").trigger("click");	
				$("#not_successfully_log_in").append("<p id=\"not_login\">Invalid username or password</p>");
			}
			
			else if(role == "Student"){
				window.location.replace("studenthome.html");				
			}
			else if(role == "Teacher"){
				window.location.replace("teacherhome.html")
			}
		}
	});

}
