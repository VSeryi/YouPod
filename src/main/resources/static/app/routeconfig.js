angular.module("app").config(RouteConfig);

RouteConfig.$inject = [ '$routeProvider' ];

function RouteConfig($routeProvider) {

	$routeProvider.when('/', { templateUrl : "templates/index.html" });
	$routeProvider.when('/signup', { templateUrl : "templates/signup.html" });
	$routeProvider.when('/contact', { templateUrl : "templates/contact.html" });
	$routeProvider.when('/lastnews', { templateUrl : "templates/lastnews.html" });
	$routeProvider.when('/we', { templateUrl : "templates/we.html" });
	$routeProvider.when('/faq', { templateUrl : "templates/faq.html" });
	$routeProvider.when('/setting', { templateUrl : "templates/setting.html" });
	$routeProvider.when('/perfil', { templateUrl : "templates/perfil.html" });
	$routeProvider.when('/view', { templateUrl : "templates/view.html" });
	$routeProvider.otherwise('/404', { templateUrl : "templates/404.html" });

}