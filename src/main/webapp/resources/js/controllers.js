var appControllers = angular.module('StatusReaderApp.controllers', ['StatusReaderApp.services']);

appControllers.controller('StatusController', function ($scope, $rootScope, WebSocketStatusService) {
        WebSocketStatusService.openPipe();

        $rootScope.$watch('statusUpdate', function () {
            $scope.status = $rootScope.statusUpdate;
        });
    }
);