angular.module("app").controller("LastnewsController", LastnewsController);

LastnewsController.$inject = ["$resource","$scope", "$auth", "SessionService"];

function LastnewsController($resource,$scope, $auth, SessionService) {

	var vm = this;
	
	vm.logged = SessionService.isLogged();
	
	//View model properties
	
	//Controller logic
	
	$scope.authenticate = function(provider) {
      		$auth.authenticate(provider);
    	};
	
	//Controller actions

		var Nacional = $resource ('/video/nacional')
		vm.nacional = Nacional.get();
		
		var Internacional = $resource ('/video/internacional')
		vm.internacional = Internacional.get();
		
		var Sonoras = $resource ('/video/bandassonarasmusic')
		vm.sonoras = Sonoras.get();
};