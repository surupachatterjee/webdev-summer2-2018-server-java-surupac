(function () {

    $(main)

    var userService = new UserServiceClient();
    
    function main() {


        $("#loginBtn").click(login);
        
    }
    
    function login(e) {
        e.preventDefault();
        var username = $("#usernameFld").val();
        var password = $("#passwordFld").val();


        var user = {
          "username":username,
          "password":password
        };

        userService
            .login(user)
            .then(function (response) {
                if (response.status == '404'){
                    alert("Incorrect Username or Password ");
                }
                else{
                    window.location.href = "../profile/profile.template.client.html";
                }
            });


    }
})();