angular.module("app").controller("SettingController", SettingController);

SignupController.$inject = ["$resource","$scope", "$auth","$location"];

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
	var editarPerfil = $resource('/user/:id/edit', {
		id : '@id'},
		{update : {method : "PUT"}
		
		});
	
	vm.editPerfil = function(user) {
		new editarPerfil(vm.user).$update;
		//perfilService.editar(user);
	}
	
};