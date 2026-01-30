package edu.neumont.csc150.models;

public enum CarState {
	NORMAL("Normal"),
	FLAT_TIRE("Flat tire"),
	ENGINE_BLOWN("Engine blown");

	private final String FRIENDLY_NAME;

	CarState(String friendlyName) {
		this.FRIENDLY_NAME = friendlyName;
	}

	public String toString() {
		return this.FRIENDLY_NAME;
	}
}
