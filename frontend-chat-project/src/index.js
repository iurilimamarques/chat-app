const angular = require('angular');
require('../node_modules/bootstrap/dist/css/bootstrap.css');

module.exports = angular
  .module('chatApp', [
    require('angular-ui-router'),
    require('./face-page')
  ])
  .config(require('./index.states')).name;
