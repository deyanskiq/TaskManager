function delete_student() {
	var faculty_number = $("#delete_student_faculty_number")[0].value;

	if (faculty_number == "") {
		alert("All fields should be specified!");
		return;
	}

	var data = {
		faculty_number : faculty_number
	}

	console.log(data);

	if (faculty_number == "") {
		alert("All fields should be specified!");
		return;
	}
	console.log(faculty_number);
	$.ajax({
		url : 'rest/students/deletebyfn',
		type : "POST",
		data : faculty_number,
		contentType : "text/plain",
		statusCode : {
			406 : function() {
				alert("Fail to delete student");
				$("#reset_delete_student").trigger("click");
			},
			200 : function() {
				alert("Successfully deleted student");
				$("#reset_delete_student").trigger("click");
			}
		}
	});
}