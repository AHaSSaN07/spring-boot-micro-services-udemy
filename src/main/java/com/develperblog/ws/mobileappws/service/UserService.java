package com.develperblog.ws.mobileappws.service;

import com.develperblog.ws.mobileappws.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User createUser(User user);
    User getSingleUser(String userID);

}
