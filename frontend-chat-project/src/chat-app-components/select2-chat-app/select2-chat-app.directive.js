let template = require('./select2-chat-app.html');
let angular = require('angular');

module.exports = directive;

directive.$inject = [];

function directive() {
  return {
    restrict: 'E',
    template: template,
    controller: Controller,
    controllerAs: 'vm',
    scope: {},
    bindToController: {
      ngModel: '='
    }
  }
}

Controller.$inject = ['$rootScope', 'SelectService', 'UserFactory'];

function Controller($rootScope, SelectService, UserFactory) {
  let vm = null;
  this.$onInit = function() {
    vm = this;
    vm.searchUser = _searchUser;
    vm.onSelectUser = _onSelectUser;
  }

  function _onSelectUser($select) {
    let userSelected = angular.copy(vm.ngModel.selected);
    delete $select.selected;
    $rootScope.$emit('onSelectUser', userSelected);
  }

  function _searchUser($select) {
    let loggedUser = UserFactory.model.id;
    SelectService.searchUser($select.search, loggedUser).then(response => {
      vm.people = response.data;
    });
  }
}
