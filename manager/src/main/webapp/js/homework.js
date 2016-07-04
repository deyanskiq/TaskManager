"use strict";
var all_courses;
function show_all_homeworks(){
	$('#all > :not(#homeworks)').hide();
	$("#homeworks").show();
		$.ajax({
			url : 'rest/homeworks/getall',
			type : "GET",
			contentType: "application/json",
			success: function(homeworks) {
				renderTableHomeworks(homeworks);
				console.log("S " + homeworks);
//				for ( var i in homeworks) {			
//					console.log(homeworks[i].name);
//					$("#homeworks").append("<p>" + homeworks[i].name + "</p>");
//				}
			}
		});
}

function renderTableHomeworks(data) {
	$("#homeworks tr").remove();
	$("#homeworks thead").remove();
    $("#homeworks").append("<thead><tr><th>Homework name</th><th>Homework description</th><th>Homework deadline</th></tr></thead>");
    for (var i in data) {
    	console.log(data[i]);
        renderRowHomework(data[i]);
    }
}

function renderRowHomework(rowData) {
    var row = $("<tr />")
    $("#homeworks").append(row);
    console.log(rowData.name);
    console.log(rowData.description);
    console.log(rowData.deadline);

    row.append($("<td>" + rowData.name + "</td>"));
    row.append($("<td>" + rowData.description + "</td>"));
    row.append($("<td>" + new Date(rowData.deadline).toString() + "</td>"));
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
	        contentType:  "text/plain",
	        statusCode: {
			        304: function() {
			        	alert("Fail to delete homework");
			        	$( "#reset_delete_homework" ).trigger( "click" );
			          },
			        200: function() {
			        	alert("Successfully deleted homework");
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
	    	url : 'rest/courses/getCoursesByCurrentTeacher',
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