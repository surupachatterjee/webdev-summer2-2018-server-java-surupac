function UserServiceClient() {
    this.findAllUsers = findAllUsers;
    this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.url = 'http://localhost:8080/api/user';
    var self = this;

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {

        fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function deleteUser(userId) {
        console.log(self.url+"/"+userId);
            fetch(self.url + "/"+ userId,{
                method:'delete'
            });

    }
}

