(function () {
    $(main);
    var tbody;
    var template;
    var userService = new UserServiceClient();

    function main() {
        tbody = $("tbody");
        template = $(".template");
        $("#createUser").click(createUser);
       // $("#updateUser").click(updateUser);
        $("#searchUser").click(findAllUsers);
        $("#deleteUser").click(deleteUser);

        /*var promise = fetch('http://localhost:8080/api/user');
        promise.then(function (response) {
            return response.json();
        }).then(renderUsers);*/

    }


    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    
    function renderUsers(users) {
        tbody.empty();
        for (var i=0;i<users.length;i++)
        {
            var user = users[i];
            var clone = template.clone();


            clone.attr('id', user.id);

            clone.find('#deleteUser').click(deleteUser);
            clone.find(".username").html(user.username);
            console.log(clone);
            tbody.append(clone);
        }
    }

    function createUser() {
        console.log("user created");
        var username = $("#usernameFld").val();
        var password = $("#passwordFld").val();
        var firstName =$("#firstNameFld").val();
        var lastName = $("#lastNameFld").val();
        var role = $("#roleFld").val();

        var user ={
            username:username,
            password:password,
            firstName:firstName,
            lastName:lastName,
            role:role
        };

        userService
            .createUser(user)
            .then(findAllUsers);

    }

    function deleteUser(event) {
        console.log("inside delete user");
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);

        console.log(userId);
    }



})();