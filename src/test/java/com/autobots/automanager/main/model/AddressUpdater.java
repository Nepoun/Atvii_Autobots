package com.autobots.automanager.main.model;

import com.autobots.automanager.main.entity.Address;

public class AddressUpdater {
	private NullStringVerifier verifier = new NullStringVerifier();

	public void update(Address address, Address updated) {
		if (updated != null) {
			if (!verifier.verify(updated.get_city())) {
				address.set_city(updated.get_city());
			}
			if (!verifier.verify(updated.get_neighboorhood())) {
				address.set_neighboorhood(updated.get_neighboorhood());
			}
		}
	}

}