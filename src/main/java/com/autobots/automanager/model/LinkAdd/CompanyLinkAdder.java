package com.autobots.automanager.model.LinkAdd;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.CompanyController;
import com.autobots.automanager.entity.Company;
import com.autobots.automanager.model.LinkAdder;

@Component
public class CompanyLinkAdder implements LinkAdder<Company> {

    @Override
    public void AddLink(List<Company> list) {
        for (Company company : list) {
            long id = company.get_id();
            Link selfLink = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(CompanyController.class)
                            .getCompany(id))
                    .withSelfRel();
            company.add(selfLink);
        }

    }

    @Override
    public void AddLink(Company company) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CompanyController.class)
                        .getCompanies())
                .withRel("companies");
        company.add(linkProprio);
    }

}