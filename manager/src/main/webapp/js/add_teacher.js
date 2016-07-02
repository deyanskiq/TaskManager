function add_teacher() {
    var username = $("#teacher_username")[0].value;
    var password = $("#teacher_password")[0].value;
    var email = $("#teacher_email")[0].value;
    var name = $("#teacher_name")[0].value;
    var title = $("#teacher_title")[0].value;

    if (username == "" || password == "" || email == "" || name == "" || title == "") {
        alert("All fields should be specified!");
        return;
    }

    var data = {
        username : username,
        password : password,
        name : name,
        email : email,
        title : title
    }

    console.log(data);

}
