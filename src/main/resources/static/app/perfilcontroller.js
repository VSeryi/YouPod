angular.module("app").controller("PerfilController", PerfilController);

PerfilController.$inject = ["$resource","$scope", "$auth","SessionService"];

function PerfilController($resource,$scope, $auth, SessionService) {

	var vm = this;
	
	//View model properties
	
	vm.amigo = {};
	vm.user = {};
	
	//Controller logic
	
	$scope.authenticate = function(provider) {
      		$auth.authenticate(provider);
    	};
	
	//Controller actions

		var nac = $resource ('/video/nac', {
			id : '@id'},
			{update : {method : "PUT"}
			
			});
		vm.nac = nac.get();
		
		var inter = $resource ('/video/inter', {
			id : '@id'},
			{update : {method : "PUT"}
			
			});
		vm.inter = inter.get();
		
		var son = $resource ('/video/son')
		vm.son = son.get();
		
		var amigos = $resource('/user/:id/friends', {
			id : '@id'},
			{update : {method : "PUT"}
			
			});
		vm.amigos = amigos.query(SessionService.getId());
		
		var anadirAmigos = $resource('user/:id/add', {
			id : '@id'},
			{update : {method : "PUT"}
			
			});
		
		vm.addAmigos = function(amigo) {
			new anadirAmigos(vm.amigo).$save;
			//perfilService.add(amigo);
		}
		
		var borrarAmigos = $resource('user/:id/delete', {
			id : '@id'},
			{update : {method : "PUT"}
			
			});
		
		vm.deleteAmigos = function(amigo) {
			new borrarAmigos(vm.amigo).$remove;
			//perfilService.deleteAmigo(amigo);
		}
		
		var editarPerfil = $resource('/user/:id/edit', {
			id : '@id'},
			{update : {method : "PUT"}
			
			});
		
		vm.editPerfil = function(user) {
			new editarPerfil(vm.user).$update;
			//perfilService.editar(user);
		}
		
		
};