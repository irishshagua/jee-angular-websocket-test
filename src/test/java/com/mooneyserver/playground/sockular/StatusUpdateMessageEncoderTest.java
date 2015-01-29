package com.mooneyserver.playground.sockular;

import javax.websocket.EncodeException;

import org.testng.Assert;
import org.testng.annotations.Test;


public class StatusUpdateMessageEncoderTest {
	
	@Test
	public void testMessageEncoder() throws EncodeException {
		StatusDetails sd = new StatusDetails();
		sd.setTitle("SomeTitle");
		sd.setValue(1.123d);
		
		StatusUpdateMessageEncoder encoder = new StatusUpdateMessageEncoder();		
		Assert.assertEquals(encoder.encode(sd), "{\"title\":\"SomeTitle\",\"value\":1.123}");
	}

}
