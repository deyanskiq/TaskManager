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

}
