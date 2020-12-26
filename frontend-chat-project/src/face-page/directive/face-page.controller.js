module.exports = Controller;

Controller.$inject = ['ServiceAuthentication'];

function Controller(ServiceAuthentication) {
  var vm = this;

  vm.userLogin = _userLogin;

  function _userLogin() {
    ServiceAuthentication.userLogin(vm.userCredentials).then(response => {
      console.log(response);
    });
  }
  
  (function() {
    vm.userCredentials = {};
  })();
}
