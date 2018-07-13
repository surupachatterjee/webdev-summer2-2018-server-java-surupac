(function () {

    console.log("In profile controller anounimous")
    $(main)
    var userService = new UserServiceClient();
    var currentUser;
    console.log("In profile controller anounimous Setting value for render prof");
    var renderProf =true;

    function main() {
        console.log("In profile controller main");
        userService.getProfile().then(renderProfile);
        $("#updateBtn").click(updateUser);
        $("#logoutBtn").click(logout);
    }

    function updateUser() {
        console.log("In profile controller updateUser");
        var username = $('#usernameFld').val();
        var firstName =$('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var phone = $('#phoneFld').val();
        var email =$('#emailFld').val();
        var datedb = $('#dateFld').val();
        var role = $('#roleFld').val();

        var dob = null;//= new Date(datedb);
        if (datedb === "") {
        } else {
            console.log(datedb);
            dob = new Date(datedb);
            console.log(dob.toISOString());

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
                //renderProfile(response)
            });

    }

    function logout(e) {
        e.preventDefault();
        console.log("In profile controller logout");
        userService.logout().then(function (response) {
        //alert("Logged out!!!");
        window.location.href = "../login/login.template.client.html";

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
                //var dob = new Date(user.dateOfBirth)
                $('#dateFld').val(user.dateOfBirth.substr(0, 10));
                console.log(user.dateOfBirth);
            }
            $("#roleFld").val(user.role);

        }
    }
})();