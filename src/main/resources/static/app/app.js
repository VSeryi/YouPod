angular.module("app", [ "ngResource", "ngRoute","satellizer" ]).config(function($authProvider) {

    $authProvider.facebook({
      clientId: '1639304679648814'
    });

    $authProvider.google({
      clientId: '572360036658-qst6aur51pjsboosagukdrd5puu6cttj.apps.googleusercontent.com'
    });
    $authProvider.twitter({
      url: '/auth/twitter'
    });
  });