angular.module("app").controller("SignupController", SignupController);

SignupController.$inject = [ "$resource", "$scope", "$auth", "userService",
		"$location" ];

function SignupController($resource, $scope, $auth, userService, $location) {

	var vm = this;

	// View model properties
	vm.newUser = {};
	vm.user = {};

	// Controller logic

	$scope.authenticate = function(provider) {
		$auth.authenticate(provider);
	};

	vm.addUser = function(newUser) {
		userService.addUser(newUser);
		vm.newUser = {};
	};
	
	vm.loginUser = function(user) {
		userService.loginUser(user);
		vm.user = {};
	};
	
	vm.loginSocial = function(provider) {
			userService.loginSocial(provider);
	    };
	  };