
function add_student() {
	var name = $("#student_name")[0].value;
    var password = $("#student_password")[0].value;
    var email = $("#student_email")[0].value;
    var faculty_number = $("#student_faculty_number")[0].value;
    var speciality = $("#student_speciality")[0].value;

    if ( name == "" || password == "" || email == "" || faculty_number == "" || speciality == "") {
    	
    	alert("All fields should be specified!");
        return;
    }
    if(!validateEmail(email)){
    	alert("Wrong email format");
    	return;
    }

    var data = {
    	facultyNumber : faculty_number,
    	password : password,
    	email : email,
    	name : name,
        speciality : speciality
    }

    console.log(data);

     $.ajax({
         url : 'rest/students/addstudent',
         type : "POST",
         data : JSON.stringify(data),
         contentType: "application/json",
         statusCode: {
		        406: function() {
		        	alert("Fail to add student");
		        	$( "#reset_add_student" ).trigger( "click" );
		          },
		        200: function() {
		        	alert("Successfully added student");
		        	$( "#reset_add_student" ).trigger( "click" );
		        }
	        }
     });

}
function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
