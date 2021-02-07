let angular = require('angular');

module.exports = Controller;

Controller.$inject = ['ServiceAuthenticationSignUp', '$state'];

function Controller(ServiceAuthenticationSignUp, $state) {
  var vm = this;

  vm.saveUser = _saveUser;

  function _saveUser() {
    ServiceAuthenticationSignUp.saveUser(vm.userCredentials).then(response => {
      console.log(response.data);
      if(response.data.id != undefined) {
        $state.go('face-page');
      }
    }, function(error) {
      console.log(error)
      vm.messageError = error.data.response;
      console.log(error);
    });
  }
  
  (function() {
    vm.userCredentials = {};
  })();
}
