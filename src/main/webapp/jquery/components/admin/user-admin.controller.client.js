
(function () {
    $(main);
    var tbody;
    var template;
    var currentUserId;
    var userService = new UserServiceClient();

    function main() {
        tbody = $("tbody");
        template = $(".template");
        findAllUsers();

        $("#createUser").click(createUser);
        $("#updateUser").click(updateUser);
       // $("#searchUser").click(findAllUsers);
        //$("#deleteUser").click(deleteUser);


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
    
    function updateUser() {
        var username = $("#usernameFld").val();
        var password = $("#passwordFld").val();
        var firstName =$("#firstNameFld").val();
        var lastName = $("#lastNameFld").val();
        var role = $("#roleFld").val();
        //var userId = $("#userIdFld").val();


        var user ={
            username:username,
            password:password,
            firstName:firstName,
            lastName:lastName,
            role:role,
            userId:currentUserId
        };

        userService
            .updateUser(user)
            .then(findAllUsers);

    }


    function renderUsers(users) {
        tbody.empty();
        for (var i=0;i<users.length;i++)
        {
            var user = users[i];
            var clone = template.clone(true,true);


            clone.attr('id', user.id);

            clone.find(".username").html(user.username);
            //clone.find(".password").html(user.password);
            clone.find(".firstName").html(user.firstName);
            clone.find(".lastName").html(user.lastName);
            clone.find(".role").html(user.role);

            clone.find('#deleteUser').click(deleteUser);
            clone.find('#editUser').click(findUserById);

            console.log(clone.firstName);
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

    function findUserById(event) {
        var editBtn = $(event.currentTarget);
        var userId = editBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService
            .findUserById(userId)
            .then(renderUser);
        currentUserId = userId;
        //$("#userIdFld").val(userId);



    }

    function renderUser(user) {
        console.log(user.username);
        //console.log(user.);


        console.log($('usernameFld'));
        $('#usernameFld').val(user.username);
        $('#passwordFld').val(user.password);
        $("#firstNameFld").val(user.firstName);
        $("#lastNameFld").val(user.lastName);
        $("#roleFld").val(user.role);

    }
    function deleteUser(event) {
        //console.log("inside delete user");
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);


    }



})();