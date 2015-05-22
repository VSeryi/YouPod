angular.module("app").controller("MainController", MainController);

MainController.$inject = ["SessionService","$window"];

function MainController(SessionService,$window) {

	var vm = this;
	vm.logged = SessionService.isLogged();
	
	vm.logout = function (){
		SessionService.logout();
		$window.location.href = '/';
	}
};