package com.mooneyserver.playground.sockular;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusDetailsTest {

	@Test
	public void testToStringFormat() {
		StatusDetails sd = new StatusDetails();
		sd.setTitle("TestTitle");
		sd.setValue(1.123d);
		
		Assert.assertEquals(sd.toString(), "{title: TestTitle, value: 1.123000}");
	}
}