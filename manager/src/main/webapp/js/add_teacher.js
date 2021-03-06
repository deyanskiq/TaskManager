function add_teacher() {
    var username = $("#teacher_username")[0].value;
    var password = $("#teacher_password")[0].value;
    var name = $("#teacher_name")[0].value;
    var title = $("#teacher_title")[0].value;

    if (username == "" || password == "" || name == "" || title == "") {
        alert("All fields should be specified!");
        return;
    }

    var data = {
        username : username,
        password : password,
        name : name,
        email : email,
        title : title
    }

    $.ajax({
        url : 'rest/teachers/addteacher',
        type : "POST",
        data : JSON.stringify(data),
        contentType: "application/json",
        statusCode: {
		        406: function() {
		        	alert("Fail to add teacher");
		        	$( "#reset_add_teacher" ).trigger( "click" );
		          },
		        200: function() {
		        	alert("Successfully added teacher");
		        	$( "#reset_add_teacher" ).trigger( "click" );
		        }
	        }
    });

}
