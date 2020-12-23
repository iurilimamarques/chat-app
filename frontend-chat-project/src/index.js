const angular = require('angular');
require('../node_modules/bootstrap/dist/css/bootstrap.css');

module.exports = angular
  .module('chatApp', [
    require('angular-ui-router')
  ])
  .config(require('./index.states'))
  .directive('facePage', require('./face-page/face-page.directive')).name;
