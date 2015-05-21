angular.module("app").controller("SignupController", SignupController);

SignupController.$inject = ["$resource","$scope", "$auth","userService","$location"];

function SignupController($resource,$scope, $auth,userService,$location) {

	var vm = this;
	
	var users = [];
	
	//View model properties
	vm.newUser = {};
	
	// Controller logic

	$scope.authenticate = function(provider) {
		$auth.authenticate(provider);
	};
	
	vm.addUser = function (newUser) {
		userService.addUser(newUser);
		vm.newUser = {};
		$location.path("/");
	};
};