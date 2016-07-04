"use strict";

$( document ).ready(function() {
    get_all_courses_by_teacher();
});

function get_all_courses_by_teacher(){
	
	$("#courses p").remove();
	$('#all > :not(#courses)').hide();
	$("#courses").show();
		$.ajax({
			url : 'rest/courses/getCoursesByCurrentTeacher',
			type : "GET",
			contentType: "application/json",
			success: function(courses) {
				renderTable(courses);
//				console.log("S " + courses);
//				for ( var i in courses) {			
//					console.log(courses[i].name);
//					$("#courses").append("<p>" + courses[i].name + "</p>");
//				}
			}
		});
	
	}

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


function renderTable(data) {
    $("#courses tr").remove();
    $("#courses thead").remove();
    $("#courses").append("<thead><tr><th>Course name</th><th>Course credits</th><th>Course description</th></tr></thead>")
    for (var i in data) {
        renderRow(data[i]);
    }
}

function renderRow(rowData) {
    var row = $("<tr />")
    $("#courses").append(row);
    row.append($("<td>" + rowData.name + "</td>"));
    row.append($("<td>" + rowData.credits + "</td>"));
    row.append($("<td>" + rowData.description + "</td>"));
}
