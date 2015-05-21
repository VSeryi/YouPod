angular.module("app").controller("SessionController", SessionController);

SessionController.$inject = ["$resource","$scope", "$auth","SessionService","$location"];

function SessionController($resource,$scope, $auth,SessionService,$location) {

	var vm = this;
	
	var users = [];
	
	//View model properties
	vm.newUser = {};
	
	// Controller logic

	$scope.authenticate = function(provider) {
		$auth.authenticate(provider);
	};
	
	vm.addUser = function (newUser) {
		alert("Hello! I am an alert box!!");
		userService.addUser(newUser);
		vm.newUser = {};
		$location.path("/");
	};
};