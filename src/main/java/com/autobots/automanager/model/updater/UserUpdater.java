package com.autobots.automanager.model.updater;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.model.NullStringVerifier;

public class UserUpdater {
  private NullStringVerifier verifier = new NullStringVerifier();

  public void update(User user, User updatedUser) {
    if (updatedUser != null) {
      if (!verifier.verify(updatedUser.get_name())) {
        user.set_name(updatedUser.get_name());
      }

      if (!verifier.verify(updatedUser.get_socialName())) {
        user.set_socialName(updatedUser.get_socialName());
      }

      if (!verifier.verify(updatedUser.get_address())) {
        user.set_address(updatedUser.get_address());
      }
    }
  }
}