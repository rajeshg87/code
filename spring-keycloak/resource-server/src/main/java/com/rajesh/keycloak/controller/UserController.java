package com.rajesh.keycloak.controller;

import com.rajesh.keycloak.security.model.UserRest;
import com.rajesh.keycloak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    Environment environment;

    @Autowired
    UserService userService;

    @GetMapping(value = "/status/{name}")
    public String getStatus(@PathVariable String name){
        return String.format("%s Working on Port = %s", name, environment.getProperty("local.server.port"));
    }

    @PostAuthorize("returnObject.id == #jwt.subject")
    @GetMapping("/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return new UserRest("bc5d5957-d9b7-4d2f-bf39-07b3a630d409","Rajesh", "G");
    }

    @GetMapping
    public List<UserRest> getUsers(){
        return userService.getUsers();
    }
}
