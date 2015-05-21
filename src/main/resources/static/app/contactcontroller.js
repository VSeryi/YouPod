angular.module("app").controller("ContactController", SignupController);

ContactController.$inject = ["$resource","$scope", "$auth"];

function ContactController($resource,$scope, $auth) {

	var vm = this;
	
	//View model properties
	vm.email = {};
		
	//Controller logic
	
	
	//Controller actions

	vm.sendEmail = function() {
		
	};
};