angular.module("app").controller("ViewController", ViewController);

ViewController.$inject = [ "$resource", "$scope", "$auth" ];

function ViewController($resource, $scope, $auth) {

	var vm = this;

	// View model properties
	vm.title = "";
	vm.type = "Nacional";
	vm.download = "";
	vm.thumbail = "";
	vm.description = "";

	// Controller logic

	$scope.authenticate = function(provider) {
		$auth.authenticate(provider);
	};

	// Controller actions

	
	vm.title = "Wisin - Adrenalina ft. Jennifer Lopez, Ricky Martin";
	