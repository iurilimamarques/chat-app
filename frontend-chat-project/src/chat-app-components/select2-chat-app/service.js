module.exports = service;

service.$inject = ['$http'];

function service($http) {
  let path = 'http://localhost:8080/api/api-chat/user';

  return {
    searchUser: _searchUser
  }

  function _searchUser(keyWord, loggedUser) {
    return $http({
      url: path,
      method: 'GET',
      params: { keyWord, loggedUser }
    });
  }
}