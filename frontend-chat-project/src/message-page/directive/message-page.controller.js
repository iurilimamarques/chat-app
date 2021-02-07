const angular = require('angular');

module.exports = Controller;

Controller.$inject = ['SockJS', 'Stomp', 'MessageService', 'UserFactory', '$rootScope', '$scope'];

function Controller(SockJS, Stomp, MessageService, UserFactory, $rootScope, $scope) {
  var vm = this;
  let stompClient = {};

  vm.selectChat = _selectChat;
  vm.isObjectEmpty = _isObjectEmpty;
  vm.sendMessage = _sendMessage;

  vm.activeChats = [];
  vm.selectedChat = {};
  vm.userSearch = {};
  vm.newMessages = {};

  function _removeClassSelection() {
    for(let i = 0; i <= vm.activeChats.length; i++) {
      let element = angular.element(document.querySelector(`#chat-${i}`));
      element.removeClass('active');
    }
  }

  function _selectChat(index, item) {
    _removeClassSelection();
    let elementSelected = angular.element(document.querySelector(`#chat-${index}`));
    elementSelected.addClass('active');
    vm.selectedChat = item;
  }

  function _onSelectUser(event, data) {
    vm.activeChats.push(data);
  }

  function _restoreState() {
    $rootScope.$broadcast('restoreState');
  }

  function _isObjectEmpty(object) {
    return angular.equals({}, object);
  }

  function _defineUserInformation() {
    vm.user = UserFactory.model;
  }

  function _getActiveChats() {
    MessageService.getActiveChats(UserFactory.model.id).then(response => {
      vm.activeChats = response.data;
    });
  }

  function _connectUser(email) {
    let ws = SockJS('http://localhost:8080/chat');
  
    stompClient = Stomp.over(ws);

    stompClient.connect({username: email}, function () {
      _messageSubscriber();
      _updateChecker();
    }, function (err) {
      console.log(err);
    });
  }

  function _isknownUser(item, fromUserId) {
    return item.id == fromUserId;
  }

  function _verifyActiveChats(fromUserId) {
    let knownUser = vm.activeChats.find(a => _isknownUser(a, fromUserId));
    console.log();
    if(!knownUser) _getActiveChats();
  }

  function _messageSubscriber() {
    stompClient.subscribe('/user/queue/messages', function (output) {
      let incomingMessage = angular.fromJson(output.body);
      _verifyActiveChats(incomingMessage.fromUser.id);
      $scope.$broadcast('newMessage', incomingMessage);
    });
  }

  function _updateChecker() {
    stompClient.subscribe('/topic/active', function () {
      console.log('updating....')
    });
  }

  function _sendMessage(payload) {
    stompClient.send("/app/chat", {'sender': payload.fromUser},
      angular.toJson(payload));            
  }

  $rootScope.$on('onSelectUser', _onSelectUser);

  (function _init() {
    if(!UserFactory.model.id) _restoreState();
    _defineUserInformation();
    _connectUser(UserFactory.model.email);
    _getActiveChats();
  })();
}
