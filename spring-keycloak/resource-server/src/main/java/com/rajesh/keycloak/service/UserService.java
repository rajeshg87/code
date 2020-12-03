package com.rajesh.keycloak.service;

import com.rajesh.keycloak.security.model.UserRest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<UserRest> getUsers(){
        List<UserRest> users=new ArrayList<>();
        users.add(new UserRest("0","Amutha", "Ganeshan"));
        users.add(new UserRest("1","Rajesh", "Ganeshan"));
        users.add(new UserRest("2", "Lenin", "Ganeshan"));
        users.add(new UserRest("3", "Aravind", "Kathiresan"));
        users.add(new UserRest("4", "Vasanth", "Kathiresan"));
        users.add(new UserRest("5", "Rajav", "Rajesh"));
        users.add(new UserRest("6", "Gowsalya", "Pethanaraj"));
        users.add(new UserRest("7", "Deshna", "Lenin"));
        return users;
    }
}
