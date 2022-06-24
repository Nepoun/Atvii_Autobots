package com.autobots.automanager.model.LinkAdd;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.UserController;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.model.LinkAdder;

@Component
public class UserLinkAdder implements LinkAdder<User>{

    @Override
    public void AddLink(List<User> _list) {
      for (User company : _list) {
        long id = company.get_id();
        Link selfLink = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder
                .methodOn(UserController.class)
                .getUser(id))
            .withSelfRel();
        company.add(selfLink);
      }
  
    }
    
  @Override
  public void AddLink(User user) {
    Link selfLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(UserController.class)
            .GetUsers())
        .withRel("users");
    user.add(selfLink);
  }


}