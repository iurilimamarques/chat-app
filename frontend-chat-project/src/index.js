const angular = require('angular');
require('../node_modules/bootstrap/dist/css/bootstrap.css');
require('../content/styles/styles-chat-app.css');

module.exports = angular
  .module('chatApp', [
    require('angular-ui-router'),
    require('./face-page'),
    require('./sign-up'),
    require('./message-page')
  ])
  .config(require('./index.states')).name;
