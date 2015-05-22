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
	

	vm.thumbail = "https://i.ytimg.com/vi/ME2Hufquz0k/hqdefault.jpg";
	
	
	vm.description = "Pre-order Wisin's new album El Regreso del Sobreviviente now on iTunes: http://smarturl.it/WisinPreOrder Adrenalina - (Audio) http://www.youtube.com/watch?v=-HF5SMsXnPQ Sigue A Wisin en: http://www.elsobreviviente.com https://www.facebook.com/wisinelsobreviviente https://twitter.com/Juanlmorera Music video by Wisin feat. Jennifer Lopez & Ricky Martin performing Adrenalina. (C) 2014 Sony Music Entertainment US Latin LLC";
	

	vm.download = "http://w11.youtubeinmp3.com/download/grabber/?mp3=Wisin_-_Adrenalina_ft_Jennifer_Lopez_Ricky_Martin.mp3&id=ME2Hufquz0k&t=Wisin+-+Adrenalina+ft.+Jennifer+Lopez%2C+Ricky+Martin&s=10";
	
	
	vm.type = "Internacional";
	
};