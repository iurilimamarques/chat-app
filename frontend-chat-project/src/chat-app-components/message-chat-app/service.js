const angular = require('angular');

module.exports = service;

service.$inject = ['$http'];

function service($http) {
  let path = 'http://localhost:8080/api/api-chat/message';

  return {
    loadAllMessages: _loadAllMessages
  }

  function _loadAllMessages(recipient, loggedUser) {
    return $http({
      url: `${path}/load-all-messages/${loggedUser}/${recipient}`,
      method: 'GET'
    });
  }
}
