package com.autobots.automanager.main.model;

import com.autobots.automanager.main.entity.Phone;

public class PhoneUpdater {
	private NullStringVerifier verifier = new NullStringVerifier();

	public void update(Phone phone, Phone updated) {
		if (updated != null) {
			if (!verifier.verify(updated.get_ddd())) {
				phone.set_ddd(updated.get_ddd());
			}
			if (!verifier.verify(updated.get_number())) {
				phone.set_number(updated.get_number());
			}
		}
	}

}