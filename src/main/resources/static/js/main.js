function hello() {
    console.log("Hello from console!");
    $(".test").val("Не тыкай");
    console.log("dfdf");
}

function getJwtToken() {
    return localStorage.getItem("tokenJWT");
}

function setJwtToken(token) {
    localStorage.setItem("tokenJWT", token);
}

function createAuthorizationTokenHeader() {
    var token = getJwtToken();
    if (token) {
        return {"Authorization": token};
    } else {
        return {};
    }
}

function sendData() {
    var us = $("#us").val();
    var pa = $("#pa").val();
    var testData = {
        "username": us,
        "password": pa
    }

    $.ajax({
        url: "/jwt/auth",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(testData),
        dataType: "json",
        complete: function (jqXHR, textStatus) {
            setJwtToken(jqXHR.getResponseHeader("Authorization"));
            console.log(jqXHR);
            console.log(jqXHR.getResponseHeader("Roles"))
            $("#butons").show();


        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("jqXHR:");
            console.log(jqXHR);
            console.log("textStatus");
            console.log(textStatus);
            console.log("errorThrown");
            console.log(errorThrown);
        }
    })


}

function getUserInfo(){
    $.ajax({
        url: "/jwt/user",
        type: 'GET',
        headers: createAuthorizationTokenHeader(),
        success: function (data){
            console.log(data)
        }
    })
}

function getAdminInfo(){
    $.ajax({
        url: "/jwt/admin",
        type: 'GET',
        headers: createAuthorizationTokenHeader(),
        success: function (data){
            console.log(data)
        }
    })
}

