let template = require('./select2-chat-app.html');
module.exports = directive;

directive.$inject = [];

function directive() {
  return {
    restrict: 'E',
    template: template,
    controller: Controller,
    controllerAs: 'vm',
    scope: {
      declarado: '=?',
      apurado: '=?',
      usuarioAlteracao: '=?'
    }
  }
}

Controller.$inject = [];

function Controller() {
  let vm = this;

  vm.person = {};
  vm.people = [
    { name: 'Adam',      email: 'adam@email.com',      age: 10 },
    { name: 'Amalie',    email: 'amalie@email.com',    age: 12 },
    { name: 'Wladimir',  email: 'wladimir@email.com',  age: 30 },
    { name: 'Samantha',  email: 'samantha@email.com',  age: 31 },
    { name: 'Estefanía', email: 'estefanía@email.com', age: 16 },
    { name: 'Natasha',   email: 'natasha@email.com',   age: 54 },
    { name: 'Nicole',    email: 'nicole@email.com',    age: 43 },
    { name: 'Adrian',    email: 'adrian@email.com',    age: 21 }
  ];
}