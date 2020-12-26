const angular = require('angular');

module.exports = angular
  .module('facePage', [])
  .service('ServiceAuthentication', require('./service/service'))
  .directive('facePage', require('./directive/face-page.directive')).name;