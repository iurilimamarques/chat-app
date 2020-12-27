module.exports = service;

service.$inject = ['$http'];

function service($http) {
  let path = 'http://localhost:8080/api/api-chat/auth';

  return {
    userLogin: userLogin
  }

  function userLogin(userCredentials) {
    return $http({
      url: path,
      method: 'POST',
      data: userCredentials
    });
  }
}