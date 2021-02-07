let angular = require('angular');

module.exports = Controller;

Controller.$inject = ['ServiceAuthentication', '$state', 'UserFactory', '$rootScope'];

function Controller(ServiceAuthentication, $state, UserFactory, $rootScope) {
  var vm = this;

  vm.userLogin = _userLogin;

  function _userLogin() {
    ServiceAuthentication.userLogin(vm.userCredentials).then(response => {
      console.log(response.data);
      if(response.data.id != undefined) {
        if(!UserFactory.model) {
          UserFactory.model = {};
        }
        UserFactory.model.id = response.data.id;
        UserFactory.model.name = response.data.name;
        UserFactory.model.email = response.data.email;

        $rootScope.$broadcast('saveState');
        $state.go('chat-app');
      }
    }, function(error) {
      vm.messageError = error.data.response;
      console.log(error);
    });
  }
  
  (function() {
    vm.userCredentials = {};
  })();
}
