angular.module("app").service("SessionService", SessionService);

SessionService.$inject = ["$resource", "$cookiesProvider"];

function SessionService($resource, $cookies) {
	
	var that = this;
	
	this.isLogged = function(){
		alert("Hello! I am an alert box!!");
		return $cookies.get("youpodsessionlogged");
	}
	
	this.login = function(id){
		$cookies.put("youpodsessionlogged",true);
		$cookies.put("youpodsessionid",id);
		
	}
	
	this.logout = function(){
		$cookies.remove("youpodsessionlogged");
		$cookies.remove("youpodsessionid");
		
	}
	
	this.getId = function(){
		return $cookies.get("youpodsessionid");
	}
	
}