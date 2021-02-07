const angular = require('angular');
const moment = require('moment')

module.exports = Controller;

Controller.$inject = ['UserFactory', 'MessageChatAppService', '$scope'];

function Controller(UserFactory, MessageChatAppService, $scope) {
  let vm = {};

  this.$onInit = function() {
    vm = this;

    vm.sendMessageCtrl = _sendMessage;
    vm.messagesList = [];
    vm.message = '';
    vm.loggedUser = UserFactory.model.id;
    vm.onKeyPress = _onKeyPress;

    $scope.$on('newMessage', _onNewMessage);
    $scope.$watch('vm.recipient.id', _onRecipientUserChanges);

    _loadAllMessages();
  };

  function _onRecipientUserChanges(newValue, oldValue) {
    if(newValue != oldValue) _loadAllMessages();
  }

  function _onKeyPress($event) {
    if(vm.message && $event.keyCode == 13) _sendMessage();
  }

  function _onNewMessage(event, data) {
    if(data.fromUser.id == vm.recipient.id) _pushNewMessage(data);
  }

  function _sendMessage() {
    let message = angular.copy(vm.message);
    vm.message = '';
    let payload = {
      fromUser: UserFactory.model, 
      message: message, 
      userDestination: vm.recipient, 
      createdAt: moment().format('YYYY-MM-DD HH:mm:ss')
    };
    vm.sendMessage(payload);
    _pushNewMessage(payload, true);
  }

  function _pushNewMessage(data, onSend = false) {
    vm.messagesList.push(data);
    if(!onSend) $scope.$apply(vm.messagesList);
  }

  function _loadAllMessages() {
    MessageChatAppService.loadAllMessages(vm.recipient.id, UserFactory.model.id).then(response => {
      vm.messagesList = response.data;
    });
  }
}
