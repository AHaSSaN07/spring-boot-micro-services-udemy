package com.develperblog.ws.mobileappws.service;

import com.develperblog.ws.mobileappws.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private Map<String,User> userMap;

    @Override
    public User createUser(User user) {
        String UID = UUID.randomUUID().toString();
        if(this.userMap == null) userMap = new HashMap<>();
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setId(UID);
        userMap.put(UID,newUser);
        return newUser;
    }

    @Override
    public User getSingleUser(String userID) {
        User user;
        if(this.userMap == null) return null;
        if(this.userMap.containsKey(userID)){
            user = userMap.get(userID);
            return user;
        }
        return null;
    }
}
