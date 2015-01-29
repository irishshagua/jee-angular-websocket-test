package com.mooneyserver.playground.sockular;

import java.io.Serializable;

public class StatusDetails implements Serializable {

	private static final long serialVersionUID = -5670053427717444414L;

	public String title;
	public Double value;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("{title: %s, value: %f}", title, value);
	}
}