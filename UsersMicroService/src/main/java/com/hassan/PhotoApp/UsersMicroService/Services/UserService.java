package com.hassan.PhotoApp.UsersMicroService.Services;

import com.hassan.PhotoApp.UsersMicroService.Models.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDetails);
}
