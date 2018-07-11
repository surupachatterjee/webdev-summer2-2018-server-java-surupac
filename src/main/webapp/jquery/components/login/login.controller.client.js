(function () {

    $(main)

    var userService = new UserServiceClient();
    
    function main() {


        $("#loginBtn").click(login);
        
    }
    
    function login() {
        var username = $("#usernameFld").val();
        var password = $("#passwordFld").val();


        var user = {
          "username":username,
          "password":password
        };

        userService
            .login(user)
            .then(function (response) {
                window.location.href = "../profile/profile.template.client.html";
            });


    }
})();