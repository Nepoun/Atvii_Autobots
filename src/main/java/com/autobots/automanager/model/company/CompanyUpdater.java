package com.autobots.automanager.model.company;

import com.autobots.automanager.entity.Company;
import com.autobots.automanager.model.NullStringVerifier;

public class CompanyUpdater {
  private NullStringVerifier verifier = new NullStringVerifier();

  public void atualizar(Company company, Company updater) {
    if (updater != null) {
      if (!verifier.verify(updater.get_socialReason())) {
        company.set_socialReason(updater.get_socialReason());
      }

      if (!verifier.verify(updater.get_fantasyName())) {
        company.set_fantasyName(updater.get_fantasyName());
      }

      if (!verifier.verify(updater.get_address())) {
        company.set_address(updater.get_address());
      }

      if (!verifier.verify(updater.get_registration())) {
        company.set_registration(updater.get_registration());
      }

    }
  }

}