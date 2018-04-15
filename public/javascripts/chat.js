var app = angular.module('chatApp', ['ngMaterial']);

app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('purple')
        .accentPalette('green');
});

app.controller('chatController', function ($scope) {
    $scope.messages = [
        {
            'sender': 'USER',
            'text': 'HEYA!'
		},
        {
            'sender': 'BOT',
            'text': 'LTNC'
		},
        {
            'sender': 'USER',
            'text': 'LUNCH WEEKEND'
		},
        {
            'sender': 'BOT',
            'text': 'SURE!'
		}

	];

});