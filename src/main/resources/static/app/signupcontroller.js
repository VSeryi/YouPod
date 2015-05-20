angular.module("app").controller("SignupController", SignupController);

SignupController.$inject = ["$resource","$scope", "$auth","blogmanager"];

function SignupController($resource,$scope, $auth) {

	var vm = this;
	
	var users = [];
	
	//View model properties
	vm.link = "";
	vm.downloading = false;
	vm.loading = false;
	vm.loaded = false;
	vm.newUser = {};
	
	//Controller logic
	
	$scope.authenticate = function(provider) {
      		$auth.authenticate(provider);
    	};
	
	//Controller actions

	vm.convertLink = function() {
		vm.downloading = true;
		vm.loading = true;
		var SimpleMusic = $resource('/video/:link', {
			link : '@link'
		});
		vm.music = SimpleMusic.get({youtubeLink : vm.link}, function(){
			vm.loading = true;
			vm.loaded = true;
		});
	};
	
	vm.addUser = function (newUser) {
		blogmanager.newUser(newUser);
		vm.newUser = {};
		$location.path("/");
	};
};