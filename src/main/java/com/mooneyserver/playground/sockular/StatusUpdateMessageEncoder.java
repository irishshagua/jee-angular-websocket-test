package com.mooneyserver.playground.sockular;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatusUpdateMessageEncoder implements Encoder.Text<StatusDetails> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StatusUpdateMessageEncoder.class);

	@Override
	public void init(EndpointConfig endpointConfig) {
		LOGGER.info("Status Details encoder has been initialised");
	}

	@Override
	public String encode(StatusDetails statusDetails) throws EncodeException {
		return Json.createObjectBuilder()
				.add("title", statusDetails.getTitle())
				.add("value", statusDetails.getValue()).build().toString();
	}

	@Override
	public void destroy() {
	}
}