package com.hassan.PhotoApp.UsersMicroService.controllers;


import com.hassan.PhotoApp.UsersMicroService.Models.RequestUser;

import com.hassan.PhotoApp.UsersMicroService.Models.UserDTO;
import com.hassan.PhotoApp.UsersMicroService.Models.UserResponseModel;
import com.hassan.PhotoApp.UsersMicroService.Services.UserService;
import com.hassan.PhotoApp.UsersMicroService.Services.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {


    @Autowired
    private Environment environment;

    @Autowired
    private UserService userService;

    @GetMapping("/status/check")
    public String status() {
        return "working " + environment.getProperty("local.server.port") + "with token " + environment.getProperty("token.secret");
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity createUser(@RequestBody RequestUser user) {
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);//same field names as the parent class
        UserDTO addedUser = this.userService.createUser(userDTO);
        return new ResponseEntity(modelMapper.map(addedUser, UserResponseModel.class), HttpStatus.CREATED);
    }


    //@PreAuthorize("principal = #userId")
    @GetMapping(value = "/{userId}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getUsers(@PathVariable("userId") String userId){
        ModelMapper modelMapper = new ModelMapper();
        UserDTO user = userService.getUserByUserId(userId);
        return new ResponseEntity(modelMapper.map(user, UserResponseModel.class),HttpStatus.OK);
    }







}
