'use strict';

angular.module('uiApp')
  .controller('MainCtrl', function ($scope, $http) {
    $scope.user = {};
    $scope.users = [];

    function pushFixedUser(user) {
      var m = moment(user.created_at);
      user.created_at_formatted = m.format("YYYY/MM/DD HH:mm:ss");
      $scope.users.push(user);
    }
    
    var listUrl = jsRoutes.controllers.Application.list().url;
    $http.get(listUrl)
    .success(function(data) {
      data.users.forEach(pushFixedUser);
    });

    $scope.insertUser = function() {
      // API ‚Ì URL ‚ðŽæ“¾
      var url = jsRoutes.controllers.Application.insert().url;
      $http({
        method: "POST",
        url: url,
        data: $scope.user
      }).success(function(user) {
        pushFixedUser(user);
      });
    }
  });
