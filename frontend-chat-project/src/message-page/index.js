const angular = require('angular');

module.exports = angular
  .module('messagePage', [
    require('ui-select'),
    require('angular-sanitize'),
    require('../chat-app-components/select2-chat-app')
  ])
  .directive('messagePage', require('./directive/message-page.directive'))
  .service('MessageService', require('./service/service'))
  .config(require('./message-page.states')).name;
