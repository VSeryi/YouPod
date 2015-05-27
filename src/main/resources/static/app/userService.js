angular.module("app").factory("blogManager", blogManager);

blogManager.$inject = [ "$resource", "$timeout" ];

function blogManager($resource, $timeout) {

	var UserResource = $resource('/users/:id', {
		id : '@id'});
	

	var users = [];
	
	function addUser (newUser) {
		new UserResource(newUser).$save(function(user) {
			users.push(user);
		});
	}
}