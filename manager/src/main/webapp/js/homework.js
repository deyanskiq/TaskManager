"use strict";
var all_courses;
function show_all_homeworks(){
	$("#homeworks p").remove();
	$('#all > :not(#homeworks)').hide();
	$("#homeworks ").show();
		$.ajax({
			url : 'rest/homeworks/getall',
			type : "GET",
			contentType: "application/json",
			success: function(homeworks) {
				console.log("S " + homeworks);
				for ( var i in homeworks) {			
					console.log(homeworks[i].name);
					$("#homeworks").append("<p>" + homeworks[i].name + "</p>");
				}
			}
		});
}



function add_homework(){

	var course_name= $("#add_homework_with_course_name")[0].value;
	var homework_name= $("#homework_name")[0].value;
	var description= $("#homework_description")[0].value;
	var deadline= $("#deadline")[0].value;

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
	        	alert("Successfully added homework");
	        	$( "#reset_add_homework" ).trigger( "click" );
	        }
        }
});

}
function delete_homework(){
	
	var deleted_homework_name= $("#deleted_homework_name")[0].value;
	
	if( deleted_homework_name=="" ){
		alert("Homework name should be filled");
		return;
	}
	
	 $.ajax({
	        url : 'rest/homeworks/deleteByName',
	        type : "POST",
	        data : deleted_homework_name,
	        contentType:  "application/json",
	        statusCode: {
			        304: function() {
			        	alert("Fail to delete course");
			        	$( "#reset_delete_homework" ).trigger( "click" );
			          },
			        200: function() {
			        	alert("Successfully deleted course");
			        	$( "#reset_delete_homework" ).trigger( "click" );
			        }
		        }
	    });
	
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
	$('#all > :not(#delete_homework)').hide();
	$("#delete_homework").show();
}