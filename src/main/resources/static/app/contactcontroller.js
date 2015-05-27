angular.module("app").controller("ContactController", ContactController);

ContactController.$inject = ["$resource","$scope", "$auth"];

function ContactController($resource,$scope, $auth) {

	var vm = this;
	
	//View model properties
	vm.email = {};
		
	var EmailResource = $resource('/email');	
		
	vm.sendEmail = function() {
		new EmailResource(vm.email).$save();
	};
};