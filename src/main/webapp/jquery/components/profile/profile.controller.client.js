(function () {

    $(main)
    var userService = new UserServiceClient();

    function main() {
        userService.getProfile().then(renderProfile);

    }

    function renderProfile(user){
        $('#usernameFld').val(user.username);
        $("#roleFld").val(user.role);
    }

})();