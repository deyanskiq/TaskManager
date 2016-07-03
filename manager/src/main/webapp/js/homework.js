"use strict";
var all_courses;
function show_all_homeworks(){
	
}


function homeworks_getall(){
	
}
function add_homework(){

	var course_name= $("#add_homework_with_course_name")[0].value;
	var homework_name= $("#homework_name")[0].value;
	var description= $("#homework_description")[0].value;
	var deadline= $("#deadline")[0].value;
		console.log(course_name);
		console.log(homework_name);
		console.log(description);
		console.log(deadline);

if (course_name == "" || homework_name == "" || description == "" || deadline == "") {
	alert("All fields should be filled");
	return;
}

var course_object;

for (var course in all_courses) {
	if (all_courses[course].name = course_name) {
		course_object = all_courses[course];
		break;
	}
}

var data = {
		course : course_object,
		name : homework_name,
		description : description,
		deadline: new Date(deadline)
	}

console.log(course_object);
console.log(data);

$.ajax({
	
    url : 'rest/homeworks/addhomework',
    type : "POST",
    data : JSON.stringify(data),
    contentType: "json",
    statusCode: {
	        304: function() {
	        	alert("Fail to add homework");
	        	$( "#reset_add_homework" ).trigger( "click" );
	          },
	        200: function() {
	        	alert("Successfully added course");
	        	$( "#reset_add_homework" ).trigger( "click" );
	        }
        }
});

}
function delete_homework(){
	
}
function show_add_homework_form(){
	$("#add_homework_with_course_name option").remove();
	$('#all > :not(#add_homework)').hide();
	$("#add_homework").show();
	
		    $.ajax({
	    	url : 'rest/courses/getall',
	    	type : "GET",
	    	contentType: "application/json",
	    	success: function(courses) {
	    		all_courses=courses;
	    		for ( var i in courses) {			
	    			$("#add_homework_with_course_name").append("<option>" + courses[i].name + "</option>");
	    		}
	    	}
	    });
		    
	    $('input[name="daterange"]').daterangepicker({
	    	singleDatePicker: true,
	        timePicker: true,
	        timePickerIncrement: 30,
	        locale: {
	            format: 'MM/DD/YYYY h:mm A'
	        }
	    });
	    
	
	 
}
function show_delete_homework_form(){
	
}