angular.module("app").controller("SignupController", SignupController);

SignupController.$inject = ["$resource","$scope", "$auth","userService"];

function SignupController($resource,$scope, $auth) {

	var vm = this;
	
	var users = [];
	
	//View model properties
	vm.newUser = {};
	
	vm.addUser = function (newUser) {
		userService.newUser(newUser);
		vm.newUser = {};
		$location.path("/");
	};
};