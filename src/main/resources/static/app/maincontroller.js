angular.module("app").controller("MainController", MainController);

MainController.$inject = [ "$resource", "$scope", "$auth", "SessionService" ];

function MainController($resource, $scope, $auth, SessionService) {

	var vm = this;
	vm.session = SessionService;
	
	// Controller logic

	$scope.authenticate = function(provider) {
		$auth.authenticate(provider);
	};

	
};