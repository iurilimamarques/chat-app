const angular = require('angular');

module.exports = angular
  .module('signUp', [])
  .service('ServiceAuthenticationSignUp', require('./service/service'))
  .directive('signUp', require('./directive/sign-up.directive')).name;
  