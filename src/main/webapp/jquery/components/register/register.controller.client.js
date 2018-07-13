(function () {


    $(init)

    var userService = new UserServiceClient();

    function init() {
        $("#signUpBtn").click(register);
    }

    function register(e) {
        e.preventDefault();
        var username = $("#usernameFld").val();
        var password = $("#passwordFld").val();
        var verifypassword = $("#verifyPasswordFld").val();

        if(username === "" || password === "" || verifypassword === "")
        {
            alert("All Fields are mandatory");
        }
        else if(password !== verifypassword)
        {
            alert("Passwords don't Match");
        }else{

        var user ={
          "username":username,
          "password":password
        };

        userService.register(user)
            .then(function (response) {
                if (response.status == '409'){
                    alert("Username already taken");
                }
                else{
                    window.location.href = "../profile/profile.template.client.html";
                }

            });
    }
    }


})();