"use strict";
window.onload = function(){
    $.ajax({
        url: 'rest/user/role',
        type: "GET",
        dataType : "text",
        success: function(role){
            console.log("ROLE " + role);
            if (role == "Teacher" || role == "No user") {
                window.location.replace("index.html");
            }
        }
    }) 
};