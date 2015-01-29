package com.mooneyserver.playground.sockular;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/status", encoders = StatusUpdateMessageEncoder.class)
public class WebSocketEndpoint {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WebSocketEndpoint.class);

	private static final Set<Session> ACTIVE_SESSIONS = new HashSet<>();

	@OnOpen
	public void openSession(Session session) {
		ACTIVE_SESSIONS.add(session);
		LOGGER.info(
				"New session registered to '/status' endpoint. Now broadcasting to {} clients.",
				ACTIVE_SESSIONS.size());
	}

	@OnClose
	public void closeSession(Session session) {
		ACTIVE_SESSIONS.remove(session);
		LOGGER.info(
				"Client connected to '/status' endpoint has dropped. Now broadcasting to {} clients.",
				ACTIVE_SESSIONS.size());
	}

	public void broadcastMessage(@Observes StatusDetails statusUpdate) throws IOException, EncodeException {
		if (ACTIVE_SESSIONS.size() > 0) {
			LOGGER.info("Broadcasting message {} to all connected clients", statusUpdate);
			for (Session session : ACTIVE_SESSIONS) {
				session.getBasicRemote().sendObject(statusUpdate);
			}
		} else {
			LOGGER.info("Tried to broadcast status update {} but no clients are connected", statusUpdate);
		}		
	}
}