angular.module("app").service("userService", userService);

userService.$inject = ["$resource","SessionService"];

function userService($resource, SessionService) {
	
	var that = this;
	
	var UserResource = $resource('/user/:id', {
		id : '@id'},
		{update : {method : "PUT"}
		
		});
	

	var users = [];
	
	this.getUsers = function getUsers() {
		return users;
	}

	this.getUser = function getUser(id) {
		for (var i = 0; i < users.length; i++) {
			if (users[i].id.toString() === id) {
				return users[i];
			}
		}
	}
	
	
	
	this.addUser = function addUser (newUser) {
		console.log("servidor");
		console.log(newUser.email);

		new UserResource(newUser).$save(function(user) {
			SessionService.login(user.id);
			users.push(user);
			
		});
	}
}