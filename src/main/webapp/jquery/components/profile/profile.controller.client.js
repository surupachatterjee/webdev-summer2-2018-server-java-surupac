(function () {

    $(main)
    var userService = new UserServiceClient();
    var currentUser;


    function main() {


        userService.getProfile().then(renderProfile);
        $("#updateBtn").click(updateUser);
        $("#logoutBtn").click(logout);
    }

    function updateUser() {
        var username = $('#usernameFld').val();
        var firstName =$('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var phone = $('#phoneFld').val();
        var email =$('#emailFld').val();
        var date = $('#dateFld').val();
        var role = $('#roleFld').val();

        currentUser ={
          "username" : username,
          "firstName" :firstName,
          "lastName" : lastName,
          "phone" : phone,
          "email" : email,
          "date" : date,
          "role" : role
        };

        console.log(currentUser.date);

        userService.updateProfile(currentUser)
            .then(function(response){
                alert("Profile Update Successful");
                renderProfile(response)
            });

    }

    function logout() {
        userService.logout().then(function (response) {
            window.location.href = "../login/login.template.client.html";
            //alert("Logged out!!!");
        });
    }

    function renderProfile(user){



        $('#usernameFld').val(user.username);
        $('#firstNameFld').val(user.firstName);
        $('#lastNameFld').val(user.lastName);
        $('#phoneFld').val(user.phone);
        $('#emailFld').val(user.email);
        $('#dateFld').val(user.date);
        $("#roleFld").val(user.role);

    }

})();