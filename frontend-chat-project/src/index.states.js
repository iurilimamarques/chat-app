module.exports = states;

states.$inject = ['$stateProvider', '$urlRouterProvider'];

function states($stateProvider, $urlRouterProvider) {
  $urlRouterProvider.otherwise('/face-page');

  $stateProvider
    .state('face-page', {
      url: '/face-page',
      views: {
        content: {
          template: '<face-page/>'
        }
      }
    })
    .state('sign-up', {
      url: '/sign-up',
      views: {
        content: {
          template: '<sign-up/>'
        }
      }
    });
}
