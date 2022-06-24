package com.autobots.automanager.main.model;


import com.autobots.automanager.main.entity.User;

public class UserUpdater {
	private NullStringVerifier verifier = new NullStringVerifier();

	public void update(User user, User updated) {
		if (updated != null) {
			if (!verifier.verify(updated.get_name())) {
				user.set_name(updated.get_name());
			}
			if (!verifier.verify(updated.get_socialName())) {
				user.set_socialName(updated.get_socialName());
			}
		}
	}

}