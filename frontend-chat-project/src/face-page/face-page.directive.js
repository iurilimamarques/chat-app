var controller = require('./face-page.controller');
var template = require('./face-page.html');

module.exports = directive;

directive.$inject = [];

function directive() {
    return {
        template: template,
        controller: controller,
        controllerAs: 'vm'
    }
}
