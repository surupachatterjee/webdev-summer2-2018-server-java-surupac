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

        if(password !== verifypassword)
        {
            alert("Passwords don't Match");
        }else{

        var user ={
          "username":username,
          "password":password
        };

        userService.register(user)
            .then(function (response) {
                window.location.href = "../profile/profile.template.client.html";
            });
    }}


})();