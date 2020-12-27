module.exports = Controller;

Controller.$inject = ['ServiceAuthentication', '$state'];

function Controller(ServiceAuthentication, $state) {
  var vm = this;

  vm.userLogin = _userLogin;

  function _userLogin() {
    ServiceAuthentication.userLogin(vm.userCredentials).then(response => {
      console.log(response.data);
      if(response.data.id != undefined) {
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
