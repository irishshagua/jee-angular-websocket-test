angular.module('StatusReaderApp.services', []).
	factory('WebSocketStatusService', function($log, $rootScope) {
		var StatusService = {};
		StatusService.statusUpdate = {};
		
		var webSocketUrl = (document.URL + 'status')
			.replace("https", "wss")
			.replace("http", "ws");
		$log.info('Attempting to connect to: ' + webSocketUrl);
		
		var ws = new WebSocket(webSocketUrl);
		ws.onopen = function() {
			$log.info('Successfully connected to status endpoint');
		}
		ws.onmessage = function(event) {
			$log.info('Status Update message received: ' + event.data);
			StatusService.statusUpdate = event.data;
			$rootScope.$apply();
		}		
		
		return StatusService;		
	}
);