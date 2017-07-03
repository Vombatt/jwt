function hello(){
    console.log("Hello from console!");
    $(".test").val("Не тыкай");
    console.log("dfdf");
}

function setJwtToken(token) {
        localStorage.setItem(TOKEN_KEY, token);
    }

function sendData(){
    var us = $("#us").val();
    var pa = $("#pa").val();
    var testData = {
        "username":us,
        "password":pa
    }
    
    $.ajax({
        url: "/jwt/auth",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(testData),
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
                setJwtToken(data.token);
                console.log(data);
                console.log("textStatus: " + textStatus);
                console.log("jqXHR: " + jqXHR);
            }
    })
}

