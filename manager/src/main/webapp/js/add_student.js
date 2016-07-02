function add_student() {
    var username = $("#student_username")[0].value;
    var password = $("#student_password")[0].value;
    var email = $("#student_email")[0].value;
    var name = $("#student_name")[0].value;
    var faculty_number = $("#student_faculty_number")[0].value;
    var speciality = $("#student_speciality")[0].value;

    if (username == "" || password == "" || email == "" || name == "" || faculty_number == "" || speciality == "") {
        alert("All fields should be specified!");
        return;
    }

    var data = {
        username : username,
        password : password,
        name : name,
        email : email,
        faculty_number : faculty_number,
        speciality : speciality
    }

    console.log(data);

    // $.ajax({
    //     url : 'rest/user/login',
    //     type : "POST",
    //     data : JSON.stringify(data),
    //     contentType: "application/json",
    //     dataType: "text",
    //     success: function(role) {
    //         if (role == "Not Found") {
    //             $("#reset_login").trigger("click");
    //         }
    //         else if(role == "Student"){
    //             window.location.replace("student.html");
    //         }
    //         else if(role == "Teacher"){
    //             window.location.replace("teacherhome.html")
    //         }
    //     }
    // });

}
