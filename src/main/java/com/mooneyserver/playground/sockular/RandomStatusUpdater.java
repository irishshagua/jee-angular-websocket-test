package com.mooneyserver.playground.sockular;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class RandomStatusUpdater {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RandomStatusUpdater.class);

	@Inject
	private Event<StatusDetails> statusUpdateEvent;
	private Random rand;

	@PostConstruct
	public void initRandom() {
		rand = new Random();
	}

	@Schedule(second = "*/15", minute = "*", hour = "*", persistent = false)
	public void performStatusUpdate() {
		LOGGER.info("A status update has been generated");
		statusUpdateEvent.fire(generateStatusDetails());
	}

	public StatusDetails generateStatusDetails() {
		StatusDetails statusDetails = new StatusDetails();
		statusDetails.setTitle(generateRandomTitle());
		statusDetails.setValue(generateRandomValue());

		return statusDetails;
	}

	public String generateRandomTitle() {
		return EStatusTitle.values()[rand.nextInt(EStatusTitle.values().length)]
				.name();
	}

	public Double generateRandomValue() {
		return rand.nextDouble();
	}
}