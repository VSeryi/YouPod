angular.module("app").controller("SettingController", SettingController);

SignupController.$inject = ["$resource","$scope", "$auth","userService","$location"];

function SettingController($resource,$scope, $auth,userService,$location) {

	var vm = this;
	
	var users = [];
	
	//View model properties
	vm.newUser = {};
	
	// Controller logic

	$scope.authenticate = function(provider) {
		$auth.authenticate(provider);
	};
	
	//Controller actions
	
	vm.updateUser = function(user) {
		userService.updateUser(user);
	}
	
};