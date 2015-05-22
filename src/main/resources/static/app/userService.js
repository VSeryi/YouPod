angular.module("app").service("userService", userService);

userService.$inject = [ "$resource", "SessionService","$auth" ,"$window"];

function userService($resource, SessionService,  $auth, $window) {

	var that = this;

	var UserResource = $resource('/user/:id', {
		id : '@id'
	}, {
		update : {
			method : "PUT"
		}

	});

	this.addUser = function addUser(newUser) {
		new UserResource(newUser).$save(function(user) {
			SessionService.login(user.id);
			alert("Se ha registrado correctamente. Disfrute de la aplicación.");
			$window.location.href = '/';
		}, function(){
			alert("Lo sentimos, ya existe un usuario con ese email.");
		});
		
	};
	
	this.loginSocial = function loginSocial(provider){
		$auth.authenticate(provider)
        .then(function(user) {
        	if(user.data.facebookId){
        	SessionService.login(user.data.facebookId);}else{SessionService.login(user.data.googleId)}
          alert("Se ha longeado correctamente. Disfrute de la aplicación.");
          $window.location.href = '/';
        })
        .catch(function() {
          alert("No se le ha podido dar de alta.");
        });
		
	}
	
	this.loginUser = function loginUser(user){
		UserResource.get({
				email : user.email,
				password : user.password
			},function(id) {
			SessionService.login(id);
			alert("Se ha longeado correctamente. Disfrute de la aplicación.");
			$window.location.href = '/';
		}, function(){
			alert("Lo sentimos, datos incorrectos.");
		});
		
	}
}
