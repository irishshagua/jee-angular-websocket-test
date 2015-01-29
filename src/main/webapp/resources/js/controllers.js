angular.module('StatusReaderApp.controllers', []).
	controller('StatusController', [function($scope, WebSocketStatusService) {
		$scope.status = WebSocketStatusService.statusUpdate;
	}]
);