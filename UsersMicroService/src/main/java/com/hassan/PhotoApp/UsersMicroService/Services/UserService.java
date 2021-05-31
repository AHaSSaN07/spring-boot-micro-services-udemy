package com.hassan.PhotoApp.UsersMicroService.Services;

import com.hassan.PhotoApp.UsersMicroService.Models.UserDTO;
import com.hassan.PhotoApp.UsersMicroService.Models.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO createUser(UserDTO userDetails);
    UserDTO getUserDetailsByUserName(String userName);
}
