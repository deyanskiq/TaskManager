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