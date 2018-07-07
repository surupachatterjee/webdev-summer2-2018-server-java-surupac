function User(username,password,firstName,lastName,role) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;

    this.getUsername = getUsername;
    this.setUsername = setUsername;
    this.getPassword = getPassword;
    this.setPassword = setPassword;
    this.getfirstName = getfirstName;
    this.setfirstName = setfirstName;
    this.getlastName = getlastName;
    this.setlastName = setlastName;
    this.getRole = getRole;
    this.setRole = setRole;

    function getUsername() {
        return this.username;

    }

    function setUsername() {
        this.username = username;
    }

    function getPassword() {
        return this.password;

    }

    function setPassword() {
        this.password = password;
    }

    function getfirstName() {
        return this.firstName;
    }

    function setfirstName() {
        this.firstName = firstName;

    }

    function  getlastName() {
        return this.lastName;
    }

    function setlastName() {
        this.lastName =lastName;

    }

    function getRole() {
        return this.role;
    }

    function  setRole() {
        this.role = role;

    }
}