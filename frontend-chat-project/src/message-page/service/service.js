const angular = require('angular');

module.exports = service;

service.$inject = ['$http'];

function service($http) {
  let path = 'http://localhost:8080/api/api-chat/user';

  return {
    getActiveChats: _getActiveChats
  }

  function _getActiveChats(loggedUser) {
    return $http({
      url: `${path}/active-chats/${loggedUser}`,
      method: 'GET'
    });
  }
}
