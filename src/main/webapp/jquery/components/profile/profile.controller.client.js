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
        var datedb = $('#dateFld').val();
        var role = $('#roleFld').val();

        var dob ;//= new Date(datedb);
        if (datedb !== ""){
            dob = new Date(datedb);
        }

        currentUser ={
          "username" : username,
          "firstName" :firstName,
          "lastName" : lastName,
          "phone" : phone,
          "email" : email,
          "dateOfBirth" : dob,
          "role" : role
        };

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

    function renderProfile(user) {
        if (user !== "") {


            $('#usernameFld').val(user.username);
            $('#firstNameFld').val(user.firstName);
            $('#lastNameFld').val(user.lastName);
            $('#phoneFld').val(user.phone);
            $('#emailFld').val(user.email);
            if (user.dateOfBirth === null) {
            } else {
                $('#dateFld').val(user.dateOfBirth.substr(0, 10));
            }
            $("#roleFld").val(user.role);

        }
    }
})();