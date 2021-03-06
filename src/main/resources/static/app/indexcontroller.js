angular.module("app").controller("IndexController", IndexController);

IndexController.$inject = [ "$resource", "$scope", "$auth" ];

function IndexController($resource, $scope, $auth) {

	var vm = this;

	// View model properties
	vm.link = "";
	vm.downloading = false;
	vm.loading = false;
	vm.loaded = false;

	// Controller logic

	$scope.authenticate = function(provider) {
		$auth.authenticate(provider);
	};

	// Controller actions

	vm.convertLink = function() {
		vm.downloading = true;
		vm.loading = true;
		var SimpleMusic = $resource('/video/:link', {
			link : '@link'
		});
		vm.music = SimpleMusic.get({
			youtubeLink : vm.link
		}, function() {
			vm.loading = false;
			vm.loaded = true;
		});
	};
};