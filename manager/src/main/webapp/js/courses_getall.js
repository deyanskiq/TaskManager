"use strict";
$("#getcourses").click(function(){
	
	$.ajax({
		url : 'rest/students/getall',
		type : "GET",
		dataType: "json",
		success: function(students) {
			console.log("Students " + students);
			for ( var i in students) {			
				console.log(students[i].name);
				$("#courses").append("<p>" + students[i].name + "-" + students[i].facultyNumber + "</p>");
			}
		}
	});
})	
	
