"use strict";
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
        title : title
    }
    console.log(data);
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
function delete_teacher() {
    var username = $("#delete_teacher_username")[0].value;

    if (username == "") {
        alert("All fields should be specified!");
        return;
    }


    $.ajax({
        url : 'rest/teachers/deleteByUsername',
        type : "POST",
        data : username,
        contentType: "text/plain",
        statusCode: {
		        406: function() {
		        	alert("Fail to delete teacher");
		        	$( "#reset_delete_teacher" ).trigger( "click" );
		          },
		        200: function() {
		        	alert("Successfully deleted teacher");
		        	$( "#reset_delete_teacher" ).trigger( "click" );
		        }
	        }
    });
    }
