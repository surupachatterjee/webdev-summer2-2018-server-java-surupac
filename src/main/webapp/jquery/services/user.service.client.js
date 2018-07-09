function UserServiceClient() {
    this.findAllUsers = findAllUsers;
    this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.register = register;
    this.url = 'http://localhost:8080/api';
    //this.register_url = 'http://localhost:8080/api/register';
    var self = this;

    function findAllUsers() {
        return fetch(self.url + "/user")
            .then(function (response) {
                return response.json();
            });
    }

    function updateUser(user) {
        return fetch(self.url + "/user"+ "/"+ user.userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function createUser(user) {
        return fetch(self.url + "/user", {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function deleteUser(userId) {
        console.log(self.url + "/user" +"/"+userId);
            fetch(self.url + "/"+ userId,{
                method:'delete'
            }).then(function(response){
                return response.json();
            });

    }

    function findUserById(userId) {
        return fetch(self.url + "/user" + '/' + userId)
            .then(function(response){
                return response.json();
            });
    }
    
    function register(user) {
        return fetch(self.url + "/register", {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

}

