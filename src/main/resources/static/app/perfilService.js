angular.module("app").service("perfilService", perfilService);

perfilService.$inject = ["$resource","perfilService"];

function perfilService($resource, perfilService) {
	
	var that = this;
	
	var UserResource = $resource('/user/:id/add', {
		id : '@id'},
		{update : {method : "PUT"}
		
		});
	
	var UserResource2 = $resource('/user/:id/delete', {
		id : '@id'},
		{update : {method : "PUT"}
		
		});
	
	var UserResource3 = $resource('/user/:id/edit', {
		id : '@id'},
		{update : {method : "PUT"}
		
		});
	
	this.add = function add(amigo) {
		new UserResource(amigo).$save(function(user) {
			alert("Se ha añadido el amigo correctamente");
		}, function() {
			alert("No se ha encontrado un usuario con ese email");
		})
	};

	
	this.deleteAmigo = function deleteAmigo(amigo) {
		new UserResource2(amigo).$remove(function(user) {
			alert("Se ha borrado correctamente");
		}, function (){
			alert("No se ha podido borrar");
		})
	};
	
	this.editar = function editar(user) {
		new UserResource3(user).$update;
	}
	
} 