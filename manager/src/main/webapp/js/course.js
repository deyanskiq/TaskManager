"use strict";

function show_add_course_form() {
	$('#all > :not(#add_course)').hide();
	$("#add_course").show();
}


function add_course() {
	var course_name = $("#course_name")[0].value;
	var course_credits = $("#course_credits")[0].value;
	var course_description = $("#course_description")[0].value;
	
	
	if (course_name == "" || course_credits == "" || course_description == "") {
		alert("All fields should be filled");
		return;
	}
	
	var data = {
		name : course_name,
		credits : course_credits,
		description : course_description
	}
	
    $.ajax({
        url : 'rest/courses/addcourse',
        type : "POST",
        data : JSON.stringify(data),
        contentType: "application/json",
        statusCode: {
		        406: function() {
		        	alert("Fail to add course");
		        	$( "#reset_add_course" ).trigger( "click" );
		          },
		        200: function() {
		        	alert("Successfully added course");
		        	$( "#reset_add_course" ).trigger( "click" );
		        }
	        }
    });
}


function get_all_courses(){
	
	$("#courses p").remove();
	$('#all > :not(#courses)').hide();
	$("#courses ").show();
		$.ajax({
			url : 'rest/courses/getall',
			type : "GET",
			contentType: "application/json",
			success: function(courses) {
				console.log("S " + courses);
				for ( var i in courses) {			
					console.log(courses[i].name);
					$("#courses").append("<p>" + courses[i].name + "-" + courses[i].name + "</p>");
				}
			}
		});
	
	}

function delete_course(){
	
	var deleted_course_name = $("#deleted_course_name")[0].value;
	
	if (deleted_course_name == "") {
		alert("Course name should be filled");
		return;
	}
	$.ajax({
        url : 'rest/courses/deletebyname',
        type : "POST",
        data : deleted_course_name,
        contentType: "text/plain",
        statusCode: {
		        304: function() {
		        	alert("Course with name " + deleted_course_name+ " doesn't exist");
		        	$( "#reset_delete_course" ).trigger( "click" );
		          },
		        200: function() {
		        	alert("Successfully deleted course");
		        	$( "#reset_delete_course" ).trigger( "click" );
		        }
	        }
    });
	
}
function show_delete_course_form(){
	$('#all > :not(#delete_course)').hide();
	$("#delete_course").show();
}
