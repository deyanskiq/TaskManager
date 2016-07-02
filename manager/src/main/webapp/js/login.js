function login() {
	var username = $("#userName")[0].value;
	var password = $("#pwd")[0].value;

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
			if (role == "Not Found") {
				$("#reset_login").trigger("click");				
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
