package com.mooneyserver.playground.sockular;

import javax.enterprise.event.Event;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RandomStatusUpdaterTest {

	@Mock
	private Event<StatusDetails> mockedStatusDetailsEvent;
	@InjectMocks
	private RandomStatusUpdater randomStatusUpdater;

	@BeforeClass
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testScheduledCallFiresEvent() {
		randomStatusUpdater.initRandom();
		randomStatusUpdater.performStatusUpdate();

		Mockito.verify(mockedStatusDetailsEvent).fire(
				Mockito.any(StatusDetails.class));
	}

	@Test
	public void testGenerateStatusDetails() {
		RandomStatusUpdater rsu = new RandomStatusUpdater();
		rsu.initRandom();
		StatusDetails sd = rsu.generateStatusDetails();

		Assert.assertNotNull(sd);
		Assert.assertNotNull(sd.getTitle());
		Assert.assertNotNull(sd.getValue());
	}

	@Test
	public void testTitleGeneratedFromEStatusTitle() {
		RandomStatusUpdater rsu = new RandomStatusUpdater();
		rsu.initRandom();
		String randomTitle = rsu.generateRandomTitle();

		Assert.assertNotNull(randomTitle);
		Assert.assertTrue(EStatusTitle.valueOf(randomTitle) instanceof EStatusTitle);
	}

	@Test
	public void testValueGenerated() {
		RandomStatusUpdater rsu = new RandomStatusUpdater();
		rsu.initRandom();
		Double randomValue = rsu.generateRandomValue();

		Assert.assertNotNull(randomValue);
	}
}