angular.module("app").controller("MainController", MainController);

MainController.$inject = ["$resource","$scope", "$auth","SessionService","$location"];

function MainController($resource,$scope, $auth,SessionService,$location) {

	var vm = this;
	
	vm.session = SessionService;
};