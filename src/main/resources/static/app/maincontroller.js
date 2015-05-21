angular.module("app").controller("MainController", MainController);

MainController.$inject = ["SessionService"];

function MainController(SessionService) {

	var vm = this;
	vm.logged = SessionService.isLogged();
};