angular.module("app").service('SessionService', SessionService);

function SessionService() {
	  var that = this;
	  
	  function setCookie(cname, cvalue, exdays) {
		    var d = new Date();
		    d.setTime(d.getTime() + (exdays*24*60*60*1000));
		    var expires = "expires="+d.toUTCString();
		    document.cookie = cname + "=" + cvalue + "; " + expires;
		}

		function getCookie(cname) {
		    var name = cname + "=";
		    var ca = document.cookie.split(';');
		    for(var i=0; i<ca.length; i++) {
		        var c = ca[i];
		        while (c.charAt(0)==' ') c = c.substring(1);
		        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
		    }
		    return "";
		}

		
		that.isLogged = function(){
			return getCookie("youpodsession") != "";
			console.log("COOKIEAS: "+document.cookie);
		}
		
		that.login = function(id){
			setCookie("youpodsession",id,7);
			console.log("COOKIEAS: "+document.cookie);
		}
		
		that.logout = function(){
			setCookie("youpodsession","",7);
			console.log("COOKIEAS: "+document.cookie);
			
		}
		
		that.getId = function(){
			return getCookie("youpodsession");
			console.log("COOKIEAS: "+document.cookie);
		}
		
	}