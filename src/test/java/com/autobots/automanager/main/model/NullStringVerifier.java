package com.autobots.automanager.main.model;


public class NullStringVerifier {

	public boolean verify(String data) {
		boolean nullData = true;
		if (!(data == null)) {
			if (!data.isBlank()) {
				nullData = false;
			}
		}
		return nullData;
	}
}