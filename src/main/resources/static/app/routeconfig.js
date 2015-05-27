angular.module("app").config(RouteConfig);

RouteConfig.$inject = [ '$routeProvider' ];

function RouteConfig($routeProvider) {

	$routeProvider.when('/', { templateUrl : "templates/index.html" });
	$routeProvider.when('/signup', { templateUrl : "templates/signup.html" });
	$routeProvider.when('/post/:id', { templateUrl : "templates/post.html" });

}