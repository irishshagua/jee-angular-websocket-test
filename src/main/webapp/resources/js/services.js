angular.module('StatusReaderApp.services', [], function ($provide) {
        $provide.factory('WebSocketStatusService', function ($log, $rootScope) {
            var StatusService = {};
            StatusService.openPipe = function() {
                $rootScope.statusUpdate = {}
                var webSocketUrl = (document.URL + 'status')
                    .replace("https", "wss")
                    .replace("http", "ws");
                $log.info('Attempting to connect to: ' + webSocketUrl);

                var ws = new WebSocket(webSocketUrl);
                ws.onopen = function () {
                    $log.info('Successfully connected to status endpoint');
                }
                ws.onmessage = function (event) {
                    $log.info('Status Update message received: ' + event.data);
                    $rootScope.statusUpdate = angular.fromJson(event.data);
                    $rootScope.$apply();
                }
            };
            return StatusService;
        });
    }
);