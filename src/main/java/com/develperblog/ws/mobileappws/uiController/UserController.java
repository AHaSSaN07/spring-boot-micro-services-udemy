package com.develperblog.ws.mobileappws.uiController;


import com.develperblog.ws.mobileappws.model.User;
import com.develperblog.ws.mobileappws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    private Map<String, User> userMap;

    @GetMapping
    public String getUsers(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit) {
        return "hello the page is " + page + " and the limit is " + limit;
    }

    @GetMapping(value = "/{userID}", produces = {MediaType.APPLICATION_XML_VALUE
            , MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getSingleUser(@PathVariable String userID) {
        User user = this.userService.getSingleUser(userID);
        if (user != null)
            return new ResponseEntity(user, HttpStatus.ACCEPTED);
        return new ResponseEntity("user not found", HttpStatus.NOT_FOUND);

    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User newUser = this.userService.createUser(user);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userID}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateUser(@PathVariable String userID, @Valid @RequestBody User updatedUser) {
        if (this.userMap.containsKey(userID)) {
            User user = this.userMap.get(userID);
            user.setEmail(updatedUser.getEmail());
            user.setLastName(updatedUser.getLastName());
            user.setFirstName(updatedUser.getFirstName());
            this.userMap.put(userID, user);
            return new ResponseEntity(user, HttpStatus.CREATED);
        }
        return new ResponseEntity("user not found", HttpStatus.NOT_FOUND);
    }

}
