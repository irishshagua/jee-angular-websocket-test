package com.mooneyserver.playground.sockular;

import java.util.Set;

import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.mooneyserver.playground.sockular.util.TestUtils;

@SuppressWarnings("unchecked")
public class WebSocketEndpointTest {

	@AfterMethod
	public void resetStaticActiveSessions() {
		Set<Session> activeSessions = TestUtils.getPrivateFieldFromClass(
				new WebSocketEndpoint(), "ACTIVE_SESSIONS", Set.class);
		activeSessions.clear();
	}
	
	@Test
	public void testNewSessionsAreAddedToSet() {
		WebSocketEndpoint wse = new WebSocketEndpoint();
		Session mockSession = Mockito.mock(Session.class);
		
		wse.openSession(mockSession);
		Set<Session> activeSessions = TestUtils.getPrivateFieldFromClass(wse, "ACTIVE_SESSIONS", Set.class);
		
		Assert.assertNotNull(activeSessions);
		Assert.assertEquals(activeSessions.size(), 1);
		Assert.assertTrue(activeSessions.contains(mockSession));
	}
	
	@Test
	public void testClosedSessionsAreRemovedFromSet() {
		WebSocketEndpoint wse = new WebSocketEndpoint();
		Session mockSession = Mockito.mock(Session.class);
		
		wse.openSession(mockSession);
		wse.closeSession(mockSession);
		
		Set<Session> activeSessions = TestUtils.getPrivateFieldFromClass(wse, "ACTIVE_SESSIONS", Set.class);
		
		Assert.assertNotNull(activeSessions);
		Assert.assertEquals(activeSessions.size(), 0);
		Assert.assertFalse(activeSessions.contains(mockSession));
	}
	
	@Test
	public void testBroadCastMessage() throws Exception {
		WebSocketEndpoint wse = new WebSocketEndpoint();
		Session mockSession = Mockito.mock(Session.class);
		Basic mockBasic = Mockito.mock(Basic.class);
		StatusDetails statusUpdate = Mockito.mock(StatusDetails.class);
		
		Mockito.when(mockSession.getBasicRemote()).thenReturn(mockBasic);
		
		wse.openSession(mockSession);
		wse.broadcastMessage(statusUpdate);
		
		Mockito.verify(mockSession).getBasicRemote();
		Mockito.verify(mockBasic).sendObject(statusUpdate);
	}
}