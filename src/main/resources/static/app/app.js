angular.module("app", [ "ngResource", "ngRoute","satellizer", "ngCookies"]).config(function($authProvider) {

    $authProvider.facebook({
    	url: '/user/facebook',
      clientId: '1639304679648814'
    });

    $authProvider.google({
    	url: '/user/google',
      clientId: '572360036658-qst6aur51pjsboosagukdrd5puu6cttj.apps.googleusercontent.com'
    });

  });