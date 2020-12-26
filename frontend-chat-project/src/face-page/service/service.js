module.exports = service;

service.$inject = ['$http'];

function service($http) {
  return {
    userLogin: userLogin
  }

  function userLogin(userCredentials) {
    return $http({
      url: '',
      method: 'POST',
      data: {}
    });
  }
}