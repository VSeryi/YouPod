angular.module("app").config(RouteConfig);

RouteConfig.$inject = [ '$routeProvider' ];

function RouteConfig($routeProvider) {

	$routeProvider.when('/', { templateUrl : "templates/index.html" });
	$routeProvider.when('/signup', { templateUrl : "templates/signup.html" });
	$routeProvider.when('/contact', { templateUrl : "templates/contact.html" });
	$routeProvider.when('/lastnews', { templateUrl : "templates/lastnews.html" });
	$routeProvider.when('/we', { templateUrl : "templates/we.html" });

}